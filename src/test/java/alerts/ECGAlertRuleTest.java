package alerts;

import com.alerts.Alert;
import com.alerts.ECGAlertRule;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides tests for {@link ECGAlertRule}.
 * Checks correctness of outputting {@link Alert} objects, by {@code evaluate} method.
 */
public class ECGAlertRuleTest {

    /**
     * Initializes multiple lists with {@link PatientRecord} objects.
     * Creates multiple instances of alerts using {@link Alert} constructor and {@link ECGAlertRule} {@code evaluate} method.
     * Compares the expected {@link Alert} objects with ones produced by {@code evaluate} method.
     */
    @Test
    void testEvaluate(){
        List<PatientRecord> sr1 = new ArrayList<>();
        List<PatientRecord> sr2 = new ArrayList<>();
        List<PatientRecord> sr3 = new ArrayList<>();


        long t = System.currentTimeMillis();
        PatientRecord r1 = new PatientRecord(1, 92, "ECG", t);
        sr1.add(r1);
        ECGAlertRule ea1 = new ECGAlertRule(sr1);

        PatientRecord r2 = new PatientRecord(1, 92, "ECG", t);
        PatientRecord r3 = new PatientRecord(1, 91, "ECG", t+1);
        PatientRecord r4 = new PatientRecord(1, 80, "ECG", t+11);
        PatientRecord r5 = new PatientRecord(1, 79, "ECG", t+111);
        PatientRecord r6 = new PatientRecord(1, 90, "ECG", t+1111);
        PatientRecord r7= new PatientRecord(1, 92, "ECG", t+11111);
        PatientRecord r8 = new PatientRecord(1, 95, "ECG", t+1111111);
        PatientRecord r9 = new PatientRecord(1, 99, "ECG", t+12222222);
        PatientRecord r10 = new PatientRecord(1, 90, "ECG", t+12222223);
        PatientRecord r11 = new PatientRecord(1, 89, "ECG", t+12222224);
        PatientRecord r12 = new PatientRecord(1, 100, "ECG", t+12222225);
        PatientRecord r13 = new PatientRecord(1, 88, "ECG", t+12222226);
        PatientRecord r14 = new PatientRecord(1, 99, "ECG", t+12222227);
        PatientRecord r15 = new PatientRecord(1, 92, "ECG", t+12222229);
        sr2.add(r2);
        sr2.add(r3);
        sr2.add(r4);
        sr2.add(r5);
        sr2.add(r6);
        sr2.add(r7);
        sr2.add(r8);
        sr2.add(r9);
        sr2.add(r10);
        sr2.add(r11);
        sr2.add(r12);
        sr2.add(r13);
        sr2.add(r14);
        sr2.add(r15);
        ECGAlertRule ea2 = new ECGAlertRule(sr2);
//38.4
        long t2 = t+ Duration.ofMinutes(10).toMillis();
        long t1 = t+Duration.ofMinutes(6).toMillis();
        PatientRecord r16 = new PatientRecord(1, 92, "ECG", t);
        PatientRecord r17 = new PatientRecord(1, 91, "ECG", t+Duration.ofMinutes(1).toMillis());
        PatientRecord r18 = new PatientRecord(1, 80, "ECG", t+Duration.ofMinutes(2).toMillis());
        PatientRecord r19 = new PatientRecord(1, 79, "ECG", t+Duration.ofMinutes(3).toMillis());
        PatientRecord r20 = new PatientRecord(1, 90, "ECG", t+Duration.ofMinutes(4).toMillis());
        PatientRecord r21= new PatientRecord(1, 92, "ECG", t+Duration.ofMinutes(5).toMillis());
        PatientRecord r22 = new PatientRecord(1, 10, "ECG", t1);
        PatientRecord r23 = new PatientRecord(1, 40, "ECG", t+Duration.ofMinutes(7).toMillis());
        PatientRecord r24 = new PatientRecord(1, 30, "ECG", t+Duration.ofMinutes(8).toMillis());
        PatientRecord r25 = new PatientRecord(1, 20, "ECG", t+Duration.ofMinutes(9).toMillis());
        PatientRecord r26 = new PatientRecord(1, 200, "ECG", t2);
        PatientRecord r27 = new PatientRecord(1, 150, "ECG", t+Duration.ofMinutes(11).toMillis());
        PatientRecord r28 = new PatientRecord(1, 300, "ECG", t+Duration.ofMinutes(12).toMillis());
        PatientRecord r29 = new PatientRecord(1, 92, "ECG", t+Duration.ofMinutes(13).toMillis());
        sr3.add(r16);
        sr3.add(r17);
        sr3.add(r18);
        sr3.add(r19);
        sr3.add(r20);
        sr3.add(r21);
        sr3.add(r22);
        sr3.add(r23);
        sr3.add(r24);
        sr3.add(r25);
        sr3.add(r26);
        sr3.add(r27);
        sr3.add(r28);
        sr3.add(r29);
        ECGAlertRule ea3 =  new ECGAlertRule(sr3);


        Alert a3 = new Alert("1","ECG Spike Alert: There is a spike from " + 10.0 + " to " + 200.0, t2);

        assertNull(ea1.evaluate());
        assertNull(ea2.evaluate());
        assertEquals(a3.getTimestamp(), ea3.evaluate().getTimestamp());
        assertEquals(a3.getCondition(), ea3.evaluate().getCondition());
        assertEquals(a3.getPatientId(), ea3.evaluate().getPatientId());



    }
}
