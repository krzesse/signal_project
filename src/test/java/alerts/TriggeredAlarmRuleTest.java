package alerts;

import com.alerts.Alert;
import com.alerts.AlertRule;
import com.alerts.TriggeredAlarmRule;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Provides tests for {@link TriggeredAlarmRule}.
 * Checks correctness of outputting {@link Alert} objects, by {@code evaluate} method.
 */
public class TriggeredAlarmRuleTest {

    /**
     * Initializes multiple lists with {@link PatientRecord} objects.
     * Creates multiple instances of alerts using {@link Alert} constructor and {@link TriggeredAlarmRule} {@code evaluate} method.
     * Compares the expected {@link Alert} objects with ones produced by {@code evaluate} method.
     * Checks if it correctly throws alarm for the most recent pressed button.
     */
    @Test
    void testEvaluate(){
        List<PatientRecord> sr1 = new ArrayList<>();
        List<PatientRecord> sr2 = new ArrayList<>();
        List<PatientRecord> sr3 = new ArrayList<>();
        List<PatientRecord> sr4 = new ArrayList<>();

        long t = System.currentTimeMillis();
        PatientRecord r1 = new PatientRecord(1, 1, "Manual Alarm", t);
        sr1.add(r1);
        AlertRule ma1 = new TriggeredAlarmRule(sr1);

        PatientRecord r2 = new PatientRecord(1, 0, "Manual Alarm", t);
        sr2.add(r2);
        AlertRule ma2 = new TriggeredAlarmRule(sr2);

        PatientRecord r3 = new PatientRecord(1, 1, "Manual Alarm", t);
        PatientRecord r4 = new PatientRecord(1, 0, "Manual Alarm", t+100);
        PatientRecord r5 = new PatientRecord(1, 1, "Manual Alarm", t+800);
        PatientRecord r6 = new PatientRecord(1, 0, "Manual Alarm", t+7000);
        sr3.add(r3);
        sr3.add(r4);
        sr3.add(r5);
        sr3.add(r6);
        AlertRule ma3 = new TriggeredAlarmRule(sr3);

        long t2 = t+ Duration.ofHours(2).toMillis();
        PatientRecord r7 = new PatientRecord(1, 0, "Manual Alarm", t);
        PatientRecord r8 = new PatientRecord(1, 1, "Manual Alarm", t+567);
        PatientRecord r9 = new PatientRecord(1, 0, "Manual Alarm", t+8021);
        PatientRecord r10 = new PatientRecord(1, 1, "Manual Alarm", t+90909);
        PatientRecord r11 = new PatientRecord(1, 0, "Manual Alarm", t+100000);
        PatientRecord r12 = new PatientRecord(1, 1, "Manual Alarm", t+100029);
        PatientRecord r13 = new PatientRecord(1, 0, "Manual Alarm", t+100129);
        PatientRecord r14 = new PatientRecord(1, 1, "Manual Alarm", t2);
        sr4.add(r7);
        sr4.add(r8);
        sr4.add(r9);
        sr4.add(r10);
        sr4.add(r11);
        sr4.add(r12);
        sr4.add(r13);
        sr4.add(r14);
        AlertRule ma4 = new TriggeredAlarmRule(sr4);

        Alert a1 = new Alert("1","Latest Manual Alert: Triggered at "+ t , t);
        Alert a4 = new Alert("1","Latest Manual Alert: Triggered at "+ t2 , t2);

        assertEquals(a1.getCondition(), ma1.evaluate().getCondition());
        assertEquals(a1.getTimestamp(), ma1.evaluate().getTimestamp());
        assertEquals(a1.getPatientId(), ma1.evaluate().getPatientId());
        assertNull(ma2.evaluate());
        assertNull(ma3.evaluate());
        assertEquals(a4.getPatientId(), ma4.evaluate().getPatientId());
        assertEquals(a4.getCondition(), ma4.evaluate().getCondition());
        assertEquals(a4.getTimestamp(), ma4.evaluate().getTimestamp());
    }

}
