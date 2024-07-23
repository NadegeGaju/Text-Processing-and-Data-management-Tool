package com.example.textprocessing.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessingController {
    @FXML
    private TextField regexTextField;

    @FXML
    private TextField repTextField;

    @FXML
    private TextField textTextField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private TextFlow highlightResultTextFlow;


    public void findMatch(ActionEvent event){
        try{
            TextField[] fields = {textTextField, regexTextField};
            this.validateTextFields(fields);
            //Clear possible previous results
            warningLabel.setText("");
            highlightResultTextFlow.getChildren().clear();

        }catch(Exception e){
            warningLabel.setText(e.getMessage());
        }
    }

    public void replaceMatch(ActionEvent event){
        try{
            TextField[] fields = {textTextField, regexTextField, repTextField};
            this.validateTextFields(fields);

        }catch(Exception e){
            warningLabel.setText(e.getMessage());
        }
    }

    public void highlightMatch(ActionEvent event) {
        try {
            TextField[] fields = {textTextField, regexTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");

        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }



    public void validateTextFields(TextField[] fields) throws Exception {
        for(TextField field: fields){
            if(field.getText().isEmpty()){
                highlightResultTextFlow.getChildren().clear();
                resultLabel.setText("");
                warningLabel.setText(field.getPromptText() + " is required");
                throw new Exception(field.getPromptText() + " is required");
            }
        }
    }
}
