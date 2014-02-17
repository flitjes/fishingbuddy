/*
 * Multiplex.cpp
 *
 *  Created on: Jun 27, 2012
 *      Author: flitjes
 */

#include "Multiplex.h"
#include <iostream>
#include <fstream>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdint.h>
#include <sys/mman.h>
#include <unistd.h>
#include <errno.h>
namespace std {

Multiplex::Multiplex() {
	// TODO Auto-generated constructor stub

}
void Multiplex::writeMultiplex(int value, char * element) {
	FILE * pFile;
	char * str = (char *) malloc(sizeof(char) * 20);
	string path = "/sys/kernel/debug/omap_mux/";
	path.append(element);
	pFile = fopen(path.c_str(), "w+");
	if (pFile != NULL) {
		sprintf(str, "%d", value);
		fputs(str, pFile);
	} else
		cout << "Open Failed!" << endl;
	fclose(pFile);
	free(str);
}
char* Multiplex::readMultiplex(char * element) {
	char retval[256];
	FILE * pFile;
	char * str = (char *) malloc(sizeof(char) * 20);
	string path = "/sys/kernel/debug/omap_mux/";
	path.append(element);
	pFile = fopen(path.c_str(), "r");
	if (pFile != NULL) {
		fgets(retval,256,pFile);
	} else
		cout << "Open Failed!" << endl;
	fclose(pFile);
	free(str);
	return retval;

}
Multiplex::~Multiplex() {
	// TODO Auto-generated destructor stub
}

} /* namespace std */
