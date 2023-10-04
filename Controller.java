package com.internshala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLable;
	@FXML
	public ChoiceBox<String> choiceBox;//< canbe any datatype or can be blank>
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String c_to_f_text="Celsius to Fahrenheit";
	private static final String f_to_c_text="Fahrenheit to Celsius";

	private boolean isC_to_f= true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.getItems().add(c_to_f_text);
		choiceBox.getItems().add(f_to_c_text);
		choiceBox.setValue(c_to_f_text);//can use string also
		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals(c_to_f_text)){
				isC_to_f = true;
			}else{
				isC_to_f = false;
			}
		});

		convertButton.setOnAction(event ->{
				convert();
		});
	}

	private void convert() {
		String input = userInputField.getText();
		float enteredTemperature = 0.0f;
		try{
			enteredTemperature = Float.parseFloat(input);
		}catch (Exception exception){
			warnUser();
			return;//no code is executed after return in java
		}

		float newTemperature=0.0f;
		if(isC_to_f){
			newTemperature = (enteredTemperature * 9 / 5) + 32;
		}else{
			newTemperature = (enteredTemperature - 32) * 5 / 9;
		}
		diplay(newTemperature);
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occured");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid temperature");
		alert.show();
	}

	private void diplay(float newTemperature) {
		String unit = isC_to_f?" F" : " C";
		System.out.println("The new Temperature is: " + newTemperature + unit);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new Temperature is: " + newTemperature + unit);
		alert.show();
	}
}
