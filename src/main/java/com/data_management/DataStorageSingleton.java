package com.data_management;

/**
 * Implementation of singleton pattern, {@link DataStorage} class can be accessed only through the {@code getInstance} method.
 */
public class DataStorageSingleton {
    private static DataStorage INSTANCE;

    private DataStorageSingleton(){}

    /**
     * Check if {@code INSTANCE} object exists, if not create new {@link DataStorage} object.
     * Finally, provide instance.
     *
     * @return {@link DataStorage} object.
     */
    public static DataStorage getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new DataStorage();
        }
        return INSTANCE;
    }
}
