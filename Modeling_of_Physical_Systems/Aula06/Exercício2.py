# -*- coding: utf-8 -*-
"""
Created on Thu Mar 23 17:10:08 2023

@author: lazio
"""

import numpy as np
import matplotlib.pyplot as plt


r0 = np.array([[0,0,23.8]])
v0 = np.array([[24,5,-50]])
g = 9.81
vT = 100*1000/3600
D = g/vT**2

#consts
m = 0.45 #kg
raio = 0.11 #m
A = np.pi*raio**2
dens = 1.225
g = 9.81

#t
t0 = 0
tf = 0.5
dt = 0.01
Nt = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, Nt)

#velocidade angular
w = np.array([0, 400, 0])

#alocar zeros em t, v e a
r = np.vstack((r0,np.zeros((Nt-1, 3))))
v = np.vstack((v0,np.zeros((Nt-1, 3))))
a = np.zeros((Nt, 3))

#Met Euler. Aceleração afectada pela força de Magnus
for i in range(Nt-1):
    a[i,:] = .5/m*A*dens*raio*np.cross(w,v[i,:])+\
    np.array([0,-g,0])+\
    - D*np.linalg.norm(v[i,:])*v[i,:]
    v[i+1,:] = v[i,:]+a[i,:]*dt
    r[i+1,:] = r[i,:]+v[i,:]*dt



fig, ax = plt.subplots(1, 3)
ax[0].plot(t,r[:,0])
ax[0].set_xlabel("t [s]")
ax[0].set_title("x [m]")
ax[1].plot(t,r[:,1])
ax[1].set_xlabel("t [s]")
ax[1].set_title("y [m]")
ax[2].plot(t,r[:,2])
ax[2].set_xlabel("t [s]")
ax[2].set_title("z [m]")

ax = plt.figure().add_subplot(projection="3d")

ax.plot(r[:,0],r[:,2],r[:,1], label="trajetória bola")
ax.legend()

plt.show()