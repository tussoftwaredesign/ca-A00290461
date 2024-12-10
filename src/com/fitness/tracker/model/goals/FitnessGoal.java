package com.fitness.tracker.model.goals;
import java.time.LocalDate;
import java.util.Objects;

public record FitnessGoal(
        String goalType,
        double targetValue,
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
    // Getter method for targetValue
    public double targetValue() {
        return targetValue;
    }
    @Override
    public String toString() {
        return String.format("Goal: %s, Target: %.2f, Duration: %s to %s",
                goalType, targetValue, startDate, endDate);
    }
}
