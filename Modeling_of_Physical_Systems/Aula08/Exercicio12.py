# -*- coding: utf-8 -*-
"""
Created on Thu Apr 20 16:21:59 2023

@author: lazio
"""


import numpy as np
import matplotlib.pyplot as plt

g,m,c_atr,c_ar,ro_ar,pot, A=[9.81, 75, 0.004, 0.9, 1.225, 0.4*745, 0.3]
D = c_ar/2 * A * ro_ar
#tempo
dt = 0.1
t0 = 0
tf = 200
Nt = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, Nt)

x = np.zeros(Nt)
vx = np.zeros(Nt)
ax = np.zeros(Nt)

[x[0], vx[0], ax[0]] = [0, 1, 0]



#a)
#Euler-Cromer
for i in range(Nt-1):

    ax[i]= 1/m*(pot/vx[i]-m*g*c_atr-D*vx[i]**2)
    vx[i+1]= vx[i]+ax[i]*dt
    x[i+1]= x[i] + vx[i+1]*dt
vt = vx[-1]
print("12.a) Velocidade terminal é de: ", vx[-1])
#Velocidades
plt.figure()
plt.plot(t,vx)#,label='Euler-Cromer')
plt.legend()
plt.ylabel('v [m//s]')
plt.xlabel('t [s]')
plt.title('Velocidade (m/s)')
plt.grid()
plt.show()
    
#b
t_b=t[np.where(vx>.9*vt)[0][0]]
print("12.b) "+str(t_b)+" s")


#Posições 
plt.figure()
plt.plot(t,x)#,label='Euler-Cromer')
plt.xlabel('t [s]')
plt.ylabel('x [m]')
plt.grid()
plt.show()

#c
t_c=t[np.where(x>2000)[0][0]]
print("12.c) "+str(t_c)+" s")




