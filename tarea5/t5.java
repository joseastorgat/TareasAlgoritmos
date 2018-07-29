import java.util.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DecimalFormat;
public class t5{

public static HashMap<Character, Integer> LeerArchivo(String infilename){
    HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	MinHeap heap = new MinHeap();
	charMap.put('\n',-1);
	

	try {
		File file = new File(infilename);
		FileReader fileReader = new FileReader(file);		
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			charMap.put('\n',charMap.get('\n') + 1);
			for (int i = 0, n = line.length(); i < n; i++) {
			    char c = line.charAt(i);
			    
			    if(charMap.containsKey(c)){
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

	return charMap;

}


public static void main(String[] args){
	
	Scanner sc = new Scanner(System.in);

	System.out.println("Ingrese Nombre Archivo Entrada");
	String infilename = sc.nextLine();

	System.out.println("Ingrese Nombre Archivo Salida");
	String outfilename = sc.nextLine();


	// Crear HASHMAP
	HashMap<Character, Integer>	charMap= LeerArchivo(infilename);
	MinHeap heap = new MinHeap();


	int suma = 0;
	int char_numb = charMap.size();
	char[] charsOrdenados = new char[char_numb];
	
	for (Character name: charMap.keySet()){
		suma +=  charMap.get(name);
        ab AB = new ab(name, charMap.get(name));
        heap.insertar(AB);
    } 

    // Algoritmo de Huffman
    int size = heap.size;
    for(int i = 0; i < size - 1 ; i++){
    	ab min1 = heap.extraermin();
    	ab min2 = heap.extraermin();
    	if( min1.esHoja()){	
			char_numb-=1;
			charsOrdenados[char_numb] = min1.elemento; 
		}
		if( min2.esHoja()){	
			char_numb-=1;
			charsOrdenados[char_numb] = min2.elemento; 
		}
		
    	ab nuevo = new ab(min1, min2);

    	heap.insertar(nuevo);
    }


   	ab last = heap.extraermin();

	FileWriter fw = null;
	BufferedWriter bw = null;

	try {

		fw = new FileWriter(outfilename);
		bw = new BufferedWriter(fw);

	    bw.write("CHAR | ASCII | Huffman Code | Frequency\n");
		
		DecimalFormat formatter = new DecimalFormat("000");
	    for(int i = 0; i < size - 1 ; i++){
	    	char name = charsOrdenados[i];
	    	String value = String.format( "%.5f", (float)charMap.get(name)/ suma);
	    	String code = last.buscar(name);
			String ascii = formatter.format((int) name);
	        bw.write("'"+name + "' " + ascii + " " + value + " " + code+"\n");  
		}


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

