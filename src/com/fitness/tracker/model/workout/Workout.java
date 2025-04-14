
package com.fitness.tracker.model.workout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public record Workout(WorkoutType type, int durationMinutes, double caloriesBurned, LocalDate date) {

    private static final List<Workout> workoutHistory = new ArrayList<>();

    // Example constructor: You can also define custom logic if needed
    public Workout {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        if (caloriesBurned < 0) {
            throw new IllegalArgumentException("Calories burned cannot be negative");
        }
        if (type == null ) {
            throw new IllegalArgumentException("Workout type cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Workout date cannot be null");
        }
    }
    //constructor using 'this()' to call the full constructor
    public Workout(WorkoutType type, int durationMinutes) {
        this(type, durationMinutes, 0.0, LocalDate.now()); // Default caloriesBurned to 0 and date to today
    }

    // Static factory method 1: For full details
    public static Workout of(WorkoutType type, int durationMinutes, double caloriesBurned, LocalDate date) {
        return new Workout(type, durationMinutes, caloriesBurned, date);
    }

    // Static factory method 2: Defaults caloriesBurned to 0.0
    public static Workout of(WorkoutType type, int durationMinutes, LocalDate date) {
        return new Workout(type, durationMinutes, 0.0, date);
    }

    // Static factory method 3: Defaults caloriesBurned to 0.0 and date to today
    public static Workout of(WorkoutType type, int durationMinutes) {
        return new Workout(type, durationMinutes, 0.0, LocalDate.now());
    }

    // Log a new workout and add it to the workout history
    public static void logWorkout(Workout workout) {
        workoutHistory.add(workout);
    }

    // Get the complete workout history
    public static String getWorkoutHistory() {
        if (workoutHistory.isEmpty()) {
            return "No workouts logged yet.";
        }

        StringBuilder history = new StringBuilder("Workout History:\n");
        workoutHistory.forEach(workout -> history.append(workout.toString()).append("\n"));
        return history.toString();
    }

    // Filter workouts by date range
    public static String getWorkoutsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Workout> filteredWorkouts = workoutHistory.stream()
                .filter(workout -> !workout.date().isBefore(startDate) && !workout.date().isAfter(endDate))
                .collect(Collectors.toList());

        if (filteredWorkouts.isEmpty()) {
            return "No workouts logged in the specified date range.";
        }

        StringBuilder history = new StringBuilder("Filtered Workout History by Date range:\n");
        filteredWorkouts.forEach(workout -> history.append(workout.toString()).append("\n"));
        return history.toString();
    }


    public static String formatWorkout(Workout workout) {
        return String.format("Workout: %s - %d minutes, %.2f calories on %s",
                workout.type, workout.durationMinutes, workout.caloriesBurned, workout.date);
    }
    public LocalDate getDate() {
        return date;
    }

    public WorkoutType getType() {
        return type;
    }

    public int getDuration() {
        return durationMinutes;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }
}