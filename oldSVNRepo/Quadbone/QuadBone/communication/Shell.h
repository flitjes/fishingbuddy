/*
 * Shell.h
 *
 *  Created on: Jun 22, 2012
 *      Author: flitjes
 */

#ifndef SHELL_H_
#define SHELL_H_
#include "ICommunication.h"
namespace std {

class Shell : public ICommunication{
public:
	Shell();
	virtual void read_com(void * buffer);
	virtual void write_com(void * buffer,int size);
	virtual void init();
	virtual void close_com();
	virtual ~Shell();
};

} /* namespace std */
#endif /* SHELL_H_ */
