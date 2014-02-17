/*
 * QuadBone.cpp
 *
 *  Created on: Apr 22, 2012
 *      Author: flitjes
 */

/*Pinlayout
 * P9
 * 4		3V
 * 6		5V
 * 14		PWM1:0
 * 16		PWM1:1
 * 19		SCL
 * 20		SDA
 * 21		PWM0:1
 * 22		PWM0:0
 */


#include <iostream>
#include <cstring>
#include <sstream>
#include <cstdlib>
#include <string.h>
#include "actuators/PwmController.h"
#include "sensors/L3G4200D.h"
#include "sensors/ADXL345.h"
#include "communication/Ethernet.h"
#include "communication/Shell.h"
using namespace std;
void setup(ADXL345 *adxl) {
	adxl->powerOn();

	//set activity/ inactivity thresholds (0-255)
	adxl->setActivityThreshold(75); //62.5mg per increment
	adxl->setInactivityThreshold(75); //62.5mg per increment
	adxl->setTimeInactivity(10); // how many seconds of no activity is inactive?

	//look of activity movement on this axes - 1 == on; 0 == off
	adxl->setActivityX(1);
	adxl->setActivityY(1);
	adxl->setActivityZ(1);

	//look of inactivity movement on this axes - 1 == on; 0 == off
	adxl->setInactivityX(1);
	adxl->setInactivityY(1);
	adxl->setInactivityZ(1);

	//look of tap movement on this axes - 1 == on; 0 == off
	adxl->setTapDetectionOnX(0);
	adxl->setTapDetectionOnY(0);
	adxl->setTapDetectionOnZ(1);

	//set values for what is a tap, and what is a double tap (0-255)
	adxl->setTapThreshold(50); //62.5mg per increment
	adxl->setTapDuration(15); //625μs per increment
	adxl->setDoubleTapLatency(80); //1.25ms per increment
	adxl->setDoubleTapWindow(200); //1.25ms per increment

	//set values for what is considered freefall (0-255)
	adxl->setFreeFallThreshold(7); //(5 - 9) recommended - 62.5mg per increment
	adxl->setFreeFallDuration(45); //(20 - 70) recommended - 5ms per increment

	//setting all interupts to take place on int pin 1
	//I had issues with int pin 2, was unable to reset it
	adxl->setInterruptMapping(ADXL345_INT_SINGLE_TAP_BIT, ADXL345_INT1_PIN);
	adxl->setInterruptMapping(ADXL345_INT_DOUBLE_TAP_BIT, ADXL345_INT1_PIN);
	adxl->setInterruptMapping(ADXL345_INT_FREE_FALL_BIT, ADXL345_INT1_PIN);
	adxl->setInterruptMapping(ADXL345_INT_ACTIVITY_BIT, ADXL345_INT1_PIN);
	adxl->setInterruptMapping(ADXL345_INT_INACTIVITY_BIT, ADXL345_INT1_PIN);

	//register interupt actions - 1 == on; 0 == off
	adxl->setInterrupt(ADXL345_INT_SINGLE_TAP_BIT, 1);
	adxl->setInterrupt(ADXL345_INT_DOUBLE_TAP_BIT, 1);
	adxl->setInterrupt(ADXL345_INT_FREE_FALL_BIT, 1);
	adxl->setInterrupt(ADXL345_INT_ACTIVITY_BIT, 1);
	adxl->setInterrupt(ADXL345_INT_INACTIVITY_BIT, 1);
}

int main() {
	char read_buffer[256];
	PwmController *pwm_controller = new PwmController();
	L3G4200D *gyro = new L3G4200D();
	//ADXL345 *accellerometer = new ADXL345();
	ICommunication *com = new Ethernet();
	com->init();
	//setup(accellerometer);
	string userinput;
	bool quit = false;
	int i;
	while (!quit) {
		com->write_com(
				(char*) " ****************** \n "
				"Options \n"
				"1. PWM1\n"
				"2. PWM2\n"
				"3. PWM3\n"
				"4. PWM4\n"
				"5. PWM INFO\n"
				"6. Gyro values\n"
				"\n ******************\n ",
				256);
		com->read_com(read_buffer);
		i = strtol((char *) read_buffer, NULL, 0);
		if (1 == i) {
			pwm_controller->pwm0->setEnable(ENABLE);
			pwm_controller->pwm0->setFrequentie(50);
			pwm_controller->pwm0->setDuty_ns(0);
			while(i != 0)
			{
				com->write_com((char *)"Enter dutycyle in ns ranging from 600000 - 2000000 \n "
						"0 means quit\n",256);
				com->write_com((char *)"\n",256);
				com->read_com(read_buffer);
				i = atoi((char *) read_buffer);
				printf("%d",i);
				if(i != 0){
					pwm_controller->pwm0->setDuty_ns(i);
				}
			}
			com->write_com((char*)"PWM1",256);
		} else if (2 == i) {
			pwm_controller->pwm1->setEnable(ENABLE);
			pwm_controller->pwm1->setFrequentie(50);
			pwm_controller->pwm1->setDuty_ns(0);
			while(i != 0)
			{
				com->write_com((char *)"Enter dutycyle in ns ranging from 600000 - 200000 \n "
						"0 means quit\n",256);
				com->write_com((char *)"\n",256);
				com->read_com(read_buffer);
				i = atoi((char *) read_buffer);
				printf("%d",i);
				if(i != 0){
					pwm_controller->pwm1->setDuty_ns(i);
				}
			}
			com->write_com((char*)"PWM2",256);
		} else if (3 == i) {
			pwm_controller->pwm2->setEnable(ENABLE);
			pwm_controller->pwm2->setFrequentie(50);
			pwm_controller->pwm2->setDuty_ns(0);
			while(i != 0)
			{
				com->write_com((char *)"Enter dutycyle in ns ranging from 600000 - 2000000 \n "
						"0 means quit\n",256);
				com->write_com((char *)"\n",256);
				com->read_com(read_buffer);
				i = atoi((char *) read_buffer);
				printf("%d",i);
				if(i != 0){
					pwm_controller->pwm2->setDuty_ns(i);
				}
			}
			com->write_com((char*)"PWM3",256);
		} else if (4 == i) {
			pwm_controller->pwm3->setEnable(ENABLE);
			pwm_controller->pwm3->setFrequentie(50);
			pwm_controller->pwm3->setDuty_ns(0);
			while(i != 0)
			{
				com->write_com((char *)"Enter dutycyle in ns ranging from 600000 - 2000000 \n "
						"0 means quit\n",256);
				com->write_com((char *)"\n",256);
				com->read_com(read_buffer);
				i = atoi((char *) read_buffer);
				printf("%d",i);
				if(i != 0){
					pwm_controller->pwm3->setDuty_ns(i);
				}
			}
			com->write_com((char*)"PWM4",256);
		} else if (5 == i) {
			pwm_controller->getSettings();
			com->write_com((char*)"PWM Info",256);
		} else if (6 == i) {
			int sizeofbuffer;
			int *buffer;
			char write_buffer_local[256];
			bzero(write_buffer_local,256);
			buffer = gyro->get(&sizeofbuffer);
			int amount_written = sprintf(write_buffer_local, "Gyro X: %d Gyro Y: %d Gyro Z: %d\n",
					buffer[0], buffer[1], buffer[2]);
			com->write_com(write_buffer_local, amount_written);

		} else if (7 == i) {
			int sizeofbuffer;
			int *buffer;
			char write_buffer_local[256];
			bzero(write_buffer_local,256);
			//buffer = accellerometer->get(&sizeofbuffer);
			int amount_written = sprintf(write_buffer_local,
					"Accelerometer X: %d Accelerometer Y:%d Accelerometer Z:%d\n",
					buffer[0], buffer[1], buffer[2]);
			com->write_com(write_buffer_local, amount_written);
		} else if (0 == i)
			quit = true;

	}
	pwm_controller->disablePwms();
	pwm_controller->~PwmController();
	com->close_com();
	return 0;
}

