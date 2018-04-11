import java.util.*;

public class PilaArenar{
	public static int[][] torre(long n , long m) {
        System.out.format("\n ### N = %d ####\n",n);
		
		int tablero[][] = new int[m][m]; //tablero donde se encuentran las pilas de arena
		
		long r=(m/2);
		
		System.out.format("N:  %d  -- M: %d -- R %d  \n",n,m,r);
		// long contador_derrumbes=0; // contador de cuantas veces se ha caído una pila de arena
		// long contador_derrumbes_ult_it = 0;// variable que guarda el último contador
		// long iterador = 0; //contador para la cantidad de iteraciones que hara el ciclo while
		// boolean cae_pila_arena = true;

		long tiempo_inicial = System.nanoTime(); // inicio de cronometro para tiempo de ejecución
		tablero[r][r] = n; // la torre inicial se encuentra en el medio del tablero
		tablero = recursion(tablero,r,r);		

		long tiempo_final = System.nanoTime() - tiempo_inicial; //fin cronometro para tiempo de ejecucion

		return tablero;
	}

	public static long get_tablero_size(long n){
		// calculo de los lados de la matriz
		double f = Math.sqrt(n);
		long m = (long) f;
		if(m%2==0){
			m+=1;} // esto es para asegurar que la torre siempre se encontrara justo al centro
		return m;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("n?");
		int n = sc.nextInt();
        sc.nextLine(); 
        long n = (long) n;
        long m = get_tablero_size(n);
        int tablero[][] = torre(n,m);
     	Ventana ventanita = new Ventana(750, "matriz");
		ventanita.mostrarMatriz(tablero);   

	}

	public static void test(String[] args) {
		for (int i = 7; i<20; i++){
			long N= (int) Math.pow(2,i);
	        long m = get_tablero_size(N);
			int tab[][] = torre(N,m);
		}
	}


	public static int[][] recursion(int tablero[][], long i, long j){

		if(tablero[i][j]>=4){
			int aux = tablero[i][j]/4;
			tablero[i][j] = tablero[i][j]%4;
			tablero[i+1][j]+=1*aux;
			tablero[i-1][j]+=1*aux;
			tablero[i][j+1]+=1*aux;
			tablero[i][j-1]+=1*aux;
		// contador_derrumbes+=1;
			int tab1[][] = recursion(tablero, i+1,j);
			
			int tab2[][] = recursion(tab1, i-1,j);
			
			int tab3[][] = recursion(tab2, i,j+1);
			
			return recursion(tab3, i,j-1);
		}
		else{
			return tablero;
		}

	}
}


