package generators;

import com.cardio_generator.generators.AlertGenerator;
import com.cardio_generator.generators.PatientDataGenerator;
import com.cardio_generator.outputs.ConsoleOutputStrategy;
import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks correctness of {@code generate} method of {@link AlertGenerator} class.
 * Due to randomness of generation only correct output types are checked, generated values remain unchecked.
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
    void testGenerate(){
        OutputStrategy strategy = new ConsoleOutputStrategy();
        PatientDataGenerator generator = new AlertGenerator(0);

        for (int i = 0; i < 100; i++) {
            generator.generate(0, strategy);
        }

        String output = outputStream.toString();

        boolean foundTriggered = output.contains("triggered");
        boolean foundResolved = output.contains("resolved");
        boolean foundId = output.contains("Patient ID: 0");
        boolean foundType = output.contains("Alert");

        assertTrue(foundTriggered || foundResolved);
        assertTrue(foundId);
        assertTrue(foundType);
    }
}
