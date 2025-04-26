package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * Handles client connection with tcp server and stores generated data there.
 * Implements {@code OutputStrategy} interface to ensure correct structure.
 */
public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * Upon declaring, it is required to specify integer specifying the port at which tcp server will start.
     * It ensures to connection and access to a client, that provides access to {@code PrintWriter} object, which enables sending data.
     *
     * @param port identification of port number at which tcp will listen for client, enabling to connect correctly.
     */
    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Using client connection it sends all the input data to tcp server.
     * It overrides {@code output} method of {@code OutputStrategy} interface.
     *
     * @param patientId identification of a patient
     * @param timestamp specifies when the action was taken
     * @param label description of stored information
     * @param data describes type of data stored
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}
