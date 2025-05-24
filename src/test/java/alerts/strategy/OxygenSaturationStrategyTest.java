package alerts.strategy;

import com.alerts.strategy.AlertStrategy;
import com.alerts.strategy.AlertStrategySelector;
import com.alerts.strategy.*;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks validity of evaluation logic of {@link OxygenSaturationStrategy}.
 */
public class OxygenSaturationStrategyTest {

    /**
     * Selects adequate strategy, creates test records, checks if the strategy correctly assesses them.
     */
    @Test
    void testCheckAlert(){
        AlertStrategySelector selector = new AlertStrategySelector();
        AlertStrategy strategy = selector.get("Oxygen Saturation");

        PatientRecord record1 = new PatientRecord(1, -200, "Saturation", 11452204);
        PatientRecord record2 = new PatientRecord(2, 100.0001, "Saturation", 142205);
        PatientRecord record3 = new PatientRecord(3, 01.7892, "Saturation", 930031);
        PatientRecord record4 = new PatientRecord(3, 93.7892, "Not Saturation", 812983100);

        assertTrue(strategy.checkAlert(record1));
        assertTrue(strategy.checkAlert(record2));
        assertFalse(strategy.checkAlert(record3));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.checkAlert(record4);
        });
        String expectedMessage = "Wrong record type";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
