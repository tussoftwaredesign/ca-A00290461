package com.fitness.tracker.model.workout;

import java.time.LocalDate;



public final class UpdatableWorkout extends BaseWorkout implements Trackable, TrackableSealed {
    public UpdatableWorkout(WorkoutType workoutType, int duration, int caloriesBurned, LocalDate workoutDate) {
        super(workoutType, duration, caloriesBurned, workoutDate);
    }

    @Override
    public void updateWorkout(String workoutType, int duration, int caloriesBurned) {
        super.updateWorkout(workoutType, duration, caloriesBurned);
    }

    public String getWorkoutDetails() {
        return "Workout Type: " + workoutType + ", Duration: " + duration + " mins, Calories Burned: " + caloriesBurned
                + ", Date: " + workoutDate;
    }
}
