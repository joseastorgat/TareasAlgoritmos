class NodoAC
{
  int elemento;
  Float prioridad;
  NodoAC izq;
  NodoAC der;

  NodoAC(int o, Float p, NodoAC padre){
    this.elemento  = o;
    this.prioridad = p;
    this.izq = null;
    this.der = null;
    this.padre = padre;
  }

  NodoAC(){
    this.elemento  = null;
    this.prioridad = null;
    this.izq = null;
    this.der = null;
    this.padre = null;
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

    return (this.izq == null && this.der == null)
  }

  public boolean esRaiz(){
    return (this.padre == null)
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


  public void insertar(int x, float y){
    if(this.elemento == null){
        this.elemento = x;
        this.prioridad = y;

    }
    if( x < this.elemento){
      
      if(this.izq == null){
        
        this.izq = new NodoAC(x, y, this);
        this.izq.rotar();


        return;
      }
      else{
        this.izq.insertar(x, y);

      }
    }
    else{
      
      if(root.der == null){
        root.der = new NodoAC(x, y, this);
        this.der.rotar();
        return;
      }
      
      else{
        this.der.insertar(x, y);
      }
    }
  }


  public void rotar(){

    if(this.prioridad < this.padre.prioridad){


      NodoAC aux = new NodoAC(this);
      NodoAC aux_padre = new NodoAC(this.padre);

      if(this.elemento < this.padre.elemento){ // rotación izquierda

        aux_padre.der = this.izq;
        aux_padre.padre = this;
        aux.padre = this.padre.padre;
        aux.izq = aux_padre;
        this = aux;


      else if (this.elemento> this.padre.elemento) { //rotación derecha

        aux_padre.izq = this.der;
        aux_padre.padre = this;
        aux.padre = this.padre.padre;
        aux.der = aux_padre;
        this = aux;        
      }

        if(this.padre.elemento < this.elemento){

          this.padre.der = this;


        }
        else{
          this.padre.izq = this; 
        }

      }

      this.rotar();


    }
    else{

      return;
    }

      






    }


  }

