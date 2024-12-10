package com.fitness.tracker.model.workout;

import java.util.ArrayList;
import java.util.List;

public class SealedWorkoutUpdater {
    private final List<TrackableSealed> workoutList = new ArrayList<>();

    // Add a new workout to the list
    public void addWorkout(TrackableSealed workout) {
        workoutList.add(workout);
    }

    // Update an existing workout by index
    public void updateWorkout(int index, String workoutType, int duration, int caloriesBurned) {
        TrackableSealed workout = workoutList.get(index);
        workout.updateWorkout(workoutType, duration, caloriesBurned);
    }

    // Show details of a workout
    public void showWorkoutDetails(int index) {
        TrackableSealed workout = workoutList.get(index);

        // Check if it's an UpdatableWorkout instance
        if (workout instanceof UpdatableWorkout updatableWorkout) {
            System.out.println(updatableWorkout.getWorkoutDetails());
        } else {
            System.out.println("Unknown workout type.");
        }
    }
}
