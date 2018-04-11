# coding=utf-8

import matplotlib.pyplot as plt
import numpy as np
from numpy import genfromtxt
import seaborn as sns

def main():
    a = range(7,20)
    # n = [2**b for b in a] 
    # print n
    tiempo1 = []
    iteraciones1 = []
    derrumbes1 = []

    my_data = genfromtxt('datosP3.csv', delimiter=',')
    n =  my_data[:,0]
    derrumbes1 = my_data[:,1]
    iteraciones1 = my_data[:,2]
    tiempo1 = my_data[:,3]
    print " ######## P1 #######"
    print n 
    print derrumbes1
    print iteraciones1
 
    my_data2 = genfromtxt('datosP4.csv', delimiter=',')
    
    # n =  my_data2[:,0]
    derrumbes2 = my_data2[:,1]
    iteraciones2 = my_data2[:,2]
    tiempo2 = my_data2[:,3]

    tiempo2_sec = [t*(10**(-9)) for t in tiempo2]
    tiempo1_sec = [t*(10**(-9)) for t in tiempo1]
    print " ######## P2 #######"
    print n
    print derrumbes2
    
    print "##### TIEMPO DE EJECUCION ####"
    print tiempo2_sec
    print tiempo1_sec
    

    fig = plt.figure()
    ax = fig.add_subplot(111)
    graph1 = plt.plot(n, derrumbes1 , color='red', linestyle='-', linewidth=2, label='Derrumbes en algoritmo 1')
    graph2 = plt.plot(n, derrumbes2 , color='blue', linestyle='-', linewidth=2, label='Derrumbes en algoritmo 2')
    ax.set_yscale('log')
    ax.set_xscale('log')
    # fig.legend( [graph1[0],graph2[0]], ['Derrumbes en algoritmo 1','Derrumbes en algoritmo 2'], fontsize = 10)
    plt.legend(fontsize=10,loc=2)
    # plt.legend(bbox_to_anchor=(1.05, 1), loc=2, borderaxespad=0.)
    plt.xlabel('N: Numero de granos de la torre inicial')
    plt.ylabel('Numero de Derrumbes de pilas de arena')
    # plt.title("Numero de derrumbes de pilas de arenas vs Numero de Granos",size=15)


    
    fig2 = plt.figure()
    bx = fig2.add_subplot(111)
    graph3 = plt.plot(n, iteraciones1 , color='red', linestyle='-', linewidth=2, label = "Iteraciones alg 1")
    graph4 = plt.plot(n, iteraciones2 , color='blue', linestyle='-', linewidth=2, label = "Iteraciones alg 2")
    plt.legend(fontsize=10,loc=2)
    bx.set_yscale('log')
    bx.set_xscale('log')

    plt.xlabel('N: Numero de granos de la torre inicial')
    plt.ylabel('Numero Iteraciones sobre el Tablero')
    # plt.title("Numero de Iteraciones sobre la Matriz vs Numero de Granos",size=15)

 
    fig3 = plt.figure()
    cx = fig3.add_subplot(111)
    graph5 = plt.plot(n, tiempo1 , color='red', linestyle='-', linewidth=2, label = "Tiempo de ejecucion alg 1")
    graph6 = plt.plot(n, tiempo2 , color='blue', linestyle='-', linewidth=2, label = "Tiempo de ejecucion alg 2")
    cx.set_yscale('log')
    cx.set_xscale('log')
    plt.legend(fontsize=10,loc=2)
    plt.xlabel('N: Numero de granos de la torre inicial')
    plt.ylabel('Tiempo de ejecucion [nano segundos]')
    # plt.title("Tiempo de ejecucion vs Numero de Granos",size=15)

    # plt.legend(bbox_to_anchor=(0., 1.02, 1., .102), loc=3, ncol=1, borderaxespad=0)    
    plt.show()



if __name__ == '__main__':
    main() 