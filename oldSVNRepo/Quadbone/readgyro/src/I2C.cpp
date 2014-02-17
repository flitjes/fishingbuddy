/*
 * I2C.cpp
 *
 *  Created on: Jun 3, 2012
 *      Author: flitjes
 */

#include "I2C.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <cstdio>
#include <sys/ioctl.h>
#include <linux/i2c-dev.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <iostream>
#include <cstdlib>
namespace std {

I2C::I2C(int addr) {
	// TODO Auto-generated constructor stub

	openi2c(addr);

}
void I2C::openi2c(char addr)
{
	char *filename = "/dev/i2c-3";
	deviceaddr = addr;
		if ((file = open(filename, O_RDWR)) < 0) {
			/* ERROR HANDLING: you can check errno to see what went wrong */
			perror("Failed to open the i2c bus");
		}

		if (ioctl(file, I2C_SLAVE, addr) < 0) {
			printf("Failed to acquire bus access and/or talk to slave.\n");
			/* ERROR HANDLING; you can check errno to see what went wrong */
		}
#ifdef DEBUG
		cout << "I2C communcation ready!" << endl;
#endif
}
void I2C::closei2c()
{
	close(file);

}
void I2C::writei2c(char addr, char byte) {
	char buffer[10] = { 0 };
	buffer[0] = addr;
	buffer[1] = byte;
	if (write(file, buffer, 2) != 2) {
		/* ERROR HANDLING: i2c transaction failed */
		printf("Failed to write to the i2c bus.\n");
		printf(buffer);
		printf("\n\n");
	}
}

char I2C::readi2c(char addrtoread) {
	char buffer[10] = { 0 };
	char data;
	buffer[0] = addrtoread;

	if (write(file, buffer, 1) != 1) {
		/* ERROR HANDLING: i2c transaction failed */
		printf("Failed to write to the i2c bus.\n");
		printf(buffer);
		printf("\n\n");
	}
	// Using I2C Read
	if (read(file, buffer, 1) != 1) {
		/* ERROR HANDLING: i2c transaction failed */
		printf("Failed to read from the i2c bus.\n");
		printf("\n\n");
	}else {
		data = buffer[0];
#ifdef DEBUG
		//printf("Data: 0x%x\n", data);
#endif

	}
	return data;

}
I2C::~I2C() {
	// TODO Auto-generated destructor stub
}

} /* namespace std */
