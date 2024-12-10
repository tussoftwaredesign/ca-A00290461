package com.fitness.tracker.model.workout;
import java.time.LocalDate;
public sealed class BaseWorkout implements TrackableSealed permits UpdatableWorkout {
    String workoutType;
    int duration; // in minutes
    int caloriesBurned;
    LocalDate workoutDate;
    public BaseWorkout(WorkoutType workoutType, int duration, int caloriesBurned, LocalDate workoutDate) {
        this.workoutType = workoutType.toString(); // Interaction with a custom `WorkoutType` class
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.workoutDate = workoutDate;
    }
    // Update workout details
    public void updateWorkout(String workoutType, int duration, int caloriesBurned) {
        this.workoutType = workoutType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }
    // Getter methods
    public String getWorkoutType() {
        return workoutType;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    // Optionally, you can add a toString() method if you want to represent workout data as a string
    @Override
    public String toString() {
        return String.format("%s - %d minutes, %d calories, Date: %s", workoutType, duration, caloriesBurned, workoutDate);
    }
}
