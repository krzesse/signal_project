package alerts.strategy;

import com.alerts.strategy.AlertStrategy;
import com.alerts.strategy.AlertStrategySelector;
import com.alerts.strategy.*;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Checks validity of evaluation logic of {@link BloodPressureStrategy}.
 */
public class BloodPressureStrategyTest {
    /**
     * Selects adequate strategy, creates test records, checks if the strategy correctly assesses them.
     */
    @Test
    void testCheckAlert(){
        AlertStrategySelector selector = new AlertStrategySelector();
        AlertStrategy strategy = selector.get("Blood Pressure");

        PatientRecord record1 = new PatientRecord(1, 234.9482, "Systolic", 112204);
        PatientRecord record2 = new PatientRecord(2, 100.0001, "Diastolic", 122205);
        PatientRecord record3 = new PatientRecord(3, -3.0202, "Heart Rate", 929201);

        assertTrue(strategy.checkAlert(record1));
        assertFalse(strategy.checkAlert(record2));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.checkAlert(record3);
        });
        String expectedMessage = "Wrong record type";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
