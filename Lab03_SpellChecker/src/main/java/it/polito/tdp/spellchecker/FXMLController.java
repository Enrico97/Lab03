package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	Dictionary dizionario;
	String language = "";
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private TextArea Inserisci;

    @FXML
    private Button Ceck;

    @FXML
    private TextArea Risultato;

    @FXML
    private Label errori;

    @FXML
    private Button Clear;

    @FXML
    private Label tempo;

    @FXML
    void Choose(ActionEvent event) {
    	language = ComboBox.getValue();
    }

    @FXML
    void doClearText(ActionEvent event) {
    	Risultato.clear();
    	Inserisci.clear();
    	errori.setText("");
    	tempo.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long startTime = System.nanoTime();
    	dizionario = new Dictionary();
    	if (language.equals("")) {
    		Risultato.setText("Devi selezionare la lingua");
    		return;}
    	dizionario.loadDictnionary(language);
    	Risultato.clear();
    	int i=0;
    	List<String> testo = new ArrayList <>();
    	String inserito = Inserisci.getText().toLowerCase().replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	String s [] = inserito.split(" ");
    	for (String ss : s)
    		if (!ss.equals(""))
    			testo.add(ss);
    	List <RichWord> listaRW = dizionario.spellCheckText(testo);
    	for (RichWord rw : listaRW) {
    		if (rw.isCorretta()==false) {
    			Risultato.appendText(rw.getParola()+"\n");
    			i++;
    }}
    	long elapsedNanos = System.nanoTime()-startTime;
    	errori.setText("Il testo contiene "+i+" errori");
    	tempo.setText("Spellcheck completato in "+elapsedNanos+" nano secondi");
    	
    }

    @FXML
    void initialize() {
    	 assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Inserisci != null : "fx:id=\"Inserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Ceck != null : "fx:id=\"Ceck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Risultato != null : "fx:id=\"Risultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert errori != null : "fx:id=\"errori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Clear != null : "fx:id=\"Clear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tempo != null : "fx:id=\"tempo\" was not injected: check your FXML file 'Scene.fxml'.";
        ComboBox.getItems().addAll("English","Italian");
    }
}
