package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles writing and storing generated data as a file, in specified filepath.
 * Implements {@code OutputStrategy} interface to ensure correct structure.
 */
public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory;  // changed to camelCase

    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();  // changed variable name to camel case

    /**
     *  Upon declaring {@code FileOutputStrategy} object, it is required to specify in which directory the file containing data should be stored at.
     *
     * @param baseDirectory specifies the location of directory.
     */
    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;  //Deleted line break after the opening brace; changed var name to camel case
    }

    /**
     * Stores given data to specified file path.
     * First makes sure that directory specified when declaring {@code FileOutputStrategy} exists.
     * Then takes this filepath, navigates there and stores all the data in a file.
     * It ensures to handle I/O and file writing errors.
     *
     * @param patientId identification of a patient
     * @param timestamp specifies when the action was taken
     * @param label description of stored information
     * @param data describes type of data stored
     */
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
