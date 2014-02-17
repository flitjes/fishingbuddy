/*
 * Shell.cpp
 *
 *  Created on: Jun 22, 2012
 *      Author: flitjes
 */

#include "Shell.h"
#include <iostream>
#include <cstring>
#include <sstream>
#include <cstdlib>
#include <cstdio>

namespace std {

Shell::Shell() {
	// TODO Auto-generated constructor stub

}
void Shell::init()
{

}
void Shell::read_com(void * buffer)
{
	string userinput;
	getline(cin, userinput);
	int d=sprintf((char*)buffer,userinput.c_str());

}
void Shell::write_com(void * buffer, int size)
{
	char buf[size];
	sprintf(buf,(char*) buffer);
	cout << buf << endl;
}

Shell::~Shell() {
	// TODO Auto-generated destructor stub
}
void Shell::close_com()
{

}


} /* namespace std */
