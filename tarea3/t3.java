import java.util.*;

public class t3{

public static String[] split( String str){
	String a[] = str.split(" ");         
	return a;
}


public static abb AddDataToABB(String[] o) throws DivisionPor0, ExpresionInvalida{
	PilaABB pila = new PilaABB();

	for(int i = 0; i<=o.length-1; i++){
		if(o[i].equals("*") || o[i].equals("/") || o[i].equals("+")  || o[i].equals("-") ){
			

			abb auxder = pila.desapilar();
			//System.out.format("Primero Desapilo: %s \n",auxder.elemento); 
			abb auxizq = pila.desapilar();
			//System.out.format("Segundo Desapilo: %s \n ",auxizq.elemento ); 
			if(auxder==null || auxizq==null){
				ExpresionInvalida e = new ExpresionInvalida("Expresión Invalida");
				throw e;

			}
			
			if(o[i].equals("/") && auxder.elemento.equals("0")){
			         DivisionPor0 e = new DivisionPor0("División por 0");
			         throw e;
			}		
			//System.out.format("Ahora Apilo     : %s %s %s \n", o[i], auxizq.elemento, auxder.elemento );
			pila.apilar(new abb(o[i],auxizq, auxder ));
			}

			else{
			//System.out.format("Apilo:  %s \n",o[i]);
			pila.apilar(new abb(o[i]));
		}
	}
	return pila.desapilar();
}


public static String RecorrerArbol(abb a){

	if(a.esHoja()){
		return a.elemento;	
	}

	else{

		boolean por_der = a.elemento.equals("*") && (a.der.elemento.equals("+") || a.der.elemento.equals("-") );
		boolean por_izq = a.elemento.equals("*") && (a.izq.elemento.equals("+") || a.izq.elemento.equals("-") || a.izq.elemento.equals("/"));
		
		boolean div_der = a.elemento.equals("/") && (a.der.elemento.equals("+") || a.der.elemento.equals("-") || a.der.elemento.equals("*"));
		boolean div_izq = a.elemento.equals("/") && (a.izq.elemento.equals("+") || a.izq.elemento.equals("-") );

		if((por_der && por_izq) || (div_izq && div_der)) 
		{
			return "(" + RecorrerArbol(a.izq) + ")" + a.elemento + "(" + RecorrerArbol(a.der) + ")";
		}
		

		else if((por_der) || (div_der)){

			return RecorrerArbol(a.izq)  + a.elemento + "(" + RecorrerArbol(a.der) + ")";

		}


		else if((por_izq) || (div_izq)){

			return "(" + RecorrerArbol(a.izq) + ")" + a.elemento + RecorrerArbol(a.der);

		}

		else{

			return  RecorrerArbol(a.izq) + a.elemento + RecorrerArbol(a.der) ;
		}

	}
}


public static abb Derivate(abb a, String var){
	String aux = a.elemento;

	if(aux.equals("+") || aux.equals("-") )
	{ 
		return new abb(aux,Derivate(a.izq, var), Derivate(a.der, var));
	}

	else if(aux.equals("*"))
	{
		abb auxizq = new abb("*", Derivate(a.izq, var), a.der);
		abb auxder = new abb("*", a.izq, Derivate(a.der, var)); 
		return new abb("+", auxizq, auxder);
	}

	else if(aux.equals("/"))
	{
		abb auxizq = new abb("*", Derivate(a.izq, var), a.der);
		abb auxder = new abb("*", a.izq, Derivate(a.der, var)); 
		abb auxtop = new abb("-", auxizq, auxder);
		abb auxbot = new abb("*",a.der,a.der);
		return new abb("/", auxtop, auxbot);
	}

	else if(aux.equals(var)){
			return new abb("1");
	}
	
	else{
			return new abb("0");
	}
}


public static abb SimplificarArbol(abb a){
	String aux = a.elemento;
	if(!(aux.equals("*") || aux.equals("/") || aux.equals("+")  || aux.equals("-")) )
	{
		return a;
	}
	else
	{

		abb sizq = SimplificarArbol(a.izq);
		abb sder = SimplificarArbol(a.der);

		if(sder.elemento.equals("0"))
		{
			if(aux.equals("*"))//|| aux.equals("/")){
			{ 
				return sder;
			}

			else if(aux.equals("+") || aux.equals("-")){
				return sizq;
			}
		}

		else if(sizq.elemento.equals("0")){
			if(aux.equals("*") || aux.equals("/"))
			{
				return sizq;
			}

			else if(aux.equals("+")) // Falta 0 - -> numero negativo
			{
				return sder;
			}
		}


		else if(sizq.elemento.equals("1") && aux.equals("*"))
		{
			return sder;
		}
		
		else if(sder.elemento.equals("1") && (aux.equals("*") || aux.equals("/")))
		{
			return sizq;
		}
		return new abb(aux,sizq,sder);



	}
}
public static void main(String[] args){
	System.out.println("Ingrese Expresion (Notación Polaca Reversa): ");
	Scanner sc = new Scanner(System.in);
	while(sc.hasNextLine()){
		String str 		 = sc.nextLine();
		String strlist[] = split(str);

		try

			{
			abb expresion_inicial = AddDataToABB(strlist);
			// String s = RecorrerArbol(data);
			// System.out.println("Expresión in-fijo:");
			// System.out.println(s);
			
			System.out.println("Ingrese variable: ");
			String variable = sc.nextLine();
			abb derivada = Derivate(expresion_inicial, variable);		
			
			// String sin_s = RecorrerArbol(derivada);
			// System.out.println("Derivada respecto a: " + variable );		
			// System.out.println(ss);
			
			
			abb derivada_simplificada = SimplificarArbol(derivada);
			String output = RecorrerArbol(derivada_simplificada);

			System.out.println("Derivada respecto a: " + variable);		
			System.out.println(output);
		}
		catch(DivisionPor0 exception)
		{
			System.out.println("[ERROR] Expresión ingresada contiene una división por 0! \n\nSiguiente Expresión");
		}
		catch(ExpresionInvalida exception)
		{
			System.out.println("[ERROR] Expresión Ingresada no se encuentra en formato correcto. \nVerifica que la expresión ingresada se encuentra en notación polaca inversa.\n\nSiguiente Expresión");
		}

	}	
	System.out.println("Fin de Ejecución" );// Fin de Ejecución 
	}	
}

class DivisionPor0 extends Exception{
     public DivisionPor0(String mensaje){
         super(mensaje);
     }
 }

class ExpresionInvalida extends Exception{
     public ExpresionInvalida(String mensaje){
         super(mensaje);
     }
 }