package com.alerts;

/**
 * Provides logic for checking if it is necessary to create {@link Alert} object.
 */
public interface AlertRule {
    /**
     * Checks if provided data qualifies for creating the alert.
     *
     * @return {@link Alert} object or {@code null} based on specified implementation.
     */
    Alert evaluate();
}
