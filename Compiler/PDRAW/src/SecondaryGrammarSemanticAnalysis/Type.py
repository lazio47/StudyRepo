class Type:
    def __init__(self, name):
        assert name is not None
        self.name = name

    def get_name(self):
        return self.name

    def subtype(self, other):
        return self.name == other.get_name()
