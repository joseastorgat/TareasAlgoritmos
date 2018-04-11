import java.util.*;

public class PilaArena{
	public static int[][] torre(int n , int m) {
        System.out.format("\n ### N = %d ####\n",n);
		int tablero[][] = new int[m][m]; //tablero donde se encuentran las pilas de arena
		int r=(m/2);
		tablero[r][r] = n; // la torre inicial se encuentra en el medio del tablero
		long contador_derrumbes=0; // contador de cuantas veces se ha caído una pila de arena
		long contador_derrumbes_ult_it = 0;// variable que guarda el último contador
		long iterador = 0; //contador para la cantidad de iteraciones que hara el ciclo while
		boolean cae_pila_arena = true;
		long tiempo_inicial = System.nanoTime(); // inicio de cronometro para tiempo de ejecución
		while(cae_pila_arena){ // itera otra vez si es que en el ciclo pasado aun han caido pilas de arena

			for (int i = 0; i < m; i++){		// El primer índice recorre las filas.
				for (int j = 0; j < m; j++){ //el segundo recorre las columnas
					if(tablero[i][j]>=4){
						tablero[i][j]-=4;    //se quitan 4 granos que se derrumban
						tablero[i+1][j]+=1;  // los 4 granos se distribuyen de manera uniforme entre los vecinos
						tablero[i-1][j]+=1;  // 1 para cada vecino
						tablero[i][j+1]+=1;
						tablero[i][j-1]+=1;
						contador_derrumbes+=1; //cayo una pila de arena, se suma al contador
					}	
				}
			}
		iterador +=1; //se suma una iteracion
		cae_pila_arena = !(contador_derrumbes_ult_it == contador_derrumbes); // se comprueba si durante la última iteracion ha caido una pila de arena 
		contador_derrumbes_ult_it = contador_derrumbes; // se guarda la cuenta de la última iteracion
		}
		long tiempo_final = System.nanoTime() - tiempo_inicial; //fin cronometro para tiempo de ejecucion
		System.out.format("Numero de derrumbes de torres de arena: %d \n",contador_derrumbes_ult_it);
		System.out.format("Numero de iteraciones hasta que ninguna pila cae: %d \n",iterador);
		System.out.format("Tiempo de ejecucion: %d\n",tiempo_final);
		return tablero;
	}

	public static int get_tablero_size(int n){
		// calculo de los lados de la matriz
		double f = Math.sqrt(n);
		int m = (int) f;
		if(m%2==0){
			m+=1;} // esto es para asegurar que la torre siempre se encontrara justo al centro
		return m;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("n?");
		int n = sc.nextInt();
        sc.nextLine(); 
        int m = get_tablero_size(n);
        int tablero[][] = torre(n,m);
     	Ventana ventanita = new Ventana(m, "matriz");
		ventanita.mostrarMatriz(tablero);   

	}

	public static void test(String[] args) {
		for (int i = 7; i<20; i++){
			int N= (int) Math.pow(2,i);
	        int m = get_tablero_size(N);
			int tab[][] = torre(N,m);
		}
	}
}









