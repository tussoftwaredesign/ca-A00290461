package com.fitness.tracker;

import com.fitness.tracker.model.UserProfile;
import com.fitness.tracker.model.goals.FitnessGoal;
import com.fitness.tracker.model.goals.FitnessProgress;
import com.fitness.tracker.model.workout.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for their name, age, weight, and height
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your weight (in kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter your height (in meters): ");
        double height = scanner.nextDouble();

        // Create and display User Profile with the provided details
        UserProfile user = new UserProfile(name, age, weight, height);

        // After profile creation success message
        System.out.println("\nProfile created successfully!");
        FitnessGoal calorieGoal = new FitnessGoal(
                "Calories", 2000.0,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );
        WorkoutLog workoutLog = new WorkoutLog();

        boolean exitMenu = false;
        while (!exitMenu) {
            System.out.println("\nSelect an option from the menu:");
            System.out.println("1: Show User Details");
            System.out.println("2: Show BMI");
            System.out.println("3: Show Fitness Goal and Available Workout Types");
            System.out.println("4: Show Fitness Progress");
            System.out.println("5: Show Individual workouts");
            System.out.println("6: Show Multiple  workouts");
            System.out.println("7: Workouts History");
            System.out.println("8: Find calories burned with Filtered Workouts/days");
            System.out.println("9: Update Workout Entries");
            System.out.println("10: Log Default Running workout ");
            System.out.println("11: Updated Workouts status");
            System.out.println("12: Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Show User Details
                    System.out.println("\nHere are your details:");
                    System.out.println(user);
                    break;

                case 2:
                    // Show BMI
                    System.out.println("\nYour BMI:");
                    System.out.println(user.displayBMI());
                    break;

                case 3:
                    // Show Fitness Goal
                    System.out.println("\n==================== Fitness Goal ====================");
                    System.out.println(calorieGoal);// Ensure `calorieGoal` is defined earlier in the program
                    System.out.println("\n==================== Available Workout Types ====================");
                    for (WorkoutType type : WorkoutType.values()) {
                        System.out.println(type);
                    }
                    break;
                case 4:
                    // Show Fitness Progress
                    System.out.println("\n==================== Fitness Progress ========================");
                    // Create a FitnessProgress tracker for this goal
                    FitnessProgress progress = new FitnessProgress(calorieGoal);
                    // Add progress for a couple of days
                    progress.addProgress(LocalDate.now(), 500); // Day 1 progress
                    progress.addProgress(LocalDate.now().plusDays(1), 800); // Day 2 progress

                    // Print the progress details
                    System.out.println(progress);
                    // Display progress bar for goal achievement
                    double totalCaloriesBurned = progress.getTotalProgress();
                    double goalCalories = calorieGoal.targetValue();
                    double progressPercentage = (totalCaloriesBurned / goalCalories) * 100;
                    System.out.println("Progress towards goal: " + String.format("%.2f", progressPercentage) + "%");

                    // Show progress bar (highlighted line)
                    progress.showProgressBar();  // **Progress bar added here**


                    // Print the goal achievement summary
                    System.out.println(progress.getGoalSummary());
                    // Check if the goal is met
                    if (progress.isGoalMet()) {
                        System.out.println("Congratulations! Goal met.");
                    } else {
                        System.out.println("Keep going! You're on track.");
                    }
                    break;
                case 5:
                    //Individual workouts
                    System.out.println("\nInd Workouts:");

                    // Log some workouts
                    workoutLog.logWorkout(new Workout(WorkoutType.RUNNING, 30, 300, LocalDate.now().plusDays(1)));
                    // Print  workouts
                    System.out.println("Workouts:");
                    workoutLog.getAllWorkouts().forEach(workout ->
                            System.out.println(workout.type() + " - " + workout.durationMinutes() + " minutes, "
                                    + workout.caloriesBurned() + " calories, Date: " + workout.date()));
                    break;
                case 6:
                    System.out.println("\nMultiple Workouts:");
                    // Show Multiple  workouts
                    workoutLog.logWorkouts(
                            new Workout(WorkoutType.SWIMMING, 60, 700, LocalDate.now().plusDays(3)),
                            new Workout(WorkoutType.STRENGTH, 40, 350, LocalDate.now().plusDays(4))
                    );

                    // Print all workouts
                    System.out.println("Workouts:");
                    workoutLog.getAllWorkouts().forEach(workout ->
                            System.out.println(workout.type() + " - " + workout.durationMinutes() + " minutes, "
                                    + workout.caloriesBurned() + " calories, Date: " + workout.date())
                    );
                    break;
                case 7:
                    System.out.println("\nWorkouts History:");
                    workoutLog.getAllWorkouts().forEach(workout ->
                            System.out.println(workout.type() + " - " + workout.durationMinutes() + " minutes, "
                                    + workout.caloriesBurned() + " calories, Date: " + workout.date())
                    );
                    System.out.println("\nTotal calories burned: " + workoutLog.getTotalCaloriesBurned());
                    break;
                case 8:
                    // Prompt the user to input the start and end dates for filtering workouts
                    System.out.println("Enter the date range for filtering workouts.");

                    scanner.nextLine();

                    LocalDate goalStartDate = calorieGoal.startDate();
                    LocalDate goalEndDate = calorieGoal.endDate();

                    LocalDate startDate = null;
                    LocalDate endDate = null;
                    boolean validDates = false;

                    while (!validDates) {
                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        String startDateInput = scanner.nextLine();
                        System.out.print("Enter end date (YYYY-MM-DD): ");
                        String endDateInput = scanner.nextLine();

                        try {
                            startDate = LocalDate.parse(startDateInput);
                            endDate = LocalDate.parse(endDateInput);

                            if (!startDate.isBefore(goalStartDate) && !endDate.isAfter(goalEndDate)) {
                                validDates = true; // Dates are within the valid range
                            } else {
                                System.out.println("Incorrect dates entered. Please provide dates within the goal range: " +
                                        goalStartDate + " to " + goalEndDate + ".");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please enter dates in the format YYYY-MM-DD.");
                        }
                    }
                    System.out.println("\nFiltered Workouts (from " + startDate + " to " + endDate + "):");

// Fetch filtered workouts
                    var filteredWorkouts = workoutLog.getWorkoutsByDateRange(startDate, endDate);

                    filteredWorkouts.forEach(workout ->
                            System.out.println(workout.type() + " - " + workout.durationMinutes() + " minutes, "
                                    + workout.caloriesBurned() + " calories, Date: " + workout.date())
                    );

//// Calculate and display total calories burned within the filtered range
                    double filteredCalories = filteredWorkouts.stream()
                            .mapToDouble(Workout::caloriesBurned)
                            .sum();
//
                    System.out.println("\nTotal calories burned across the  filtered workouts: " + filteredCalories);

                    break;
                case 9:
                    // Create instances of UpdatableWorkout
                    UpdatableWorkout workout1 = new UpdatableWorkout(WorkoutType.RUNNING, 30, 400, LocalDate.now());
                     System.out.println("Workout Created: " + workout1.toString());
                    TrackableSealed trackable = new UpdatableWorkout(WorkoutType.RUNNING, 30, 300, LocalDate.now());
                    trackable.updateWorkout("Cycling", 45, 500);
                    System.out.println("Updated workout via TrackableSealed reference.");

                    // Create a WorkoutUpdater
                    WorkoutUpdater updater = new WorkoutUpdater();

                    // Add workouts to updater
                    updater.addWorkout(workout1);

                    // Show original details
                    System.out.println("\nBefore Update:");
                    updater.showWorkoutDetails(0);

                    // Update a workout
                    updater.updateWorkout(0, "SWIMMING", 30, 400);

                    // Show updated details
                    System.out.println("\nAfter Update:");
                    updater.showWorkoutDetails(0);

                    break;
                case 10:
                    // Log Default Running Workout
                    workoutLog.logDefaultRunningWorkout();  // **LOG DEFAULT RUNNING WORKOUT HERE**
                    System.out.println("\nDefault Running Workout logged!");
                    // Display the details of the logged workout
                    Workout lastWorkout = workoutLog.getAllWorkouts().get(workoutLog.getAllWorkouts().size() - 1); // Get the last logged workout
                    System.out.println("\nWorkout Details:");
                    System.out.println("Type: " + lastWorkout.type());  // Display workout type (Running)
                    System.out.println("Duration: " + lastWorkout.durationMinutes() + " minutes");  // Display workout duration
                    System.out.println("Calories Burned: " + lastWorkout.caloriesBurned() + " calories");  // Display calories burned
                    System.out.println("Date: " + lastWorkout.date());  // Display the date of the workout
                    break;

                case 11:

                    TrackableSealed workout3 = new BaseWorkout(WorkoutType.RUNNING, 30, 300, LocalDate.now());
                    System.out.println(workout3);

                    SealedWorkoutUpdater updater1 = new SealedWorkoutUpdater();
                    updater1.addWorkout(new UpdatableWorkout(WorkoutType.RUNNING, 30, 300, LocalDate.now()));
                    updater1.addWorkout(new UpdatableWorkout(WorkoutType.SWIMMING, 45, 500, LocalDate.now()));
                    updater1.showWorkoutDetails(0); // Show details of the first workout
                    updater1.updateWorkout(0, "Cycling", 40, 400); // Update the first workout
                    updater1.showWorkoutDetails(0); // Show updated details
                    break;

                case 12:
                    // Exit the menu
                    System.out.println("Thank you!");
                    exitMenu = true;
                    break;

                default:
                    // Invalid input
                    System.out.println("Invalid choice, please try again.");

            }
        }
    }
}
