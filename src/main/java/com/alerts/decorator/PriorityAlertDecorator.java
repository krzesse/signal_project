package com.alerts.decorator;

/**
 * Extends {@link AlertDecorator} to specify function of this alert, that is priority alert.
 */
public class PriorityAlertDecorator extends AlertDecorator{
    public PriorityAlertDecorator(Alert alert){
        super(alert);
    }

    /**
     * Specifies function of alert as priority.
     *
     * @return type of alert
     */
    @Override
    public String getFunction(){
        return "PRIORITY ALERT";
    }
}
