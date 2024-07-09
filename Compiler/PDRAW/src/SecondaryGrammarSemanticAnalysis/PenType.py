from Type import Type

class PenType(Type):
    def __init__(self):
        super().__init__("pen")

    def subtype(self, other):
        return (
            other.name == "position" or other.name == "color" or super().subtype(other)
        )
