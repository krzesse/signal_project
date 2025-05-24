package com.alerts.factory;

import com.alerts.Alert;

/**
 * Implements {@link AlertFactory} and creates specialized ECG alert.
 */
public class ECGAlertFactory implements AlertFactory{

    /**
     * Creates instance of {@link Alert} object with specialized condition, specifying type of alert.
     *
     * @param patientId that is assigned to {@link Alert} object.
     * @param condition that is assigned to {@link Alert} object.
     * @param timestamp that is assigned to {@link Alert} object.
     * @return specialized alert.
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp){
        return new Alert(patientId, "ECG Alert: " + condition, timestamp);
    }
}
