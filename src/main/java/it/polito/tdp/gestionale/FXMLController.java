package it.polito.tdp.gestionale;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gestionale.model.Corso;
import it.polito.tdp.gestionale.model.Model;
import it.polito.tdp.gestionale.model.StampaCorsoStudenti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

//controller del turno A --> switchare al master_turnoB per turno B

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCorsiFrequentati(ActionEvent event) {
    	this.txtResult.clear();
    	this.model.createGraph();
    	this.txtResult.appendText("Grafo creato con\n");
    	this.txtResult.appendText(String.format("#Vertici: %d\n#Archi: %d\n", this.model.nVertici(),this.model.nArchi()));
    	List<StampaCorsoStudenti> lista = this.model.getStudentiCorsi();
    	for(StampaCorsoStudenti s : lista) {
    		this.txtResult.appendText(s.toString()+"\n");
    	}

    }

    @FXML
    void doVisualizzaCorsi(ActionEvent event) {
    	this.txtResult.clear();
    	this.model.cercaPercorso();
    	List<Corso> cammino = this.model.getCammino();
    	if(cammino ==null || cammino.size()==0) {
    		this.txtResult.appendText("Cammino non trovato");
    		return;
    	}
    	for(Corso c : cammino) {
    		this.txtResult.appendText(c.toString()+"\n");
    	}
    	this.txtResult.appendText("NUmero corsi minimi: "+cammino.size()+"\n");

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DidatticaGestionale.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
