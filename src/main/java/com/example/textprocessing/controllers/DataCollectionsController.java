package com.example.textprocessing.controllers;

import com.example.textprocessing.DataCollection;
import com.example.textprocessing.FilesHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
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

    private DataCollection dataCollection;


    final String[] collections = {"ArrayList", "Set", "Map"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(collections);
        choiceBox.setOnAction(this::getCollection);
    }

    public void getCollection(ActionEvent event) {
        try {
            String collection = choiceBox.getValue();
            String data;
            resultLabel.setText("");
            type.setText(collection);
            String fileName = "";

            switch (collection) {
                case "ArrayList":
                    fileName = "arrayList.txt";
                    break;
                case "Set":
                    fileName = "set.txt";
                    break;
                case "Map":
                    fileName = "map.txt";
                    break;
                default:
                    warningLabel.setText("Invalid collection type");
                    return;
            }

            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            data = FilesHelper.readFromFile(fileName);
            if (data.isEmpty()) {
                dataCollection = new DataCollection(DataCollection.CollectionType.valueOf(collection.toUpperCase()));
                return;
            }
            dataCollection = new DataCollection(DataCollection.CollectionType.valueOf(collection.toUpperCase()), data);
            displayData();
        } catch (IOException e) {
            warningLabel.setText("Error reading file: " + e.getMessage());
        }
    }
    public void addItem(ActionEvent event) throws IOException{
        TextField[] fields = {itemTextField};

        if(!this.validateFields(fields)){
            return;
        };

        warningLabel.setText("");
        resultLabel.setText("");
        if(dataCollection.type == DataCollection.CollectionType.MAP) {
            dataCollection.addItem(indexTextField.getText(), itemTextField.getText());
            displayData();
        }else{
            dataCollection.addItem(itemTextField.getText());
            displayData();
        }


    }

    public void editItem(ActionEvent event) throws IOException {
        TextField[] fields = {indexTextField, itemTextField};
        if (!this.validateFields(fields)) {
            return;
        }

        warningLabel.setText("");
        resultLabel.setText("");
        try {
            if (dataCollection.type == DataCollection.CollectionType.MAP) {
                dataCollection.updateItem(indexTextField.getText(), itemTextField.getText());
            } else {
                int index = Integer.parseInt(indexTextField.getText());
                dataCollection.updateItem(index, itemTextField.getText());
            }
            displayData();
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }

    public void removeItem(ActionEvent event) throws IOException {
        TextField[] fields = {indexTextField};
        if (!this.validateFields(fields)) {
            return;
        }

        warningLabel.setText("");
        resultLabel.setText("");
        try {
            if (dataCollection.type == DataCollection.CollectionType.MAP) {
                dataCollection.removeItem(indexTextField.getText());
            } else {
                int index = Integer.parseInt(indexTextField.getText());
                dataCollection.removeItem(index);
            }
            displayData();
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }
    public void clear(){
//        dataCollection.clear();
        String fileName = choiceBox.getValue().toLowerCase() + ".txt";
        try {
            FilesHelper.writeToFile(fileName, "");
            dataCollection.clear();
        } catch (IOException e) {
            warningLabel.setText("Error clearing file: " + e.getMessage());
        }
        warningLabel.setText("");
        resultLabel.setText("");

    }

    public  boolean validateFields(TextField[] fields){
        if(choiceBox.getValue() == null){
            warningLabel.setText("Collection type is required");
            return false;
        }
        for(TextField field: fields){
            if(field.getText().isEmpty()){
                warningLabel.setText(field.getPromptText() + " is required");
                return false;
            }
            if (dataCollection.type != DataCollection.CollectionType.MAP&&field == indexTextField) {
                try {
                    Integer.parseInt(field.getText());
                } catch (NumberFormatException e) {
                    warningLabel.setText("Index must be a number");
                    return false;
                }
            }
        }
        return true;
    }

    public void displayData(){
        resultLabel.setText(dataCollection.toString());
    }
}
