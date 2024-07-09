"""Middleware to communicate with PubSub Message Broker."""
from collections.abc import Callable
from enum import Enum
import enum
import json
import pickle
from queue import LifoQueue, Empty
import selectors
import socket
from typing import Any
from xml.etree.ElementTree import XML
from xml.etree import ElementTree as ET 

from src.protocol import CDProto

class Serializer(enum.Enum):
    """Possible message serializers."""

    JSON = 0
    XML = 1
    PICKLE = 2

class MiddlewareType(Enum):
    """Middleware Type."""

    CONSUMER = 1
    PRODUCER = 2


class Queue:
    """Representation of Queue interface for both Consumers and Producers."""

    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        """Create Queue."""
        self.topic = topic
        self._type = _type
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.connect(("localhost", 5000))
        self.protocolo = CDProto()

    def push(self, value):
        """Sends data to broker."""
        msg = self.protocolo.message(self.topic, 0, value)
        self.protocolo.send_msg(self.sock, msg, msg.format)

    def pull(self) -> (str, Any):
        """Receives (topic, data) from broker.

        Should BLOCK the consumer!"""
        msg = self.protocolo.recv_msg(self.sock)
        if (msg.format != 1):
            return (msg.dicionario["topic"], msg.dicionario["message"])
        else:
            root = ET.XML(msg.dicionario)
            topic = root.find("topic").text
            message = root.find("message").text
            return (topic, message)


    def list_topics(self, callback: Callable):
        """Lists all topics available in the broker."""
        msg = self.protocolo.list(0)
        self.protocolo.send_msg(self.sock, msg, 0)

    def cancel(self):
        """Cancel subscription."""
        msg = self.protocolo.cancel(self.topic, 0)
        self.protocolo.send_msg(self.sock, msg, 0)

    def __str__(self):
        return "Topic: "+self.topic+"; Type: "+self._type


class JSONQueue(Queue):
    """Queue implementation with JSON based serialization."""
    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        super().__init__(topic, _type)
        if self._type == MiddlewareType.CONSUMER:
            msg = self.protocolo.subscribe(self.topic, 0)
            self.protocolo.send_msg(self.sock, msg, 0)


class XMLQueue(Queue):
    """Queue implementation with XML based serialization."""
    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        super().__init__(topic, _type)
        if self._type == MiddlewareType.CONSUMER:
            msg = self.protocolo.subscribe(self.topic, 1)
            self.protocolo.send_msg(self.sock, msg, 1)
    
    def push(self, value):
        """Sends data to broker."""
        msg = self.protocolo.message(self.topic, 1, value)
        self.protocolo.send_msg(self.sock, msg, msg.format)


    def list_topics(self, callback: Callable):
        """Lists all topics available in the broker."""
        msg = self.protocolo.list(1)
        self.protocolo.send_msg(self.sock, msg, 1)

    def cancel(self):
        """Cancel subscription."""
        msg = self.protocolo.cancel(self.topic, 1)
        self.protocolo.send_msg(self.sock, msg, 1)


class PickleQueue(Queue):
    """Queue implementation with Pickle based serialization."""
    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        super().__init__(topic, _type)
        if self._type == MiddlewareType.CONSUMER:
            msg = self.protocolo.subscribe(self.topic, 2)
            self.protocolo.send_msg(self.sock, msg, 2)

    def push(self, value):
        """Sends data to broker."""
        msg = self.protocolo.message(self.topic, 2, value)
        self.protocolo.send_msg(self.sock, msg, msg.format)
    
    def list_topics(self, callback: Callable):
        """Lists all topics available in the broker."""
        msg = self.protocolo.list(2)
        self.protocolo.send_msg(self.sock, msg, 2)

    def cancel(self):
        """Cancel subscription."""
        msg = self.protocolo.cancel(self.topic, 2)
        self.protocolo.send_msg(self.sock, msg, 2)
