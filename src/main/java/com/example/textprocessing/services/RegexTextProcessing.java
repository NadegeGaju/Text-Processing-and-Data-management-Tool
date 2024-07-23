package com.example.textprocessing.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTextProcessing {

    public Matcher matcher(String regexText, String text) {
        Pattern pattern = Pattern.compile(regexText, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }
}
