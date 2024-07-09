# -*- coding: utf-8 -*-
"""
Created on Thu Mar  2 16:17:38 2023

@author: lazio
"""

import matplotlib.pyplot as plt
import numpy as np
import os

dir_figs = './figures/'
if not os.path.exists(dir_figs): 
    os.mkdir(dir_figs)
    
t0 = 0
tf = 60
dt = 0.01

Nt = int((tf-t0)/dt + 1)

t = np.linspace(t0, tf, Nt)
# t = np.arange(t0,tf+dt,dt)

v0_a = 70*1000/3600 # m/s
a0_p = 2 # m/s^2

#carro a

x_a = np.zeros((Nt,))
v_a = np.ones((Nt,))*v0_a
a_a = np.zeros((Nt,))

#carro p

x_p = np.zeros((Nt,))
v_p = np.zeros((Nt,))
a_p = np.ones((Nt,))*a0_p


for i in range(Nt - 1):
    x_a[i+1] = x_a[i]+v_a[i]*dt
    v_p[i+1] = v_p[i]+a_p[i]*dt
    x_p[i+1] = x_p[i]+v_p[i]*dt
    
plt.figure()
plt.plot(t, x_a, "k-", label= "Carro A")
plt.plot(t, x_p, "r-", label="Carro Patrulha")
plt.xlabel("t (s)")
plt.ylabel("x (m)")
plt.legend()
plt.grid()
plt.show()

inters = np.where(x_p > x_a)[0][0]
t_inters = t[inters]
print(f"tempo de interseção: {t_inters}")
x_inters = x_p[inters]
print(f"espaço percorido: {x_inters}")


"""inters = np.where(x_p > x_a)
t_inters = t[inters]
dt_range = 0.5
t_range = [t_inters - dt_range, t_inters + dt_range]
x_range = [370,390]
dt_range = 0.05
t_range2 = [t_inters - dt_range, t_inters + dt_range]
x_range2 = [377.5,379]

print(f"tempo de interseção: {t_inters}")"""
