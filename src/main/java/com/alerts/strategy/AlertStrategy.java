package com.alerts.strategy;

import com.data_management.PatientRecord;

/**
 * Determines the structure of strategy object.
 */
public interface AlertStrategy {
    boolean checkAlert(PatientRecord record);
}
