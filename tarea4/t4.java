import java.util.*;

public class t4{


public static void main(String[] args){
	
	// Crear Arbol
	ArbolCartesiano arbol = new ArbolCartesiano();

	Scanner sc = new Scanner(System.in);

	// Leer Linea a Linea
	while(sc.hasNextLine()){

		String str 		 = sc.nextLine();
		String a[] = str.split(" ");

		// x elemento
		// y prioridad
		int x = Integer.parseInt(a[0]);
		float y = Float.parseFloat(a[1]);
		
		//insertar
		arbol.insertar(x,y);

	}	

	//Fin e imprimir
	arbol.imprimir();
	System.out.println("Costo Promedio" );// Fin de Ejecución 
	System.out.println(arbol.CostoPromedio());
	}	
}

