package alerts;

import com.alerts.Alert;
import com.alerts.BloodSaturationAlertRule;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Provides tests for {@link BloodSaturationAlertRule}.
 * Checks correctness of outputting {@link Alert} objects, by {@code evaluate} method.
 */
public class BloodSaturationAlertRuleTest {

    /**
     * Initializes multiple lists with {@link PatientRecord} objects.
     * Creates multiple instances of alerts using {@link Alert} constructor and {@link BloodSaturationAlertRule} {@code evaluate} method.
     * Compares the expected {@link Alert} objects with ones produced by {@code evaluate} method.
     */
    @Test
    void testEvaluate(){
        List<PatientRecord> sr1 = new ArrayList<>();
        List<PatientRecord> sr2 = new ArrayList<>();
        List<PatientRecord> sr3 = new ArrayList<>();
        List<PatientRecord> sr4 = new ArrayList<>();
        List<PatientRecord> sr5 = new ArrayList<>();

        long t1 = System.currentTimeMillis();
        PatientRecord r1 = new PatientRecord(1, 92, "Saturation", t1);
        sr1.add(r1);
        BloodSaturationAlertRule bs1 = new BloodSaturationAlertRule(sr1);

        PatientRecord r2 = new PatientRecord(1, 10, "Saturation", t1);
        sr2.add(r2);
        BloodSaturationAlertRule bs2 = new BloodSaturationAlertRule(sr2);

        PatientRecord r3 = new PatientRecord(1, 100, "Saturation", t1);
        sr3.add(r3);
        BloodSaturationAlertRule bs3 = new BloodSaturationAlertRule(sr3);

        PatientRecord r4 = new PatientRecord(1, 97, "Saturation", t1);
        PatientRecord r5 = new PatientRecord(1, 99, "Saturation", t1+1);
        PatientRecord r6 = new PatientRecord(1, 96, "Saturation", t1+3);
        PatientRecord r7 = new PatientRecord(1, 92, "Saturation", t1+ Duration.ofHours(11).toMillis());
        sr4.add(r4);
        sr4.add(r5);
        sr4.add(r6);
        sr4.add(r7);
        BloodSaturationAlertRule bs4 = new BloodSaturationAlertRule(sr4);

        long t2 = t1+1;
        long t3 = t1+3;
        PatientRecord r8 = new PatientRecord(1, 92, "Saturation", t1);
        PatientRecord r9 = new PatientRecord(1, 99, "Saturation", t2);
        PatientRecord r10 = new PatientRecord(1, 95, "Saturation", t1+2);
        PatientRecord r11 = new PatientRecord(1, 93, "Saturation", t3);
        sr5.add(r8);
        sr5.add(r9);
        sr5.add(r10);
        sr5.add(r11);
        BloodSaturationAlertRule bs5 = new BloodSaturationAlertRule(sr5);

        Alert a1 = null;
        Alert a2 = new Alert("1", "Low Saturation Alert: Saturation level to low at 10.0", t1);
        Alert a3 = null;
        Alert a4 = null;
        Alert a5 = new Alert("1", "Rapid Drop Alert: Saturation dropped from 99.0 at " + t2 + " to " + 93.0 + " at " + t3, t3);

        assertEquals(a1, bs1.evaluate());
        assertEquals(a3, bs3.evaluate());
        assertEquals(a4, bs4.evaluate());
        assertEquals(a2.getCondition(), bs2.evaluate().getCondition());
        assertEquals(a2.getTimestamp(), bs2.evaluate().getTimestamp());
        assertEquals(a2.getPatientId(), bs2.evaluate().getPatientId());
        assertEquals(a5.getPatientId(), bs5.evaluate().getPatientId());
        assertEquals(a5.getTimestamp(), bs5.evaluate().getTimestamp());
        assertEquals(a5.getCondition(), bs5.evaluate().getCondition());
    }
}
