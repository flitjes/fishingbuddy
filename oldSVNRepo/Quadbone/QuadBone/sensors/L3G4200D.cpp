/*
 * L3G4200D.cpp
 *
 *  Created on: Jun 14, 2012
 *      Author: flitjes
 */

#include "L3G4200D.h"
#include <unistd.h>

namespace std {

L3G4200D::L3G4200D() {
	i2c = new I2C(DEVICEADDR);
	init();

}
void L3G4200D::init() {
	i2c->writei2c(CTRL_REG1, 0b00001111);
	i2c->writei2c(CTRL_REG2, 0b00000000);
	i2c->writei2c(CTRL_REG3, 0b00001000);
	i2c->writei2c(CTRL_REG4, 0b00110000);
	i2c->writei2c(CTRL_REG5, 0b00000000);
}
void L3G4200D::getGyroValues(int *x, int *y, int *z) {
	char MSB;
	char LSB;

	MSB = i2c->readi2c(0x29);
	LSB = i2c->readi2c(0x28);
	*x = ((MSB << 8) | LSB);

	MSB = i2c->readi2c(0x2B);
	LSB = i2c->readi2c(0x2A);
	*y = ((MSB << 8) | LSB);

	MSB = i2c->readi2c(0x2D);
	LSB = i2c->readi2c(0x2C);
	*z = ((MSB << 8) | LSB);

	//cout << "x :" << *x << "y: " << *y << "z: " << *z << endl;
}
int* L3G4200D::get(int *sizeofbuffer) {
	static const int amount_of_data_ellements = 3;
	int buffer[amount_of_data_ellements];
	*sizeofbuffer = amount_of_data_ellements;
	getGyroValues(&buffer[0], &buffer[1], &buffer[2]);
	return &buffer[0];
}
void L3G4200D::enable() {

}
void L3G4200D::disable() {

}
L3G4200D::~L3G4200D() {
	// TODO Auto-generated destructor stub
}
ISensor::~ISensor() {

}

} /* namespace std */
