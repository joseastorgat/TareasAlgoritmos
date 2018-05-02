import java.util.*;

public class t2{
public static int[] minMult(int[] p, int[][] mult_min , int i, int j ){
	int r[] = new int[2]; // variable para entregar ambos resultados (k y #mult_min)
	r[0] = Integer.MAX_VALUE; //max int posible para obtener algo menor
	r[1] = i;
	for (int k = i; k <= j-1; k++){								      // estos m siempre serán conocidos
		int q = mult_min[i][k] + mult_min[k+1][j] + p[i-1]*p[k]*p[j]; // debido al orden que se itera
		if (q < r[0])	// si es menor que el mínimo actual
		{
			r[0] = q; //actualizar valores 
			r[1] = k;} 
	}
	return r;
}
public static int[][] MultiplicacionOptima(int[] dim){
	int n = dim.length - 1;
	int mult_min[][] = new int[n+1][n+1];  // #mult_min almacenamiento
	int k_optimos[][] = new int[n+1][n+1]; // pos_k que minimizan mult_min almacenamiento
	for (int l = 1; l <= n-1; l++) {
		for (int i = 1; i <= n - l ; i++){
			int j = i + l ;
			int r[] = minMult(dim, mult_min , i, j);
			mult_min[i][j] = r[0];
			k_optimos[i][j] = r[1];
		}
	}
	return k_optimos;
}

public static String ParentesisOptimos(int[][] k_optimos, int i, int j){
	if(i== j)
		{ return ".";
	}	
	else{
		int k = k_optimos[i][j];
		return "("+ParentesisOptimos(k_optimos, i , k)+ParentesisOptimos(k_optimos,k+1,j)+")";
	}
}

public static int[] split( String str){  //Pasar input de String a un arreglo de ints[]
	String a[] = str.split(" ");         // divide el string en arreglo de strings
	int p[] = new int[a.length]; 
	for(int i=0; i<a.length; i++){ 
		try{ // Captar si se ingresa un caracter no válido (no int) 
			p[i] = Integer.parseInt(a[i]);}  // convertir string a int
    	catch(NumberFormatException excepcion){
     		p[i] = 0;}
	}
	return p;
}

public static boolean checkInput(int[] p){
	for(int i = 0; i<=p.length-1; i++){
		if(p[i]==0){
			return false;
		}
	}
	return true;
}

public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	while(sc.hasNextLine()){
		String matrices = sc.nextLine();
		int dimensiones[] = split(matrices);
		if(!checkInput(dimensiones)){
			System.out.println("Error en input: Corrobora que sean solo enteros mayores a 0");
			return;
		}
		else{
			// long tiempo_inicial = System.nanoTime();
			int s[][] = MultiplicacionOptima(dimensiones);
			String S = ParentesisOptimos(s, 1, dimensiones.length-1);
			// long tiempo_final = System.nanoTime() - tiempo_inicial;
			// System.out.println("Parentización Óptima:");
			System.out.println(S);
			// System.out.format("Tiempo de Ejecución: %d \n", tiempo_final);
		}
	}
	System.out.println("Fin de Ejecución" );// Fin de Ejecución 
}


public static void printMatriz(int[][] matriz){
	int l = matriz.length-1;
	for(int i =0; i <= l; i++){
		for(int j=0; j<=l; j++){
			System.out.print(matriz[i][j]);
		}
		System.out.println("");
	}
}
}

