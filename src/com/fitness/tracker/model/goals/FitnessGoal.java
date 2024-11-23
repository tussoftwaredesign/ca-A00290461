package com.fitness.tracker.model.goals;
import java.time.LocalDate;
import java.util.Objects;

public record FitnessGoal(
        String goalType,      // e.g., "Calories" or "Workout Duration"
        double targetValue,   // e.g., 2000 calories, 300 minutes
        LocalDate startDate,
        LocalDate endDate
) {
    public FitnessGoal {
        // Validation: Ensure targetValue > 0
        if (targetValue <= 0) {
            throw new IllegalArgumentException("Target value must be greater than 0.");
        }
        // Validation: Ensure startDate <= endDate
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must not be after end date.");
        }
    }

    @Override
    public String toString() {
        return String.format("Goal: %s, Target: %.2f, Duration: %s to %s",
                goalType, targetValue, startDate, endDate);
    }
}
