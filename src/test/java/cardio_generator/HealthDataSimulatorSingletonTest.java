package cardio_generator;

import com.cardio_generator.*;
import com.data_management.DataStorage;
import com.data_management.DataStorageSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Check correctness of implementation of {@link HealthDataSimulatorSingleton} singleton pattern for {@link HealthDataSimulator} class.
 */
public class HealthDataSimulatorSingletonTest {

    /**
     * Checks if {@code getInstance} method returns the same object.
     * {@link HealthDataSimulator} object is static object, so instance of objects cannot be changed.
     */
    @Test
    void testGetInstance(){
        HealthDataSimulator singleton1 = HealthDataSimulatorSingleton.getInstance();
        HealthDataSimulator singleton2 = HealthDataSimulatorSingleton.getInstance();

        assertEquals(singleton1, singleton2);
    }
}
