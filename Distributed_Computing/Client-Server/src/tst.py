import json
from protocol import (
    CDProto,
    TextMessage,
    JoinMessage,
    RegisterMessage,
    CDProtoBadFormat,
)

p = CDProto()

class mock_socket:
    def __init__(self, content):
        self.g = self.gen_stream(content)

    def gen_stream(self, content):
        yield len(content).to_bytes(2, "big")
        yield content

    def recv(self, n):
        return next(self.g)


def test_recv():

    assert isinstance(
        CDProto.recv_msg(mock_socket(b'{"command": "register", "user": "student"}')),
        RegisterMessage,
    )

    assert isinstance(
        CDProto.recv_msg(mock_socket(b'{"command": "join", "channel": "#cd"}')),
        JoinMessage,
    )
    assert isinstance(
        CDProto.recv_msg(
            mock_socket(
                b'{"command": "message", "message": "Hello World", "ts": 1615852800}'
            )
        ),
        TextMessage,
    )


#print(str(p.register("student")))
#print(str(p.join("#cd")))
#print(str(p.message("Hello World")))
mg = json.loads((b'{"command": "register", "user": "student"}'))
print(">>>>>>>>",mg, "  ", mg["command"])
test_recv()

