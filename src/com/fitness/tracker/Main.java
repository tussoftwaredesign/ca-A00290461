package com.fitness.tracker;

import com.fitness.tracker.model.UserProfile;
import com.fitness.tracker.model.goals.FitnessGoal;
import com.fitness.tracker.model.goals.FitnessProgress;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create and display User Profile
        UserProfile user = new UserProfile("Alice", 30, 65.0, 1.70);
        System.out.println(user);  // Output: User Profile: Alice, Age: 30, Weight: 65.00 kg, Height: 1.70 m

        // Create a fitness goal: burn 2000 calories in 7 days
        FitnessGoal calorieGoal = new FitnessGoal(
                "Calories", 2000.0,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        // Print the fitness goal
        System.out.println(calorieGoal);

        // Create a FitnessProgress tracker for this goal
        FitnessProgress progress = new FitnessProgress(calorieGoal);

        // Add progress for a couple of days
        progress.addProgress(LocalDate.now(), 500); // Day 1 progress
        progress.addProgress(LocalDate.now().plusDays(1), 800); // Day 2 progress

        // Print the progress details
        System.out.println(progress);

        // Print the goal achievement summary
        System.out.println(progress.getGoalSummary());

        // Check if the goal is met
        if (progress.isGoalMet()) {
            System.out.println("Congratulations! Goal met.");
        } else {
            System.out.println("Keep going! You're on track.");
        }
    }
}
