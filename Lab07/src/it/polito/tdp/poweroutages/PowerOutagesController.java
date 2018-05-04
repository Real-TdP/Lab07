package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.bean.Nerc;
import it.polito.tdp.poweroutages.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> NERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private TextArea txtResult;

    @FXML
    void buttonWCA(ActionEvent event) {
    	txtResult.clear();

    	Nerc nerc = NERC.getValue();
    	if(txtHours.getText().isEmpty()||txtYears.getText().isEmpty()||nerc==null) {
    		txtResult.appendText("Compila tutti i campi.");
    		return;
    	}
    	
    	int hours;
		int years;
		
		try {
			hours = Integer.parseInt(txtHours.getText());
			years = Integer.parseInt(txtYears.getText());
			if(hours<0||years<0) {
				txtResult.appendText("Inserisci numeri validi(>0).");
	    		return;
			}
		} catch (NumberFormatException e) {
			txtResult.appendText("Inserisci numeri validi.");
    		return;
		}
		
    	String result =model.getWorstCase(nerc,hours,years);
    	txtResult.appendText(result);

    }

    @FXML
    void initialize() {
        assert NERC != null : "fx:id=\"NERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        

    }
    
    public void setModel(Model m) {
    	this.model=m;
    	NERC.getItems().addAll(model.getNercList());
    }
    
}
