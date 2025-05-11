package com.alerts;

import com.data_management.PatientRecord;

import java.time.Duration;
import java.util.List;

/**
 * Describes rules of evaluating the blood saturation records.
 */
public class BloodSaturationAlertRule implements AlertRule{
    private List<PatientRecord> bloodSaturationRecords;
    private String patientId;
    private String condition;
    private long timestamp;

    /**
     * Sets the values of blood saturation records and patient identification, needed for medical evaluation.
     *
     * @param bloodSaturationRecords list of {@link PatientRecord} objects containing blood saturation information.
     */
    public BloodSaturationAlertRule(List<PatientRecord> bloodSaturationRecords){
        this.bloodSaturationRecords = bloodSaturationRecords;
        this.patientId = Integer.toString(bloodSaturationRecords.get(0).getPatientId());
    }

    /**
     * Checks if either rapid drop of blood saturation or overall blood saturation level is existent.
     *
     * @return {@link Alert} if medical emergency related {@code bloodSaturationRecords} appears, otherwise {@code false}.
     */
    @Override
    public Alert evaluate(){
        if(checkBloodSaturation() || rapidDrop())
            return new Alert(patientId, condition, timestamp);
        return null;
    }

    /**
     * Checks if {@code measurementValues} in {@code bloodSaturationRecords} are not below 92%.
     * If yes, then the {@code timestamp} and {@code condition} are saved.
     *
     * @return {@code true} if blood saturation is too low, otherwise {@code false}.
     */
    private boolean checkBloodSaturation(){
        for(PatientRecord record : bloodSaturationRecords) {
            if (record.getMeasurementValue() < 92) {
                this.condition = "Low Saturation Alert: " + record.getRecordType() + " level to low at " + record.getMeasurementValue();
                this.timestamp = record.getTimestamp();
                return true;
            }
        }
        return false;
    }

    /**
     * Provides logic for evaluating if {@code bloodSaturationRecords} are rapidly dropping by more than 5% in less than 10 minutes.
     * From the given list of {@link PatientRecord}, each record is compared to the ones within 10 minute interval.
     * If blood saturation is increasing the measurements are skipped. If it is decreasing the values are checked if the drop is not too sudden.
     * During rapid drop the {@code timestamp} and {@code condition} are saved.
     *
     * @return {@code true} if rapid drop appears, otherwise {@code false}.
     */
    private boolean rapidDrop(){
        PatientRecord currentHigh;
        int n = bloodSaturationRecords.size();
        for(int i = 0; i < n; i++){
            long tenMinMark = bloodSaturationRecords.get(i).getTimestamp() + Duration.ofMinutes(10).toMillis();
            currentHigh = bloodSaturationRecords.get(i);

            for(int j = i + 1; j < n; j++){

                if(bloodSaturationRecords.get(j).getTimestamp() <= tenMinMark){
                    PatientRecord nextVal = bloodSaturationRecords.get(j);

                    if(nextVal.getMeasurementValue() > currentHigh.getMeasurementValue()) {
                        i = j-1;
                        break;
                    }

                    if((currentHigh.getMeasurementValue() - nextVal.getMeasurementValue()) >= 5) {
                        this.timestamp = nextVal.getTimestamp();
                        this.condition = "Rapid Drop Alert: " + nextVal.getRecordType() + " dropped from " + currentHigh.getMeasurementValue()
                                + " at " + currentHigh.getTimestamp() + " to " + nextVal.getMeasurementValue() + " at " + nextVal.getTimestamp();
                        return true;
                    }

                }
                else
                    break;
            }
        }
        return false;
    }
}
