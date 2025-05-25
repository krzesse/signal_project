package com.data_management;

import java.io.IOException;

public interface DataReader {
    /**
     * Reads data from a specified source and stores it in the data storage.
     * Week 5 change: Now method does not require {@link DataStorage} object, since it is accessed through {@link DataStorageSingleton}.
     * This enables more flexibility with data parsing, critical for WebSocket approach.
     *
     * @throws IOException if there is an error reading the data
     */
    void readData() throws IOException;
}
