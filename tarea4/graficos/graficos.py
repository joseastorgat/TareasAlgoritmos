# coding=utf-8

import matplotlib.pyplot as plt
import numpy as np
from numpy import genfromtxt
import math
# import seaborn as sns

def main():
 
    tiempo1 = []
    iteraciones1 = []
    derrumbes1 = []

    costos = genfromtxt('costos.csv', delimiter=',')
    

    fig = plt.figure()
    cx = fig.add_subplot(111)

    n =  costos[:,0]
    costo = [np.mean(costos[i,1:]) for i in range(0,len(n))]

    print(n)
    print(costo)

    log_n = [ math.log(i, 2) for i in n]
    log_costo = [math.log(i, 2) for i in costo]
    print(log_n)
    print(log_costo)


    r = np.polyfit(log_n, costo, 1)
    regresion = [r[0]*i+r[1] for i in log_n]
    # regresion2 = [2**i for i in regresion]

    # regresion2 = [(i**(r[0]))*(2**(r[1])) for i in n]


    # graph1 = plt.plot(log_n, log_costo , color='red', linestyle='--', marker='*', linewidth=2, label = "Costo de busqueda promedio")
    # graph2 = plt.plot(log_n, regresion , color='blue', linestyle='--', linewidth=2, label = "regresion")

    graph1 = plt.plot(n, costo , color='red', linestyle='--', marker='o', markersize=10, linewidth=2, label = " datos ")
    graph2 = plt.plot(n, regresion , color='blue', linestyle='-', linewidth=2, label = " regresion lineal: \n $ " + str(round(r[0],3)) + " *log_{2}(n) + " + str(round(r[1],3)) + "$"  )
 
    # cx.set_yscale('log', basey=2)
    cx.set_xscale('log', basex=2)
    

    plt.legend(fontsize=13,loc=2)
    plt.xlabel(' Numero de nodos del arbol ( n )')
    plt.ylabel(' Costo de Busqueda Promedio')


    fig2 = plt.figure()

    cx = fig.add_subplot(111)
    graph1 = plt.plot(n, costo , color='red', linestyle='--', marker='o', markersize=10, linewidth=2, label = " datos ")
    # graph2 = plt.plot(n, regresion2 , color='blue', linestyle='-', linewidth=2, label = " regresion lineal: \n $ 2^{ " + str(r[0]) + " *n + " + str(r[1]) + "}$"  )
 
    # cx.set_yscale('log', basey=2)
    # cx.set_xscale('log', basex=2)
    

    plt.legend(fontsize=13,loc=2)
    plt.xlabel(' Numero de nodos del arbol ( n )')
    plt.ylabel(' Costo de Busqueda Promedio')

    plt.show()



if __name__ == '__main__':
    main() 