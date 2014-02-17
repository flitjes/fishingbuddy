/*
 * I2C.h
 *
 *  Created on: Jun 3, 2012
 *      Author: flitjes
 */

#ifndef I2C_H_
#define I2C_H_
//#define DEBUG;
namespace std {

class I2C {
private:
	int file;
	char deviceaddr;
public:
	I2C(int addr);
	void openi2c(char addr);
	void closei2c();
	char readi2c(char addrtoread);
	void writei2c(char addr, char byte);
	virtual ~I2C();

};

} /* namespace std */
#endif /* I2C_H_ */
