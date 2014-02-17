/*
 * PwmDriver.h
 *
 *  Created on: May 6, 2012
 *      Author: flitjes
 */

#ifndef PWMDRIVER_H_
#define PWMDRIVER_H_
#include <string>

namespace std {
const int PWM1 = 0;
const int PWM2 = 1;
const int PWM3 = 2;
const int PWM4 = 3;
const string PWM1_PATH = "ehrpwm.0:0";
const string PWM2_PATH = "ehrpwm.0:1";
const string PWM3_PATH = "ehrpwm.1:0";
const string PWM4_PATH = "ehrpwm.1:1";
const int ENABLE = 1;
const int DISABLE = 0;

class PwmDriver {
private:
	int duty_ns;
	int frequentie;
	int enable;
	string selected_pwm;
	void writePwm(int value, char * element);
	string readPwm(char * element);

public:
	PwmDriver(string motor);
	void setDuty_ns(int ns);
	int getDuty_ns();
	void setFrequentie(int frequentie);
	int getFrequentie();
	void setEnable(int enable);
	int getEnable();
	char * toString();
	virtual ~PwmDriver();

};


} /* namespace std */
#endif /* PWMDRIVER_H_ */
