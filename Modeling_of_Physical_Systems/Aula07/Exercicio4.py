# -*- coding: utf-8 -*-
"""
Created on Thu Mar 30 16:10:37 2023

@author: lazio
"""


import numpy as np
import matplotlib.pyplot as plt

#consts
m = 0.057 #kg
raio = 0.067/2 #m
A = np.pi*raio**2
dens = 1.225
g = 9.81


r0 = np.array([[-10,1,0]])
alpha = 10*np.pi/180
v0_abs = 130*1000/3600
v0 = v0_abs*np.array([[np.cos(alpha),np.sin(alpha),0]])
a0 = np.array([0,-g,0])
g = 9.81
vT = 100*1000/3600
D = g/vT**2
w_b = np.array([0,0,100])
w_c = np.array([0,0,-100])



#t
t0 = 0
tf = 2
dt = 0.01
Nt = int(np.ceil((tf-t0)/dt)+1)
t = np.linspace(t0, tf, Nt)

#velocidade angular
#w = np.array([0, 400, 0])

#alocar zeros em t, v e a
r = np.vstack((r0,np.zeros((Nt-1, 3))))
v = np.vstack((v0,np.zeros((Nt-1, 3))))
a = np.vstack((a0,np.zeros((Nt-1, 3))))
rb = np.vstack((r0,np.zeros((Nt-1, 3))))
vb = np.vstack((v0,np.zeros((Nt-1, 3))))
ab = np.vstack((a0,np.zeros((Nt-1, 3))))
rc = np.vstack((r0,np.zeros((Nt-1, 3))))
vc = np.vstack((v0,np.zeros((Nt-1, 3))))
ac = np.vstack((a0,np.zeros((Nt-1, 3))))

#Met Euler. Aceleração afectada pela força de Magnus
for i in range(Nt-1):
    #Método de Euler...
    #a[i,:] = .5/m*A*dens*raio*np.cross(w_b,v[i,:])+\
    #np.array([0,-g,0])+\
    #- D*np.linalg.norm(v[i,:])*v[i,:]
    
    #alinea a)
    a[i,:] = np.array([-D*np.linalg.norm(v[i,:])*v[i,0], -g -D*np.linalg.norm(v[i,:])*v[i,0],0])
    v[i+1,:] = v[i,:]+a[i,:]*dt
    r[i+1,:] = r[i,:]+v[i,:]*dt
    
    #alinea b)
    ab[i,:] = .5/m*A*dens*raio*np.cross(w_b,vb[i,:])+\
        np.array([0,-g,0])+\
        - D*np.linalg.norm(vb[i,:])*vb[i,:]
    vb[i+1,:] = vb[i,:]+ab[i,:]*dt
    rb[i+1,:] = rb[i,:]+vb[i,:]*dt
    
    #alinea c)
    ac[i,:] = .5/m*A*dens*raio*np.cross(w_c,vc[i,:])+\
        np.array([0,-g,0])+\
        - D*np.linalg.norm(vc[i,:])*vc[i,:]
    vc[i+1,:] = vc[i,:]+ac[i,:]*dt
    rc[i+1,:] = rc[i,:]+vc[i,:]*dt



fig, ax = plt.subplots(1, 3)
ax[0].plot(t,r[:,0])
ax[0].set_xlabel("t [s]")
ax[0].set_title("x [m]")
ax[1].plot(t,r[:,1])
ax[1].set_xlabel("t [s]")
ax[1].set_title("y [m]")
ax[2].plot(t,r[:,2])
ax[2].set_xlabel("t [s]")
ax[2].set_title("z [m]")

#ax = plt.figure().add_subplot(projection="3d")

plt.figure()
plt.plot(r[:,0],r[:,1], label="caso a")
plt.plot(rb[:,0],rb[:,1], label="caso b")
plt.plot(rc[:,0],rc[:,1], label="caso c")
plt.legend()

plt.show()