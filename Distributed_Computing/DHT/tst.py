from utils import contains

assert contains(100, 300, 200)
assert contains(100, 300, 300)
assert not contains(100, 300, 100)
assert not contains(100, 300, 400)
assert not contains(100, 300, 0)
assert not contains(100, 300, 900)

assert contains(800, 300, 900)
assert contains(800, 300, 200)
assert contains(800, 300, 300)
assert not contains(800, 300, 700)
assert not contains(800, 300, 400)
print("Tudo passou!")