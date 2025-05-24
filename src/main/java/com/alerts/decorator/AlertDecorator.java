package com.alerts.decorator;

/**
 * Wraps the {@link Alert} implementation to extend its functionality.
 */
public class AlertDecorator implements Alert{
    private Alert decoratedAlert;

    public AlertDecorator(Alert decoratedAlert){
        this.decoratedAlert = decoratedAlert;
    }

    @Override
    public String getPatientId(){
        return decoratedAlert.getPatientId();
    }

    @Override
    public String getCondition(){
        return decoratedAlert.getCondition();
    }

    @Override
    public long getTimestamp(){
        return decoratedAlert.getTimestamp();
    }

    @Override
    public String getFunction(){
        return decoratedAlert.getFunction();
    }
}
