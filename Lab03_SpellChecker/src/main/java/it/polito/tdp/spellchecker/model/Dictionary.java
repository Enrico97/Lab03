package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List <String> dizionario = new ArrayList <> ();
	List <String> dizionario1 = new ArrayList <> ();
	
	public void loadDictnionary (String language) {
	
	if (language.compareTo("English")==0) {
		try {
			FileReader fr = new FileReader ("/Users/enricomarando/git/Lab03/Lab03_SpellChecker/src/main/resources/English.txt");
			BufferedReader br = new BufferedReader (fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word.toLowerCase());
			dizionario1.add(word.toLowerCase());}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}}
	else { 
		try {
			FileReader fr = new FileReader ("/Users/enricomarando/git/Lab03/Lab03_SpellChecker/src/main/resources/Italian.txt");
			BufferedReader br = new BufferedReader (fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word);
			dizionario1.add(word.toLowerCase());}
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
	public List <RichWord> spellCheckTextDichotomic (List <String> inputTextList) {
		List <RichWord> lista = new ArrayList<>();
		List <String> rimuovi = new ArrayList<>();
		
		int i = dizionario.size()/2;
		for (String s : inputTextList) {
			RichWord r = new RichWord (s);
			for (int x=0; x<dizionario1.size()/2; i++) {
		if (dizionario1.get(i).equals(r.getParola())) {
			r.setCorretta(true);
			lista.add(r);
		}
		if (dizionario1.get(i).compareTo(s)<0){
			for (int j=0; j<(dizionario.size()/2); j++) {
				for (String str : dizionario) 
					rimuovi.add(str);
		}
			dizionario.removeAll(rimuovi);
			 i = dizionario.size()/2;
		}
		if (dizionario1.get(i).compareTo(s)>0){
			for (int j=dizionario.size(); j<(dizionario.size()/2); j--) {
				for (String str : dizionario) 
					rimuovi.add(str);
		}
			dizionario.removeAll(rimuovi);
			i = dizionario.size()/2;
		}}}
		return lista;
	}
	}
