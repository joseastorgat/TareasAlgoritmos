import java.util.*;

class ab
{
  Character elemento;
  int frecuencia;
  ab izq;
  ab der;

  ab(Character o, int frecuencia){
    // Creación de un nodo de árbol que contiene un caracter o  de cierta frecuencia. 
    this.elemento=o;
    this.frecuencia = frecuencia;
    this.izq=null;
    this.der=null;

  }

  ab(ab izq, ab der){
    // Crea un ab con ambos arboles input como hijos izquiero y derecho
    // La raiz no contiene elemento y contiene frecuencia igual a la suma de ambas frecuenciass

    this.elemento=null;
    this.izq = izq;
    this.der = der;
    this.frecuencia = this.izq.frecuencia + this.der.frecuencia;

  }
    public boolean esHoja()
    // Retorna True si un nodo es hoja ( nodo externo)
  {
    return this.izq==null && this.der==null;
  }


  public String buscar(char buscado){
    // Busca la codificación de un char en el árbol
    return this.busquedarecursiva("", buscado);

  }

  private String busquedarecursiva(String code, char buscado){

  if(this.esHoja()){
      if (this.elemento.equals(buscado)){
        return code;}
      else{
        return "";}
  }
  
  else{
    String buscarizq =this.izq.busquedarecursiva(code+"0", buscado);
    
    if(buscarizq != ""){
      return buscarizq; }
    
    String buscarder = this.der.busquedarecursiva(code+"1", buscado);
    
    if(buscarder != ""){
      return buscarder; }
    }
    return "";
  }
}




class MinHeap
{
  // int size;
  ab[] heap;
  int size;
  int max_size;

  MinHeap(int max_size)
  {
    // Creación de Heap vacío de tamaño máximo max_size
    this.max_size = max_size;
    this.size = 0;
    this.heap = new ab[max_size];
  }

  public void insertar( ab arbolchar){
    // Inserta un elemento en el Heap, conservando su estructura
  	this.heap[size] = arbolchar;
  	this.size+=1;
  	int i = this.size - 1;

  	while( i>0 && this.heap[i].frecuencia < this.heap[(i-1)/2].frecuencia ){
  		this.swap((i-1)/2, i);
  		i = (i-1)/2;
  	}

  }

  public ab extraermin(){
    // Extrae el nodo de mínima frecuencia (máxima prioridad)
    // Conserva la condición de Heap
  	if(this.size == 0){
  		System.out.println("HEAP VACIO"); // Deberia levantar excepción :(
  	}

    // Swap y eliminación
  	ab min = this.heap[0];  
  	this.heap[0] = this.heap[this.size - 1];
  	this.heap[this.size-1] = null;

  	int i = 0;
  	boolean insertado = false;
  	this.size-=1;

  	while(!insertado){

  		// Primero comprobamos el hijo izquierdo < padre
  		if( 2*i+1 < this.size  &&  this.heap[i].frecuencia > this.heap[2*i+1].frecuencia){
  			
  			// Si hd < hi < padre!
  			if( 2*i+2 < this.size && this.heap[2*i+1].frecuencia > this.heap[2*i+2].frecuencia){

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
  		else if (2*i+2 < this.size && this.heap[i].frecuencia > this.heap[2*i+2].frecuencia){
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
  		
  		ab aux = this.heap[i];
  		this.heap[i] = this.heap[j];
  		this.heap[j] = aux;
  }

}