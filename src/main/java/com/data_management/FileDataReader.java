package com.data_management;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

/**
 * Stores specified file path.
 * Extracts valuable information, and finally adds it to {@link DataStorage}.
 * Enables change of file path.
 */
public class FileDataReader implements DataReader{
    private Path filePath;

    public FileDataReader(String filePath){
        this.filePath = Paths.get(filePath);
    }

    /**
     * Accesses the file and processes it line by line.
     * From each line variables are extracted in correct data type and stored in {@link DataStorage} object.
     * Week 5 update: {@link DataStorage} object is now accessed through {@link DataStorageSingleton}.
     *
     * @param storage the storage where data will be stored
     * @throws IOException if error occurs while reading the file
     */
    @Override
    public void readData() throws IOException {
        DataStorage storage = DataStorageSingleton.getInstance();

        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String record;
            while((record = reader.readLine()) != null){

                String[] data = record.split(",");
                int patientID = Integer.parseInt(data[0].split(":")[1].trim());
                long timestamp = Long.parseLong(data[1].split(":")[1].trim());
                String recordType = data[2].split(":")[1].trim();
                String measurement = data[3].split(":")[1].trim();

                if(measurement.endsWith("%")){
                    measurement = measurement.substring(0,measurement.length()-1);
                }
                else if(measurement.equals("triggered")){
                    measurement = "1";
                }
                else if(measurement.equals("resolved")){
                    measurement= "0";
                }
                double measurementValue = Double.parseDouble(measurement);

                storage.addPatientData(patientID, measurementValue, recordType, timestamp);
            }

        }catch(IOException e){
            System.err.println(e.getMessage());
        }


    }

    public void setFilePath(String filePath){
        this.filePath = Paths.get(filePath);
    }
}
