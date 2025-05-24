package com.alerts.strategy;

import com.data_management.PatientRecord;

/**
 * Implements checking strategy to assess if Patient record should trigger alert.
 */
public class OxygenSaturationStrategy implements AlertStrategy{

    /**
     * Checks if record has correct type. Implements logic to check if some oxygen saturation level measurement is safe.
     *
     * @param record contains information, needed for algorithm to conclude assessment.
     * @return {@code true} if alert should be thrown
     * @throws IllegalArgumentException if record's measurement type does not match the strategy.
     */
    @Override
    public boolean checkAlert(PatientRecord record){
        String recordType = record.getRecordType();
        double value = record.getMeasurementValue();

        if (recordType.equals("Saturation")){
            return value > 92 || value < 0;
        }
        else{
            throw new IllegalArgumentException("Wrong record type");
        }

    }
}
