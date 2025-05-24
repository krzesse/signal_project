package alerts.decorator;

import com.alerts.decorator.Alert;
import com.alerts.decorator.AlertUndecorated;
import com.alerts.decorator.PriorityAlertDecorator;
import com.alerts.decorator.RepeatedAlertDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks correctness of {@link PriorityAlertDecorator} getters output.
 */
public class PriorityAlertDecoratorTest {

    @Test
    void testGetters(){
        Alert alert = new AlertUndecorated("1", "test condition", 0);
        Alert priorityAlert = new PriorityAlertDecorator(alert);

        assertEquals(priorityAlert.getPatientId(), "1");
        assertEquals(priorityAlert.getCondition(), "test condition");
        assertEquals(priorityAlert.getTimestamp(), 0);
        assertEquals(priorityAlert.getFunction(), "PRIORITY ALERT");
    }
}
