package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Enables generation of alerts for patients.
 * Implements {@code PatientDataGenerator} interface to ensure {@code generate} method is declared correctly.
 */
public class AlertGenerator implements PatientDataGenerator {
    public static final Random RANDOM_GENERATOR = new Random(); // changed variable to upper snake case; deleted unnecessary spacing
    private boolean[] alertStates; // false = resolved, true = pressed; changed variable name to camel case

    /**
     * Creates boolean array of size of number of patients.
     *
     * @param patientCount specifies number of patients in database.
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1]; // camel case; line break after/before opening/closing brace
    }

    /**
     * For specified patient it randomly decides if there is an alert triggered for the patient.
     * There is 90% chance that alert will not happen.
     * Upon resolved/triggered alert, it is stored using specified output method.
     * It catches any error and prints message upon generating data.
     *
     * @param patientId specifies to which patient data is generated.
     * @param outputStrategy declares how the generated data is stored.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {  // camel case
                if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve; upper snake case
                    alertStates[patientId] = false; // camel case
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency; changed variable name
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period; changed variable name
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p; // upper snake case

                if (alertTriggered) {
                    alertStates[patientId] = true; // camel case
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
