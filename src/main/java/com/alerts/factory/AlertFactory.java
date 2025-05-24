package com.alerts.factory;

import com.alerts.Alert;

/**
 * Defines the structure of factory object.
 */
public interface AlertFactory {
    Alert createAlert(String patientId, String condition, long timestamp);
}
