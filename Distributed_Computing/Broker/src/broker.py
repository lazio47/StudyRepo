"""Message Broker"""
import enum
import socket
from typing import Dict, List, Any, Tuple
import selectors
import json
import pickle
import xml.etree.ElementTree as ET

from src.protocol import CDProto, CDProtoBadFormat, CancelMessage, SubscribeMessage, TextMessage

class Serializer(enum.Enum):
    """Possible message serializers."""

    JSON = 0
    XML = 1
    PICKLE = 2


class Broker:
    """Implementation of a PubSub Message Broker."""

    def __init__(self):
        """Initialize broker."""
        self.canceled = False
        self.host = "localhost"
        self.port = 5000

        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.bind((self.host, self.port))
        self.sock.listen(100)
        self.sock.setblocking(False)
        self.sel = selectors.DefaultSelector()
        self.protocolo = CDProto()

        self.topics = {}
        self.subscriptions = {}
        self.format_map = {} 
        self.canceled = False


    def list_topics(self) -> List[str]:
        """Returns a list of strings containing all topics containing values."""
        tops_list = []
        for topic in self.topics.keys():
            if self.topics[topic] != "":
                tops_list.append(topic)
        return tops_list

    def get_topic(self, topic):
        """Returns the currently stored value in topic."""
        return self.topics.get(topic, None)

    def put_topic(self, topic, value):
        """Store in topic the value."""
        self.subscriptions.setdefault(topic, [])
        self.topics[topic] = value

    def list_subscriptions(self, topic: str) -> List[Tuple[socket.socket, Serializer]]:
        """Provide list of subscribers to a given topic."""

        if topic in self.subscriptions:
            return self.subscriptions[topic]
        return []


    def subscribe(self, topic: str, address: socket.socket, _format: Serializer = None):
        """Subscribe to topic by client in address."""
        sub = (address, _format)
        if sub not in self.format_map:
            self.format_map[sub] = _format
        if topic not in self.subscriptions:
            self.subscriptions[topic] = [] 
        self.subscriptions[topic].append(sub)
        if topic in self.topics:
            last_msg = self.topics[topic]
            mesg = self.protocolo.message(topic, _format, last_msg)
            self.protocolo.send_msg(address, mesg, _format)    

    def unsubscribe(self, topic, address):
        """Unsubscribe to topic by client in address."""
        for subscriber in self.subscriptions[topic]:
            if address == subscriber[0]:
                self.subscriptions[topic].remove(subscriber)


    def accept(self, sock, mask):
        conn, addr = sock.accept()   
        self.sel.register(conn, selectors.EVENT_READ, self.read)


    def handle_text_message(self, message: TextMessage):
        """Handle incoming text message."""
        if message.format != 1:
            topic = message.dicionario["topic"]
            value = message.dicionario["message"]
        else:
            topic = ET.XML.SubElement(message, "topic").text
            value = ET.XML.SubElement(message, "message").text
        self.topics[topic] = value
        for sub_topic in self.subscriptions.keys():
            if sub_topic in topic:
                for subscriber, _format in self.subscriptions[sub_topic]:
                    try:
                        new_message = self.protocolo.message(topic, _format, value)
                        self.protocolo.send_msg(subscriber, new_message, _format)
                    except Exception as e:
                        print(f"Error sending message to {subscriber}: {e}")


    def read(self, conn, mask):
        try:
            message = self.protocolo.recv_msg(conn)
            if message:
                if message.format == Serializer.XML.value:
                    msg = message.readMessage(message.dicionario)
                else:
                    msg = message.dicionario
                if msg["command"] == "subscribe":
                    self.subscribe(msg["topic"], conn, message.format)
                elif msg["command"] == "cancel":
                    self.unsubscribe(msg["topic"], conn)
                    print("cancelled ", msg["topic"])
                elif msg["command"] == "message":
                    self.handle_text_message(message)
        except CDProtoBadFormat as e:
            print("Invalid message format:", e.original_msg)
        except Exception as e:
            print("Error:", e)

    def run(self):
        """Run until canceled."""
        self.sel.register(self.sock, selectors.EVENT_READ, self.accept)
        while not self.canceled:
            events = self.sel.select()
            for key, mask in events:
                callback = key.data
                callback(key.fileobj, mask)
