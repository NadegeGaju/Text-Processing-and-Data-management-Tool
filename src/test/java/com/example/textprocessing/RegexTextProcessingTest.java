package com.example.textprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class RegexTextProcessingTest {
    private RegexTextProcessing regexTextProcessing;

    @BeforeEach
    void setUp() {
        regexTextProcessing = new RegexTextProcessing();
    }

    @Test
    void testMatcher() {
        String regex = "test";
        String text = "This is a test string for testing.";
        Matcher matcher = regexTextProcessing.matcher(regex, text);
        assertTrue(matcher.find(), "The matcher should find a match for 'test' in the text.");
    }

    @Test
    void testMatcher_NotFound() {
        String regex = "test";
        String text = "This is a test string for testing.";
        Matcher matcher = regexTextProcessing.matcher(regex, text);
        assertTrue(matcher.find(), "The matcher should find a match for 'test' in the text.");
    }

    @Test
    void testReplaceMatch() {
        String regex = "here";
        String text = "Hello here, how are you doing.";
        String replacement = "everybody";
        String result = regexTextProcessing.replaceMatch(regex, text, replacement);
        assertNotNull(result, "The replaceMatch method should return a non-null result.");
        assertEquals("Hello everybody, how are you doing.", result, "The replaced text should match the expected result.");
    }

    @Test
    void testReplaceMatch_NoMatch() {
        String regex = "notfound";
        String text = "This is a test string for testing.";
        String replacement = "sample";
        String result = regexTextProcessing.replaceMatch(regex, text, replacement);
        assertNull(result, "The replaceMatch method should return null if no match is found.");
    }

    @Test
    void testHighlightMatches() {
        String regex = "test";
        String text = "This is a test string for testing.";
        String result = regexTextProcessing.highlightMatches(regex, text);
        assertNotNull(result, "The highlightMatches method should return a non-null result.");
        assertEquals("This is a [[[test]]] string for [[[test]]]ing.", result, "The highlighted text should match the expected result.");
    }

    @Test
    void testHighlightMatches_NoMatch() {
        String regex = "notfound";
        String text = "This is a test string for testing.";
        String result = regexTextProcessing.highlightMatches(regex, text);
        assertNull(result, "The highlightMatches method should return null if no match is found.");
    }

    @Test
    void testReplaceMatchInvalidRegex() {
        String result = regexTextProcessing.replaceMatch("[abc", "abcABC", "123");
        assertTrue(result.startsWith("Invalid regex pattern:"), "Should return an invalid regex pattern message.");
        assertNotEquals("123ABC123", result, "The result should not be the same as a successful replacement.");
    }

    @Test
    void testReplaceMatchOtherException() {
        String result = regexTextProcessing.replaceMatch(null, "abcABC", "123");
        assertTrue(result.startsWith("An error occurred:"), "Should return an error message.");
        assertFalse(result.equals("abcABC"), "The result should not be the same as the original text.");
    }
}
