package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.QueueIF;

public class WordListN {
	/* Atributos de la clase con la estructura adecuada */
	private int wordSize;
	/* Atributos de la clase con la estructura adecuada */
	private QueueIF<String> wordN;
	
	public WordListN(int size) {
		wordSize = size;
		this.wordN = new Queue<String>();
	}
	/* añade una palabra a la estructura */
	public void add(String word) {
		wordN.enqueue(word);
	}
	
	/* devuelve el tamaño de las palabras en la estructura*/
	public int getWordSize() {
		return wordSize;
	}
	
	/* construye un toString con el contenido de la estructura*/
	public String toString() {
		StringBuilder salida = new StringBuilder();
		int numPalabras = wordN.size(); /* Longitud de la secuencia de palabras */
		salida.append("-Palabras de ");
		salida.append(this.getWordSize());
		salida.append(" letra");
		if ( this.getWordSize() > 1 ) { salida.append('s'); }
		salida.append(": ");
		for (int pos = 1 ; pos <= numPalabras ; pos++) {
			/* Estas líneas dependen de la estructura escogida */
			String word = wordN.getFirst(); /* Obtener la siguiente palabra */
			/* Avanzar a la siguiente sin destruir la estructura */
			wordN.enqueue(wordN.getFirst());
			wordN.dequeue();
			/* Estas líneas dependen de la estructura escogida */
			salida.append(word);
			if ( pos < numPalabras ) {
				salida.append(", ");
			}
		}
		salida.append('\n');
		return salida.toString();
	}
}
