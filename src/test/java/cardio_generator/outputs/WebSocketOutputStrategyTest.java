package cardio_generator.outputs;

import org.junit.jupiter.api.Test;

import com.cardio_generator.outputs.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Checks correctness of {@link OutputStrategy} implementation.
 * Correct handling of server setup and error handling.
 */
public class WebSocketOutputStrategyTest {

    /**
     * Checks if all illegal formats of variables are handled correctly.
     *
     * @throws InterruptedException if thread is interrupted.
     */
    @Test
    void testOutput() throws InterruptedException {
        WebSocketOutputStrategy server = new WebSocketOutputStrategy(8887);
        Thread.sleep(5000);

        //checks correct error handling of label as null
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            server.output( 1, 1234L, null, "89.34%");
        });
        String expectedMessage1 = "Illegal null values";
        String actualMessage1 = exception1.getMessage();

        assertEquals(expectedMessage1, actualMessage1);

        //checks correct error handling of data type as null
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            server.output( 1, 1234L, "test", null);
        });
        String expectedMessage2 = "Illegal null values";
        String actualMessage2 = exception2.getMessage();

        assertEquals(expectedMessage2, actualMessage2);

        //checks correct error handling of invalid data type
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            server.output( 1, 1234L, "test", "invalid");
        });
        String expectedMessage3 = "Data should be convertible to double";
        String actualMessage3 = exception3.getMessage();

        assertEquals(expectedMessage2, actualMessage2);


    }
}
