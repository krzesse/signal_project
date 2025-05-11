    package com.alerts;

    import com.data_management.PatientRecord;

    import java.util.List;

    /**
     * Implements {@link AlertRule} to enable evaluating  list of {@link PatientRecord} objects if the entries show health blood pressure of patient
     * or the {@link Alert} is needed.
     */
    public class BloodPressureAlertRule implements AlertRule{
        private List<PatientRecord> systolicPressureRecords;
        private List<PatientRecord> diastolicPressureRecords;
        private String patientId;
        private String condition;
        private long timestamp;

        /**
         * Constructs the {@link AlertRule} implementation with needed readings of a patient needed for correct evaluation.
         *
         * @param systolicPressureRecords contains all {@link PatientRecord} objects with systolic pressure readings.
         * @param diastolicPressureRecords contains all {@link PatientRecord} objects with diastolic pressure readings.
         */
        public BloodPressureAlertRule(List<PatientRecord> systolicPressureRecords, List<PatientRecord> diastolicPressureRecords){
            this.systolicPressureRecords = systolicPressureRecords;
            this.diastolicPressureRecords = diastolicPressureRecords;
            this.patientId = Integer.toString(systolicPressureRecords.get(0).getPatientId());
        }

        /**
         * Check if both {@code systolicPressureRecords} and {@code diastolicPressureRecords} have any entries implying creating {@link Alert} object.
         *
         * @return {@link Alert} object or {@code null}.
         */
        @Override
        public Alert evaluate(){
            if(checkSystolicPressure() || checkDiastolicPressure())
                return new Alert(patientId, condition, timestamp);
            return null;
        }

        /**
         * Checks validity both {@code trendAlert} and {@code criticalThreshold} for {@code systolicPressureRecords}.
         *
         * @return {@code true} if the alert should be thrown, otherwise {@code false}.
         */
        private boolean checkSystolicPressure(){
            if(trendAlert(systolicPressureRecords) || criticalThreshold(systolicPressureRecords, 90, 180))
                return true;
            return false;
        }

        /**
         * Checks validity both {@code trendAlert} and {@code criticalThreshold} for {@code diastolicPressureRecords}.
         *
         * @return {@code true} if the alert should be thrown, otherwise {@code false}.
         */
        private boolean checkDiastolicPressure(){
            if(trendAlert(diastolicPressureRecords) || criticalThreshold(diastolicPressureRecords, 60, 120))
                return true;
            return false;
        }

        /**
         * Checks if there exist three consecutive records that either decrease or increase by more than {@code 10}.
         * If such trend appears the {@code timestamp} and {@code condition} are saved.
         *
         * @param records contains the {@link PatientRecord} objects needed to be evaluated.
         * @return {@code true} if there exist a trend of increasing or decreasing {@code measurementValue}, otherwise {@code false}.
         */
        private boolean trendAlert(List<PatientRecord> records){
            int increaseCounter = 1;
            int decreaseCounter = 1;
            double lastMeasurementValue = records.get(0).getMeasurementValue();
            for(PatientRecord record : records){
                double diff = record.getMeasurementValue() - lastMeasurementValue;
                if(diff > 10){
                    decreaseCounter = 1;
                    increaseCounter += 1;
                }
                else if(diff < -10){
                    increaseCounter = 1;
                    decreaseCounter += 1;
                }
                else{
                    increaseCounter = 1;
                    decreaseCounter = 1;
                }

                if (increaseCounter > 2){
                    this.timestamp = record.getTimestamp();
                    this.condition = "Trend Alert: " + record.getRecordType() + "is increasing";
                    return true;
                }
                else if (decreaseCounter > 2){
                    this.timestamp = record.getTimestamp();
                    this.condition = "Trend Alert: " + record.getRecordType() + " is decreasing";
                    return true;
                }
            }
            return false;
        }

        /**
         * Checks, if all {@code List<PatientRecord>} objects have {@code measurementValue} within specified threshold.
         * If instance fails the check, its {@code timestamp} and {@code condition} are saved.
         *
         * @param records contains the {@link PatientRecord} objects needed to be evaluated.
         * @param min sets the minimum of threshold.
         * @param max sets the maximum of threshold.
         * @return {@code false} if every value in the {@code records} is within the threshold, otherwise {@code true}.
         */
        private boolean criticalThreshold(List<PatientRecord> records, int min, int max){
            for(PatientRecord record: records){
                double val = record.getMeasurementValue();
                if(val > max){
                    this.timestamp = record.getTimestamp();
                    this.condition = "Critical Threshold Alert: " + record.getRecordType() + " is above threshold at " + val;
                    return true;
                }
                else if(val < min){
                    this.timestamp = record.getTimestamp();
                    this.condition = "Critical Threshold Alert: " + record.getRecordType() + " is below threshold at " + val;
                    return true;
                }
            }
            return false;
        }
    }
