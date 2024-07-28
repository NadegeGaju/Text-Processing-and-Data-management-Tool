package com.example.textprocessing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHelperTest {
    private static final String TEST_FILE_NAME = "testFile.txt";

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testWriteToFile() throws IOException {
        String content = "Hello, world!";
        FilesHelper.writeToFile(TEST_FILE_NAME, content);

        File file = new File(TEST_FILE_NAME);
        assertTrue(file.exists(), "File should exist after writing to it.");
    }

    @Test
    void testReadFromFile() throws IOException {
        String content = "Hello, world!";
        FilesHelper.writeToFile(TEST_FILE_NAME, content);

        String readContent = FilesHelper.readFromFile(TEST_FILE_NAME);
        assertEquals(content, readContent, "Read content should match written content.");
    }
}
