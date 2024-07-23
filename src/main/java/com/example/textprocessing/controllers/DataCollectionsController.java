package com.example.textprocessing.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DataCollectionsController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Label type;

    @FXML
    private Label warningLabel;

    @FXML
    private TextField indexTextField;

    @FXML
    private TextField itemTextField;

    @FXML
    private Label resultLabel;


    final String[] collections = {"ArrayList", "Set", "Map"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(collections);
        choiceBox.setOnAction(this::getCollection);
    }

    public void getCollection(ActionEvent event){
        String collection = choiceBox.getValue();
        resultLabel.setText("");
        type.setText(collection);

    }

    public void addItem(ActionEvent event){
        TextField[] fields = {itemTextField};
        // check for required fields to add data into our collection
        if(!this.validateFields(fields)){
            return;
        };


    }

    public void removeItem(ActionEvent event){
        TextField[] fields = {indexTextField};
    }

    public void editItem(ActionEvent event){
        TextField[] fields = {indexTextField, itemTextField};

    }

    public void clear(){
//        dataCollection.clear();
        warningLabel.setText("");
        resultLabel.setText("");
    }

    public  boolean validateFields(TextField[] fields){
        if(choiceBox.getValue() == null){
            warningLabel.setText("Collection type is required");
            return false;
        }
        return true;
    }

    public void displayData(){
        resultLabel.setText("");
    }
}
