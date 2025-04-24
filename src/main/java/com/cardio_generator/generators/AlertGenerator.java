package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {
    public static final Random RANDOM_GENERATOR = new Random(); // changed variable to upper snake case; deleted unnecessary spacing
    private boolean[] alertStates; // false = resolved, true = pressed; changed variable name to camel case

    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1]; // camel case; line break after/before opening/closing brace
    }

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
