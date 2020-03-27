package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List <String> dizionario = new LinkedList <> ();
	
	public void loadDictnionary (String language) {
	
	if (language.compareTo("English")==0) {
		try {
			FileReader fr = new FileReader ("/Users/enricomarando/git/Lab03/Lab03_SpellChecker/src/main/resources/English.txt");
			BufferedReader br = new BufferedReader (fr);
			String word;
			while ((word = br.readLine()) != null) 
				dizionario.add(word.toLowerCase());
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}}
	else { 
		try {
			FileReader fr = new FileReader ("/Users/enricomarando/git/Lab03/Lab03_SpellChecker/src/main/resources/Italian.txt");
			BufferedReader br = new BufferedReader (fr);
			String word;
			while ((word = br.readLine()) != null)
				dizionario.add(word);
			br.close();
		} catch (IOException ee) {
			System.out.println("Errore nella lettura del file");
		}
	}

}
	public List <RichWord> spellCheckText (List <String> inputTextList) {
		List <RichWord> lista = new ArrayList<>();
		for (String s : inputTextList) {
			RichWord r = new RichWord (s);
				if (dizionario.contains(s))
					r.setCorretta(true);
				lista.add(r);
				}
		return lista;
				}
}
