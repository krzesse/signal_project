package data_management;

import com.data_management.*;
import com.cardio_generator.outputs.FileOutputStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class is a test for {@link FileDataReader}.
 * It checks correctness of parsing data from specified filepath and outputting it to {@link DataStorage} object.
 */
public class FileDataReaderTest {
    /**
     * Uses {@link FileOutputStrategy} to create test file.
     * Next, initializes {@link FileDataReader} and runs {@code readData} to parse the file and store output in {@link DataStorage} object.
     * Finally, it checks correctness of the stored output and deletes test files.
     *
     * @throws IOException if there is an error while deleting files.
     */
    @Test
    void testReadData() throws IOException {
        String testDirectory = "testOutput";
        String filePath1 = "testOutput/Cholesterol.txt";
        String filePath2 = "testOutput/Saturation.txt";

        try {
            Files.deleteIfExists(Paths.get(filePath1));
            Files.deleteIfExists(Paths.get(filePath2));
            Files.deleteIfExists(Paths.get(testDirectory));
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        FileOutputStrategy testWriter = new FileOutputStrategy(testDirectory);
        testWriter.output(1, 111111, "Cholesterol", "5.7");
        testWriter.output(1, 111112, "Cholesterol", "1.1");
        testWriter.output(1, 111113, "Saturation", "99%");



        DataReader reader = new FileDataReader(filePath1);
        DataStorage storage = DataStorageSingleton.getInstance();

        try {
            reader.readData();
            ((FileDataReader) reader).setFilePath(filePath2);
            reader.readData();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        List<PatientRecord> records = storage.getRecords(1, 111110, 111114);

        assertEquals(3, records.size());
        assertEquals("Cholesterol", records.get(1).getRecordType());
        assertEquals(111112, records.get(1).getTimestamp());
        assertEquals(1, records.get(0).getPatientId());
        assertEquals(5.7, records.get(0).getMeasurementValue());
        assertEquals(99, records.get(2).getMeasurementValue());

        try {
            Files.deleteIfExists(Paths.get(filePath1));
            Files.deleteIfExists(Paths.get(filePath2));
            Files.deleteIfExists(Paths.get(testDirectory));
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
