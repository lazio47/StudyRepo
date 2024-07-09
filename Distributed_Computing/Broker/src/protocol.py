"""Protocol for chat server - Computação Distribuida Assignment 1."""
import enum
import json
from datetime import datetime
import pickle
from socket import socket
from xml.etree.ElementTree import XML
from xml.etree import ElementTree as ET 

class Serializer(enum.Enum):
    """Possible message serializers."""

    JSON = 0
    XML = 1
    PICKLE = 2

class Message:
    """Message Type."""
    def __init__(self):
        self.dicionario = None
        self.format = None

    def readMessage(self, message):
        # if message.format == Serializer.JSON:
        #     return json.loads(message)
        # elif message.format == Serializer.PICKLE:
        #     return pickle.loads(message)
        # else:
        message = message.decode('utf-8')
        msg = {}
        root = ET.fromstring(message)
        for sub_element in root:
            msg[sub_element.tag] = sub_element.text
        return msg
    
    
    def encodeMessage(self, _format):
        if _format == Serializer.JSON.value:
            return json.dumps(self.dicionario).encode('utf-8')
        elif _format == Serializer.PICKLE.value:
            return pickle.dumps(self.dicionario)
        elif _format == Serializer.XML.value:
            return self.dicionario
        
    def __str__(self) -> str:
        return self.encodeMessage(self.format)

    
class SubscribeMessage(Message):
    """Message to register username in the server."""
    def __init__(self, topic, _format):
        self.format = _format
        if(_format == Serializer.XML.value):
            msg = ET.Element("root")
            command = ET.SubElement(msg, "command")
            command.text = "subscribe"
            topic_elem = ET.SubElement(msg, "topic")
            topic_elem.text = topic
            self.dicionario = ET.tostring(msg, encoding='utf-8')
        else:
            self.dicionario = {"command": "subscribe", "topic": topic}


class ListMessage(Message):
    """Message to list topics."""
    def __init__(self, _format):
        self.format = _format
        if _format == Serializer.XML.value:
            msg = ET.Element("root") 
            command = ET.SubElement(msg, "command") 
            command.text = "list_topics" 
            self.dicionario = ET.tostring(msg, encoding='utf-8')
        else:
            self.dicionario = {"command": "list_topics"}

class CancelMessage(Message):
    """Message to register username in the server."""
    def __init__(self, topic, _format):
        self.format = _format
        if _format == Serializer.XML.value:
            msg = ET.Element("root")
            command = ET.SubElement(msg, "command") 
            command.text = "cancel"
            topic_elem = ET.SubElement(msg, "topic")
            topic_elem.text = topic
            self.dicionario = ET.tostring(msg, encoding='utf-8')
        else:
            self.dicionario = {"command": "cancel", "topic": topic}
    
class TextMessage(Message):
    """Message to chat with other clients."""
    def __init__(self, topic, value, _format):
        self.format = _format
        if _format == Serializer.XML.value:
            msg = ET.Element("root")
            command = ET.SubElement(msg, "command")
            command.text = "message"
            data = ET.SubElement(msg, "message") 
            data.text = str(value)
            topic_elem = ET.SubElement(msg, "topic")
            topic_elem.text = str(topic)
            self.dicionario = ET.tostring(msg, encoding='utf-8')
        else:
            self.dicionario = {"command": "message", "message": value, "topic": topic}
        
class CDProto:
    """Computação Distribuida Protocol."""

    @classmethod
    def subscribe(cls, topic: str, _format) -> SubscribeMessage:
        """Creates a SubscribeMessage object."""
        return SubscribeMessage(topic, _format)
    
    @classmethod
    def list(cls, _format) -> ListMessage:
        """Creates a SubscribeMessage object."""
        return ListMessage( _format)

    @classmethod
    def cancel(cls, topic: str, _format) -> CancelMessage:
        """Creates a CancelMessage object."""
        return CancelMessage(topic, _format)

    @classmethod
    def message(cls, topic: str, _format, value: str = None) -> TextMessage:
        """Creates a TextMessage object."""
        return TextMessage(topic, value, _format)

    @classmethod
    def send_msg(cls, connection: socket, msg: Message, _format=Serializer.JSON):
        """Sends through a connection a Message object."""
        connection.send(_format.to_bytes(2, 'big'))
        message = msg.encodeMessage( _format)
        size = len(message).to_bytes(2, 'big')
        connection.send(size+message)

    @classmethod
    def recv_msg(cls, connection: socket) -> Message:
        """Receives through a connection a Message object."""
        format_bytes = connection.recv(2)
        format = int.from_bytes(format_bytes, byteorder='big')
        size_bytes = connection.recv(2)
        size = int.from_bytes(size_bytes, byteorder='big')
        if size == 0:
            return None
    
        msg_byte = connection.recv(size)
        try:
            if format == Serializer.JSON.value:
                msg = msg_byte.decode()
                message = json.loads(msg)
                if message["command"] == "subscribe":
                    return SubscribeMessage(message["topic"], Serializer.JSON.value)
                elif message["command"] == "cancel":
                    return CancelMessage(message["topic"], Serializer.JSON.value)
                elif message["command"] == "message":
                    return TextMessage(message["topic"], message["message"], Serializer.JSON.value)
            elif format == Serializer.PICKLE.value:
                # msg = msg_byte.decode()
                message = pickle.loads(msg_byte)
                if message["command"] == "subscribe":
                    return SubscribeMessage(message['topic'], Serializer.PICKLE.value)
                elif message["command"] == "cancel":
                    return CancelMessage(message["topic"], Serializer.PICKLE.value)
                elif message["command"] == "message":
                    return TextMessage(message["topic"], message["message"], Serializer.PICKLE.value)
            elif format == Serializer.XML.value:
                msg = Message()
                message = msg.readMessage( msg_byte)
                if message["command"] == "subscribe":
                    return SubscribeMessage(message["topic"], Serializer.XML.value)
                elif message["command"] == "cancel":
                    return CancelMessage(message["topic"], Serializer.XML.value)
                elif message["command"] == "message":
                    return TextMessage(message["topic"], message["message"], Serializer.XML.value)
        except json.decoder.JSONDecodeError:
            raise CDProtoBadFormat(msg_byte)
    

class CDProtoBadFormat(Exception):
    """Exception when source message is not CDProto."""

    def __init__(self, original_msg: bytes=None) :
        """Store original message that triggered exception."""
        self._original = original_msg

    @property
    def original_msg(self) -> str:
        """Retrieve original message as a string."""
        return self._original.decode("utf-8")
