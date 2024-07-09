# -*- coding: utf-8 -*-
"""
Created on Thu Mar  2 17:33:53 2023

@author: lazio
"""

import matplotlib.pyplot as plt
import numpy as np
import sympy as sy
import os

dir_figs = './figures/'
if not os.path.exists(dir_figs): 
    os.mkdir(dir_figs)
    
t0 = 0
tf = 4
dt = 0.01

Nt = int((tf-t0)/dt + 1)

t = np.linspace(t0, tf, Nt)
# t = np.arange(t0,tf+dt,dt)

vt = 6.80 # m/s
g = 9.8 # m/s^2

y = np.zeros((Nt,))


for i in range(Nt - 1):
    y[i+1] = (vt**2/g)*sy.log(sy.cosh((g*t[i])/vt))


plt.figure()
plt.plot(t, y, "k-")
plt.xlabel("t (s)")
plt.ylabel("y (m)")
plt.legend()
plt.grid()
plt.show()

#b e c

y,v,a,t,vt,g = sy.symbols('y,v,a,t,vt,g')

y = (vt**2/g)*sy.log(sy.cosh(g*t/vt))
y = y.subs([(g, 9.81),(vt,6.8)])
v = sy.diff(y,t)
a = sy.diff(v,t)

print('y= ', y)
print('v= ', v)
print('a= ',a)

v_plot = sy.lambdify(t,v,"numpy")
a_plot = sy.lambdify(t,a,"numpy")

ts = np.linspace(0,4,100)
plt.plot(ts,v_plot(ts),'-')
plt.xlabel('t')
plt.ylabel('v(t)')
plt.show()

ts = np.linspace(0,4,100)
plt.plot(ts,a_plot(ts),'-')
plt.xlabel('t')
plt.ylabel('a(t)')
plt.show()

#d

acheck = sy.symbols('acheck')
acheck = g - g*np.abs(v)*v/vt**2
print('a expected = ',acheck)
acheck = acheck.subs([(g, 9.8),(vt,6.8)])
acheck_plot = sy.lambdify(t,acheck,'numpy')

plt.plot(ts, a_plot(ts), 'r-')
plt.plot(ts,acheck_plot(ts),'k--')
plt.xlabel('t')
plt.ylabel('a(t)')
plt.show()

