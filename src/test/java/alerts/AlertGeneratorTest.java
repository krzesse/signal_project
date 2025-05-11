package alerts;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks correctness of {@code evaluateData} method.
 * Validates the sorting logic and the outputting the triggered alerts via terminal.
 */
public class AlertGeneratorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to a ByteArrayOutputStream
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        // Restore the original System.out after each test
        System.setOut(originalOut);
    }

    @Test
    void testEvaluateData(){
        Patient p = new Patient(1);

        double[] measurements = {171.87, 73.42, -1, 92.01, 0.001, 21.89, 54.72, 0};
        String[] recordTypes = {"SystolicPressure", "DiastolicPressure", "Don't belong anywhere", "Saturation", "ECG", "Saturation", "SystolicPressure", "Manual Alarm"};
        long[] t = new long[measurements.length];
        for (int j = 0; j< t.length; j++){
            t[j] = System.currentTimeMillis();
        }
        for (int i = 0; i < recordTypes.length; i++){
            p.addRecord(measurements[i], recordTypes[i], t[i]);
        }

        DataStorage storage = new DataStorage();
        AlertGenerator generator = new AlertGenerator(storage);
        generator.evaluateData(p);

        String expectedOutput = "Critical Threshold Alert: SystolicPressure is below threshold at 54.72" + System.lineSeparator()
                + "Low Saturation Alert: Saturation level to low at 21.89" + System.lineSeparator()
                + "Hypoxemia Alert: Saturation value 21.89 at " + t[6] + " and SystolicPressure value 54.72 at " + t[7] + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());

    }
}
