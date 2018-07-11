class NodoAC
{
  int elemento;
  Float prioridad;
  NodoAC izq;
  NodoAC der;
  NodoAC padre;

  NodoAC(int o, Float p, NodoAC padre){
    this.elemento  = o;
    this.prioridad = p;
    this.izq = null;
    this.der = null;
    this.padre = padre;
  }


  NodoAC(int o, Float p){
    this.elemento  = o;
    this.prioridad = p;
    this.izq = null;
    this.der = null;
    this.padre = null;
  }

  NodoAC(NodoAC another){
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

    else if (this.der == null) {
     return  this.izq.imprimir() + "[]" + "(" +Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")"; 
      
    }
    
    else if (this.izq == null) {
      return "[]" + this.der.imprimir() + "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")"; 
    }

    else{
      return this.izq.imprimir() + this.der.imprimir() + "(" + Integer.toString(this.elemento) + "," + Float.toString(this.prioridad) + ")" ;
    }
  }


  public void insertar(int x, float y){
    if( x < this.elemento){
      
      if(this.izq == null){
        
        this.izq = new NodoAC(x, y, this);
        System.out.println( this.imprimir());
        this.izq.rotar();
        System.out.println( this.imprimir());


        return;
      }
      else{
        this.izq.insertar(x, y);

      }
    }
    else{
      
      if(this.der == null){
        this.der = new NodoAC(x, y, this);
        System.out.println( this.imprimir());
        this.der.rotar();
        System.out.println( this.imprimir());
        return;
      }
      
      else{
        this.der.insertar(x, y);
      }
    }
  }


  public void rotar(){

    //No se Hace Nada
    if(this.esRaiz() || this.prioridad >= this.padre.prioridad){
      return;
    }


    // Rotacion!!!

    NodoAC aux_padre = new NodoAC(this.padre);
    


    // rotación izquierda
    if(this.elemento < this.padre.elemento){ 

        if(this.der==null){
          aux_padre.izq = null;
        }
        
        else{
          aux_padre.izq = new NodoAC(this.der);
          
        }

        aux_padre.padre = this;     
        this.der = aux_padre;
        this.padre =this;
      }
      
      //rotación derecha
    else if (this.elemento> this.padre.elemento) { 
      
      if(this.izq == null){
          aux_padre.der = null;
        }
      
      else{
          aux_padre.der = new NodoAC(this.izq);    
        }
        aux_padre.padre = this;
        this.der = aux_padre;
        this.padre = this;
      }
    
    this.rotar();


    }
}
