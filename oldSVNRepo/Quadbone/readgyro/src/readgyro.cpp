//============================================================================
// Name        : readgyro.cpp
// Author      : Christian Litjes
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <sys/time.h>
#include <stdlib.h>
#include <time.h>
#include "L3G4200D.h"

using namespace std;
bool switching = false;
struct gyro_data{
	int x;
	int y;
	int z;
};

gyro_data gyrodata;
int xgyro,ygyro,zgyro;
L3G4200D *gyro;
int convert_twos_complement_to_decimal(int *twos_comp)
{
	int decimal = 0;
	if(*twos_comp >= 0xF000){
		decimal = *twos_comp ^ 0xFFFF;
		decimal += 1;
		decimal *= -1;

	}
	else {
		decimal = *twos_comp;
	}
	return decimal;

}
void timer_handler (int signum)
{

 static int count = 0;
 gyro->getGyroValues(&xgyro, &ygyro,&zgyro);



 /*if((xgyro > 50 && xgyro < 32525))
	 gyrodata.x += xgyro * 0.01;
 if((xgyro > 32525 && xgyro < 65500))
 {
	 xgyro -= 65500;
	 gyrodata.x -= xgyro * 0.01;
 }

 if((ygyro > 50 && ygyro < 32525))
	 gyrodata.y += ygyro * 0.01;
 if((ygyro > 32525 && ygyro < 65500))
 {
	 ygyro -= 65500;
	 gyrodata.y -= ygyro * 0.01;
 }


 if((zgyro > 50 && zgyro < 32525))
	 gyrodata.z += zgyro * 0.01;
 if((zgyro > 32525 && zgyro < 65500))
 {
	 zgyro -= 65500;
	 gyrodata.z -= zgyro * 0.01;
 }*/
 /*gyrodata.x += convert_twos_complement_to_decimal(&xgyro);
 gyrodata.y += convert_twos_complement_to_decimal(&ygyro);
 gyrodata.z += convert_twos_complement_to_decimal(&zgyro);
 //printf ("timer expired %d times with %d\n", ++count, random);
 printf ("Current position is: \t %u \t %u \t %u\n", gyrodata.x,gyrodata.y,gyrodata.z );*/


 printf ("Current position is: %d, \t %d,\t  %d\n", convert_twos_complement_to_decimal(&xgyro),convert_twos_complement_to_decimal(&ygyro),convert_twos_complement_to_decimal(&zgyro));

}

int main() {
	 struct sigaction sa;
	 struct itimerval timer;
	 gyro = new L3G4200D();
	 gyro->init();
	 /* Install timer_handler as the signal handler for SIGVTALRM. */
	 memset (&sa, 0, sizeof (sa));
	 sa.sa_handler = &timer_handler;
	 sigaction (SIGVTALRM, &sa, NULL);

	 /* Configure the timer to expire after 10 msec... */
	 timer.it_value.tv_sec = 0;
	 timer.it_value.tv_usec = 10000;
	 /* ... and every 10 msec after that. */
	 timer.it_interval.tv_sec = 0;
	 timer.it_interval.tv_usec = 10000;
	 /* Start a virtual timer. It counts down whenever this process is
	   executing. */
	 setitimer (ITIMER_VIRTUAL, &timer, NULL);

	 /* Do busy work. */
	 while (1);

	return 0;
}
