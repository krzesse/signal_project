package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory;  // changed to camelCase

    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();  // changed variable name to camel case

    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;  //Deleted line break after the opening brace; changed var name to camel case
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));  //changed var name to camel case
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());  //changed variable names to camel case

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {  //changed variable name to camel case
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n",
                    patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());  // camel case
        }
    }
}
