package alerts;

import com.alerts.Alert;
import com.alerts.AlertRule;
import com.alerts.HypoxemiaAlertRule;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides tests for {@link HypoxemiaAlertRule}.
 * Checks correctness of outputting {@link Alert} objects informing of hypoxemia symptoms, by {@code evaluate} method.
 */
public class HypoxemiaAlertRuleTest {

    /**
     * Initializes multiple lists with {@link PatientRecord} objects.
     * Creates multiple instances of alerts using {@link Alert} constructor and {@link HypoxemiaAlertRule} {@code evaluate} method.
     * Compares the expected {@link Alert} objects with ones produced by {@code evaluate} method.
     */
    @Test
    void testEvaluate(){
        List<PatientRecord> sr1 = new ArrayList<>();
        List<PatientRecord> sr2 = new ArrayList<>();
        List<PatientRecord> sr3 = new ArrayList<>();
        List<PatientRecord> sr4 = new ArrayList<>();
        List<PatientRecord> sr5 = new ArrayList<>();
        List<PatientRecord> sr6 = new ArrayList<>();
        List<PatientRecord> sr7 = new ArrayList<>();
        List<PatientRecord> sr8 = new ArrayList<>();

        long t = System.currentTimeMillis();
        PatientRecord r1 = new PatientRecord(1, 98.99, "Saturation", t);
        PatientRecord r2 = new PatientRecord(1, 153.07, "SystolicPressure", t + 20);
        sr1.add(r1);
        sr2.add(r2);
        AlertRule ha1 = new HypoxemiaAlertRule(sr2, sr1);

        PatientRecord r3 = new PatientRecord(1, 70, "Saturation", t);
        PatientRecord r4 = new PatientRecord(1, 103.84, "SystolicPressure", t + 20);
        sr3.add(r3);
        sr4.add(r4);
        AlertRule ha2 = new HypoxemiaAlertRule(sr4, sr3);

        long t2 = t + Duration.ofDays(29).toMillis();
        PatientRecord r5 = new PatientRecord(1, 70, "Saturation", t);
        PatientRecord r6 = new PatientRecord(1, 0, "SystolicPressure", t2);
        sr5.add(r5);
        sr6.add(r6);
        AlertRule ha3 = new HypoxemiaAlertRule(sr6, sr5);

        long t3 = t + Duration.ofSeconds(20).toMillis();
        PatientRecord r7 = new PatientRecord(1, 70.1, "Saturation", t);
        PatientRecord r8 = new PatientRecord(1, 0.9, "SystolicPressure", t3);
        sr7.add(r7);
        sr8.add(r8);
        AlertRule ha4 = new HypoxemiaAlertRule(sr8, sr7);

        Alert a1 = null;
        Alert a2 = null;
        Alert a3 = null;
        Alert a4 = new Alert("1", "Hypoxemia Alert: Saturation value 70.1 at " + t + " and SystolicPressure value 0.9 at " + t3, t);

        assertEquals(a1, ha1.evaluate());
        assertEquals(a2, ha2.evaluate());
        assertEquals(a3, ha3.evaluate());
        assertEquals(a4.getPatientId(), ha4.evaluate().getPatientId());
        assertEquals(a4.getCondition(), ha4.evaluate().getCondition());
        assertEquals(a4.getTimestamp(), ha4.evaluate().getTimestamp());

    }
}
