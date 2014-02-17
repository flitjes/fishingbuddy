/* 
 * File:   uart.h
 * Author: flitjesdev
 *
 * Created on July 7, 2013, 4:22 PM
 */

#ifndef UART_H
#define	UART_H

#ifdef	__cplusplus
extern "C" {
#endif

void uart_init(long FOSC);
void uart_interrupts(void);
void put_c (unsigned char c);
void putstring(char * string);
unsigned char get_c(void);


#ifdef	__cplusplus
}
#endif

#endif	/* UART_H */

