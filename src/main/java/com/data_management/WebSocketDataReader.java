package com.data_management;

import java.net.URI;

/**
 * Implements {@link DataReader} for reading and parsing data from WebSocket server specified in constructor.
 * Uses {@link RecordsWebSocketClient} to connect with the server.
 */
public class WebSocketDataReader implements DataReader{
    private String uri;

    public WebSocketDataReader(String uri) {
        this.uri = uri;
    }

    /**
     * Connects {@link RecordsWebSocketClient} client to the server specified by {@code uri}.
     * Client handles messages from server parsing them to {@link DataStorage} object.
     * Handles any errors occurring while client stays connected to the server.
     */
    @Override
    public void readData(){
        try {
            URI port = new URI(uri);
            RecordsWebSocketClient client = new RecordsWebSocketClient(port);
            client.connect();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
