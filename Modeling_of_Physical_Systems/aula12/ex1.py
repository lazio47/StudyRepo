# -*- coding: utf-8 -*-
"""
Created on Thu May 25 15:14:03 2023

@author: Giovanni Santos
"""
import matplotlib.pyplot as plt
import numpy as np


dt = 0.001
tf=200
t0=0
n = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, n)

m = 1
k = 1
b = 0.05
F0 =  7.5
wf = 1
w0 = np.sqrt(k/m)

x = np.zeros(n)
vx = np.zeros(n)
ax = np.zeros(n)

#a)
x[0] = 4
vx[0] = 0

for i in range(n-1):
    ax[i] = (-k*x[i] -b*vx[i] + F0*np.cos(wf*t[i]))/m
    vx[i+1]= vx[i] + ax[i] * dt
    x[i+1] = x[i] + vx[i+1] * dt
    
plt.figure()
plt.plot(t,x)
plt.title("Alínea a)")
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.grid()
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



min = [] #(t, x)
max = []
extremos = 0
i = -1

while True:
    if (x[i-1] > x[i] < x[i+1]):
        min.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos += 1
        
    if (x[i-1] < x[i] > x[i+1]):
        max.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos +=1 
        
    if extremos==4: 
        break
    i-=1

A = (max[1][1] - min[0][1])/2
T = max[0][0] - max[1][0]
print("b)")
print("Amplitude: ", A)
print("Periodo: ", T)
print("\nAnalítico")
print("Amplitude: ", F0/m/(np.sqrt((wf**2 - w0**2)**2 + (b*wf/w0)**2)))
print("Periodo: ", 2*np.pi*np.sqrt(m/k))

#c)
x = np.zeros(n)
vx = np.zeros(n)
ax = np.zeros(n)

x[0] = -2
vx[0] = -4

for i in range(n-1):
    ax[i] = (-k*x[i] -b*vx[i] + F0*np.cos(wf*t[i]))/m
    vx[i+1]= vx[i] + ax[i] * dt
    x[i+1] = x[i] + vx[i+1] * dt
    
plt.figure()
plt.plot(t,x)
plt.title("Alínea c)")
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.grid()
plt.show()

min = [] #(t, x)
max = []
extremos = 0
i = -1

while True:
    if (x[i-1] > x[i] < x[i+1]):
        min.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos += 1
        
    if (x[i-1] < x[i] > x[i+1]):
        max.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos +=1 
        
    if extremos==4: 
        break
    i-=1

A = (max[1][1] - min[0][1])/2
T = max[0][0] - max[1][0]
print("\nd)")
print("Amplitude: ", A)
print("Periodo: ", T)

Em = 0.5*m*vx**2 + 0.5*k*x**2
plt.figure()
plt.plot(t,Em)
plt.title("e) Energia Mecânica")
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.grid()
plt.show()