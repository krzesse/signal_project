package data_management;

import com.data_management.CSVToPatientRecord;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Develops testing to check correctness of parsing logic.
 */
public class CSVToPatientRecordTest {

    /**
     * checks correctness of parsed CSV format strings to {@link PatientRecord} object.
     */
    @Test
    void testParse(){
        String testMessage1 = "1,234,ttt,93.23";
        String testMessage2 = "9567,110200,345,00.00%";
        String testMessage3 = "671,999999, stkn,resolved";
        String testMessage4 = "671,999999, stkn,triggered";


        PatientRecord record1 = CSVToPatientRecord.parse(testMessage1);
        PatientRecord record2 = CSVToPatientRecord.parse(testMessage2);
        PatientRecord record3 = CSVToPatientRecord.parse(testMessage3);
        PatientRecord record4 = CSVToPatientRecord.parse(testMessage4);

        assertEquals(record1.getPatientId(), 1);
        assertEquals(record1.getTimestamp(), 234);
        assertEquals(record1.getRecordType(), "ttt");
        assertEquals(record1.getMeasurementValue(), 93.23);

        assertEquals(record2.getPatientId(), 9567);
        assertEquals(record2.getTimestamp(), 110200);
        assertEquals(record2.getRecordType(), "345");
        assertEquals(record2.getMeasurementValue(), 0.0);

        assertEquals(record3.getPatientId(), 671);
        assertEquals(record3.getTimestamp(), 999999);
        assertEquals(record3.getRecordType(), " stkn");
        assertEquals(record3.getMeasurementValue(), 0.0);

        assertEquals(record4.getPatientId(), 671);
        assertEquals(record4.getTimestamp(), 999999);
        assertEquals(record4.getRecordType(), " stkn");
        assertEquals(record4.getMeasurementValue(), 1.0);
    }
}
