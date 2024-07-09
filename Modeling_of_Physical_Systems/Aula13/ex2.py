# -*- coding: utf-8 -*-
"""
Created on Thu Jun  1 15:25:34 2023

@author: Giovanni Santos
"""

import matplotlib.pyplot as plt
import numpy as np

dt = 0.01
tf=100
t0=0
n = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, n)

m = 1
a = 0.25
k = 1
b = 0.05
F0 = 7.5
wf = 1

def Euler_Croner(x0, v0, F0):
    
    x = np.zeros(n)
    vx = np.zeros(n)
    ax = np.zeros(n)
    x[0] = x0
    vx[0] = v0
    
    for i in range(n-1):
        ax[i] = (-4*a*x[i]**3 -b*vx[i] + F0*np.cos(wf*t[i]))/m
        vx[i+1]= vx[i] + ax[i] * dt
        x[i+1] = x[i] + vx[i+1] * dt
    return x, vx;

x, v = Euler_Croner(3, 0, F0)
x2, v2 = Euler_Croner(3.003, 0, F0)

#a) e b)
plt.figure()
plt.plot(t, x, label="x0=3")
plt.plot(t, x2, label="x0=3.003")
plt.title("Movimento/Alínea a) e b)")
plt.legend()
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.show()

#c)
plt.figure()
plt.plot(x, v, label="x0=3")
plt.plot(x2, v2, label="x0=3.003")
plt.title("Velocidade x Posição/Alínea c)")
plt.legend()
plt.xlabel("v (m/s)")
plt.ylabel("x (m)")
plt.show()

#d)
x3, v3 = Euler_Croner(3, 0, 0.5)
x4, v4 = Euler_Croner(3.003, 0, 0.5)

plt.figure()
plt.plot(t, x3, label="x0=3")
plt.plot(t, x4, label="x0=3.003")
plt.title("Movimento/Alínea d)")
plt.legend()
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.show()

plt.figure()
plt.plot(x3, v3, label="x0=3")
plt.plot(x4, v4, label="x0=3.003")
plt.title("Velocidade x Posição/Alínea d)")
plt.legend()
plt.xlabel("v (m/s)")
plt.ylabel("x (m)")
plt.show()
