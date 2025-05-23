package com.alerts;

import com.data_management.PatientRecord;
import com.cardio_generator.HealthDataSimulator;

import java.util.List;

/**
 * Describes rules of evaluating manually triggered alarms.
 * The assumption is that triggered alarm is generated the same way as other values using {@link HealthDataSimulator}.
 * Where {@link PatientRecord} {@code recordType} is "Manual Alarm" and {@code measurementValue} is 1 if alarm button is pressed and 0 if alarm button is untriggered.
 */
public class TriggeredAlarmRule implements AlertRule{
    private List<PatientRecord> triggeredAlarmRecords;
    private String patientId;
    private String condition;
    private long timestamp;

    /**
     * Sets the values of records and patient identification, needed for evaluation.
     *
     * @param triggeredAlarmRecords contains list of {@link PatientRecord} that contain instances with manual alarm triggers.
     */
    public  TriggeredAlarmRule(List<PatientRecord> triggeredAlarmRecords){
        this.triggeredAlarmRecords = triggeredAlarmRecords;
        this.patientId = Integer.toString(triggeredAlarmRecords.get(0).getPatientId());
    }

    /**
     * Evaluates the latest alert triggered by staff or patient.
     * If list is nonempty and latest value is equal 1, that means that there is currently alarm button pressed.
     * THe {@code timestamp} and {@code condition} of instance is saved. The adequate {@link Alert} is created.
     *
     * @return {@link Alert} if alert button is pressed, otherwise {@code null}.
     */
    @Override
    public Alert evaluate(){
        int alertCount = triggeredAlarmRecords.size();
        if (alertCount > 0 && triggeredAlarmRecords.get(alertCount - 1).getMeasurementValue() == 1) {
            PatientRecord latest = triggeredAlarmRecords.get(alertCount - 1);
            this.timestamp = latest.getTimestamp();
            this.condition = "Latest Manual Alert: Triggered at " + latest.getTimestamp();
            return new Alert(patientId, condition, timestamp);
        }
        return null;
    }
}
