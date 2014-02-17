/*
 * PwmController.cpp
 *
 *  Created on: May 14, 2012
 *      Author: flitjes
 */

#include "PwmController.h"

#include "PwmDriver.h"
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
#include <stdlib.h>
#define CM_PER_REG_START 0x44e00000
#define CM_PER_REG_LENGTH 1024
#define CM_PER_EPWMSS0_CLKCTRL_OFFSET 0xd4
#define CM_PER_EPWMSS1_CLKCTRL_OFFSET 0xcc
#define CM_PER_EPWMSS2_CLKCTRL_OFFSET 0xd8
#define PWM_CLOCK_ENABLE 0x2
#define PWM_CLOCK_DISABLE 0x0
#define PWM_LIST_MAX 3
int PWM_OFFSETS[PWM_LIST_MAX] = {
	CM_PER_EPWMSS0_CLKCTRL_OFFSET / sizeof (uint32_t),
	CM_PER_EPWMSS1_CLKCTRL_OFFSET / sizeof (uint32_t),
	CM_PER_EPWMSS2_CLKCTRL_OFFSET / sizeof (uint32_t)
};

namespace std {

PwmController::PwmController() {
	enableclocks();
	initPwm(PWM1);
	initPwm(PWM2);
	initPwm(PWM3);
	initPwm(PWM4);

}


string PwmController::initPwm(int pwm)
{
	string retval;
	Multiplex * multiplex = new Multiplex();
	switch(pwm)
	{
		case PWM1:
			retval = PWM1_PATH;
			pwm0 = new PwmDriver(PWM1_PATH);
			multiplex->writeMultiplex(6,(char *)"gpmc_a2");
			printf("%s", multiplex->readMultiplex((char *)"gpmc_a2"));
		break;
		case PWM2:
			retval = PWM2_PATH;
			pwm1 = new PwmDriver(PWM2_PATH);
			multiplex->writeMultiplex(6,(char *)"gpmc_a3");
			printf("%s", multiplex->readMultiplex((char *)"gpmc_a3"));
		break;
		case PWM3:
			retval = PWM3_PATH;
			pwm2 = new PwmDriver(PWM3_PATH);
			multiplex->writeMultiplex(3,(char *)"spi0_sclk");
			printf("%s", multiplex->readMultiplex((char *)"spi0_sclk"));
		break;
		case PWM4:
			retval = PWM4_PATH;
			pwm3 = new PwmDriver(PWM4_PATH);
			multiplex->writeMultiplex(3,(char *)"spi0_d0");
			printf("%s", multiplex->readMultiplex((char *)"spi0_d0"));
		break;
	}
	free(multiplex);

	return retval;
}


void print_usage (const char *message)
{
	if (message)
		printf ("%s\n", message);
	printf ("pwm_clock <-e | -d> <PWM [PWM]>\n\n");
}
void PwmController::enableclocks(void)
{
	int i;
	int *cur_list = NULL;
	int *cur_list_index = NULL;
	int enable_list[PWM_LIST_MAX];
	int enable_list_index = 0;
	int disable_list[PWM_LIST_MAX];
	int disable_list_index = 0;
	int dev_mem_fd;
	int argc = 5;
	char* argv[5] = {(char *) "enable_clocks.cpp",(char *) "-e",(char *) "0",(char *) "1",(char *) "2"};
	volatile uint32_t *cm_per_regs;
	for (i = 0; i < PWM_LIST_MAX; ++i) {
		enable_list[i] = -1;
		disable_list[i] = -1;
	}
	for (i = 1; i < argc; ++i) {
		if (strncmp (argv[i], "-e", 2) == 0) {
			cur_list = enable_list;
			cur_list_index = &enable_list_index;
		}
		else if (strncmp (argv[i], "-d", 2) == 0) {
			cur_list = disable_list;
			cur_list_index = &disable_list_index;
		}
		else {
			if (!cur_list) {
				print_usage (0);
			}
			if (*cur_list_index >= PWM_LIST_MAX) {
				print_usage ("Too many PWM's specified for an option");
			}
			cur_list[*cur_list_index] = atoi (argv[i]);
			++*cur_list_index;
		}
	}
	dev_mem_fd = open ("/dev/mem", O_RDWR);
	if (dev_mem_fd == -1) {
		perror ("open failed");
	}
	cm_per_regs = (volatile uint32_t *)mmap (NULL, CM_PER_REG_LENGTH,
			PROT_READ | PROT_WRITE, MAP_SHARED, dev_mem_fd, CM_PER_REG_START);
	if (cm_per_regs == (volatile uint32_t *)MAP_FAILED) {
		perror ("mmap failed");
		close (dev_mem_fd);
	}
	for (i = 0; i < PWM_LIST_MAX && enable_list[i] != -1; ++i) {
		if (enable_list[i] < 0 || enable_list[i] >= PWM_LIST_MAX) {
			printf ("Invalid PWM specified, %d\n", enable_list[i]);
			goto out;
		}
		printf ("Enabling PWM %d\n", enable_list[i]);
		cm_per_regs[PWM_OFFSETS[enable_list[i]]] = PWM_CLOCK_ENABLE;
	}
	for (i = 0; i < PWM_LIST_MAX && disable_list[i] != -1; ++i) {
		if (disable_list[i] < 0 || disable_list[i] >= PWM_LIST_MAX) {
			printf ("Invalid PWM specified, %d\n", disable_list[i]);
			goto out;
		}
		printf ("Disabling PWM %d\n", disable_list[i]);
		cm_per_regs[PWM_OFFSETS[disable_list[i]]] = PWM_CLOCK_DISABLE;
	}
out:
	munmap ((void *)cm_per_regs, CM_PER_REG_LENGTH);
	close (dev_mem_fd);
}

void PwmController::disablePwms(void)
{
	pwm0->setEnable(0);
	pwm1->setEnable(0);
	pwm2->setEnable(0);
	pwm3->setEnable(0);
}

void PwmController::getSettings()
{
	cout << "PWM0" << endl;
	cout << "Duty: " << pwm0->getDuty_ns() << endl;
	cout << "Freq: " << pwm0->getFrequentie() << endl;
	cout << "Enabled: " << pwm0->getEnable() << endl;
	cout << "PWM1" << endl;
	cout << "Duty: " << pwm1->getDuty_ns() << endl;
	cout << "Freq: " << pwm1->getFrequentie() << endl;
	cout << "Enabled: " << pwm1->getEnable() << endl;
	cout << "PWM2" << endl;
	cout << "Duty: " << pwm2->getDuty_ns() << endl;
	cout << "Freq: " << pwm2->getFrequentie() << endl;
	cout << "Enabled: " << pwm2->getEnable() << endl;
	cout << "PWM3" << endl;
	cout << "Duty: " << pwm3->getDuty_ns() << endl;
	cout << "Freq: " << pwm3->getFrequentie() << endl;
	cout << "Enabled: " << pwm3->getEnable() << endl;
}

PwmController::~PwmController() {
	// TODO Auto-generated destructor stub

	pwm0->~PwmDriver();
	pwm1->~PwmDriver();
	pwm2->~PwmDriver();
	pwm3->~PwmDriver();
}

} /* namespace std */
