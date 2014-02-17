/*
 * Multiplex.h
 *
 *  Created on: Jun 27, 2012
 *      Author: flitjes
 */

#ifndef MULTIPLEX_H_
#define MULTIPLEX_H_

namespace std {

class Multiplex {
public:
	Multiplex();
	void writeMultiplex(int value, char * element);
	char * readMultiplex(char * element);
	virtual ~Multiplex();
};

} /* namespace std */
#endif /* MULTIPLEX_H_ */
