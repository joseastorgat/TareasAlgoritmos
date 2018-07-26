import java.util.*;

class ab
{
  Character elemento;
  int frecuencia;
  ab izq;
  ab der;

  ab(Character o, int frecuencia){
    this.elemento=o;
    this.frecuencia = frecuencia;
    this.izq=null;
    this.der=null;

  }

  ab(ab izq, ab der){
    this.elemento=null;
    this.izq=izq;
    this.der=der;
    this.frecuencia = this.izq.frecuencia + this.der.frecuencia;

  }
    public boolean esHoja()
  {
    return this.izq==null && this.der==null;
  }


  public void Lectura(String code){
	
	if(this.esHoja()){
  		System.out.println("'" + this.elemento.toString() + "': " + code);
	}
	else{
		this.izq.Lectura(code+"0");
		this.der.Lectura(code+"1");
	}


  }

}





class MinHeap
{
  // int size;
  ArrayList<ab> heap;
  int size;

  MinHeap()
  {
    this.heap = new ArrayList<ab>();
    this.size = 0;
  }

  public void insertar( ab arbolchar){
  	this.heap.add(arbolchar);
  	this.size+=1;
  	int i = this.size - 1;

  	while( i>0 && this.heap.get(i).frecuencia < this.heap.get((i-1)/2).frecuencia ){
  		this.swap((i-1)/2, i);
  		i = (i-1)/2;
  	}

  }

  public ab extraermin(){

  	if(this.size == 0){
  		System.out.println("HEAP VACIO");
  	}


  	ab min = this.heap.get(0);  
  	this.heap.set( 0, this.heap.get(this.size - 1));
  	this.heap.remove(this.size-1);

  	int i = 0;
  	boolean insertado = false;
  	this.size-=1;

  	while(!insertado){

  		// Primero comprobamos el hijo izquierdo < padre
  		if( 2*i+1 < this.size  &&  this.heap.get(i).frecuencia > this.heap.get(2*i+1).frecuencia){
  			
  			// Si hd < hi < padre!
  			if( 2*i+2 < this.size && this.heap.get(2*i+1).frecuencia > this.heap.get(2*i+2).frecuencia){

  				this.swap(i, 2*i+2);
  				i = 2*i+2;

  			}
  			
			// hi < padre < hd || hi < hd < padre   
  			else{
  				this.swap(i, 2*i+1);
  				i = 2*i+1;
  			}
  		}

  		// hd < padre < hi
  		else if (2*i+2 < this.size && this.heap.get(i).frecuencia > this.heap.get(2*i+2).frecuencia){
  			this.swap(i, 2*i+2);
  			i = 2*i+2;
  		}
  		
  		//  padre < hi < hd || padre < hd < hi
  		else{
  			insertado = true;
  		}
  	}
  	return min;
  }

  private void swap(int i, int j){
  		
  		ab aux = this.heap.get(i);
  		this.heap.set(i, this.heap.get(j));
  		this.heap.set(j, aux);
  }

}