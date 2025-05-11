package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;  // implementing line break before/after opening/closing brace
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        // Implementation goes here

        long endTime = System.currentTimeMillis();
        long startTime = endTime - Duration.ofHours(24).toMillis();

        List<PatientRecord> records = patient.getRecords(startTime, endTime);
        List<PatientRecord> systolicPressureRecords = new ArrayList<>();
        List<PatientRecord> diastolicPressureRecords = new ArrayList<>();
        List<PatientRecord> bloodSaturationRecords = new ArrayList<>();
        List<PatientRecord> bloodECGRecords = new ArrayList<>();
        List<PatientRecord> triggeredAlarmRecords = new ArrayList<>();

        for(PatientRecord record : records){
            switch(record.getRecordType()){
                case "SystolicPressure":
                    systolicPressureRecords.add(record);
                    break;
                case "DiastolicPressure":
                    diastolicPressureRecords.add(record);
                    break;
                case "Saturation":
                    bloodSaturationRecords.add(record);
                    break;
                case "ECG":
                    bloodECGRecords.add(record);
                    break;
                case "Manual Alarm":
                    triggeredAlarmRecords.add(record);
                    break;
                default:
            }
        }

        AlertRule bloodPressureAlertEvaluator = new BloodPressureAlertRule(systolicPressureRecords, diastolicPressureRecords);
        AlertRule bloodSaturationAlertEvaluator = new BloodSaturationAlertRule(bloodSaturationRecords);
        AlertRule hypoxemiaAlertEvaluator = new HypoxemiaAlertRule(systolicPressureRecords, bloodSaturationRecords);
        AlertRule ecgAlertEvaluator = new ECGAlertRule(bloodECGRecords);
        AlertRule triggeredAlarmEvaluator = new TriggeredAlarmRule(triggeredAlarmRecords);

        if (bloodPressureAlertEvaluator.evaluate() != null)
            triggerAlert(bloodPressureAlertEvaluator.evaluate());
        if (bloodSaturationAlertEvaluator.evaluate() != null)
            triggerAlert(bloodSaturationAlertEvaluator.evaluate());
        if (hypoxemiaAlertEvaluator.evaluate() != null)
            triggerAlert(hypoxemiaAlertEvaluator.evaluate());
        if (ecgAlertEvaluator.evaluate() != null)
            triggerAlert(ecgAlertEvaluator.evaluate());
        if (triggeredAlarmEvaluator.evaluate() != null)
            triggerAlert(triggeredAlarmEvaluator.evaluate());

    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * I have implemented one change here, to print the condition of alert, for tests sake.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        System.out.println(alert.getCondition());
    }


}
