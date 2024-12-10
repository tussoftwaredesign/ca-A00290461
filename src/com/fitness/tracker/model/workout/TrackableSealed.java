package com.fitness.tracker.model.workout;

public sealed interface TrackableSealed permits BaseWorkout, UpdatableWorkout {
    void updateWorkout(String workoutType, int duration, int caloriesBurned);
}

