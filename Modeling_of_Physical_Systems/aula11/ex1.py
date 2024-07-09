# -*- coding: utf-8 -*-
"""
Created on Thu May 18 16:12:45 2023

@author: Giovanni Santos
"""

import matplotlib.pyplot as plt
import numpy as np

#a)
dt = 0.01
tf=20
t0=0
n = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, n)

m = 1
k = 1

x = np.zeros(n)
vx = np.zeros(n)
ax = np.zeros(n)

x[0] = 4
vx[0] = 0



for i in range(n-1):
    ax[i] = -k*x[i]/m
    vx[i+1]= vx[i] + ax[i] * dt
    x[i+1] = x[i] + vx[i+1] * dt
    


plt.figure()
plt.plot(t,x)
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.show()


#b)
def maxminv(x0,x1,x2,y0,y1,y2):
    # Máximo ou mínimo usando o polinómio de Lagrange
    # Dados (input): (x0,y0), (x1,y1) e (x2,y2)
    # Resultados (output): xm, ymax
    xab=x0-x1
    xac=x0-x2
    xbc=x1-x2
    a=y0/(xab*xac)
    b=-y1/(xab*xbc)
    c=y2/(xac*xbc)
    xmla=(b+c)*x0+(a+c)*x1+(a+b)*x2
    xm=0.5*xmla/(a+b+c)
    xta=xm-x0
    xtb=xm-x1
    xtc=xm-x2
    ymax=a*xtb*xtc+b*xta*xtc+c*xta*xtb
    return xm, ymax


ext = [] #(t, x)
extremos = 0
i = 1
while True:
    if (x[i-1] > x[i] < x[i+1]):
        ext.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos += 1
    if (x[i-1] < x[i] > x[i+1]):
        ext.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos +=1 
    if extremos==4: break
    i+=1
    
A = (ext[1][1] - ext[0][1])/2
T = ext[2][0] - ext[0][0]

print("Amplitude: ", A)
print("Periodo: ", T)
print("Analítico:")
print("Amplitude: ", x[0]/np.cos(0))
print("Perido: ", 2*np.pi*np.sqrt(m/k))
        
#d)
Em= 0.5*m*vx**2 + 0.5*k*x**2

plt.figure()
plt.plot(t,Em)
plt.xlabel("t (s)")
plt.ylabel("Em (j)")
plt.show()
        
