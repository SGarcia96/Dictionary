package es.uned.lsi.eped.pract2019_2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import es.uned.lsi.eped.DataStructures.QueueIF;
import es.uned.lsi.eped.pract2019_2020.Node.NodeType;
import es.uned.lsi.eped.DataStructures.GTreeIF;

/**********************************************
 * INSTRUCCIONES:                             *
 *                                            *
 * 1) Cambiar la visibilidad del atributo     *
 * GTree<Node> dict; de la clase Dictionary   *
 * de private a protected.                    *
 *                                            *
 * 2) En el lugar indicado, invocar el getter *
 * de la clase LetterNode que nos devuelve el *
 * carácter contenido en dicho nodo.          *
 *                                            *
 **********************************************/

public class DictionaryLatex extends Dictionary {
	
	public void export(QueueIF<String> pCola, String pFile) {
		try {
			File file = new File(pFile);
	        PrintStream stream = new PrintStream(file);
	        System.setOut(stream);
			System.out.println("\\documentclass[28pt,a4paper,landscape]{article}");
			System.out.println("\\usepackage[center]{qtree}");
			System.out.println("\\begin{document}");
			System.out.println("\\pagestyle{empty}");
			while(!pCola.isEmpty()) {
				String word = pCola.getFirst();
				pCola.dequeue();
				insert(word);
				System.out.print("\\Tree [.{RN}  ");
				exportTree(dict);
				System.out.println(" ]");
				System.out.println("\\newpage");
			}
			System.out.println("\\end{document}");
		}
		catch(FileNotFoundException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	private void exportTree(GTreeIF<Node> node) {		
		for(int pos = 1; pos <= node.getNumChildren(); pos++) {
			if (node.getChild(pos).getRoot().getNodeType() == NodeType.WORDNODE) {
				System.out.print("WN ");
			}else {
				GTreeIF<Node> hijoActual = node.getChild(pos);
				LetterNode nodoLetra = (LetterNode)hijoActual.getRoot();
				/* Invocar el getter de LetterNode que nos devuelve el carácter */
				System.out.print("[." + nodoLetra.getLetra() + " ");
				exportTree(hijoActual);
				System.out.print(" ] ");
			}
		}
	}

}
