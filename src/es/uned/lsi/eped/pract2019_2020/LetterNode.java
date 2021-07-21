package es.uned.lsi.eped.pract2019_2020;

public class LetterNode extends Node {
	
	char letra;
	
	public LetterNode (char letra) {
		this.letra = letra;
	}

	public NodeType getNodeType() {
		NodeType letterNode = NodeType.LETTERNODE;
		return letterNode;
	}
	
	public char getLetra() {
		return letra;
	}
	
}
