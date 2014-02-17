opt subtitle "HI-TECH Software Omniscient Code Generator (Lite mode) build 5239"

opt pagewidth 120

	opt lm

	processor	16F690
clrc	macro
	bcf	3,0
	endm
clrz	macro
	bcf	3,2
	endm
setc	macro
	bsf	3,0
	endm
setz	macro
	bsf	3,2
	endm
skipc	macro
	btfss	3,0
	endm
skipz	macro
	btfss	3,2
	endm
skipnc	macro
	btfsc	3,0
	endm
skipnz	macro
	btfsc	3,2
	endm
indf	equ	0
indf0	equ	0
pc	equ	2
pcl	equ	2
status	equ	3
fsr	equ	4
fsr0	equ	4
c	equ	1
z	equ	0
pclath	equ	10
# 25 "X:\PIC Projects\BonzaiIrrigation\main.c"
	psect config,class=CONFIG,delta=2 ;#
# 25 "X:\PIC Projects\BonzaiIrrigation\main.c"
	dw 0x3FFC & 0x3FF7 & 0x3FEF & 0x3FFF & 0x3FFF & 0x3FFF & 0x3CFF & 0x3BFF & 0x37FF ;#
;COMMON:	_main->__delay_ms
	FNCALL	_main,__delay_ms
	FNROOT	_main
	global	_ADCON0
psect	text9,local,class=CODE,delta=2
global __ptext9
__ptext9:
_ADCON0  equ     31
	global	_ADRESH
_ADRESH  equ     30
	global	_CCP1CON
_CCP1CON  equ     23
	global	_CCPR1H
_CCPR1H  equ     22
	global	_CCPR1L
_CCPR1L  equ     21
	global	_ECCPAS
_ECCPAS  equ     29
	global	_FSR
_FSR  equ     4
	global	_INDF
_INDF  equ     0
	global	_INTCON
_INTCON  equ     11
	global	_PCL
_PCL  equ     2
	global	_PCLATH
_PCLATH  equ     10
	global	_PIR1
_PIR1  equ     12
	global	_PIR2
_PIR2  equ     13
	global	_PORTA
_PORTA  equ     5
	global	_PORTB
_PORTB  equ     6
	global	_PORTC
_PORTC  equ     7
	global	_PWM1CON
_PWM1CON  equ     28
	global	_RCREG
_RCREG  equ     26
	global	_RCSTA
_RCSTA  equ     24
	global	_SSPBUF
_SSPBUF  equ     19
	global	_SSPCON
_SSPCON  equ     20
	global	_STATUS
_STATUS  equ     3
	global	_T1CON
_T1CON  equ     16
	global	_T2CON
_T2CON  equ     18
	global	_TMR0
_TMR0  equ     1
	global	_TMR1H
_TMR1H  equ     15
	global	_TMR1L
_TMR1L  equ     14
	global	_TMR2
_TMR2  equ     17
	global	_TXREG
_TXREG  equ     25
	global	_ADDEN
_ADDEN  equ     195
	global	_ADFM
_ADFM  equ     255
	global	_ADIF
_ADIF  equ     102
	global	_ADON
_ADON  equ     248
	global	_C1IF
_C1IF  equ     109
	global	_C2IF
_C2IF  equ     110
	global	_CARRY
_CARRY  equ     24
	global	_CCP1IF
_CCP1IF  equ     98
	global	_CCP1M0
_CCP1M0  equ     184
	global	_CCP1M1
_CCP1M1  equ     185
	global	_CCP1M2
_CCP1M2  equ     186
	global	_CCP1M3
_CCP1M3  equ     187
	global	_CHS0
_CHS0  equ     250
	global	_CHS1
_CHS1  equ     251
	global	_CHS2
_CHS2  equ     252
	global	_CHS3
_CHS3  equ     253
	global	_CKP
_CKP  equ     164
	global	_CREN
_CREN  equ     196
	global	_DC
_DC  equ     25
	global	_DC1B0
_DC1B0  equ     188
	global	_DC1B1
_DC1B1  equ     189
	global	_ECCPAS0
_ECCPAS0  equ     236
	global	_ECCPAS1
_ECCPAS1  equ     237
	global	_ECCPAS2
_ECCPAS2  equ     238
	global	_ECCPASE
_ECCPASE  equ     239
	global	_EEIF
_EEIF  equ     108
	global	_FERR
_FERR  equ     194
	global	_GIE
_GIE  equ     95
	global	_GODONE
_GODONE  equ     249
	global	_INTE
_INTE  equ     92
	global	_INTF
_INTF  equ     89
	global	_IRP
_IRP  equ     31
	global	_OERR
_OERR  equ     193
	global	_OSFIF
_OSFIF  equ     111
	global	_P1M0
_P1M0  equ     190
	global	_P1M1
_P1M1  equ     191
	global	_PD
_PD  equ     27
	global	_PDC0
_PDC0  equ     224
	global	_PDC1
_PDC1  equ     225
	global	_PDC2
_PDC2  equ     226
	global	_PDC3
_PDC3  equ     227
	global	_PDC4
_PDC4  equ     228
	global	_PDC5
_PDC5  equ     229
	global	_PDC6
_PDC6  equ     230
	global	_PEIE
_PEIE  equ     94
	global	_PRSEN
_PRSEN  equ     231
	global	_PSSAC0
_PSSAC0  equ     234
	global	_PSSAC1
_PSSAC1  equ     235
	global	_PSSBD0
_PSSBD0  equ     232
	global	_PSSBD1
_PSSBD1  equ     233
	global	_RA0
_RA0  equ     40
	global	_RA1
_RA1  equ     41
	global	_RA2
_RA2  equ     42
	global	_RA3
_RA3  equ     43
	global	_RA4
_RA4  equ     44
	global	_RA5
_RA5  equ     45
	global	_RABIE
_RABIE  equ     91
	global	_RABIF
_RABIF  equ     88
	global	_RB4
_RB4  equ     52
	global	_RB5
_RB5  equ     53
	global	_RB6
_RB6  equ     54
	global	_RB7
_RB7  equ     55
	global	_RBIE
_RBIE  equ     91
	global	_RBIF
_RBIF  equ     88
	global	_RC0
_RC0  equ     56
	global	_RC1
_RC1  equ     57
	global	_RC2
_RC2  equ     58
	global	_RC3
_RC3  equ     59
	global	_RC4
_RC4  equ     60
	global	_RC5
_RC5  equ     61
	global	_RC6
_RC6  equ     62
	global	_RC7
_RC7  equ     63
	global	_RCIF
_RCIF  equ     101
	global	_RP0
_RP0  equ     29
	global	_RP1
_RP1  equ     30
	global	_RX9
_RX9  equ     198
	global	_RX9D
_RX9D  equ     192
	global	_SPEN
_SPEN  equ     199
	global	_SREN
_SREN  equ     197
	global	_SSPEN
_SSPEN  equ     165
	global	_SSPIF
_SSPIF  equ     99
	global	_SSPM0
_SSPM0  equ     160
	global	_SSPM1
_SSPM1  equ     161
	global	_SSPM2
_SSPM2  equ     162
	global	_SSPM3
_SSPM3  equ     163
	global	_SSPOV
_SSPOV  equ     166
	global	_T0IE
_T0IE  equ     93
	global	_T0IF
_T0IF  equ     90
	global	_T1CKPS0
_T1CKPS0  equ     132
	global	_T1CKPS1
_T1CKPS1  equ     133
	global	_T1GINV
_T1GINV  equ     135
	global	_T1OSCEN
_T1OSCEN  equ     131
	global	_T1SYNC
_T1SYNC  equ     130
	global	_T2CKPS0
_T2CKPS0  equ     144
	global	_T2CKPS1
_T2CKPS1  equ     145
	global	_TMR1CS
_TMR1CS  equ     129
	global	_TMR1GE
_TMR1GE  equ     134
	global	_TMR1IF
_TMR1IF  equ     96
	global	_TMR1ON
_TMR1ON  equ     128
	global	_TMR2IF
_TMR2IF  equ     97
	global	_TMR2ON
_TMR2ON  equ     146
	global	_TO
_TO  equ     28
	global	_TOUTPS0
_TOUTPS0  equ     147
	global	_TOUTPS1
_TOUTPS1  equ     148
	global	_TOUTPS2
_TOUTPS2  equ     149
	global	_TOUTPS3
_TOUTPS3  equ     150
	global	_TXIF
_TXIF  equ     100
	global	_VCFG
_VCFG  equ     254
	global	_WCOL
_WCOL  equ     167
	global	_ZERO
_ZERO  equ     26
	global	_ADCON1
_ADCON1  equ     159
	global	_ADRESL
_ADRESL  equ     158
	global	_BAUDCTL
_BAUDCTL  equ     155
	global	_IOCA
_IOCA  equ     150
	global	_OPTION
_OPTION  equ     129
	global	_OSCCON
_OSCCON  equ     143
	global	_OSCTUNE
_OSCTUNE  equ     144
	global	_PCON
_PCON  equ     142
	global	_PIE1
_PIE1  equ     140
	global	_PIE2
_PIE2  equ     141
	global	_PR2
_PR2  equ     146
	global	_SPBRG
_SPBRG  equ     153
	global	_SPBRGH
_SPBRGH  equ     154
	global	_SSPADD
_SSPADD  equ     147
	global	_SSPMSK
_SSPMSK  equ     147
	global	_SSPSTAT
_SSPSTAT  equ     148
	global	_TRISA
_TRISA  equ     133
	global	_TRISB
_TRISB  equ     134
	global	_TRISC
_TRISC  equ     135
	global	_TXSTA
_TXSTA  equ     152
	global	_WDTCON
_WDTCON  equ     151
	global	_WPUA
_WPUA  equ     149
	global	_ABDEN
_ABDEN  equ     1240
	global	_ABDOVF
_ABDOVF  equ     1247
	global	_ADCS0
_ADCS0  equ     1276
	global	_ADCS1
_ADCS1  equ     1277
	global	_ADCS2
_ADCS2  equ     1278
	global	_ADIE
_ADIE  equ     1126
	global	_BF
_BF  equ     1184
	global	_BOR
_BOR  equ     1136
	global	_BRG0
_BRG0  equ     1224
	global	_BRG1
_BRG1  equ     1225
	global	_BRG10
_BRG10  equ     1234
	global	_BRG11
_BRG11  equ     1235
	global	_BRG12
_BRG12  equ     1236
	global	_BRG13
_BRG13  equ     1237
	global	_BRG14
_BRG14  equ     1238
	global	_BRG15
_BRG15  equ     1239
	global	_BRG16
_BRG16  equ     1243
	global	_BRG2
_BRG2  equ     1226
	global	_BRG3
_BRG3  equ     1227
	global	_BRG4
_BRG4  equ     1228
	global	_BRG5
_BRG5  equ     1229
	global	_BRG6
_BRG6  equ     1230
	global	_BRG7
_BRG7  equ     1231
	global	_BRG8
_BRG8  equ     1232
	global	_BRG9
_BRG9  equ     1233
	global	_BRGH
_BRGH  equ     1218
	global	_C1IE
_C1IE  equ     1133
	global	_C2IE
_C2IE  equ     1134
	global	_CCP1IE
_CCP1IE  equ     1122
	global	_CKE
_CKE  equ     1190
	global	_CSRC
_CSRC  equ     1223
	global	_DA
_DA  equ     1189
	global	_EEIE
_EEIE  equ     1132
	global	_HTS
_HTS  equ     1146
	global	_INTEDG
_INTEDG  equ     1038
	global	_IOCA0
_IOCA0  equ     1200
	global	_IOCA1
_IOCA1  equ     1201
	global	_IOCA2
_IOCA2  equ     1202
	global	_IOCA3
_IOCA3  equ     1203
	global	_IOCA4
_IOCA4  equ     1204
	global	_IOCA5
_IOCA5  equ     1205
	global	_IRCF0
_IRCF0  equ     1148
	global	_IRCF1
_IRCF1  equ     1149
	global	_IRCF2
_IRCF2  equ     1150
	global	_LTS
_LTS  equ     1145
	global	_OSFIE
_OSFIE  equ     1135
	global	_OSTS
_OSTS  equ     1147
	global	_POR
_POR  equ     1137
	global	_PS0
_PS0  equ     1032
	global	_PS1
_PS1  equ     1033
	global	_PS2
_PS2  equ     1034
	global	_PSA
_PSA  equ     1035
	global	_RABPU
_RABPU  equ     1039
	global	_RBPU
_RBPU  equ     1039
	global	_RCIDL
_RCIDL  equ     1246
	global	_RCIE
_RCIE  equ     1125
	global	_RW
_RW  equ     1186
	global	_SBOREN
_SBOREN  equ     1140
	global	_SCKP
_SCKP  equ     1244
	global	_SCS
_SCS  equ     1144
	global	_SENDB
_SENDB  equ     1219
	global	_SMP
_SMP  equ     1191
	global	_SSPIE
_SSPIE  equ     1123
	global	_START
_START  equ     1187
	global	_STOP
_STOP  equ     1188
	global	_SWDTEN
_SWDTEN  equ     1208
	global	_SYNC
_SYNC  equ     1220
	global	_T0CS
_T0CS  equ     1037
	global	_T0SE
_T0SE  equ     1036
	global	_TMR1IE
_TMR1IE  equ     1120
	global	_TMR2IE
_TMR2IE  equ     1121
	global	_TRISA0
_TRISA0  equ     1064
	global	_TRISA1
_TRISA1  equ     1065
	global	_TRISA2
_TRISA2  equ     1066
	global	_TRISA3
_TRISA3  equ     1067
	global	_TRISA4
_TRISA4  equ     1068
	global	_TRISA5
_TRISA5  equ     1069
	global	_TRISB4
_TRISB4  equ     1076
	global	_TRISB5
_TRISB5  equ     1077
	global	_TRISB6
_TRISB6  equ     1078
	global	_TRISB7
_TRISB7  equ     1079
	global	_TRISC0
_TRISC0  equ     1080
	global	_TRISC1
_TRISC1  equ     1081
	global	_TRISC2
_TRISC2  equ     1082
	global	_TRISC3
_TRISC3  equ     1083
	global	_TRISC4
_TRISC4  equ     1084
	global	_TRISC5
_TRISC5  equ     1085
	global	_TRISC6
_TRISC6  equ     1086
	global	_TRISC7
_TRISC7  equ     1087
	global	_TRMT
_TRMT  equ     1217
	global	_TUN0
_TUN0  equ     1152
	global	_TUN1
_TUN1  equ     1153
	global	_TUN2
_TUN2  equ     1154
	global	_TUN3
_TUN3  equ     1155
	global	_TUN4
_TUN4  equ     1156
	global	_TX9
_TX9  equ     1222
	global	_TX9D
_TX9D  equ     1216
	global	_TXEN
_TXEN  equ     1221
	global	_TXIE
_TXIE  equ     1124
	global	_UA
_UA  equ     1185
	global	_ULPWUE
_ULPWUE  equ     1141
	global	_WDTPS0
_WDTPS0  equ     1209
	global	_WDTPS1
_WDTPS1  equ     1210
	global	_WDTPS2
_WDTPS2  equ     1211
	global	_WDTPS3
_WDTPS3  equ     1212
	global	_WPUA0
_WPUA0  equ     1192
	global	_WPUA1
_WPUA1  equ     1193
	global	_WPUA2
_WPUA2  equ     1194
	global	_WPUA4
_WPUA4  equ     1196
	global	_WPUA5
_WPUA5  equ     1197
	global	_WUE
_WUE  equ     1241
	global	_ANSEL
_ANSEL  equ     286
	global	_ANSELH
_ANSELH  equ     287
	global	_CM1CON0
_CM1CON0  equ     281
	global	_CM2CON0
_CM2CON0  equ     282
	global	_CM2CON1
_CM2CON1  equ     283
	global	_EEADR
_EEADR  equ     269
	global	_EEADRH
_EEADRH  equ     271
	global	_EEADRL
_EEADRL  equ     269
	global	_EEDAT
_EEDAT  equ     268
	global	_EEDATA
_EEDATA  equ     268
	global	_EEDATH
_EEDATH  equ     270
	global	_EEDATL
_EEDATL  equ     268
	global	_IOCB
_IOCB  equ     278
	global	_VRCON
_VRCON  equ     280
	global	_WPUB
_WPUB  equ     277
	global	_ANS0
_ANS0  equ     2288
	global	_ANS1
_ANS1  equ     2289
	global	_ANS10
_ANS10  equ     2298
	global	_ANS11
_ANS11  equ     2299
	global	_ANS2
_ANS2  equ     2290
	global	_ANS3
_ANS3  equ     2291
	global	_ANS4
_ANS4  equ     2292
	global	_ANS5
_ANS5  equ     2293
	global	_ANS6
_ANS6  equ     2294
	global	_ANS7
_ANS7  equ     2295
	global	_ANS8
_ANS8  equ     2296
	global	_ANS9
_ANS9  equ     2297
	global	_C1CH0
_C1CH0  equ     2248
	global	_C1CH1
_C1CH1  equ     2249
	global	_C1OE
_C1OE  equ     2253
	global	_C1ON
_C1ON  equ     2255
	global	_C1OUT
_C1OUT  equ     2254
	global	_C1POL
_C1POL  equ     2252
	global	_C1R
_C1R  equ     2250
	global	_C1VREN
_C1VREN  equ     2247
	global	_C2CH0
_C2CH0  equ     2256
	global	_C2CH1
_C2CH1  equ     2257
	global	_C2OE
_C2OE  equ     2261
	global	_C2ON
_C2ON  equ     2263
	global	_C2OUT
_C2OUT  equ     2262
	global	_C2POL
_C2POL  equ     2260
	global	_C2R
_C2R  equ     2258
	global	_C2SYNC
_C2SYNC  equ     2264
	global	_C2VREN
_C2VREN  equ     2246
	global	_IOCB4
_IOCB4  equ     2228
	global	_IOCB5
_IOCB5  equ     2229
	global	_IOCB6
_IOCB6  equ     2230
	global	_IOCB7
_IOCB7  equ     2231
	global	_MC1OUT
_MC1OUT  equ     2271
	global	_MC2OUT
_MC2OUT  equ     2270
	global	_T1GSS
_T1GSS  equ     2265
	global	_VP6EN
_VP6EN  equ     2244
	global	_VR0
_VR0  equ     2240
	global	_VR1
_VR1  equ     2241
	global	_VR2
_VR2  equ     2242
	global	_VR3
_VR3  equ     2243
	global	_VRR
_VRR  equ     2245
	global	_WPUB4
_WPUB4  equ     2220
	global	_WPUB5
_WPUB5  equ     2221
	global	_WPUB6
_WPUB6  equ     2222
	global	_WPUB7
_WPUB7  equ     2223
	global	_EECON1
_EECON1  equ     396
	global	_EECON2
_EECON2  equ     397
	global	_PSTRCON
_PSTRCON  equ     413
	global	_SRCON
_SRCON  equ     414
	global	_C1SEN
_C1SEN  equ     3317
	global	_C2REN
_C2REN  equ     3316
	global	_EEPGD
_EEPGD  equ     3175
	global	_PULSR
_PULSR  equ     3314
	global	_PULSS
_PULSS  equ     3315
	global	_RD
_RD  equ     3168
	global	_SR0
_SR0  equ     3318
	global	_SR1
_SR1  equ     3319
	global	_STRA
_STRA  equ     3304
	global	_STRB
_STRB  equ     3305
	global	_STRC
_STRC  equ     3306
	global	_STRD
_STRD  equ     3307
	global	_STRSYNC
_STRSYNC  equ     3308
	global	_WR
_WR  equ     3169
	global	_WREN
_WREN  equ     3170
	global	_WRERR
_WRERR  equ     3171
	file	"BonzaiIrrigation.as"
	line	#
psect cinit,class=CODE,delta=2
global start_initialization
start_initialization:

psect cinit,class=CODE,delta=2
global end_of_initialization

;End of C runtime variable initationation code

end_of_initialization:
clrf status
ljmp _main	;jump to C main() function
psect	cstackCOMMON,class=COMMON,space=1
global __pcstackCOMMON
__pcstackCOMMON:
	global	??__delay_ms
??__delay_ms: ;@ 0x0
	global	__delay_ms@us
__delay_ms@us:	; 1 bytes @ 0x0
	ds	1
	global	__delay_ms@i
__delay_ms@i:	; 1 bytes @ 0x1
	ds	1
	global	?__delay_ms
?__delay_ms: ;@ 0x2
	global	__delay_ms@ms
__delay_ms@ms:	; 2 bytes @ 0x2
	ds	2
	global	??_main
??_main: ;@ 0x4
	ds	3
	global	main@adcValue
main@adcValue:	; 2 bytes @ 0x7
	ds	2
	global	?_main
?_main: ;@ 0x9
;Data sizes: Strings 0, constant 0, data 0, bss 0, persistent 0 stack 0
;Auto spaces:   Size  Autos    Used
; COMMON          14      9       9
; BANK0           80      0       0
; BANK1           80      0       0
; BANK2           80      0       0


;Pointer list with targets:



;Main: autosize = 0, tempsize = 3, incstack = 0, save=0


;Call graph:                      Base Space Used Autos Args Refs Density
;_main                                                5    0   40   0.00
;                                    4 COMMO    5
;          __delay_ms
;  __delay_ms                                         2    2   30   0.00
;                                    0 COMMO    4
; Estimated maximum call depth 1
; Address spaces:

;Name               Size   Autos  Total    Cost      Usage
;BITCOMMON            E      0       0       0        0.0%
;CODE                 0      0       0       0        0.0%
;NULL                 0      0       0       0        0.0%
;COMMON               E      9       9       1       64.3%
;BITSFR0              0      0       0       1        0.0%
;SFR0                 0      0       0       1        0.0%
;SFR1                 0      0       0       2        0.0%
;BITSFR1              0      0       0       2        0.0%
;ABS                  0      0       3       2        0.0%
;STACK                0      0       0       3        0.0%
;BITBANK0            50      0       0       4        0.0%
;BITSFR3              0      0       0       4        0.0%
;SFR3                 0      0       0       4        0.0%
;BANK0               50      0       0       5        0.0%
;SFR2                 0      0       0       5        0.0%
;BITSFR2              0      0       0       5        0.0%
;BITBANK1            50      0       0       6        0.0%
;BANK1               50      0       0       7        0.0%
;BITBANK2            50      0       0       8        0.0%
;BANK2               50      0       0       9        0.0%
;DATA                 0      0       3      10        0.0%
;EEDATA             100      0       0    1000        0.0%

	global	_main
psect	maintext,local,class=CODE,delta=2
global __pmaintext
__pmaintext:

; *************** function _main *****************
; Defined at:
;		line 45 in file "X:\PIC Projects\BonzaiIrrigation\main.c"
; Parameters:    Size  Location     Type
;		None
; Auto vars:     Size  Location     Type
;  adcValue        2    7[COMMON] unsigned int 
; Return value:  Size  Location     Type
;		None               void
; Registers used:
;		wreg, status,2, status,0, pclath, cstack
; Tracked objects:
;		On entry : 17F/0
;		On exit  : 0/0
;		Unchanged: 0/0
; Data sizes:     COMMON   BANK0   BANK1   BANK2
;      Locals:         5       0       0       0
;      Temp:     3
;      Total:    5
; This function calls:
;		__delay_ms
; This function is called by:
;		Startup code after reset
; This function uses a non-reentrant model
; 
psect	maintext
	file	"X:\PIC Projects\BonzaiIrrigation\main.c"
	line	45
	global	__size_of_main
	__size_of_main	equ	__end_of_main-_main
;main.c: 44: void main(void)
;main.c: 45: {
	
_main:	
	opt stack 8
; Regs used in _main: [wreg+status,2+status,0+pclath+cstack]
	line	47
	
l30000446:	
;main.c: 46: unsigned int adcValue;
;main.c: 47: OSCCON=0x70;
	movlw	(070h)
	bsf	status, 5	;RP0=1, select bank1
	bcf	status, 6	;RP1=0, select bank1
	movwf	(143)^080h	;volatile
	line	48
;main.c: 48: TRISA = 0b00010000;
	movlw	(010h)
	movwf	(133)^080h	;volatile
	
l30000447:	
	line	49
;main.c: 49: TRISC = 0x00;
	clrc
	movlw	0
	btfsc	status,0
	movlw	1
	movwf	(135)^080h	;volatile
	
l30000448:	
	line	50
;main.c: 50: ANSEL = 0b00001000;
	movlw	(08h)
	bcf	status, 5	;RP0=0, select bank2
	bsf	status, 6	;RP1=1, select bank2
	movwf	(286)^0100h
	line	51
;main.c: 51: ANSELH = 0;
	clrc
	movlw	0
	btfsc	status,0
	movlw	1
	movwf	(287)^0100h
	
l30000449:	
	line	53
;main.c: 53: ADCON0 = 0b10001100;
	movlw	(08Ch)
	bcf	status, 5	;RP0=0, select bank0
	bcf	status, 6	;RP1=0, select bank0
	movwf	(31)	;volatile
	
l30000450:	
	line	54
;main.c: 54: ADCON1=0b00100000;
	movlw	(020h)
	bsf	status, 5	;RP0=1, select bank1
	bcf	status, 6	;RP1=0, select bank1
	movwf	(159)^080h
	
l30000451:	
	line	55
;main.c: 55: ADON=1;
	bcf	status, 5	;RP0=0, select bank0
	bcf	status, 6	;RP1=0, select bank0
	bsf	(248/8),(248)&7
	line	56
;main.c: 56: PORTC = 0x00;
	clrc
	movlw	0
	btfsc	status,0
	movlw	1
	movwf	(7)	;volatile
	
l30000452:	
	line	58
;main.c: 58: GODONE=1;
	bsf	(249/8),(249)&7
	
l16:	
	line	59
	btfsc	(249/8),(249)&7
	goto	u41
	goto	u40
u41:
	goto	l16
u40:
	
l30000453:	
	line	60
;main.c: 60: adcValue=ADRESL;
	bsf	status, 5	;RP0=1, select bank1
	bcf	status, 6	;RP1=0, select bank1
	movf	(158)^080h,w	;volatile
	movwf	(??_main+0+0)
	clrf	(??_main+0+0+1)
	movf	0+(??_main+0+0),w
	movwf	(main@adcValue)
	movf	1+(??_main+0+0),w
	movwf	(main@adcValue+1)
	line	61
;main.c: 61: adcValue += (ADRESH << 8);
	bcf	status, 5	;RP0=0, select bank0
	bcf	status, 6	;RP1=0, select bank0
	movf	(30),w	;volatile
	movwf	(??_main+0+0)
	clrf	(??_main+0+0+1)
	movlw	08h
	movwf	(??_main+2+0)
u55:
	clrc
	rlf	(??_main+0+0),f
	rlf	(??_main+0+1),f
	decfsz	(??_main+2+0),f
	goto	u55
	movf	0+(??_main+0+0),w
	addwf	(main@adcValue),f
	skipnc
	incf	(main@adcValue+1),f
	movf	1+(??_main+0+0),w
	addwf	(main@adcValue+1),f
	
l30000454:	
	line	62
;main.c: 62: _delay_ms(500);
	movlw	low(01F4h)
	movwf	(?__delay_ms)
	movlw	high(01F4h)
	movwf	((?__delay_ms))+1
	fcall	__delay_ms
	
l30000455:	
	line	63
;main.c: 63: if(adcValue >= 880){
	movlw	high(0370h)
	subwf	(main@adcValue+1),w
	movlw	low(0370h)
	skipnz
	subwf	(main@adcValue),w
	skipc
	goto	u61
	goto	u60
u61:
	goto	l30000458
u60:
	
l30000456:	
	line	64
;main.c: 64: RC7 = 1;
	bcf	status, 5	;RP0=0, select bank0
	bcf	status, 6	;RP1=0, select bank0
	bsf	(63/8),(63)&7
	
l30000457:	
	line	65
;main.c: 65: _delay_ms(50);
	movlw	low(032h)
	movwf	(?__delay_ms)
	movlw	high(032h)
	movwf	((?__delay_ms))+1
	fcall	__delay_ms
	
l30000458:	
	line	66
;main.c: 66: RC7 = 0;
	bcf	status, 5	;RP0=0, select bank0
	bcf	status, 6	;RP1=0, select bank0
	bcf	(63/8),(63)&7
	goto	l30000452
	global	start
	ljmp	start
	opt stack 0
GLOBAL	__end_of_main
	__end_of_main:
; =============== function _main ends ============

psect	maintext
	line	73
	signat	_main,88
	global	__delay_ms
psect	text10,local,class=CODE,delta=2
global __ptext10
__ptext10:

; *************** function __delay_ms *****************
; Defined at:
;		line 34 in file "X:\PIC Projects\BonzaiIrrigation\main.c"
; Parameters:    Size  Location     Type
;  ms              2    2[COMMON] unsigned int 
; Auto vars:     Size  Location     Type
;  us              1    0[COMMON] unsigned char 
;  i               1    1[COMMON] unsigned char 
; Return value:  Size  Location     Type
;		None               void
; Registers used:
;		wreg, status,2, status,0
; Tracked objects:
;		On entry : 0/0
;		On exit  : 0/0
;		Unchanged: 0/0
; Data sizes:     COMMON   BANK0   BANK1   BANK2
;      Locals:         4       0       0       0
;      Temp:     0
;      Total:    4
; This function calls:
;		Nothing
; This function is called by:
;		_main
; This function uses a non-reentrant model
; 
psect	text10
	file	"X:\PIC Projects\BonzaiIrrigation\main.c"
	line	34
	global	__size_of__delay_ms
	__size_of__delay_ms	equ	__end_of__delay_ms-__delay_ms
;main.c: 25: asm("\tpsect config,class=CONFIG,delta=2"); asm("\tdw ""0x3FFC & 0x3FF7 & 0x3FEF & 0x3FFF & 0x3FFF & 0x3FFF & 0x3CFF & 0x3BFF & 0x37FF");
;main.c: 33: void _delay_ms(unsigned int ms)
;main.c: 34: {
	
__delay_ms:	
	opt stack 7
; Regs used in __delay_ms: [wreg+status,2+status,0]
	
l5:	
	
l30000442:	
	line	37
;main.c: 37: i = 4;
	movlw	(04h)
	movwf	(__delay_ms@i)
	
l8:	
	line	39
;main.c: 39: { unsigned char us; us = (164)/(12000000/8000000L)|1; while(--us != 0) continue; };
	movlw	(0A5h)
	movwf	(__delay_ms@us)
	
l30000443:	
	movlw	low(01h)
	subwf	(__delay_ms@us),f
	btfss	status,2
	goto	u11
	goto	u10
u11:
	goto	l30000443
u10:
	
l30000444:	
	line	40
;main.c: 40: } while(--i);
	movlw	low(01h)
	subwf	(__delay_ms@i),f
	btfss	status,2
	goto	u21
	goto	u20
u21:
	goto	l8
u20:
	
l30000445:	
	line	41
;main.c: 41: } while(--ms);
	movlw	low(01h)
	subwf	(__delay_ms@ms),f
	movlw	high(01h)
	skipc
	decf	(__delay_ms@ms+1),f
	subwf	(__delay_ms@ms+1),f
	movf	(((__delay_ms@ms+1))&07fh),w
	iorwf	(((__delay_ms@ms))&07fh),w
	skipz
	goto	u31
	goto	u30
u31:
	goto	l5
u30:
	
l2:	
	return
	opt stack 0
GLOBAL	__end_of__delay_ms
	__end_of__delay_ms:
; =============== function __delay_ms ends ============

psect	text11,local,class=CODE,delta=2
global __ptext11
__ptext11:
	line	42
	signat	__delay_ms,4216
	end
