/*
 * ISensor.h
 *
 *  Created on: Jun 21, 2012
 *      Author: flitjes
 */

#ifndef ISENSOR_H_
#define ISENSOR_H_
namespace std {
class ISensor{

public:
	struct {
		int * buffer;
		int size;
	}typedef sensordata;
	virtual void enable() = 0;
	virtual void disable() = 0;
	virtual int* get(int *sizeofbuffer) = 0;
	virtual ~ISensor() = 0;
};
}

#endif /* ISENSOR_H_ */
