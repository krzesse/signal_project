package com.alerts;

import com.data_management.PatientRecord;

import java.util.List;

/**
 * Describes rules of evaluating the blood ECG related medical problems.
 */
public class ECGAlertRule implements AlertRule{
    private List<PatientRecord> bloodECGRecords;
    private String patientId;
    private String condition;
    private long timestamp;

    /**
     * Sets the values of blood ECG records and patient identification, needed for medical evaluation.
     *
     * @param bloodECGRecords contains ECG information in form of {@link PatientRecord} list.
     */
    public ECGAlertRule(List<PatientRecord> bloodECGRecords){
        this.bloodECGRecords = bloodECGRecords;
        this.patientId = Integer.toString(bloodECGRecords.get(0).getPatientId());
    }

    /**
     * If {@code checkPeakECG} returns signs of medical emergency the {@link Alert} object is created.
     *
     * @return {@link Alert} if ECG reading show spike, otherwise {@code null}.
     */
    @Override
    public Alert evaluate(){
        if (checkPeakECG()){
            return new Alert(patientId, condition, timestamp);
        }
        return null;
    }

    /**
     * Checks using sliding window approach if there is a spike of ECG values in a reading.
     * It iterates through the records using array(window) of size 5. The average values of past and present windows are stored and compared.
     * If the new average is 1.5 times larger than previous one, the method interprets it as a spike of ECG.
     * The {@code timestamp} and {@code condition} are stored.
     *
     * @return {@code true} when there ECG appears to peak, otherwise {@code false}.
     */
    private boolean checkPeakECG(){
        int windowLength = 5;
        int n = bloodECGRecords.size();
        if(n < windowLength)
            return false;

        double previousAverage = 0;
        for(int a = 0; a < windowLength; a++){
            previousAverage += bloodECGRecords.get(a).getMeasurementValue();
        }
        previousAverage = previousAverage/windowLength;

        for(int i = 1; i < n - windowLength + 1; i++) {
            double currentAverage = 0;

            for (int j = 0; j < windowLength; j++) {
                currentAverage += bloodECGRecords.get(i + j).getMeasurementValue();
            }
            currentAverage = currentAverage / windowLength;

            if (currentAverage > 1.5 * previousAverage){
                this.timestamp = bloodECGRecords.get(i + windowLength).getTimestamp();
                this.condition = "ECG Spike Alert: There is a spike between time interval " + bloodECGRecords.get(i).getTimestamp()
                    + "-" + bloodECGRecords.get(i + windowLength - 1).getTimestamp();
                return true;
            }

            previousAverage = currentAverage;
        }
        return false;
    }
}
