class NodoArbolCartesiano
{
  int elemento;
  Float prioridad;
  NodoArbolCartesiano izq;
  NodoArbolCartesiano der;

  NodoArbolCartesiano(int o, Float p){
    this.elemento=o;
    this.prioridad=p;
    this.izq=null;
    this.der=null;
  }

  public boolean esHoja()
  {
    return this.izq==null && this.der==null;
  }

  public String imprimir(){
    if(this.izq==null && this.der==null){
      return "[][](" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")";
    }
    
    else if (this.izq == null) {
     return "[]" + this.der.imprimir() + "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")"; 
    }
    else if (this.der == null) {
     return  this.izq.imprimir() + "[]" + "(" +Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")"; 
      
    }

    else{
      return this.izq.imprimir() + this.der.imprimir() + "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")" ;
    }
  }
}



class ArbolCartesiano
{
  NodoArbolCartesiano raiz;
  
  ArbolCartesiano()
  {
    this.raiz = null ;
  }

  public void insertar(int x, float y)
  {

    if(this.raiz==null){
      this.raiz = new NodoArbolCartesiano(x, y);
    }
    else{
      NodoArbolCartesiano root = this.raiz;
      boolean insertado = false;
      while(!insertado){
        if(x<root.elemento){
          if(root.izq == null){
            root.izq = new NodoArbolCartesiano(x, y);
            insertado = true;
          }
          else{
            root = root.izq;
          }
        }
        else{
          if(root.der == null){
            root.der = new NodoArbolCartesiano(x, y);
            insertado = true;
          }
          else{
            root = root.der;
          }
        }
      }
    }
  }

  public void imprimir(){
    String out = this.raiz.imprimir();
    System.out.println(out);
  }
}