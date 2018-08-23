#!/usr/bin/env python
# -*- coding: utf-8 -*-


import math 
import numpy as np
    
def matrix_pow(A, k):
    """
    Método para elevar una matriz cuadrada A a su k-potencia
    Utilizando exponenciación binaria:
    https://en.wikipedia.org/wiki/Exponentiation_by_squaring
    """
    n =len(A)
    res = [[0]*n for _ in range(n)]
    for i in range(n):
        res[i][i]=1
    

    print(A)
    print(res)    

    while  k > 0:
        while k%2==0:
            A = matrix_mul(A,A)
            k = k/2
        res = matrix_mul(res, A)
        k-=1
    return res

def matrix_mul(A,B):
    """
    Multiplicación de 2 matrices cuadradas de igual dimensión!
    in: matrices A B
    out matriz A * B
    
    (no nos importan los demás casos (mat no cuadradas))

    """
    n = len(A)
    # if not(n==len(A[0]) and n==len(B) and n==len(B[0])):
    #     print("no matriz cuadrada!")

    res = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            for k in range(n):
               res[i][j] += A[i][k] * B[k][j]
    return res


def main():
    n = int(raw_input("numero de nodos ( enumerados de 0 a n-1 ) "))
    grafo =[[0]*n for _ in range(n)]
    print("ingrese matriz de adyacencia fila a fila")

    for i in range(n):
        grafo[i] = [int(x) for x in raw_input("").split()]
        if grafo[i]>n:
            print("Error en la matriz ingresada: verifica que sea de tamaño {}x{}")

    k = int(raw_input("Ingrese Largo de Camino"))
    _in = (raw_input("Ingrese u v"))
    _in_split = _in.split()
    u, v = int(_in_split[0]), int(_in_split[1])


    A = matrix_pow(grafo, k)

    print("output:")
    print(A[u][v])


if __name__ == '__main__':
    main()