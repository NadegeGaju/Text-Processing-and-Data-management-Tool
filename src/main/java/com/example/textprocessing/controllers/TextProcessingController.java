package com.example.textprocessing.controllers;

import com.example.textprocessing.services.RegexTextProcessing;
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

            String originalText = textTextField.getText();
            String regex = regexTextField.getText();
            String replacement = repTextField.getText();

            Matcher matcher = regexTextProcessing.matcher(regex, originalText);

            if (matcher.find()) {
                String replacedText = matcher.replaceAll(replacement);
                textTextField.setText(replacedText);
                resultLabel.setText("Replacement successful");
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

        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
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
