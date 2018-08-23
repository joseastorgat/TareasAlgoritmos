# coding=utf-8


import math 
import numpy as np


class P2(object):
  def __init__(self):


    self.read_input()
    self.pintar(1, 0) #ya que un arbol es nodo conexo da igual donde partamos pintando.
    print(self.grafo)
    print(self.nodos)
    print(self.contar())

  def read_input(self): 
    n = int(raw_input("numero de nodos ( enumerados de 1 a n ) "))
    self.n = n
    print("ingrese aristas como pares de nodos (al terminar solo presione enter)")
    self.grafo =[[0]*n for _ in range(n)]


    self.nodos = {}

    for i in range(1,n+1):
      self.nodos[i] = ""

    while True:
      _in = raw_input("")
      if _in == "":
        break
      
      else:
        _in_split = _in.split()
        u,v = int(_in_split[0]), int(_in_split[1])
        self.grafo[u-1][v-1] = 1
        self.grafo[v-1][u-1] = 1

  def pintar(self, nodo, color):


    if self.nodos[nodo] != "":
      if self.nodos[nodo] != color:
        print("Error")
        return
      else:
        return
    else:
      self.nodos[nodo] = color
      for i in range(nodo, self.n+1):
        if self.grafo[nodo-1][i-1]==1:
          self.pintar(i, abs(color-1))
          # self.nodos[i] = color
        
        else:
          pass
      return

  def contar(self):
    cuenta = 0
    for i in range(1,self.n):
      for j in range(i+1, self.n+1):
        if not self.grafo[i-1][j-1] and not ((self.nodos[i] and self.nodos[j]) or (not self.nodos[i] and not self.nodos[j])):
          print(i,j)
          cuenta+=1
    return cuenta

def main():
  P2()

if __name__ == '__main__':
  main()