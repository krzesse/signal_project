package com.cardio_generator;

import com.data_management.DataStorage;

/**
 * Implementation of singleton pattern, {@link HealthDataSimulator} class can be accessed only through the {@code getInstance} method.
 */
public class HealthDataSimulatorSingleton {
    private static HealthDataSimulator INSTANCE;

    private HealthDataSimulatorSingleton(){}

    /**
     * Check if {@code INSTANCE} object exists, if not create new {@link HealthDataSimulator} object.
     * Finally, provide instance.
     *
     * @return {@link HealthDataSimulator} object.
     */
    public static HealthDataSimulator getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new HealthDataSimulator();
        }
        return INSTANCE;
    }
}
