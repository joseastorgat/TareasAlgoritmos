import java.util.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




public class t5{


public static void main(String[] args){
	
	// Crear HASHMAP
    HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	MinHeap heap = new MinHeap();
	charMap.put('\n',-1);		
	
	try {

		File file = new File("Hamlet.txt");
		FileReader fileReader = new FileReader(file);
		
		int total = 0;
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			charMap.put('\n',charMap.get('\n') + 1);
			for (int i = 0, n = line.length(); i < n; i++) {
			    total+=1;
			    char c = line.charAt(i);
			    
			    if(charMap.containsKey(c)){
					charMap.put(c, charMap.get(c) + 1);}
				else{
					charMap.put(c, 1);
				}
			}
		}

		fileReader.close();
		
		System.out.println("Contents of file:");

		int suma = 0;
		for (Character name: charMap.keySet()){

            // String key = name.toString();
            // String value = charMap.get(name).toString();  
            // System.out.println('"'+ key + '"'+ " : " + value);  

			suma +=  charMap.get(name);
            ab AB = new ab(name, charMap.get(name));
            heap.insertar(AB);

        } 

        int size = heap.size;

        for(int i = 0; i < size - 1 ; i++){

        	ab min1 = heap.extraermin();
        	ab min2 = heap.extraermin();

        	ab nuevo = new ab(min1, min2);

        	heap.insertar(nuevo);
        }


       	ab last = heap.extraermin();
        String value = Integer.toString(last.frecuencia);  
        String asd =  Integer.toString(charMap.size());
        String zsize = Integer.toString(size);

        System.out.println( "characters: " +   zsize + " HashMap: " + asd );
        System.out.println( "TOTAL1: " +   total + " TOTALSUMA: " +  suma + "   TOTALFINAL:  " + last.frecuencia);

        last.Lectura("");

       	// ab last2 = heap.extraermin();


	}catch (IOException e) {
		e.printStackTrace();
	}


}}

