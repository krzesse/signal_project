package com.alerts.factory;

import com.alerts.Alert;

/**
 * Enables selection of factory product based on initial value/specified string.
 */
public class AlertFactoryImplemetation {

    /**
     * Given specification method chooses adequate factory type.
     *
     * @param alert specifies type of alert factory
     * @return factory class that will create specialized {@link Alert} object
     * @throws IllegalArgumentException if {@code alert} does not represent any of given alert types.
     */
    public AlertFactory get(String alert) throws IllegalArgumentException{
        switch(alert) {
            case "Blood Pressure":
                return new BloodPressureAlertFactory();
            case "Blood Oxygen":
                return new BloodOxygenAlertFactory();
            case "ECG":
                return new ECGAlertFactory();
            default:
                throw new IllegalArgumentException("Wrong alert");
        }
    }
}
