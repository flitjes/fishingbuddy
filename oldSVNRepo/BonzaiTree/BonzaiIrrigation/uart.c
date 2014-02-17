#include <uart.h>
#include <htc.h>
#include <pic.h>

#define BAUD 4800L 

void uart_init(long FOSC){
    int BRG_VALUE = ((FOSC / (BAUD * 4L)) - 1L);

    /* Setup UART */
    BAUDCTL = 0x08; /* use 16 bit BRG */
    TXSTA   = 0x04; /* TX enabled, select high speed BRG */
    RCSTA   = 0x00; /* RX enabled */
    SPBRGH  = BRG_VALUE >> 8;
    SPBRG   = BRG_VALUE & 0xFF;
    TRISB  |= 0x80; /* Set RX as inputs */
    ANS11   = 0;    /* Make RB5 a digital I/O */
    SPEN    = 1;    /* enable UART function block */
    TXEN    = 1;
    CREN    = 1;
    PORTB = 0x00;
}

void uart_interrupts(void){
    RCIE = 1;
    PEIE = 1;
}
void putstring(char * string){
    int chr = 0;
    while(string[chr] != '\0')
    {
        put_c(string[chr]);
        chr++;
    }
}

void put_c (unsigned char c)
{
    while(!TXIF);
    TXREG = c;
}

unsigned char get_c(void)
{
    unsigned char c;

    while (!RCIF)
    {
        if(OERR)
        {
            c = RCREG;  /* read twice to clear FIFO */
            c = RCREG;
            CREN = 0;   /* clear overrun error */
            CREN = 1;
        }
    }

    if (FERR)
    {
        /* maybe set a flag for a character with framming error */
    }
    c = RCREG;          /* get a character from UART */
    
    return c;
}