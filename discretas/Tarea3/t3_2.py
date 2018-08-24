# coding=utf-8

class P2(object):
  def __init__(self):


    self.read_input() # Leemos Input
    self.pintar(1, 0) #ya que un arbol es nodo conexo da igual donde partamos pintando.
    print(self.contar()) # mostramos el resultado

  def read_input(self): 
    """
    Método para leer el input del Usuario 
    Se lee el numero de nodos y los vértices en el arbol

    """

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
    """
    Si el nodo ingresado no está pintado :  lo pinta del color indicado y realiza llamadas recursivas para pintar todos los nodos adyacentes a este nodo.
    Si el nodo ya se encuentra pintado solo retorna None
    """

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
    """
    Retorna las aristas que pueden agregar sin romper la condición de que el grafo aun sea bipartito
    """
    cuenta = 0
    for i in range(1,self.n): # Recorremos todos los nodos
      for j in range(i+1, self.n+1): # Para cada nodo recorremos los nodos mayores que el  ( ya que el arbol es no dirigido, su matriz de adyacencia es simetrica)
        #si no existe el vertice i, j  y no tienen el mismo color se suma 1 a la cuenta -- (el nodo i se encuentra en la posición i -1 en la matriz de adyacencia. )
        if not self.grafo[i-1][j-1] and not ((self.nodos[i] and self.nodos[j]) or (not self.nodos[i] and not self.nodos[j])): 
          cuenta+=1
    return cuenta

def main():
  P2()

if __name__ == '__main__':
  main()