package cardio_generator.outputs;

import com.cardio_generator.generators.AlertGenerator;
import com.cardio_generator.outputs.ConsoleOutputStrategy;
import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks correctness of {@code generate} method of {@link ConsoleOutputStrategy} class.
 * Compares output of console with generated data.
 */
public class ConsoleOutputStrategyTest {
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
    void testOutput(){
        OutputStrategy strategy = new ConsoleOutputStrategy();

        strategy.output(0, 111111, "test", "test data");
        String output = outputStream.toString();

        assertEquals(output, "Patient ID: 0, Timestamp: 111111, Label: test, Data: test data\n");
    }
}
