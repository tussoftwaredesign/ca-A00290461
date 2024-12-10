package com.fitness.tracker.model.workout;

import java.util.List;
import java.util.ArrayList;

public class WorkoutUpdater {
    private List<Trackable> workoutList = new ArrayList<>();

    // Add a new workout to the list
    public void addWorkout(Trackable workout) {
        workoutList.add(workout);
    }

    // Update an existing workout by index
    public void updateWorkout(int index, String workoutType, int duration, int caloriesBurned) {
        Trackable workout = workoutList.get(index);
        workout.updateWorkout(workoutType, duration, caloriesBurned);
    }

    // Show details of a workout
    public void showWorkoutDetails(int index) {
        Trackable workout = workoutList.get(index);

        // Check if it's an UpdatableWorkout instance
        if (workout instanceof UpdatableWorkout) {
            // Safely cast to UpdatableWorkout and print details
            System.out.println(((UpdatableWorkout) workout).getWorkoutDetails());
        }
        else {
            System.out.println("Unknown workout type.");
        }
    }
}
