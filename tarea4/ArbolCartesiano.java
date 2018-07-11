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

  NodoArbolCartesiano(int o, Float p, NodoArbolCartesiano padre, NodoArbolCartesiano izq, NodoArbolCartesiano der){
    this.elemento=o;
    this.prioridad=p;
    this.izq=izq;
    this.der=der;
    this.padre = padre;
  }

  NodoArbolCartesiano(NodoArbolCartesiano another){
    this.elemento  = another.elemento;
    this.prioridad = another.prioridad;
    this.izq = another.izq;
    this.der = another.der;
    this.padre = another.padre;
  }



  public boolean esHoja(){
    return (this.izq == null && this.der == null);
  }

  public boolean esRaiz(){
    return (this.padre == null);
  }

  public boolean esHijoIzquierdo(){
    return !this.esRaiz() && (this.elemento < this.padre.elemento);
   }

  public boolean esHijoDerecho(){
    return !this.esRaiz() && (this.elemento > this.padre.elemento);
   }

  public String imprimir(){
    String imp;
    // if(this.esRaiz()){

    //   imp = "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + "{R} )";      
    // }
    // else{
    //   imp = "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + "{" + Integer.toString(this.padre.elemento)+"} )";

    // }

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
            root.izq = new NodoArbolCartesiano(x, y, root);
            // this.imprimir();
            this.rotaciones(root.izq);
            // this.imprimir();
            insertado = true;
          }
          else{
            root = root.izq;
          }
        }
        else{
          if(root.der == null){
            root.der = new NodoArbolCartesiano(x, y, root);
            // this.imprimir();
            this.rotaciones(root.der);
            // this.imprimir();
            insertado = true;
          }
          else{
            root = root.der;
          }
        }
      }
    }
  }

  public void rotaciones(NodoArbolCartesiano arotar){




        //No se Hace Nada
    if(arotar.esRaiz()){
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
    String out = this.raiz.imprimir();
    System.out.println(out);
  }
}