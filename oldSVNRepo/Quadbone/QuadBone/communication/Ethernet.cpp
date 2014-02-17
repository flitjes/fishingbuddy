/*
 * Ethernet.cpp
 *
 *  Created on: Jun 22, 2012
 *      Author: flitjes
 */

#include "Ethernet.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#define PORTNR 31337
int sockfd, newsockfd, portno;
void error(const char *msg) {
	perror(msg);
	exit(1);
}
namespace std {

Ethernet::Ethernet() {
	// TODO Auto-generated constructor stub

}

void Ethernet::init() {

	socklen_t clilen;

	struct sockaddr_in serv_addr, cli_addr;
	int n;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("ERROR opening socket");
	bzero((char *) &serv_addr, sizeof(serv_addr));
	portno = PORTNR;
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);
	if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
		error("ERROR on binding");
	listen(sockfd, 5);
	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
	if (newsockfd < 0)
		error("ERROR on accept");


}
void Ethernet::read_com(void * buffer) {
	int n;
	char buf[256];
	bzero(buf, 256);
	n = read(newsockfd, buf, 255);
	if (n < 0)
		error("ERROR reading from socket");
	sprintf((char*) buffer, buf);
}
void Ethernet::write_com(void * buffer, int size) {
	int n;
	n = write(newsockfd, (char *) buffer, size);
	if (n < 0)
		error("ERROR writing to socket");
}
void Ethernet::close_com()
{
	close(sockfd);
	close(newsockfd);
}
Ethernet::~Ethernet() {
	// TODO Auto-generated destructor stub
}
ICommunication::~ICommunication() {

}

} /* namespace std */
