package com.cardio_generator.outputs;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

import com.data_management.PatientRecord;

public class WebSocketOutputStrategy implements OutputStrategy {

    private WebSocketServer server;

    public WebSocketOutputStrategy(int port) {
        server = new SimpleWebSocketServer(new InetSocketAddress(port));
        System.out.println("WebSocket server created on port: " + port + ", listening for connections...");
        server.start();
    }

    /**
     * Week 5 changes: {@code output} method checks if given variables can be converted to meaningful data,
     * that can be parsed into {@link PatientRecord} object.
     *
     * @param patientId identification of a patient
     * @param timestamp specifies when the action was taken
     * @param label description of stored information
     * @param data describes type of data stored
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);

        //checks if strings are non-empty.
        if (label == null || data == null){
            throw new IllegalArgumentException("Illegal null values");
        }

        //checks if data can be converted into double.
        try{
            if (data.endsWith("%"))
                data = data.substring(0, data.length() - 1);
            else if (data.equals("triggered"))
                data = "1";
            else if (data.equals("resolved"))
                data = "0";
            Double.parseDouble(data);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Data should be convertible to double");
        }

        // Broadcast the message to all connected clients
        for (WebSocket conn : server.getConnections()) {
            conn.send(message);
        }
    }

    private static class SimpleWebSocketServer extends WebSocketServer {

        public SimpleWebSocketServer(InetSocketAddress address) {
            super(address);
        }

        @Override
        public void onOpen(WebSocket conn, org.java_websocket.handshake.ClientHandshake handshake) {
            System.out.println("New connection: " + conn.getRemoteSocketAddress());
        }

        @Override
        public void onClose(WebSocket conn, int code, String reason, boolean remote) {
            System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
        }

        @Override
        public void onMessage(WebSocket conn, String message) {
            // Not used in this context
        }

        @Override
        public void onError(WebSocket conn, Exception ex) {
            ex.printStackTrace();
        }

        @Override
        public void onStart() {
            System.out.println("Server started successfully");
        }
    }
}
