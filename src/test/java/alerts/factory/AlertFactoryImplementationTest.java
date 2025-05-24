package alerts.factory;

import com.alerts.factory.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.alerts.Alert;

/**
 * Checks correctness of factory implementation.
 */
public class AlertFactoryImplementationTest {

    /**
     * Consecutively creates different factory instances and checks correctness of their {@code createAlert} method.
     */
    @Test
    void testGetAndCreateAlertMethods(){
        AlertFactoryImplemetation factory = new AlertFactoryImplemetation();

        AlertFactory pressureFactory = factory.get("Blood Pressure");
        Alert pressureAlert = pressureFactory.createAlert("0", "test", 12345);
        assertEquals(pressureAlert.getTimestamp(), 12345);
        assertEquals(pressureAlert.getPatientId(), "0");
        assertEquals(pressureAlert.getCondition(), "Blood Pressure Alert: test");


        factory.get("Blood Oxygen");
        AlertFactory oxygenFactory = factory.get("Blood Oxygen");
        Alert oxygenAlert = oxygenFactory.createAlert("126", "test", 102010);
        assertEquals(oxygenAlert.getTimestamp(), 102010);
        assertEquals(oxygenAlert.getPatientId(), "126");
        assertEquals(oxygenAlert.getCondition(), "Blood Oxygen Alert: test");


        factory.get("ECG");
        AlertFactory ecgFactory = factory.get("ECG");
        Alert ecgAlert = ecgFactory.createAlert("99998", "kadjfaknv", 0);
        assertEquals(ecgAlert.getTimestamp(), 0);
        assertEquals(ecgAlert.getPatientId(), "99998");
        assertEquals(ecgAlert.getCondition(), "ECG Alert: kadjfaknv");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.get("Invalid");
        });
        String expectedMessage = "Wrong alert";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
