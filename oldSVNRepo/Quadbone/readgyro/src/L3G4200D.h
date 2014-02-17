/*
 * L3G4200D.h
 *
 *  Created on: Jun 14, 2012
 *      Author: flitjes
 */

#ifndef L3G4200D_H_
#define L3G4200D_H_
#include "I2C.h"
#include "ISensor.h"
#include <iostream>
#include <cstdlib>
#define DEVICEADDR 0x69
#define CTRL_REG1 0x20
#define CTRL_REG2 0x21
#define CTRL_REG3 0x22
#define CTRL_REG4 0x23
#define CTRL_REG5 0x24

namespace std {

class L3G4200D: public ISensor {
private:
	I2C *i2c;

public:
	struct gyro_data{
		int x;
		int y;
		int z;
		int current_angle;
	};
	L3G4200D();
	void init();
	void getGyroValues(int *x, int *y, int *z);
	virtual void enable();
	virtual void disable();
	virtual int* get(int *sizeofbuffer);
	virtual ~L3G4200D();
};

} /* namespace std */
#endif /* L3G4200D_H_ */
