package data_management;

import com.data_management.Patient;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Check correctness of retrieval of list of {@link PatientRecord} by method {@code getRecords} from {@link Patient}.
 */
public class PatientTest {

    /**
     * Generates multiple {@link PatientRecord} objects and adds them to {@link Patient} through {@code addRecord}.
     * Checks correct retrieval of those records, using different time intervals.
     * Finally, it checks correct behavior when retrieval is asked by {@link Patient} object without any records.
     */
    @Test
    void testGetRecords(){

        List<PatientRecord> records = new ArrayList<>();
        Patient p = new Patient(1);
        for (int i = 0; i < 100; i++){
            p.addRecord( 9,"xyz", i);
        }
        p.addRecord(23, "Saturation", 102);

        Patient p1 = new Patient(2);

        assertEquals(50, p.getRecords(26, 75).size());
        assertEquals(102, p.getRecords(0, 102).get(100).getTimestamp());
        assertEquals("Saturation", p.getRecords(0, 102).get(100).getRecordType());
        assertEquals(23, p.getRecords(0, 103).get(100).getMeasurementValue());
        assertTrue(p.getRecords(90, 19).isEmpty());
        assertEquals(1, p.getRecords(0, 0).size());
        assertEquals(1, p.getRecords(102, 102).size());
        assertTrue(p.getRecords(1000, 90000).isEmpty());
        assertTrue(p1.getRecords(0, 1000000000).isEmpty());
    }
}
