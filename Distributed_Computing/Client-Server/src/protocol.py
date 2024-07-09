"""Protocol for chat server - Computação Distribuida Assignment 1."""
import json
from datetime import datetime
from socket import socket


class Message:
    """Message Type."""
    def __init__(self):
        self.dicionario = None

    def readMessage(self, message):
        self.dicionario = json.loads(message)
    
    def jsonMessage(self):
        return json.dumps(self.dicionario)
    
    def __str__(self) -> str:
        return self.jsonMessage()

    
class JoinMessage(Message):
    """Message to join a chat channel."""
    def __init__(self, channel):
        self.dicionario = {"command": "join", "channel": channel}


class RegisterMessage(Message):
    """Message to register username in the server."""
    def __init__(self, user):
        self.dicionario = {"command": "register", "user": user}
    
class TextMessage(Message):
    """Message to chat with other clients."""
    def __init__(self, message, channel):
        if channel:
            self.dicionario = {"command": "message", "message": message, "channel": channel,"ts": int(datetime.now().timestamp())}
        else:
            self.dicionario = {"command": "message", "message": message, "ts": int(datetime.now().timestamp())}
        
class CDProto:
    """Computação Distribuida Protocol."""

    @classmethod
    def register(cls, username: str) -> RegisterMessage:
        """Creates a RegisterMessage object."""
        return RegisterMessage(username)

    @classmethod
    def join(cls, channel: str) -> JoinMessage:
        """Creates a JoinMessage object."""
        return JoinMessage(channel)

    @classmethod
    def message(cls, message: str, channel: str = None) -> TextMessage:
        """Creates a TextMessage object."""
        return TextMessage(message, channel)

    @classmethod
    def send_msg(cls, connection: socket, msg: Message):
        """Sends through a connection a Message object."""
        message = msg.jsonMessage().encode()
        size = len(message).to_bytes(2, 'big')
        connection.send(size+message)

    @classmethod
    def recv_msg(cls, connection: socket) -> Message:
        """Receives through a connection a Message object."""
        size_bytes = connection.recv(2)
        size = int.from_bytes(size_bytes, byteorder='big')
        if size == 0:
            return None
    
        msg_byte = connection.recv(size)
        msg = msg_byte.decode()
        try:
            message = json.loads(msg)
            if message["command"] == "join":
                return JoinMessage(message["channel"])
            elif message["command"] == "message":
                return TextMessage(message["message"], message.get("channel"))
            elif message["command"] == "register":
                return RegisterMessage(message["user"])
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
