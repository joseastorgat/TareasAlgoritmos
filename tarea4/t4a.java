import java.util.*;

public class t4a{


public static void main(String[] args){

	Scanner sc = new Scanner(System.in);
	String str 		 = sc.nextLine();
	String a[] = str.split(" ");
	NodoAC arbol = new NodoAC(Integer.parseInt(a[0]),Float.parseFloat(a[1]));
	System.out.println(a[0] + " " +  a[1]);

	System.out.println(	arbol.imprimir());

	while(sc.hasNextLine()){

		str 		 = sc.nextLine();
		a = str.split(" ");

		int x = Integer.parseInt(a[0]);
		float y = Float.parseFloat(a[1]);

		System.out.println(a[0] + " " +  a[1]);

		arbol.insertar(x,y);

		// System.out.println(	arbol.imprimir());

	}	

	System.out.println(	arbol.imprimir());
	System.out.println("Fin de Ejecución" );// Fin de Ejecución 
	}	
}

