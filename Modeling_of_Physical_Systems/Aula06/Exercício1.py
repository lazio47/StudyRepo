# -*- coding: utf-8 -*-
"""
Created on Thu Mar 23 16:09:04 2023

@author: lazio
"""

import numpy as np
import matplotlib.pyplot as plt

#Alínea a)
g = 9.81
v0 = 100*1000/3600
vT = 100*1000/3600
D = g/vT**2

y0, x0, v0x, v0y = [0, 0, v0*np.cos(np.radians(10)), v0*np.sin(np.radians(10))]

t0, tf = [0, 1]
dt = 0.00001
Nt = int(np.ceil(tf-t0)/dt)+1
t = np.linspace(t0, tf, Nt)

x = np.zeros(Nt)
y = np.zeros(Nt)
vx = np.ones(Nt)*v0x
vy = np.zeros(Nt)

x2 = np.zeros(Nt)
y2 = np.zeros(Nt)
vx2 = np.ones(Nt)*v0x
vy2 = np.zeros(Nt)
ax2 = np.ones(Nt)
ay2 = np.zeros(Nt)

x[0], y[0], vx[0], vy[0] = [x0,y0,v0x,v0y]
x2[0], y2[0], vx2[0], vy2[0], ax2[0], ay2[0] = [x0,y0,v0x,v0y, 0, -g]

for i in range(Nt-1):
    y[i+1] = y[i]+vy[i]*dt
    vy[i+1] = vy[i]+(-g)*dt
    x[i+1] = x[i]+vx[i+1]*dt
    ax2[i+1] = -D*np.sqrt(vx2[i]**2+vy2[i]**2)*vx2[i]
    ay2[i+1] = -g-D*np.sqrt(vx2[i]**2+vy2[i]**2)*vy2[i]
    vx2[i+1] = vx2[i] + ax2[i]*dt
    x2[i+1] = x2[i]+vx2[i]*dt
    vy2[i+1] = vy2[i] + ay2[i]*dt
    y2[i+1] = y2[i]+vy2[i]*dt
    
plt.figure()
plt.plot(x, y, label= "s/ resistencia do ar")
plt.plot(x2, y2, label= "c/ resistencia do ar")
plt.xlabel("x (m)")
plt.ylabel("y (m)")
plt.grid()
plt.legend()


#Alínea b)
