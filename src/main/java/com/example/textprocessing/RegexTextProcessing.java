package com.example.textprocessing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexTextProcessing {

    public Matcher matcher(String regexText, String text) {

        try {
            Pattern pattern = Pattern.compile(regexText, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            return matcher;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid regex pattern: " + e.getDescription(), e);
        }
    }

    public String replaceMatch(String regex, String originalText, String replacement) {
        try {

        Matcher matcher = matcher(regex, originalText);
        if (matcher.find()) {
            return matcher.replaceAll(replacement);
        } else {
            return null;
        }

    } catch (IllegalArgumentException e) {
        return "Invalid regex pattern: " + e.getMessage();
    } catch (Exception e) {

        return "An error occurred: " + e.getMessage();
    }
    }

    public String highlightMatches(String regex, String text) {
        try {
        Matcher matcher = matcher(regex, text);
        StringBuilder highlightedText = new StringBuilder();
        int lastMatchEnd = 0;
        boolean matchFound = false;

        while (matcher.find()) {
            matchFound = true;
            if (matcher.start() > lastMatchEnd) {
                highlightedText.append(text, lastMatchEnd, matcher.start());
            }
            highlightedText.append("[[[").append(text, matcher.start(), matcher.end()).append("]]]");
            lastMatchEnd = matcher.end();
        }

        if (lastMatchEnd < text.length()) {
            highlightedText.append(text.substring(lastMatchEnd));
        }

        return matchFound ? highlightedText.toString() : null;

    } catch (IllegalArgumentException e) {
        return "Invalid regex pattern: " + e.getMessage();
    } catch (Exception e) {

        return "An error occurred: " + e.getMessage();
    }
    }
}
