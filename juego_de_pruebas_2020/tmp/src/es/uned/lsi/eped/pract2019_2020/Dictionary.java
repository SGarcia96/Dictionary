package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.pract2019_2020.Node.NodeType;

public class Dictionary {

	protected GTree<Node> dict; /* El diccionario es un árbol general de nodos */

	/* Constructor de la clase */
	public Dictionary() {
		dict = new GTree<Node>();
		dict.setRoot(new RootNode());
	}
	
	/* Método de inserción de una nueva palabra en el diccionario */
	public void insert(String word) {
		/* Insertamos la palabra a partir del nodo raíz del árbol */
		insertInTree(word,this.dict);
	}
	
	/* Método privado llamado por el anterior */
	private void insertInTree(String word, GTreeIF<Node> node) {
		GTreeIF<Node> subArbol = null;
		LetterNode letterNode;

		for(int i=1; i<=node.getNumChildren() && word.length()>0; i++) { 
			subArbol = node.getChild(i);

			if(subArbol.getRoot().getNodeType() != NodeType.WORDNODE){
				letterNode = (LetterNode) subArbol.getRoot();
				
				if(letterNode.getLetra() < word.charAt(0) && i == node.getNumChildren()){
					subArbol = new GTree<Node>();
					letterNode = new LetterNode(word.charAt(0));
					subArbol.setRoot(letterNode);
					node.addChild(i+1,subArbol);
				}
				else if	(letterNode.getLetra() > word.charAt(0)){
					subArbol = new GTree<Node>();
					letterNode = new LetterNode(word.charAt(0));
					subArbol.setRoot(letterNode);
					node.addChild(i,subArbol);
					i = node.getNumChildren();

				}
				else if(letterNode.getLetra() == word.charAt(0)){
					i = node.getNumChildren();
				}

			}
			else if(subArbol.getRoot().getNodeType() == NodeType.WORDNODE && node.getNumChildren()==1){
				subArbol = new GTree<Node>();
				letterNode = new LetterNode(word.charAt(0));
				subArbol.setRoot(letterNode);
				node.addChild(2,subArbol);
			}

		}

		if(node.getNumChildren()==0){
			if (word.length()==0) {
				subArbol = new GTree<Node>();
				subArbol.setRoot(new WordNode());
				node.addChild(1,subArbol);
				return; 
			}else{
				subArbol = new GTree<Node>();
				letterNode = new LetterNode(word.charAt(0));
				subArbol.setRoot(letterNode);
				node.addChild(1,subArbol);
			}

		}else{
			if(word.length()==0 && node.getChild(1).getRoot().getNodeType() == NodeType.WORDNODE){
				return; 
			}
			else if(word.length()==0 && node.getChild(1).getRoot().getNodeType() != NodeType.WORDNODE){
				subArbol = new GTree<Node>();
				subArbol.setRoot(new WordNode());
				node.addChild(1,subArbol);
				return; 
			}
		}
		word = removeLetra(word,word.charAt(0)); 
		insertInTree(word,subArbol);
	}

	/* Método público de búsqueda de todas las palabras a partir de una secuencia */
	public WordList search(String sequence) {
		WordList salida = new WordList();           /* Variable donde construiremos la salida */
		searchInTree(sequence,"",this.dict,salida); /* Construimos la salida recursivamente */
		return salida;
	}
	
	/* Método privado llamado por el anterior */
	private void searchInTree(String sequence, String word,
							  GTreeIF<Node> node, WordList salida) {
		
		GTreeIF<Node> subArbol;
		LetterNode letterNode;
		String wordAux = word;
		String seqAux = sequence;
		boolean encontrado = false;

		for (int i=1; i<=node.getNumChildren(); i++) { 
			subArbol = node.getChild(i);
			word = wordAux;
			sequence = seqAux;

			if (subArbol.getRoot().getNodeType() == NodeType.LETTERNODE) {
				letterNode = (LetterNode) node.getChild(i).getRoot();

				for (int j=0; j<sequence.length(); j++) { 
					if (sequence.charAt(j) == letterNode.getLetra()) { 
						word += sequence.charAt(j);
						sequence = removeLetra(sequence, sequence.charAt(j));
						encontrado = true;
						break;
					}
				}
				if(encontrado) {
					searchInTree(sequence, word, subArbol, salida);
				}
				encontrado = false;
				
			} else if (subArbol.getRoot().getNodeType() == NodeType.WORDNODE) {
				salida.add(word);
			}
		}
	}
	
	/* Método público de búsqueda de todas las palabras de tamaño size a partir de una secuencia */
	public WordListN search(String sequence, int size) {
		WordListN salida = new WordListN(size);           /* Variable donde construiremos la salida */
		searchInTreeN(sequence,"",this.dict,salida,size); /* Construimos la salida recursivamente */
		return salida;
	}
	
	/* Método privado llamado por el anterior */
	private void searchInTreeN(String sequence, String word,
							   GTreeIF<Node> node, WordListN salida,
							   int size) {

		GTreeIF<Node> subArbol;
		LetterNode letterNode;
		String wordAux = word;
		String seqAux = sequence;
		boolean encontrado = false;

		for (int i=1; i<=node.getNumChildren(); i++) { 
			subArbol = node.getChild(i);
			sequence = seqAux;
			word = wordAux;

			if (subArbol.getRoot().getNodeType() == NodeType.LETTERNODE  && size>0) {
				letterNode = (LetterNode) node.getChild(i).getRoot();

				for (int j=0; j<sequence.length(); j++) { 
					if (sequence.charAt(j) == letterNode.getLetra()) {
						word += sequence.charAt(j);
						sequence = removeLetra(sequence, sequence.charAt(j));
						encontrado = true;
						break;
					}
				}
				if(encontrado) {
					searchInTreeN(sequence, word, subArbol, salida, size-1);
				}
				encontrado = false;

			} else if (subArbol.getRoot().getNodeType() == NodeType.WORDNODE && size==0) {
				salida.add(word);
				break;
			}
		}
	}

	private String removeLetra(String sequence, char letra){
		String salida = "";
		boolean encontrado = false;
		int pos = 0;
		while(pos < sequence.length()){
			if(sequence.charAt(pos) == letra && !encontrado){
				encontrado=true;
			}
			else {
				salida += sequence.charAt(pos);
			}
			pos++;
		}
		return salida;
	}
}
