# coding: utf-8

import socket
import selectors
import signal
import logging
import argparse

# ----------------- Load Balancer -----------------
import time

# configure logger output format
logging.basicConfig(level=logging.DEBUG,format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',datefmt='%m-%d %H:%M:%S')
logger = logging.getLogger('Load Balancer')


# used to stop the infinity loop
done = False

sel = selectors.DefaultSelector()

policy = None
mapper = None


# implements a graceful shutdown
def graceful_shutdown(signalNumber, frame):  
    logger.debug('Graceful Shutdown...')
    global done
    done = True


# n to 1 policy
class N2One:
    def __init__(self, servers):
        self.servers = servers  

    def select_server(self):
        return self.servers[0]

    def update(self, *arg):
        pass


# round robin policy
class RoundRobin:
    def __init__(self, servers):
        self.servers = servers
        self.current_index = -1

    def select_server(self):
        self.current_index = (self.current_index + 1)%len(self.servers)
        return self.servers[self.current_index]
    
    def update(self, *arg):
        pass


# least connections policy
class LeastConnections:
    def __init__(self, servers):
        self.servers = servers
        self.connections_per_server = {}
        for i in range(len(self.servers)):
            self.connections_per_server[self.servers[i]] = 0

    def select_server(self):
        min_connections = self.servers[0]
        for i in range(len(self.servers)):
            if self.connections_per_server[self.servers[i]] < self.connections_per_server[min_connections]:
                min_connections = self.servers[i]
        self.connections_per_server[min_connections] += 1
        return min_connections

    def update(self, *arg):
        if arg[0] in self.servers:
            if self.connections_per_server[arg[0]] != 0:
                self.connections_per_server[arg[0]] -= 1


# least response time
class LeastResponseTime:
    def __init__(self, servers):
        self.servers = servers
        self.average_response_time = {server: 0 for server in servers}
        self.last_request_time = {server: 0 for server in servers}
        self.response_time_history = {server: [] for server in servers}
        self.current_index = -1

    def select_server(self):
        least_response_time = min([variable for variable in self.average_response_time.values()])
        for _ in range(len(self.servers)):
            self.current_index = (self.current_index + 1) % len(self.servers)
            if self.average_response_time[self.servers[self.current_index]] == least_response_time:
                break
        return self.servers[self.current_index]

    def update(self, *args):
        print(self.last_request_time)
        for server in args:
            self.last_request_time[server] = time.time() - self.last_request_time[server]
            self.response_time_history[server].append(self.last_request_time[server])
            soma = 0
            for server_time in self.response_time_history[server]:
                soma += server_time
            self.average_response_time[server] = soma/len(self.response_time_history[server])

POLICIES = {
    "N2One": N2One,
    "RoundRobin": RoundRobin,
    "LeastConnections": LeastConnections,
    "LeastResponseTime": LeastResponseTime
}

class SocketMapper:
    def __init__(self, policy):
        self.policy = policy
        self.map = {}

    def add(self, client_sock, upstream_server):
        client_sock.setblocking(False)
        sel.register(client_sock, selectors.EVENT_READ, read)
        upstream_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        upstream_sock.connect(upstream_server)
        upstream_sock.setblocking(False)
        sel.register(upstream_sock, selectors.EVENT_READ, read)
        logger.debug("Proxying to %s %s", *upstream_server)
        self.map[client_sock] =  upstream_sock

    def delete(self, sock):
        paired_sock = self.get_sock(sock)
        sel.unregister(sock)
        sock.close()
        sel.unregister(paired_sock)
        paired_sock.close()
        if sock in self.map:
            self.map.pop(sock)
        else:
            self.map.pop(paired_sock)

    def get_sock(self, sock):
        for client, upstream in self.map.items():
            if upstream == sock:
                return client
            if client == sock:
                return upstream
        return None
    
    def get_upstream_sock(self, sock):
        return self.map.get(sock)

    def get_all_socks(self):
        """ Flatten all sockets into a list"""
        return list(sum(self.map.items(), ())) 

def accept(sock, mask):
    client, addr = sock.accept()
    logger.debug("Accepted connection %s %s", *addr)
    mapper.add(client, policy.select_server())

def read(conn,mask):
    data = conn.recv(4096)
    if len(data) == 0: # No messages in socket, we can close down the socket
        mapper.delete(conn)
    else:
        mapper.get_sock(conn).send(data)


def main(addr, servers, policy_class):
    global policy
    global mapper

    # register handler for interruption 
    # it stops the infinite loop gracefully
    signal.signal(signal.SIGINT, graceful_shutdown)

    policy = policy_class(servers)
    mapper = SocketMapper(policy)

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind(addr)
    sock.listen()
    sock.setblocking(False)

    sel.register(sock, selectors.EVENT_READ, accept)

    try:
        logger.debug("Listening on %s %s", *addr)
        while not done:
            events = sel.select(timeout=1)
            for key, mask in events:
                if(key.fileobj.fileno()>0):
                    callback = key.data
                    callback(key.fileobj, mask)
                
    except Exception as err:
        logger.error(err)

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Pi HTTP server')
    parser.add_argument('-a', dest='policy', choices=POLICIES)
    parser.add_argument('-p', dest='port', type=int, help='load balancer port', default=8080)
    parser.add_argument('-s', dest='servers', nargs='+', type=int, help='list of servers ports')
    args = parser.parse_args()
    
    servers = [('localhost', p) for p in args.servers]
    
    main(('127.0.0.1', args.port), servers, POLICIES[args.policy])
