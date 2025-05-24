package alerts.decorator;

import com.alerts.decorator.Alert;
import com.alerts.decorator.AlertUndecorated;
import com.alerts.decorator.RepeatedAlertDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks correctness of {@link RepeatedAlertDecorator} getters output.
 */
public class RepeatedAlertDecoratorTest {
    @Test
    void testGetters(){
        Alert alert = new AlertUndecorated("1", "test condition", 0);
        Alert repeatAlert = new RepeatedAlertDecorator(alert);

        assertEquals(repeatAlert.getPatientId(), "1");
        assertEquals(repeatAlert.getCondition(), "test condition");
        assertEquals(repeatAlert.getTimestamp(), 0);
        assertEquals(repeatAlert.getFunction(), "REPEATED ALERT");
    }
}
