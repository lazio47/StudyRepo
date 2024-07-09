# -*- coding: utf-8 -*-
"""
Created on Thu Jun  1 14:55:59 2023

@author: Giovanni Santos
"""

import matplotlib.pyplot as plt
import numpy as np

dt = 0.01
tf=200
t0=0
n = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, n)

m = 1
k = 1
b = 0.05
a = 0.002 #alpha
F0 =  7.5
wf = 1
w0 = np.sqrt(k/m)


def Euler_Croner(dt, x0, v0):
    
    n = int(np.ceil((tf-t0)/dt)+1)
    t = np.linspace(t0, tf, n)
    x = np.zeros(n)
    vx = np.zeros(n)
    ax = np.zeros(n)
    x[0] = x0
    vx[0] = v0
    
    for i in range(n-1):
        ax[i] = (-k*x[i]*(1+2*a*x[i]**2) -b*vx[i] + F0*np.cos(wf*t[i]))/m
        vx[i+1]= vx[i] + ax[i] * dt
        x[i+1] = x[i] + vx[i+1] * dt
    return x, vx, t;

#a)
x, vx, t = Euler_Croner(0.001, 3, 0)
x2, vx2, t2 = Euler_Croner(0.0001, 3, 0)
#x, vx, t = rk4(0.001, 3, 0)
#x2, vx2, t2 = rk4(0.001, 3, 0)

plt.figure()
plt.plot(t,x, label="dt=0.001")
plt.plot(t2, x2, label="dt=0.0001")
plt.legend()
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
i = -2
it = []
while True:
    if (x[i-1] > x[i] < x[i+1]):
        min.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos += 1
        
    if (x[i-1] < x[i] > x[i+1]):
        max.append(maxminv(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1]))
        extremos +=1
        it.append(i)
        
    if extremos==4: 
        break
    i-=1
    
A = (max[1][1] - min[0][1])/2
T = max[0][0] - max[1][0]
print("b)")
print("Amplitude: ", A)
print("Periodo: ", T)
#print("\nAnalítico")
#print("Amplitude: ", F0/m/(np.sqrt((wf**2 - w0**2)**2 + (b*wf/w0)**2)))
#print("Periodo: ", 2*np.pi*np.sqrt(m/k))

#c)
def abfourier(tp,xp,it0,it1,nf):
    # cálculo dos coeficientes de Fourier a_nf e b_nf
    # a_nf = 2/T integral ( xp cos( nf w) ) dt entre tp(it0) e tp(it1)
    # b_nf = 2/T integral ( xp sin( nf w) ) dt entre tp(it0) e tp(it1)
    # integracao numerica pela aproximação trapezoidal
    # input: matrizes tempo tp (abcissas)
    # posição xp (ordenadas)
    # indices inicial it0
    # final it1 (ao fim de um período)
    # nf índice de Fourier
    # output: af_bf e bf_nf
    dt=tp[1]-tp[0]
    per=tp[it1]-tp[it0]
    ome=2*np.pi/per
    s1=xp[it0]*np.cos(nf*ome*tp[it0])
    s2=xp[it1]*np.cos(nf*ome*tp[it1])
    st=xp[it0+1:it1]*np.cos(nf*ome*tp[it0+1:it1])
    soma=np.sum(st)
    q1=xp[it0]*np.sin(nf*ome*tp[it0])
    q2=xp[it1]*np.sin(nf*ome*tp[it1])
    qt=xp[it0+1:it1]*np.sin(nf*ome*tp[it0+1:it1])
    somq=np.sum(qt)
    intega=((s1+s2)/2+soma)*dt
    af=2/per*intega
    integq=((q1+q2)/2+somq)*dt
    bf=2/per*integq
    return af,bf

a0, b0 = abfourier(t, x, it[0], it[1], 0)
F = a0/2
for nf in range(5):
    an, bn = abfourier(t, x, it[0], it[1], nf+1)
    F += an*np.cos(wf*t[nf]) + bn*np.sin(wf*t[nf])
    
print("c)")
print(F)
    
