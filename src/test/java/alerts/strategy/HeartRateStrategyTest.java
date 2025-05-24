package alerts.strategy;

import com.alerts.strategy.*;
import com.alerts.strategy.AlertStrategy;
import com.alerts.strategy.AlertStrategySelector;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks validity of evaluation logic of {@link HeartRateStrategy}.
 */
public class HeartRateStrategyTest {

    /**
     * Selects adequate strategy, creates test records, checks if the strategy correctly assesses them.
     */
    @Test
    void testCheckAlert(){
        AlertStrategySelector selector = new AlertStrategySelector();
        AlertStrategy strategy = selector.get("Heart Rate");

        PatientRecord record1 = new PatientRecord(1, -200, "Heart Rate", 112204);
        PatientRecord record2 = new PatientRecord(2, 100.0001, "Diastolic", 122205);
        PatientRecord record3 = new PatientRecord(3, 93.7892, "Heart Rate", 929201);

        assertTrue(strategy.checkAlert(record1));
        assertFalse(strategy.checkAlert(record3));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.checkAlert(record2);
        });
        String expectedMessage = "Wrong record type";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
