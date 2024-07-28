package com.example.textprocessing.controllers;

import com.example.textprocessing.RegexTextProcessing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.util.regex.Matcher;

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

    RegexTextProcessing regexTextProcessing = new RegexTextProcessing();

    public void findMatch(ActionEvent event) {
        try {
            TextField[] fields = {textTextField, regexTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");
            highlightResultTextFlow.getChildren().clear();

            Matcher matcher = regexTextProcessing.matcher(regexTextField.getText(), textTextField.getText());

            if (matcher.find()) {
                resultLabel.setText("Match found");
            } else {
                resultLabel.setText("No match found");
            }

        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }

    public void replaceMatch(ActionEvent event) {
        try {
            TextField[] fields = {textTextField, regexTextField, repTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");
            highlightResultTextFlow.getChildren().clear();

            String replacedText = regexTextProcessing.replaceMatch(regexTextField.getText(), textTextField.getText(), repTextField.getText());

            if (replacedText != null) {
                resultLabel.setText(replacedText);
            } else {
                resultLabel.setText("No match found to replace");
            }

        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }

    public void highlightMatch(ActionEvent event) {
        try {
            TextField[] fields = {textTextField, regexTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");
            highlightResultTextFlow.getChildren().clear();

            String highlightedText = regexTextProcessing.highlightMatches(regexTextField.getText(), textTextField.getText());

            if (highlightedText != null) {
                displayHighlightedText(highlightedText);
                resultLabel.setText("");
            } else {
                resultLabel.setText("No matches found");
            }

        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }


    private void displayHighlightedText(String highlightedText) {
        String[] parts = highlightedText.split("\\[\\[\\[|\\]\\]\\]");
        for (int i = 0; i < parts.length; i++) {
            Text text = new Text(parts[i]);
            text.setStyle("-fx-font-size: 18px;-fx-font-weight:bold");
            text.setFill(Color.GREEN);
            if (i % 2 == 1) {
                text.setFill(Color.RED);
                text.setStyle("-fx-font-size: 19px; -fx-font-weight: bold;");
            }
            highlightResultTextFlow.getChildren().add(text);
        }
    }

    public void validateTextFields(TextField[] fields) throws Exception {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                highlightResultTextFlow.getChildren().clear();
                resultLabel.setText("");
                warningLabel.setText(field.getPromptText() + " is required");
                throw new Exception(field.getPromptText() + " is required");
            }
        }
    }
}
