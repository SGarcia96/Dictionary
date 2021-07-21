package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

public class WordList {
	private ListIF<WordListN> wordList;
	
	public WordList() {
Main.WORDLISTCREADA = true;

		this.wordList = new List<WordListN>();
	}
	/* añade una nueva palabra a la estructura*/
	public void add(String word) {
		int pos = 1;
		int wordSize = word.length();
		
		if (wordList.isEmpty()) {
			WordListN wordN = new WordListN(wordSize);
			wordN.add(word);
			wordList.insert(pos, wordN);
		} else {
			WordListN wordN;
			while (pos <= wordList.size()) {
				if (wordList.get(pos).getWordSize()==wordSize) { 
					wordList.get(pos).add(word);
					break;
				} else if (wordList.get(pos).getWordSize() > wordSize && pos==wordList.size()) {
					wordN = new WordListN(wordSize);
					wordN.add(word);
					wordList.insert(pos+1, wordN);
					break;
				} else if(wordList.get(pos).getWordSize() < wordSize) {
					wordN = new WordListN(wordSize);
					wordN.add(word);
					wordList.insert(pos, wordN);
					break;
				}
				pos++;
			}
		}
	}
	/* construye un string con el contenido de la estructura */
	public String toString() {
		StringBuilder salida = new StringBuilder();
		for ( int pos = 1 ; pos <= this.wordList.size() ; pos++ ) {
			salida.append(this.wordList.get(pos).toString());
		}
		return salida.toString();
	}
}
