#!/usr/bin/env python
import tweepy
import socket

# == OAuth Authentication ==
#
# This mode of authentication is the new preferred way
# of authenticating with Twitter.

# The consumer keys can be found on your application's Details
# page located at https://dev.twitter.com/apps (under "OAuth settings")
consumer_key="IeoSddEOVKBssrcI4yFXA"
consumer_secret="eawgHdk20nSA6mNey9n0QqsEtxNMqOTr9ClmegaAJfg"

# The access tokens can be found on your applications's Details
# page located at https://dev.twitter.com/apps (located 
# under "Your access token")
access_token="2207053111-G9iNq77yzeCIY1EEcrnZpoAmsT6I1gXx7F3lOKE"
access_token_secret="6RPlqrTEEo7rTMhzp3kAkZI2NSFvD3wRUpxoUKubqfsm1"

auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)

# If the authentication was successful, you should
# see the name of the account print out
print api.me().name

#Get info from the tree
HOST = '192.168.178.28'    # The remote host
PORT = 1337              # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))
data = s.recv(1024)
s.close()

# If the application settings are set for "Read and Write" then
# this line should tweet out the message to your account's 
# timeline. The "Read and Write" setting is on https://dev.twitter.com/apps
data = repr(data)
data = data.replace("'","")
data = data.replace(";","")
data = data.replace("\n","")
data = data.replace("\r","")
status =  data.split(' ')
print status[0]
value = status[1].split('M')
print value[1]
value = int(value[1]) / 10
output = "";
print value

if status[0] == "NOK":
	output = "I'm THIRSTY!";
else:
	output = "I'm OK!";

output += " Moisture level is: " + str(value) + "%";
print output
api.update_status(output)


