/*
 * PwmDriver.cpp
 *
 *  Created on: May 6, 2012
 *      Author: flitjes
 */

#include "PwmDriver.h"
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
#include <stdlib.h>

namespace std {


PwmDriver::PwmDriver(string pwm) {
	// TODO Auto-generated constructor stub
	selected_pwm = pwm;
	cout << "PwmDriver for pwm: " << selected_pwm << endl;
}
//Private





void PwmDriver::writePwm(int value, char * element)
{

	FILE * pFile;
	char * str = (char *) malloc(sizeof(char) * 20);
	string path = "/sys/class/pwm/" + selected_pwm + "/";
	path.append(element);
	pFile = fopen (path.c_str(),"w+");
	if (pFile!=NULL)
	{
		cout << "file opened! " << path << endl;
		sprintf(str,"%d",value);
		cout << "Value is: " << atoi(str) << endl;
		fputs (str,pFile);
	}
	else
		cout << "Open Failed!" << endl;
	fclose (pFile);
	free(str);

}

string PwmDriver::readPwm(char * element)
{

	FILE * pFile;
	char * str = (char *) malloc(sizeof(char) * 20);
	string retval = "";
	string path = "/sys/class/pwm/" + selected_pwm + "/";
	path.append(element);
	pFile = fopen (path.c_str(),"r");
	if (pFile!=NULL)
	{
		cout << "file opened! " << path << endl;
		fgets (str,20,pFile);
		retval = str;
	}
	else
		cout << "Open Failed!" << endl;
	fclose (pFile);
	free(str);
	return retval;

}



//Public
void PwmDriver::setDuty_ns(int ns)
{
	writePwm(ns, (char *)"duty_ns");
	cout << "Duty set to: " << ns << endl;
}

int PwmDriver::getDuty_ns()
{
	string retval = readPwm((char *)"duty_ns");
	return atoi(retval.c_str());
}

void PwmDriver::setFrequentie(int frequentie)
{
	writePwm(frequentie, (char *)"period_freq");
	cout << "Frequentie set to: " << frequentie << endl;
}

int PwmDriver::getFrequentie()
{
	string retval = readPwm((char *)"period_freq");
	return atoi(retval.c_str());
}

void PwmDriver::setEnable(int enable)
{
	writePwm(enable, (char *)"request");
	writePwm(enable, (char *)"run");
}

int PwmDriver::getEnable()
{
	int run, request;
	string retval = readPwm((char *)"run");
	run = atoi(retval.c_str());
	retval = readPwm((char *)"request");
	request = atoi(retval.c_str());
	if(request == 1 && run == 1)
		return 1;
	else
	if(request == 0 && run == 0)
		return 0;
	else
		return -1;
}
char * PwmDriver::toString()
{
	char retval[256];
	sprintf(retval, "%s Enabled: %d Duty: %d Freq: %d", selected_pwm.c_str(), getEnable(), getDuty_ns(), getFrequentie());
	return retval;
}

PwmDriver::~PwmDriver() {
	// TODO Auto-generated destructor stub
}

} /* namespace std */
