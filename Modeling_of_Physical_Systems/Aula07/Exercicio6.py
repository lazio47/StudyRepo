# -*- coding: utf-8 -*-
"""
Created on Thu Apr 13 16:15:11 2023

@author: lazio
"""


import numpy as np
import matplotlib.pyplot as plt

#consts
k = 1# N/m
m = 1 # massa, kg
A = 4# aolitude, m

#tempo
dt = 0.02
t0 = 0
tf = 40
Nt = int(np.ceil((tf-t0) / dt ) + 1)
t = np.linspace(t0, tf, Nt)

#Euler

#i
x = np.zeros(Nt)
vx = np.zeros(Nt)
ax = np.zeros(Nt)

x[0] = A
vx[0] = 0
E = .5 * k*x[0]**2+ .5*m*vx[0]**2

for i in range(Nt-1):
    ax[i]= -k/m * x[i]
    vx[i+1] = vx[i] + ax[i]*dt
    x[i+1] = x[i] + vx[i] * dt
    E = np.append(E, .5 * k * x[i] ** 2 + .5 * m * vx[i] ** 2)


#solução analitica
omega = np.sqrt(k/m)
vxt = -A*omega*np.sin(omega*t)
xt = A*np.cos(omega*t)
Et = .5*k*xt**2+ .5*m*vxt**2

#Velocidades
plt.figure()
plt.plot(t,vx,label='Euler')
plt.plot(t,vxt,label='Analítico')
plt.legend()
plt.xlabel('t [s]')
plt.title('Velocidade (m/s)')
plt.grid()
plt.show()

#Posições 
plt.figure()
plt.plot(t,x,label='Euler')
plt.plot(t,xt,label='Analítico')
plt.legend()
plt.xlabel('t [s]')
plt.title('Posição (m)')
plt.grid()
plt.show()

#Euler-Cromer

#i
x2 = np.zeros(Nt)
vx2 = np.zeros(Nt)
ax2 = np.zeros(Nt)

x2[0] = A
vx2[0] = 0
E2 = .5 * k*x2[0]**2+ .5*m*vx2[0]**2

for i in range(Nt-1):
    ax2[i]= -k/m * x2[i]
    vx2[i+1] = vx2[i] + ax2[i]*dt
    x2[i+1] = x2[i] + vx2[i+1] * dt
    E2 = np.append(E2, .5 * k * x2[i] ** 2 + .5 * m * vx2[i+1] ** 2)

#Velocidades
plt.figure()
plt.plot(t,vx2,label='Euler-Cromer')
plt.plot(t,vxt,label='Analítico')
plt.legend()
plt.xlabel('t [s]')
plt.title('Velocidade (m/s)')
plt.grid()
plt.show()

#Posições 
plt.figure()
plt.plot(t,x2,label='Euler-Cromer')
plt.plot(t,xt,label='Analítico')
plt.legend()
plt.xlabel('t [s]')
plt.title('Posição (m)')
plt.grid()
plt.show()


#Energias
plt.figure()
plt.plot(t,E2,label='Euler-Cromer')
plt.plot(t,E,label='Euler')
plt.plot(t,Et,label='Analítico')
plt.legend()
plt.xlabel('t [s]')
plt.title('Energia (J)')
plt.grid()
plt.show()


