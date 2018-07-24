Tarea 4 Algoritmos y Estructuras de Datos


ArbolCartesiano.java: Contiene la clase NodoArbolCartesiano y ArbolCartesiano, que contiene los métodos solicitados.

t4.java: Contiene el programa que ocupa estas clases para realizar lo solicitado. Desde un archivo de texto lee los nodos que componen el árbol, al finalizar imprime el árbol y el costo de búsqueda promedio.

	>> java t4 < Ejemplos/ejemplo.txt
	>> java t4 < Ejemplos/ejemplo1.txt
	>> java t4 < Ejemplos/ejemplo2.txt


treap.java: Implementación de Treaps usando ArbolCartesiano.java.

	>> java treap 

	o 

	>> java treap exp


Si se desea reproducir los gráficos de la tarea (se debe tener matplotlib): 

	1.- Si se desea generar los datos nuevamente: eliminar el archivo costos.csv de la carpeta graficos

	>> java treap exp >> graficos/costos.csv 
	>> cd graficos

En el directorio graficos!

	>> python graficos.py 