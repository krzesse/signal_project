package com.alerts.strategy;

import com.data_management.PatientRecord;

/**
 * Implements checking strategy to assess if Patient record should trigger alert.
 */
public class HeartRateStrategy implements AlertStrategy{

    /**
     * Checks if record has correct type. Implements logic to check if some arbitrary heart rate measurement is safe.
     * I assume that such record type exist.
     *
     * @param record contains information, needed for algorithm to conclude assessment.
     * @return {@code true} if alert should be thrown.
     * @throws IllegalArgumentException if record's measurement type does not match the strategy.
     */
    @Override
    public boolean checkAlert(PatientRecord record) throws IllegalArgumentException{
        String recordType = record.getRecordType();
        if (recordType.equals("Heart Rate")){
            double value = record.getMeasurementValue();
            return value > 100 || value < 60;
        }
        else{
            throw new IllegalArgumentException("Wrong record type");
        }

    }
}
