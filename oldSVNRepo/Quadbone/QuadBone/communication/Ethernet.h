/*
 * Ethernet.h
 *
 *  Created on: Jun 22, 2012
 *      Author: flitjes
 */

#ifndef ETHERNET_H_
#define ETHERNET_H_
#include "ICommunication.h"
#include <stdio.h>
#include <cstdlib>

namespace std {

class Ethernet : public ICommunication {
public:
	Ethernet();
	virtual void read_com(void * buffer);
	virtual void write_com(void * buffer,int size);
	virtual void init();
	virtual void close_com();
	virtual ~Ethernet();
};

} /* namespace std */
#endif /* ETHERNET_H_ */
