
package com.fitness.tracker.model.workout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class WorkoutLog {

    private static final List<Workout> workouts = new ArrayList<>();

    // Add a new workout to the log
    public void logWorkout(Workout workout) {
        workouts.add(workout);
    }
    // Overloaded method to log multiple workouts at once using varargs (User Story 5)
    public void logWorkouts(Workout... workouts) {
        for (Workout workout : workouts) {
            this.workouts.add(workout);
        }
    }

    // New method to log a default workout (30 minutes RUNNING, 0 calories, today as date)
    public void logDefaultRunningWorkout() {
        Workout workout = new Workout(WorkoutType.RUNNING, 30,300.0, LocalDate.now());
        workouts.add(workout);
    }

    // Get all workouts
    public List<Workout> getAllWorkouts() {
        return workouts; // Return a copy to avoid external modification
    }

    // Get workouts by date range
    public static List<Workout> getWorkoutsByDateRange(LocalDate startDate, LocalDate endDate) {
        return workouts.stream()
                .filter(workout -> !workout.date().isBefore(startDate) && !workout.date().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // Example method to summarize the total calories burned
    public double getTotalCaloriesBurned() {
        return workouts.stream()
                .mapToDouble(Workout::caloriesBurned)
                .sum();
    }
    // Print all workouts
    public void printAllWorkouts() {
        workouts.forEach(System.out::println); // Method reference replacing lambda
    }
}
