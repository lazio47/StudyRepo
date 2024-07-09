# -*- coding: utf-8 -*-
"""
Created on Thu May 18 17:08:10 2023

@author: lazio
"""

import numpy as np
import matplotlib.pyplot as plt

# a)
x = np.linspace(-6, 6, 100)
k = 1
xeq = 1.5
Ep = 1/2 * k * (x**2 - (xeq)**2)**2

plt.figure()
plt.plot(x, Ep)
plt.ylim(0, 10)
plt.ylabel("Enegia Potencial [J]")
plt.xlabel("x [m]")
plt.grid()
plt.show()