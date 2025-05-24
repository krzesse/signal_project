package com.alerts.decorator;

/**
 * Defines the methods for {@link AlertDecorator} objects.
 */
public interface Alert {
    String getPatientId();
    String getCondition();
    long getTimestamp();
    String getFunction();
}
