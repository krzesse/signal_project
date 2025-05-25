package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

import com.cardio_generator.outputs.WebSocketOutputStrategy;

/**
 * Specifies client's action when connected to the WebSocket server.
 * Designed to handle messages from {@link WebSocketOutputStrategy} server.
 */
public class RecordsWebSocketClient extends WebSocketClient {
    public RecordsWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to server");
        send("Hello from client!");
    }

    /**
     * Parses {@code message} to {@link PatientRecord} object. Uses {@link DataStorageSingleton} instance to access the {@link DataStorage} object.
     * Then, it adds {@code record} variables to storage via {@code addPatientData} method.
     *
     * @param message received from server.
     */
    @Override
    public void onMessage(String message) {
        try {
            PatientRecord record = CSVToPatientRecord.parse(message);

            DataStorageSingleton.getInstance().addPatientData(record.getPatientId(), record.getMeasurementValue(),
                    record.getRecordType(), record.getTimestamp());

        } catch (Exception e){
            System.err.println("Failure with message: " + message);
            e.printStackTrace();
        }

    }

        @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

}
