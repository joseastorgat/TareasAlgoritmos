class NodoArbolCartesiano
{
  int elemento;
  Float prioridad;
  NodoArbolCartesiano izq;
  NodoArbolCartesiano der;
  NodoArbolCartesiano padre;


  NodoArbolCartesiano(int o, Float p){
    this.elemento=o;
    this.prioridad=p;
    this.izq=null;
    this.der=null;
    this.padre=null;
  }


  NodoArbolCartesiano(int o, Float p, NodoArbolCartesiano padre){
    this.elemento=o;
    this.prioridad=p;
    this.izq=null;
    this.der=null;
    this.padre = padre;
  }

  public boolean esHoja(){
    // Retorna True si corresponde a una hoja del árbol, i.e: Ambos hijos son null
    return (this.izq == null && this.der == null);
  }

  public boolean esRaiz(){
    // Retorna True si corresponde a la raiz de un arbol, i.e: posee referencia null a un padre
    return (this.padre == null);
  }

  public boolean esHijoIzquierdo(){
    // Retorna True si es un hijo izquierdo ( a la izquierda del padre ) False si no

    return !this.esRaiz() && (this.elemento < this.padre.elemento);
   }

  public boolean esHijoDerecho(){
    // Retorna True si es un hijo derecho False si no
    return !this.esRaiz() && (this.elemento > this.padre.elemento);
   }

  public String imprimir(){
    //Método recursivo que imprime el arbol en post orden:

    // imprimir(Hijo Izquierdo) + imprimir(Hijo Derecho) + Elemento
    // Un hijo vacio se representa con: []


    String imp;
    imp = "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) +")" ;      


    if(this.izq==null && this.der==null){
      return "[][]" + imp ;
    }
    
    else if (this.izq == null) {
     return "[]" + this.der.imprimir() + imp; 
    }
    else if (this.der == null) {
     return  this.izq.imprimir() + "[]" + imp; 
      
    }

    else{
      return this.izq.imprimir() + this.der.imprimir() + imp ;
    }
  }


  public int costo(int nivel){
    // Método Recursivo para obtener la suma de los costos de búsqueda desde este nodo.
    // Retorna la suma de las distancias a la raiz, de todos los nodos desde el cual es invocado.


    // Input: nivel (distancia del nodo a la raiz)
    // Output: Costo

    //Caso Base
    if(this.izq==null && this.der==null){
      return nivel + 1 ;
    }
    
    // No hijo izquierdo
    else if (this.izq == null) {
     return 1+ nivel + this.der.costo(1+nivel) ; 
    }
    
    // No hijo derecho
    else if (this.der == null) {
     return  1+ nivel + this.izq.costo(1+nivel) ; 
      
    }

    // Caso General:
    else{
      return nivel + this.izq.costo(1+nivel) + this.der.costo(1+nivel) + 1;
    }
  }
}


class ArbolCartesiano
{
  NodoArbolCartesiano raiz;
  int n_nodos;


  ArbolCartesiano()
  {
    this.raiz = null ;
    this.n_nodos = 0;
  }

  public void insertar(int x, float y)
  {

    //Si es que es el primer elemento
    if(this.raiz==null){
      this.raiz = new NodoArbolCartesiano(x, y);
      this.n_nodos+=1;
    }
    else{
      NodoArbolCartesiano root = this.raiz;
      boolean insertado = false;
      
      // insertar como en árbol debúsqueda binaria
      while(!insertado){
        if(x<root.elemento){
          if(root.izq == null){
            // insertar el nuevo nodo
            root.izq = new NodoArbolCartesiano(x, y, root);
            // una vez insertado rotamos el nodo para ordenarlo en prioridad
            this.rotaciones(root.izq);
            this.n_nodos+=1;
            insertado = true;
          }
          else{
            root = root.izq;
          }
        }
        else{
          if(root.der == null){
            // insertar el nuevo nodo
            root.der = new NodoArbolCartesiano(x, y, root);
            // una vez insertado rotamos el nodo para ordenarlo en prioridad
            this.rotaciones(root.der);
            this.n_nodos+=1;
            insertado = true;
          }
          else{
            root = root.der;
          }
        }
      }
    }
  }

  private void rotaciones(NodoArbolCartesiano arotar){

    //si llegamos a la raiz no hay nada que rotar!
    if(arotar.esRaiz()) {
      this.raiz = arotar;
      return;
    }
    
    NodoArbolCartesiano padre = arotar.padre;
    NodoArbolCartesiano hijo = arotar;


    if (hijo.prioridad >= padre.prioridad) {
      return;      
    }


    // Rotacion!!!
    // rotación izquierda
    if(hijo.esHijoIzquierdo()){ 
        padre.izq = hijo.der;

        if(padre.izq!=null){
          padre.izq.padre = padre;
        }
  
        hijo.der = padre;      
        hijo.padre = padre.padre;
        hijo.der.padre = hijo;
      }
      
      //rotación derecha
    else if (hijo.esHijoDerecho()) { 
        padre.der = hijo.izq;
        
        if(padre.der!=null){
          padre.der.padre = padre;}
        
        hijo.izq = padre;
        hijo.padre = padre.padre;
        hijo.izq.padre = hijo;

      }


    if( arotar.esRaiz() ){
      this.raiz = arotar;
      return;
    }

    else if ( hijo.esHijoIzquierdo()){

      hijo.padre.izq = hijo;

    }

    else if ( hijo.esHijoDerecho()) {
      hijo.padre.der = hijo;
      }
    
    this.rotaciones(hijo);

    }


  public void imprimir(){

    // Metodo para imprimir el arbol
    String out = this.raiz.imprimir();
    System.out.println(out);
  }


  public float CostoPromedio(){
    //Calcular el costo de búsqueda promedio
    int n = this.raiz.costo(0);
    return (float) n/this.n_nodos;
  }
  
}