"""CD Chat client program"""
import logging
import socket
import sys

import sys
import fcntl
import os
import selectors

from .protocol import CDProto, CDProtoBadFormat

logging.basicConfig(filename=f"{sys.argv[0]}.log", level=logging.DEBUG)


class Client:
    """Chat Client process."""
    def __init__(self, name: str = "Foo"):
        """Initializes chat client."""
        self.name = name
        self.sel = selectors.DefaultSelector()
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.setblocking(False)
        self.events = selectors.EVENT_READ | selectors.EVENT_WRITE
        self.sel.register(self.sock, self.events, data=None)
        self.channel = "geral"
        orig_fl = fcntl.fcntl(sys.stdin, fcntl.F_GETFL)
        fcntl.fcntl(sys.stdin, fcntl.F_SETFL, orig_fl | os.O_NONBLOCK)
        self.protocolo = CDProto()
        self.sel.register(sys.stdin, selectors.EVENT_READ, self.keyboard_input)



    def connect(self):
        """Connect to chat server and setup stdin flags."""
        try:
                self.sock.connect_ex(('127.0.0.1', 8042))
                self.sel.select(timeout=1)  # Wait until socket is ready for write
                self.send_message(self.protocolo.register(self.name))
                print("Connected to server.")
        except ConnectionRefusedError:
                print("Connection refused.")
                sys.exit()
        except BlockingIOError:
                pass

    
    def receive_message(self):
        message = self.protocolo.recv_msg(self.sock)
        msg = message.dicionario["message"]
        if msg:
            print("received> ",msg)
        else:
            print('Server disconnected.')
            self.sel.unregister(self.sock)
            self.sock.close()
            sys.exit()

    def send_message(self, msg):
        self.protocolo.send_msg(self.sock, msg)

    def keyboard_input(self, stdin):
        try:
            input_data = stdin.read().rstrip()
            if len(input_data) > 0:
                values = input_data.split()
                if values[0] == "/join":
                    self.channel = values[1]
                    message = self.protocolo.join(values[1])
                    self.send_message(message)
                elif values[0] == "exit":
                    self.sel.unregister(self.sock)
                    self.sock.close()
                    print("value: ",values[0])
                    sys.exit()
                else:
                    message = self.protocolo.message(input_data, self.channel)
                    self.send_message(message)
        except:
            sys.exit()
            #pass


    def loop(self):
        while True:
            events = self.sel.select(timeout=None)
            for key, mask in events:
                if key.fileobj == self.sock:
                    if mask & selectors.EVENT_READ:
                        self.receive_message()
                elif key.fileobj == sys.stdin:
                    if mask & selectors.EVENT_READ:
                        self.keyboard_input(sys.stdin)



