package com.alerts.strategy;

import com.data_management.PatientRecord;

/**
 * Implements checking strategy to assess if Patient record should trigger alert.
 */
public class BloodPressureStrategy implements AlertStrategy{

    /**
     * Checks if given record type is adequate to strategy, if correct assess the measurement value of record.
     *
     * @param record contains information, needed for algorithm to conclude assessment.
     * @return if alert should be thrown.
     * @throws IllegalArgumentException if record's measurement type does not match the strategy.
     */
    @Override
    public boolean checkAlert(PatientRecord record) throws IllegalArgumentException{
        String pressureType = record.getRecordType();

        if(pressureType.equals("Systolic")){
            double value = record.getMeasurementValue();
            return  !(value > 90 && value < 180);
        }
        else if(pressureType.equals("Diastolic")){
            double value = record.getMeasurementValue();
            return  !(value > 60 && value < 120);
        }
        else{
            throw new IllegalArgumentException("Wrong record type");
        }


    }
}
