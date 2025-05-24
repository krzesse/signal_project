package com.alerts.decorator;

/**
 * Basic {@link Alert} implementation that can be later wrapped by {@link AlertDecorator} and it's children.
 */
public class AlertUndecorated implements Alert{
    private String patientId;
    private String condition;
    private long timestamp;

    public AlertUndecorated(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getFunction(){
        return "STANDARD ALERT";
    }
}
