package com.alerts.decorator;

/**
 * Extends {@link AlertDecorator} to specify function of this alert, that is repeating alert.
 */
public class RepeatedAlertDecorator extends AlertDecorator{
    public RepeatedAlertDecorator(Alert alert){
        super(alert);
    }

    /**
     * Specifies function of alert as repeating one.
     *
     * @return type of alert
     */
    @Override
    public String getFunction(){
        return "REPEATED ALERT";
    }
}
