/*
 * PwmController.h
 *
 *  Created on: May 14, 2012
 *      Author: flitjes
 */

#ifndef PWMCONTROLLER_H_
#define PWMCONTROLLER_H_
#include "PwmDriver.h"
namespace std {

class PwmController {
private:
	void enableclocks();
public:
	PwmDriver *pwm0;
	PwmDriver *pwm1;
	PwmDriver *pwm2;
	PwmDriver *pwm3;
	PwmController();
	string initPwm(int pwm);
	void disablePwms(void);
	void getSettings();
	virtual ~PwmController();
};

} /* namespace std */
#endif /* PWMCONTROLLER_H_ */
