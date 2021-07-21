package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.QueueIF;

public class TestLatex {

	public static void main(String[] args) {
		DictionaryLatex diccionario = new DictionaryLatex();
		QueueIF<String> cola = new Queue<String>();
		cola.enqueue("anfibio");
		cola.enqueue("alcantarilla");
		cola.enqueue("alcantarillado");
		cola.enqueue("bicicleta");
		cola.enqueue("alicates");
		cola.enqueue("alcazar");
		cola.enqueue("alicatado");
		cola.enqueue("alfeizar");
		cola.enqueue("binario");
		cola.enqueue("bisiesto");
		cola.enqueue("alfombra");
		cola.enqueue("bisectriz");
		diccionario.export(cola,"test.tex");
	}

}
