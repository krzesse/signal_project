package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * {@code PatientDataGenerator} interface defines base structure of generators classes.
 * Classes that implement this interface must provide {@code generate} method responsible for patient data generation.
 */
public interface PatientDataGenerator {
    /**
     * Takes parameters that are necessary to generate some arbitrary patient data and store it at arbitrary storage.
     *
     * @param patientId specifies to which patient data is generated.
     * @param outputStrategy declares how the generated data is stored.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
