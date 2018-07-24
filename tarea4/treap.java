import java.util.*;
import java.lang.Math;

public class treap{


public static float costoTreap( int l){
	// Metodo para crear un Treap con l nodos
	// Los elementos son los naturales desde 01a l 

	ArbolCartesiano arbol = new ArbolCartesiano();

	int n[]=new int[l];
	int i =0;

	// Crea una lista con numeros de 1 hasta l
	for(i=0;  i<l; i++){
		n[i] = i+1; }
	
	//Se desordena la lista
	for(i=1;  i<=l; i++){
		int rand = (int) (Math.random() * (l - i));
		int temp = n[rand];
		n[rand] = n[l-i];
		n[l-i] = temp;
	}

	//Se inserta la lista al arbol
	for(i=0;  i<l; i++){
		arbol.insertar(n[i], (float) Math.random()*10);
	}

	return arbol.CostoPromedio();

}



public static void experimentos(){
	//Metodo para realizar una serie de experimientos con Treaps
	// Se calcula el costo de busqueda promedio para Treaps con n = 1024, 2048 .... nodos 10 veces por cada n


	// Matriz para almacenar costos
	float costos[][] =new float[10][7];

	for(int k=0;k<7;k++)
	{
	
		System.out.println("");		

		// int l =  1024 * (int)(Math.pow(2,k+1));
		int l =  1024 * (int)(Math.pow(2,k));
		System.out.print(l);

		for(int j=0;j<10;j++){

			costos[j][k] = costoTreap(l);
			System.out.print(" , ");
			System.out.print(costos[j][k]);
		}
	}
	System.out.println("");		
}


public static void main(String[] args){

	if(args[0].equals("exp")){
		experimentos();
	}
	else{
		Scanner sc = new Scanner(System.in);
		System.out.print("Numero de Nodos:  " );
		int l = sc.nextInt();
		float c = costoTreap(l);

		System.out.print("Costo Promedio: " );
		System.out.println(c);
	}

}
}

