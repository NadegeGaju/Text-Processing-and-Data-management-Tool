package com.example.textprocessing.services;

import java.io.*;

public class FilesHelper {
    public static void writeToFile(String fileName, String content) throws IOException {
       File file  = new File(fileName);
       if(!file.exists()) {
           file.createNewFile();
       }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }

    public static String readFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        if (content.length() > 0) {
            content.setLength(content.length() - 1);
        }

        return content.toString();
    }
}
