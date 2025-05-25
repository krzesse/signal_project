package data_management;

import com.data_management.DataStorage;
import com.data_management.DataStorageSingleton;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import com.cardio_generator.outputs.WebSocketOutputStrategy;
import com.data_management.*;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Checks correctness of {@link RecordsWebSocketClient}, primarily if it correctly handles messages from server.
 * It does not check error handling of invalid messages from the server, since client is designed to handle messages from {@link WebSocketOutputStrategy}.
 * Where this server handles correct formating of messages.
 *
 * The {@link WebSocketDataReader} will not be tested, since all the parsing logic is implemented inside client,
 * and it only handles connecting client to server.
 */
public class RecordsWebSocketClientTest {

    /**
     * Creates new server and connects it to client.
     * Uses {@code output} method to send new message from the server.
     * Finally, checks if message is correctly handled by the client.
     *
     * @throws InterruptedException if thread is interrupted.
     */
    @Test
    void testOnMessage() throws InterruptedException {
        WebSocketOutputStrategy server = new WebSocketOutputStrategy(8887);
        Thread.sleep(5000);

        try {
            URI port = new URI("ws://localhost:8887");
            RecordsWebSocketClient client = new RecordsWebSocketClient(port);
            client.connectBlocking();

        }catch (Exception e){
            e.printStackTrace();
        }

        server.output(101, 171648L, "Heart Rate", "72.57");
        Thread.sleep(5000);
        List<PatientRecord> record = DataStorageSingleton.getInstance().getRecords(101, 0, System.currentTimeMillis());

        assertNotNull(record);

        assertEquals(record.get(0).getPatientId(), 101);
        assertEquals(record.get(0).getTimestamp(), 171648);
        assertEquals(record.get(0).getRecordType(), "Heart Rate");
        assertEquals(record.get(0).getMeasurementValue(), 72.57);
    }
}
