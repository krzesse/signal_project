package alerts;

import com.alerts.Alert;
import com.alerts.BloodPressureAlertRule;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides tests for {@link BloodPressureAlertRule}.
 * Checks correctness of outputting {@link Alert} objects, by {@code evaluate} method.
 */
public class BloodPressureAlertRuleTest {

    /**
     * Initializes multiple lists with {@link PatientRecord} objects.
     * Creates multiple instances of alerts using {@link Alert} constructor and {@link BloodPressureAlertRule} {@code evaluate} method.
     * Compares the expected {@link Alert} objects with ones produced by {@code evaluate} method.
     */
    @Test
    void testEvaluate() {

        List<PatientRecord> sr1 = new ArrayList<>();
        List<PatientRecord> dr1 = new ArrayList<>();
        List<PatientRecord> sr2 = new ArrayList<>();
        List<PatientRecord> dr2 = new ArrayList<>();
        List<PatientRecord> sr3 = new ArrayList<>();
        List<PatientRecord> dr3 = new ArrayList<>();
        List<PatientRecord> sr4 = new ArrayList<>();
        List<PatientRecord> dr4 = new ArrayList<>();

        //sys 180/90 dia 120/60
        long t1 = System.currentTimeMillis();
        PatientRecord r1 = new PatientRecord(1, 170, "SystolicPressure", t1);
        PatientRecord r2 = new PatientRecord(1, 100, "DiastolicPressure", t1 + 1);
        sr1.add(r1);
        dr1.add(r2);
        BloodPressureAlertRule bp1 = new BloodPressureAlertRule(sr1, dr1);

        PatientRecord r3 = new PatientRecord(1, 120, "DiastolicPressure", t1+2);
        PatientRecord r4 = new PatientRecord(1, 90, "SystolicPressure", t1+3);
        sr2.add(r4);
        dr2.add(r3);
        BloodPressureAlertRule bp2 = new BloodPressureAlertRule(sr2, dr2);

        PatientRecord r5 = new PatientRecord(1, 170, "SystolicPressure", t1+4);
        PatientRecord r6 = new PatientRecord(1, 159, "SystolicPressure", t1+5);
        PatientRecord r7 = new PatientRecord(1, 148, "SystolicPressure", t1+6);
        sr3.add(r5);
        sr3.add(r6);
        sr3.add(r7);

        PatientRecord r8 = new PatientRecord(1, 90, "DiastolicPressure", t1+7);
        PatientRecord r9 = new PatientRecord(1, 80, "DiastolicPressure", t1+8);
        PatientRecord r10 = new PatientRecord(1, 67, "DiastolicPressure", t1+9);
        dr3.add(r8);
        dr3.add(r9);
        dr3.add(r10);
        BloodPressureAlertRule bp3 = new BloodPressureAlertRule(sr3, dr3);

        PatientRecord r11 = new PatientRecord(1, 203, "SystolicPressure", t1+10);
        PatientRecord r12 = new PatientRecord(1, 59, "DiastolicPressure", t1+11);
        sr4.add(r11);
        dr4.add(r12);
        BloodPressureAlertRule bp4 = new BloodPressureAlertRule(sr4, dr1);
        BloodPressureAlertRule bp5 = new BloodPressureAlertRule(sr1, dr4);

        Alert as1 = null;
        Alert as2 = null;
        Alert as3 = new Alert("1", "Trend Alert: SystolicPressure is decreasing", t1+6);
        Alert as4 = new Alert("1", "Critical Threshold Alert: SystolicPressure is above threshold at 203", t1+10);
        Alert as5 = new Alert("1", "Critical Threshold Alert: DiastolicPressure is below threshold at 59.0", t1+11);

        assertEquals(as1, bp1.evaluate());
        assertEquals(as2, bp2.evaluate());
        assertEquals(as3.getCondition(), bp3.evaluate().getCondition());
        assertEquals(as4.getTimestamp(), bp4.evaluate().getTimestamp());
        assertEquals(as5.getCondition(), bp5.evaluate().getCondition());

    }
}
