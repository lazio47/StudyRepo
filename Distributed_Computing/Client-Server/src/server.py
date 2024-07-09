"""CD Chat server program."""
from socket import error as SocketError
import logging
import selectors
import socket
import errno

from .protocol import CDProto, CDProtoBadFormat

logging.basicConfig(filename="server.log", level=logging.DEBUG)

class Server:
    """Chat Server process."""
    def __init__(self):
        """Initializes Server."""
        self.sock = socket.socket()
        self.sock.bind(('localhost', 8042))
        self.sock.listen(100)
        self.sock.setblocking(False)
        self.sel = selectors.DefaultSelector()
        self.protocolo = CDProto()

        self.sockets_list = []
        self.client_channel = {}
        self.client_name = {}
        self.clients = {}


    def accept(self, sock, mask):
        conn, addr = sock.accept()   
        conn.setblocking(False)
        self.sel.register(conn, selectors.EVENT_READ, self.read)
        self.sockets_list.append(conn)
        self.client_channel[conn] = "geral"
        # self.clients["geral"] = [conn]
        self.clients.setdefault("geral", []).append(conn)
        self.client_channel[conn] = "geral"



    def read(self, conn, mask):
        try:
            message = self.protocolo.recv_msg(conn)
            if message:
                command = message.dicionario["command"]
                if command == "message":
                    channel = message.dicionario.get("channel") if message.dicionario.get("channel") else "geral"
                    for connect in self.clients[channel]:
                        self.protocolo.send_msg(connect, message)
                elif command == "join":
                    channel = self.client_channel[conn]
                    self.clients.setdefault(message.dicionario["channel"], []).append(conn)
                    self.client_channel[conn] = message.dicionario["channel"]
                    self.clients[channel].remove(conn)
            else:
                print("Cliente terminou!")
                channel = self.client_channel[conn]
                self.client_channel.pop(conn)
                self.clients[channel].remove(conn)
                self.sel.unregister(conn)
                conn.close()

        except SocketError as e:
            if e.errno == errno.ECONNRESET:
                print("Conexão terminada pelo servidor.")
                self.sock.close()
            else:
                raise

    
    def loop(self):
        """Loop indefinetely."""
        self.sel.register(self.sock, selectors.EVENT_READ, self.accept)
        while True:
            events = self.sel.select()
            for key, mask in events:
                callback = key.data
                callback(key.fileobj, mask)

