package com.alerts;

import com.data_management.PatientRecord;

import java.time.Duration;
import java.util.List;

/**
 * Describes rules of evaluating hypoxemia.
 */
public class HypoxemiaAlertRule implements AlertRule{
    private List<PatientRecord> systolicPressureRecords;
    private List<PatientRecord> bloodSaturationRecords;
    private String patientId;
    private String condition;
    private long timestamp;

    /**
     * Sets the values of blood saturation records, systolic pressure records and patient identification, needed for medical evaluation.
     *
     * @param systolicPressureRecords contains systolic pressure information in form of {@link PatientRecord} list.
     * @param bloodSaturationRecords contains diastolic pressure information in form of {@link PatientRecord} list.
     */
    public HypoxemiaAlertRule(List<PatientRecord> systolicPressureRecords, List<PatientRecord> bloodSaturationRecords){
        this.systolicPressureRecords = systolicPressureRecords;
        this.bloodSaturationRecords = bloodSaturationRecords;
        this.patientId = Integer.toString(systolicPressureRecords.get(0).getPatientId());
    }

    /**
     * If {@code checkHypoxemia} returns signs of medical emergency the {@link Alert} object is created.
     *
     * @return {@link Alert} during hypoxemia, otherwise {@code null}.
     */
    @Override
    public Alert evaluate(){
        if(checkHypoxemia())
            return new Alert(patientId, condition, timestamp);
        return null;
    }

    /**
     * Checks for given lists of {@link PatientRecord} containing blood saturation records and systolic pressure records.
     * Iterates through both, to find critical values in both. If such existed within 1 minute interval the {@code timestamp} and {@code condition} are saved.
     *
     * @return {@code true} if there are signs of hypoxemia within given lists of {@link PatientRecord}.
     */
    private boolean checkHypoxemia(){
        for(PatientRecord saturationRecord : bloodSaturationRecords){
            for(PatientRecord systolicRecord : systolicPressureRecords){

                if(Math.abs(saturationRecord.getTimestamp() - systolicRecord.getTimestamp()) < Duration.ofMinutes(1).toMillis()
                        && saturationRecord.getMeasurementValue() < 92
                        && systolicRecord.getMeasurementValue() < 90) {

                    this.timestamp = saturationRecord.getTimestamp();
                    this.condition = "Hypoxemia Alert: " + saturationRecord.getRecordType() + " value " + saturationRecord.getMeasurementValue()
                            + " at " + saturationRecord.getTimestamp() + " and " + systolicRecord.getRecordType() + " value "
                            + systolicRecord.getMeasurementValue() + " at " + systolicRecord.getTimestamp();

                    return true;
                }
            }
        }
        return false;
    }
}
