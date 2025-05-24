package alerts.decorator;

import com.alerts.decorator.Alert;
import com.alerts.decorator.AlertUndecorated;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks correctness of {@link AlertUndecorated} getters output.
 */
public class AlertUndecoratedTest {

    @Test
    void testGetters(){
        Alert alert = new AlertUndecorated("1", "test condition", 0);

        assertEquals(alert.getPatientId(), "1");
        assertEquals(alert.getCondition(), "test condition");
        assertEquals(alert.getTimestamp(), 0);
        assertEquals(alert.getFunction(), "STANDARD ALERT");
    }
}
