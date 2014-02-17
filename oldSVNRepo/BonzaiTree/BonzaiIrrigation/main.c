// ***************************************************************************
//  File Name    : adc.c
//  Version      : 1.0
//  Description  : Analog to Digital Converter
//  Author(s)    : RWB
//  Target(s)    : PICJazz 16F690 Board
//  Compiler     : HITECT PICC-Lite Version 9.60PL1
//  IDE          : Microchip MPLAB IDE v8.00
//  Programmer   : PICKit2
//  Last Updated : 28 Dec 2008
// ***************************************************************************
/*#include <pic.h>
#include <stdio.h>*/
#define _LEGACY_HEADERS /*Add to use old headers!*/
#include <pic.h>
#include <htc.h>        /* HiTech General Include File */
#include <uart.h>
#include <stdio.h>

/*   PIC Configuration Bit:
**   INTIO     - Using Internal RC No Clock
**   WDTDIS    - Wacthdog Timer Disable
**   PWRTEN    - Power Up Timer Enable
**   MCLREN    - Master Clear Enable
**   UNPROTECT - Code Un-Protect
**   UNPROTECT - Data EEPROM Read Un-Protect
**   BORDIS    - Borwn Out Detect Disable
**   IESODIS   - Internal External Switch Over Mode Disable
**   FCMDIS    - Monitor Clock Fail Safe Disable
*/
__CONFIG(INTIO & WDTDIS & PWRTEN & MCLREN & UNPROTECT & UNPROTECT \
  & BORDIS & IESODIS & FCMDIS);

//#define UART_SWITCH
#define TESTING


#ifdef UART_SWITCH
// Using Internal Clock of 125 Khz
#define FOSC 125000L
#else
// Using Internal Clock of 8 Mhz
#define FOSC 8000000L
#endif
#define FOSC_uart 8000000L
// Delay Function
#define _delay_us(x) { unsigned char us; \
	  	       us = (x)/(12000000/FOSC)|1; \
		       while(--us != 0) continue; }

#define threshold 512
#define MOTOR_ON 1000

#define CR 0x0D
/* Prototypes */
void _delay_ms(unsigned int ms);
void init(void);
void readADC(void);
char getc_spdswtch(void);
void putstring_spdswtch(char * string);

int counter = 0;
#ifdef TESTING
/*Countermax for testing*/
#define COUNTERMAX 2
#else
    #ifdef UART_SWITCH
    /*Countermax for 125KHz*/
    #define COUNTERMAX 1800
    #else
    /*Countermax for 8MHz*/
//    #define COUNTERMAX 57600
//    #define COUNTERMAX 6867
    #define COUNTERMAX 1145
    #endif
#endif

void interrupt isr(void){
	if(TMR1IE && TMR1IF){
            //if(RC6 == 1){
                if(counter == COUNTERMAX){
                    GIE=0;		// turn off all interrupts
                    //T1CON=0x30; /*Disable timer*/
                    readADC();                                              
                    counter = 0;                    
                    //T1CON=0x31; /*Disable timer*/
                    GIE=1;		// turn on all interrupts*/
                }
            //}
            TMR1IF=0;   /*Clear interrupt flag*/
            
            /*Create a analog signal on RC5 and RC6*/
            PORTC ^= (0x05 << 4);
            counter++;
            if(counter > COUNTERMAX){
                counter = 0;               
            }
	}

        if(RCIF){
            GIE=0;		// turn off all interrupts
            char retval = '0';
            char input[25] = {'0'};
            int input_counter = 0;
            while(retval != CR){
                if(input_counter == (25 - 4))
                    retval = CR;
                retval = getc_spdswtch();
                put_c(retval);
                RCREG = '0'; //Reset receive buffer
                input[input_counter] = retval;
                input[input_counter + 1] = '\n';
                input[input_counter + 2] = '\r';
                input[input_counter + 3] = '\0';
                input_counter++;                
            }
            put_c('\n');
            GIE=1;		// turn on all interrupts*/
        }  

}

   

void main(void)
{    
    init();
    uart_init(FOSC_uart);
    uart_interrupts();
  
    putstring_spdswtch("Welcome to the bonzai irrigation system!\n\r\0");    
    //readADC();
    for(;;){
       /*Read ADC every 0xFFFF times the timer overflows*/        
    }
}

void init(void){
/* A low frequency is chosen because of energy saving and slower clock ticks */
    //OSCCON= 0x40; /*125kHz*/
    OSCCON= 0x70; /*8MHz*/
    TRISA = 0b00010000;        // Input for RA4
    TRISC = 0x00;
    ANSEL = 0b00001000;  // Set PORT AN3 to analog input AN1 to AN7 digital I/O
    ANSELH = 0;          // Set PORT AN8 to AN11 as Digital I/O
    PORTC = 0xFF;
    _delay_ms(250);
    /* Init ADC */
    ADCON0 = 0b10001100; //Set adc to channel 3 according to page 115 on datasheet 16F690
    ADCON1=0b00100000;   // Select the FRC for 8 Mhz
    ADON=1;
    PORTC = 0x40;
    RA0 = 1;

    /* Init timer1 ticks with a frequency of 8000000/4/8 = 250KHz
a tick every 1 / 250000 = 0.000004 seconds
Interupt is generated at an timer overflow after 65535 ticks 0.26214s */
    T1CON=0x31;         // turn on timer 1 and enable prescaler 1:8
    TMR1IE=1;           // timer 1 is interrupt enabled
    PEIE=1;		// enable peripheral interrupts
    GIE=1;		// turn on interrupts

}
void _delay_ms(unsigned int ms)
{
  unsigned char i;
  do {
    i = 4;
    do {
      _delay_us(164);
    } while(--i);
  } while(--ms);
}

void readADC(void){

    unsigned int adcValue;
    //char adc_value_string[20];
    GODONE = 1;
    while(GODONE) continue;  // Wait conversion done
    adcValue=ADRESL;           // Get the 8 bit LSB result
    adcValue += (ADRESH << 8); // Get the 2 bit MSB result
    char output[10];
    if(adcValue < threshold){        
        putstring_spdswtch("NOK \0");
        sprintf(output,"M%d;", adcValue);
        putstring(output);
        RA0 = 0;
        _delay_ms(MOTOR_ON);
        RA0 = 1;
    }
    else{
        putstring_spdswtch("OK \0");
        sprintf(output,"M%d;", adcValue);
        putstring(output);
    }
    
    
	
}

char getc_spdswtch(void){
    #ifdef UART_SWITCH
    /*Switch to 8MHz for the UART, it won work on 125KHz*/
    OSCCON= 0x70; /*8MHz*/
    _delay_ms(50);
    #endif
    get_c();
    #ifdef UART_SWITCH
    _delay_ms(250);
    OSCCON= 0x40; /*125kHz*/
    #endif
}


void putstring_spdswtch(char * string){
    #ifdef UART_SWITCH
    /*Switch to 8MHz for the UART, it won work on 125KHz*/
    OSCCON= 0x70; /*8MHz*/
    _delay_ms(50);
    #endif
    putstring(string);
    #ifdef UART_SWITCH
    _delay_ms(250);
    OSCCON= 0x40; /*125kHz*/
    #endif
}