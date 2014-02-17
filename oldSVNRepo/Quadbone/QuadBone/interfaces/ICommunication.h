/*
 * ICommunication.h
 *
 *  Created on: Jun 22, 2012
 *      Author: flitjes
 */

#ifndef ICOMMUNICATION_H_
#define ICOMMUNICATION_H_

namespace std {
class ICommunication {

public:
	virtual void read_com(void * buffer) = 0;
	virtual void write_com(void * buffer,int size) = 0;
	virtual void init() = 0;
	virtual void close_com() = 0;
	virtual ~ICommunication() = 0;
};
}

#endif /* ICOMMUNICATION_H_ */
