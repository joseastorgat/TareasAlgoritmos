import java.util.*;

public class t4{


public static void main(String[] args){
	ArbolCartesiano arbol = new ArbolCartesiano();

	Scanner sc = new Scanner(System.in);
	while(sc.hasNextLine()){

		String str 		 = sc.nextLine();
		String a[] = str.split(" ");

		int x = Integer.parseInt(a[0]);
		float y = Float.parseFloat(a[1]);

		System.out.println(a[0] + " " +  a[1]);


		try{		arbol.insertar(x, y);
			arbol.imprimir();}
		catch(java.lang.NullPointerException exception){System.out.println("ERROR" );}

	}	

	arbol.imprimir();
	// System.out.println();
	System.out.println("Fin de Ejecución" );// Fin de Ejecución 
	}	
}

