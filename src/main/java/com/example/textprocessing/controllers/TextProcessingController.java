package com.example.textprocessing.controllers;

import com.example.textprocessing.services.RegexTextProcessing;
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

            String originalText = textTextField.getText();
            String regex = regexTextField.getText();
            String replacement = repTextField.getText();

            Matcher matcher = regexTextProcessing.matcher(regex, originalText);

            if (matcher.find()) {
                String replacedText = matcher.replaceAll(replacement);
                resultLabel.setText( replacedText);
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

            String text = textTextField.getText();
            String regex = regexTextField.getText();

            Matcher matcher = regexTextProcessing.matcher(regex, text);
            int lastMatchEnd = 0;
            boolean matchFound = false;

            while (matcher.find()) {
                matchFound = true;

                if (matcher.start() > lastMatchEnd) {
                    Text beforeMatch = new Text(text.substring(lastMatchEnd, matcher.start()));
                    highlightResultTextFlow.getChildren().add(beforeMatch);
                }


                Text match = new Text(text.substring(matcher.start(), matcher.end()));
                match.setFill(Color.RED);
                match.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
                highlightResultTextFlow.getChildren().add(match);

                lastMatchEnd = matcher.end();
            }

            if (lastMatchEnd < text.length()) {
                Text afterMatch = new Text(text.substring(lastMatchEnd));
                highlightResultTextFlow.getChildren().add(afterMatch);
            }

            if (matchFound) {
                resultLabel.setText("");
            } else {
                resultLabel.setText("No matches found");
            }

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
