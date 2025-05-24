package data_management;

import com.data_management.DataStorage;
import com.data_management.DataStorageSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Check correctness of implementation of {@link DataStorageSingleton} singleton pattern for {@link DataStorage} class.
 */
public class DataStorageSingletonTest {

    /**
     * Checks if {@code getInstance} method returns the same object.
     */
    @Test
    void testGetInstance(){
        DataStorage singleton1 = DataStorageSingleton.getInstance();
        DataStorage singleton2 = DataStorageSingleton.getInstance();

        assertEquals(singleton1, singleton2);

        DataStorage singleton3 = DataStorageSingleton.getInstance();
        singleton3.addPatientData(1, 34.56902, "Test value", 1234);
        DataStorage singleton4 = DataStorageSingleton.getInstance();

        assertEquals(singleton3, singleton4);

    }
}
