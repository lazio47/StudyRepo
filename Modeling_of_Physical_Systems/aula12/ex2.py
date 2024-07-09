# -*- coding: utf-8 -*-
"""
Created on Thu May 25 16:37:47 2023

@author: Giovanni Santos
"""

import matplotlib.pyplot as plt
import numpy as np


dt = 0.01
tf=3
t0=0
n = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, n)

g=9.81
vt = 6.8


y = np.zeros(n)
vy = np.zeros(n)
y[0] = 0
vy[0] = 0

def acel(t,g,vt,v):
    return g - (g / vt **2) * np.abs(v) * v

def rk4(t,x,v,acel,**kwargs):
    N = len(t)
    dt = t[1]-t[0]
    g, vt = [kwargs["g"],kwargs["vt"]]
    for i in range(N-1):
        a1=acel(t[i],g,vt,v[i])
        c1v=a1*dt
        c1x=v[i]*dt
        a2=acel(t[i]+dt/2,g,vt,v[i]+c1v/2.)
        c2v=a2*dt
        c2x=(v[i]+c1v/2.)*dt # predicto: v(t+dt) * dt
        a3=acel(t[i]+dt/2,g,vt,v[i]+c2v/2.)
        c3v=a3*dt
        c3x=(v[i]+c2v/2.)*dt
        a4=acel(t[i]+dt,g,vt,v[i]+c3v)
        c4v=a4*dt
        c4x=(v[i]+c3v)*dt
        x[i+1]=x[i]+(c1x+2.*c2x+2.*c3x+c4x)/6.
        v[i+1]=v[i]+(c1v+2.*c2v+2.*c3v+c4v)/6.
    return x,v
        
y, v = rk4(t, y, vy,acel, g=9.81, vt=6.8)

plt.figure()
plt.plot(t,y)
plt.xlabel("t (s)")
plt.ylabel("y (m)")
plt.grid()
plt.show()

x = np.zeros(n)
v = np.zeros(n)
a = np.zeros(n)

for i in range(n-1):
    a1 = (g - (g / vt **2) * np.abs(v[i]) * v[i])*dt
    c1 = v[i]*dt
    c1v = a1*dt
    a2 = (g - (g / vt **2) * np.abs(v[i]) * v[i]+c1v/2)
    c2 = (v[i]+c1v/2.)*dt
    c2v = a2*dt
    a3 = (g - (g / vt **2) * np.abs(v[i]) * v[i]+c2v/2)*dt
    c3v=a3*dt
    c3=(v[i]+c2v/2.)*dt
    a4= (g - (g / vt **2) * np.abs(v[i]) * v[i]+c3v)*dt
    c4v=a4*dt
    c4=(v[i]+c3v)*dt
    
    x[i+1]=x[i]+(c1+ 2*c2+ 2*c3+c4)/6
    v[i+1]=v[i]+(c1v+ 2*c2v+ 2*c3v+c4v)/6
    
#plt.figure()
#plt.plot(t,v)
#plt.xlabel("t (s)")
#plt.ylabel("y (m)")
#plt.grid()
#plt.show()
