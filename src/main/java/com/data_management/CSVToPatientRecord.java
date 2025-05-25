package com.data_management;

import com.cardio_generator.outputs.WebSocketOutputStrategy;

/**
 * Takes care of converting {@code String} CSV data into {@link PatientRecord} object.
 */
public class CSVToPatientRecord {

    /**
     * Given standardized CSV input, specified in {@link WebSocketOutputStrategy},
     * method divides the {@code String} into variables contained in record. Finally, it creates patient record instance.
     *
     * @param message CSV specified to convert
     * @return {@link PatientRecord} object with values contained in {@code message}
     */
    public static PatientRecord parse(String message){
        String[] values = message.split(",");

        //parsing CSV blocks to their types.
        int patientId = Integer.parseInt(values[0]);
        long timestamp = Long.parseLong(values[1]);
        String recordType = values[2];

        //formatting measurement value so that it can be parsed to double
        String mV = values[3];
        if (values[3].endsWith("%"))
            mV = mV.substring(0, mV.length() - 1);
        else if (mV.equals("triggered"))
            mV = "1";
        else if (mV.equals("resolved"))
            mV = "0";
        double measurementValue = Double.parseDouble(mV);

        return new PatientRecord(patientId, measurementValue, recordType, timestamp);
    }
}
