# -*- coding: utf-8 -*-
"""
Created on Thu May  4 16:14:06 2023

@author: lazio
"""

import numpy as np
import math
import matplotlib.pyplot as plt

g = 9.80
m = 0.057

dt = 0.001
t0 = 0
tf = 0.8
N = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, N)

x = np.zeros((N))
y = np.zeros((N))
vx = np.zeros((N))
vy = np.zeros((N))
v = np.zeros((N))

#a)Sem resistencia do ar

v0 = 100/3.6
ang = math.radians(10)

[x[0], y[0], vx[0], vy[0], v[0]] = [0, 0, v0*np.cos(ang), v0*np.sin(ang), v0]

for i in range(N-1):
    vx[i+1]=vx[i]
    vy[i+1]=vy[i]-g*dt
    x[i+1]=x[i]+vx[i+1]*dt
    y[i+1]=y[i]+vy[i+1]*dt

Em = .5*m*np.sqrt(vx**2+vy**2)**2+m*g*y

Em2 = .5*m*(vx**2+vy**2)+m*g*y

plt.figure()
plt.plot(t,y)
plt.title('y(t) sem resistencia do ar' )
plt.show()
plt.figure()
plt.plot(t,Em)
plt.title('E' )
plt.show()


#b, c) Com resistencia
g = 9.80
m = 0.057

dt = 0.001
t0 = 0
tf = 0.4
N = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, N) 

x = np.zeros((N))
y = np.zeros((N))
vx = np.zeros((N))
vy = np.zeros((N))
ax = np.zeros((N))
ay = np.zeros((N))
v = np.zeros((N))

vt = 100/3.6
D = g/(vt**2)
v0 = 100/3.6
ang = math.radians(10)

[x[0], y[0], vx[0], vy[0]] = [0, 0, v0*np.cos(ang), v0*np.sin(ang)]

Fresx=[0]
Fresy=[0]

for i in range(N-1):
    ax[i+1] = -D*np.sqrt(vx[i]**2+vy[i]**2)*vx[i]
    ay[i+1] = -g - D*np.sqrt(vx[i]**2+vy[i]**2)*vy[i]
    vx[i+1]=vx[i]+ax[i]*dt
    vy[i+1]=vy[i]+ay[i]*dt
    x[i+1]=x[i]+vx[i+1]*dt
    y[i+1]=y[i]+vy[i+1]*dt
    Fresx.append(-m*D*(np.sqrt(vx[i]**2+vy[i]**2))*vx[i+1])
    Fresy.append(-m*D*(np.sqrt(vx[i]**2+vy[i]**2))*vy[i+1])

Wrect = sum(Fresx[:N]*vx[:N])*dt + sum(Fresy[:N]*vy[:N])*dt
Wx = (Fresx[0]*vx[0]+Fresx[-1]*vx[-1])*0.5*dt + sum(Fresx[1:N-1]*vx[1:N-1])*dt
Wy = (Fresy[0]*vy[0]+Fresy[-1]*vy[-1])*0.5*dt + sum(Fresy[1:N-1]*vy[1:N-1])*dt
W = Wx+Wy

print("W pelo método do trapézio ",W)
print("W pelo método dos retângulos ",Wrect)

Em = .5*m*np.sqrt(vx**2+vy**2)**2+m*g*y

plt.figure()
plt.plot(t,y)
plt.title('y(t) com resistencia do ar' )
plt.show()
plt.figure()
plt.plot(t,Em)
plt.title('E' )
plt.show()

Wexact = -4.97685226

Erro = abs(W-Wexact)
print(Erro)
print(np.log(Erro)/np.log(dt))
Erro = abs(Wrect-Wexact)
print(Erro)
print(np.log(Erro)/np.log(dt))

plt.loglog(dt, abs(W-Wexact), 'o')
plt.loglog(dt, abs(Wrect-Wexact), 'x')
