import java.util.*;

//Lectira y Escritura de Archivos
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//Visualizar Floats con determinado número de decimales
import java.text.DecimalFormat;

public class t5{

public static HashMap<Character, Integer> LeerArchivo(String infilename){
    
    HashMap<Character, Integer> charMap = new HashMap<Character, Integer>(); // HashMap para chars y frecuecias
	charMap.put('\n',0); //  Se lee linea a linea manualmente

	try {
		File file = new File(infilename);
		FileReader fileReader = new FileReader(file);		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		
		while ((line = bufferedReader.readLine()) != null) {

			charMap.put('\n',charMap.get('\n') + 1); // Cada nueva linae
			for (int i = 0, n = line.length(); i < n; i++) { // Por cada char en una nueva linea
			    char c = line.charAt(i); // char
			    
			    if(charMap.containsKey(c)){ // Si el char ya esta ingresado en el hasmap aumentamos su frecuencia en 1 sino lo ingresamoss con frecuencia 1
					charMap.put(c, charMap.get(c) + 1);}
				else{
					charMap.put(c, 1);
				}
			}
		}
		fileReader.close();
	}

	catch (IOException e) {
		e.printStackTrace();
	}

	System.out.println(infilename);
	return charMap;
}


public static void main(String[] args){
	
	// Lectura de nombres de archivo de Entrada y Salida
	Scanner sc = new Scanner(System.in);
	System.out.println("Ingrese Nombre Archivo Entrada");
	String infilename = sc.nextLine();
	System.out.println("Ingrese Nombre Archivo Salida");
	String outfilename = sc.nextLine();


	// Crear HASHMAP con frecuencia de cada caracter en archivo 
	HashMap<Character, Integer>	charMap= LeerArchivo(infilename);
	

	int total_chars = 0; // Total de caracteres en el archivo
	int n_char = charMap.size(); // Cantidad de Caracteres
	MinHeap heap = new MinHeap(n_char);	// Heap para utilizarlo como cola prioridad


	for (Character name: charMap.keySet()){
		total_chars +=  charMap.get(name); // sumar frecuencia a cantidad de chars
        ab AB = new ab(name, charMap.get(name)); // 
        heap.insertar(AB);
    } 

    // Algoritmo de Compresión de Huffman
    // Se extraen los 2 nodos de mínima frecuencia
    // Estos se combinan en un arbol cuya raiz tiene la suma de ambas frecuencias
    // Así consecutivamente hasta que quede 1 solo

    int i_char = n_char;     // Iterador sobre chars! 
	ab[] charsOrdenados = new ab[n_char]; // Lista para guardar los char ordenados por frecuecnias
  
    for(int i = 0; i < n_char - 1 ; i++){
    	
    	ab min1 = heap.extraermin(); // Extraer primer mínimo
    	ab min2 = heap.extraermin(); // Extraer segundo mínimo
    	
		// Corroborar si alguno de los char no ha sido insertarlo en la lista de char ordenados por frecuencia e insertarlo
    	if( min1.esHoja()){	 
			i_char-=1;
			charsOrdenados[i_char] = min1; 
		}
		if( min2.esHoja()){	
			i_char-=1;
			charsOrdenados[i_char] = min2; 
		}
		
    	ab nuevo = new ab(min1, min2); // Se unen ambos char o arboles de char en uno sólo cuya frecuencia es la suma de ambas frecuencias
    	heap.insertar(nuevo); // Se inserta en el MinHeap
    }

    // Del proceso anterior 
   	ab last = heap.extraermin();

	FileWriter fw = null; // Comenzamos escritura
	BufferedWriter bw = null;

	try {

		int ascii_size = 0;
		int huffman_size = 0; 

		fw = new FileWriter(outfilename);
		bw = new BufferedWriter(fw);
		
		String header = "**"+infilename+" Huffman Compression **"+"\nCHAR  | ASCII | Frequency | Huffman Code \n \n"; // Cabecera del archivo 
	    bw.write(header);
				
		DecimalFormat formatter = new DecimalFormat("000"); // Para escribir float con 6 decimales justos
	    // Recorrimos la lista de chars ordenados por frecuencia (mayor a menor)
	    
		char saltolinea = '\n';	
	    for(int i = 0; i < n_char - 1 ; i++){ 
	    	char name = charsOrdenados[i].elemento; 
	    	String value = String.format( "%.6f", (float)100*charsOrdenados[i].frecuencia/ total_chars) + "%"; // Porcentaje de Frecuencia
	    	String code = last.buscar(name); // Codigo Huffman del char  ( en Binario)
			String ascii = formatter.format((int) name); // Codigo Ascii ( en Decimal)

			ascii_size+= 8*charsOrdenados[i].frecuencia; // Cada caracter ascii tiene tamaño 1 byte = 8 bits
			huffman_size+= code.length()*charsOrdenados[i].frecuencia; // caracter comprimido ocupa tamaño = largo del codigo en binario

			String line;

			if(name!=saltolinea){
				line = "'"+name + "' \t " + ascii + "\t" + value + "\t" + code+"\n"; 
			}
			else{
				line = "'\\n' \t " + ascii + "\t" + value + "\t" + code+"\n"; 

			}

        	bw.write(line);   // Se escribe la linea en el archivo
		}

		System.out.println(" \n \n **"+infilename+"**");
		System.out.println("Caracteres: " + n_char + " diferentes");
		System.out.println("Total Caracteres: " + total_chars );

		System.out.println("Tamaño original ASCII: " + ascii_size + "bits");
		System.out.println("Tamaño comprimido Huffman: " + huffman_size + "bits");

	} catch (IOException e) {
		e.printStackTrace();

	} finally {

		try {

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}


}


}

