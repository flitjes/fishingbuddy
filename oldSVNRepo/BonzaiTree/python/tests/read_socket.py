# Echo client program
import socket

HOST = '192.168.178.28'    # The remote host
PORT = 1337              # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))
data = s.recv(1024)
s.close()
print 'Received', repr(data)
