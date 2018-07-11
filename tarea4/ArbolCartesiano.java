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
            root.izq = new NodoArbolCartesiano(x, y, root);
            this.imprimir();
            this.rotaciones(root.izq);
            this.imprimir();
            insertado = true;
          }
          else{
            root = root.izq;
          }
        }
        else{
          if(root.der == null){
            root.der = new NodoArbolCartesiano(x, y, root);
            this.imprimir();
            this.rotaciones(root.der);
            this.imprimir();
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
    else if (arotar.prioridad >= arotar.padre.prioridad) {
      return;      
    }


    // Rotacion!!!

    // NodoArbolCartesiano aux_padre = new NodoArbolCartesiano(arotar.padre);
    
    // rotación izquierda
    if(arotar.elemento < arotar.padre.elemento){ 

        arotar.padre.izq = arotar.der;
        arotar.der = arotar.padre;
        arotar.padre = arotar.der.padre;
        arotar.der.padre = arotar;
        
        System.out.println("ROTACION POR LA iZQUIERDA");

      }
      
      //rotación derecha
    else if (arotar.elemento> arotar.padre.elemento) { 
        arotar.padre.der = arotar.izq;
        arotar.izq = arotar.padre;
        arotar.padre = arotar.izq.padre;
        arotar.izq.padre = arotar;
        System.out.println("ROTACION POR LA DERECHA");
      }


    if( arotar.esRaiz() ){
      this.raiz = arotar;
      return;
    }

    else if ( arotar.elemento < arotar.padre.elemento){

      arotar.padre.izq = arotar;

      System.out.println("PADRE POR LA DERECHA");
    }

    else{
      arotar.padre.der = arotar;
      System.out.println("PADRE POR LA IZQUIERDA");


      }
    
    this.rotaciones(arotar);

    }

  public void imprimir(){
    String out = this.raiz.imprimir();
    System.out.println(out);
  }
}