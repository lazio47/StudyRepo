class Symbol:
    def __init__(self, name, type):
        assert name is not None
        assert type is not None

        self.name = name
        self.type = type
        self.value = None

    def set_value(self, value):
        assert value is not None
        self.value = value

    def get_name(self):
        return self.name

    def get_type(self):
        return self.type

    def get_value(self):
        return self.value
