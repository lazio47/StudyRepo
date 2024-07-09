# -*- coding: utf-8 -*-
"""
Created on Thu May 18 16:09:46 2023

@author: lazio
"""

import numpy as np
import matplotlib.pyplot as plt

global x, k, m

def acel(x, m, k):
    return (-k*x)/m
def met_euler_1d(x0, v0, dt, Nt, acel, m):
    x = np.zeros(Nt)
    vx = np.zeros(Nt)
    x[0] = x0
    vx[0] = v0
    for i in range(Nt-1):
        ax = -k*x[i]
        vx[i+1] = vx[i] +ax*dt
        x[i+1] = x[i] + vx[i + 1]*dt
    return x, vx

def viz_data(x, y, title, xlabel, ylabel):
    plt.figure()
    plt.plot(x,y)#,label='Euler-Cromer')
    plt.legend()
    plt.ylabel(ylabel)
    plt.xlabel(xlabel)
    plt.title(title)
    plt.grid()
    plt.show()

#dados
m = 1 #kg
k = 1 #N/m
x0 = 4
v0 = 0
dt = 0.01
t0 = 0
tf = 20
Nt = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, Nt) 


[x, vx] = met_euler_1d(x0, v0, dt, Nt, acel, m)
viz_data(t, x, "Tragetória", "t [s]", "x [m]")

#alínea b)
xmax = []
i = 2
x_ext = []
t_ext = []

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

while True:
    if x[i]>x[i-1] and x[i] > x[i+1]:
        tm, xm = maxminv(t[i-1], t[i], t[i+1], x[i-1],x[i], x[i+1])
        x_ext.append(xm)
        t_ext.append(tm)
        k += 1
        
    if x[i]< x[i-1] and x[i] < x[i+1]:
        tm, xm = maxminv(t[i-1], t[i], t[i+1], x[i-1],x[i], x[i+1])
        x_ext.append(xm)
        t_ext.append(tm)
        k += 1 
    if k == 4:
        break
    i += 1

A = (x_ext[1]-x_ext[0])/2
T = t_ext[2]-t_ext[0]

print(A)
print(T)

