package com.alerts.strategy;

import com.alerts.factory.BloodOxygenAlertFactory;
import com.alerts.factory.BloodPressureAlertFactory;
import com.alerts.factory.ECGAlertFactory;

/**
 * Creates object to get specified strategy.
 */
public class AlertStrategySelector {
    /**
     * Enables selection of strategies
     *
     * @param strategy specifies type of strategy/algorithm to return
     * @return {@link AlertStrategy} implementation.
     * @throws IllegalArgumentException if specified strategy is not found.
     */
    public AlertStrategy get(String strategy) throws IllegalArgumentException{
        switch(strategy) {
            case "Heart Rate":
                return new HeartRateStrategy();
            case "Blood Pressure":
                return new BloodPressureStrategy();
            case "Oxygen Saturation":
                return new OxygenSaturationStrategy();
            default:
                throw new IllegalArgumentException("Wrong strategy");
        }
    }
}
