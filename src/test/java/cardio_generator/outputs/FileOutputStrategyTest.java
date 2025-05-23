package cardio_generator.outputs;

import com.cardio_generator.outputs.FileOutputStrategy;
import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputStrategyTest {

    @Test
    void testOutput() throws IOException{
        String testDirectory = "testOutput";
        String testFilePath = "testOutput/test.txt";
        try {
            Files.deleteIfExists(Paths.get(testFilePath));
            Files.deleteIfExists(Paths.get(testDirectory));
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        OutputStrategy output = new FileOutputStrategy(testDirectory);
        output.output(0, 111111, "test", "test data");

        Path path = Paths.get(testDirectory, "test.txt");
        assertTrue(Files.exists(path));
        String file = Files.readString(path);
        assertEquals(file, "Patient ID: 0, Timestamp: 111111, Label: test, Data: test data\n");

        try {
            Files.deleteIfExists(Paths.get(testFilePath));
            Files.deleteIfExists(Paths.get(testDirectory));
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

}
