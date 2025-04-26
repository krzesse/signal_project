package com.cardio_generator.outputs;

/**
 * Defines base structure of classes tackling outputting patient data.
 * It provides the correct structure for {@code output} method.
 * It is responsible for storing patient data safely using arbitrary method.
 */
public interface OutputStrategy {
    /**
     * Combines all given data, that is all the input variables, and stores it with method of choice.
     *
     * @param patientId identification of a patient
     * @param timestamp specifies when the action was taken
     * @param label description of stored information
     * @param data describes type of data stored
     */
    void output(int patientId, long timestamp, String label, String data);
}
