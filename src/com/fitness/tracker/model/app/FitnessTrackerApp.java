/*package com.fitness.tracker.model.app;


import com.fitness.tracker.model.goals.FitnessGoal;
import com.fitness.tracker.model.goals.FitnessProgress;
import com.fitness.tracker.model.workout.Workout;
import com.fitness.tracker.model.workout.WorkoutType;


import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class FitnessTrackerApp {

    public static void main(String[] args) {

        // Create workout objects
        Workout workout1 = new Workout(WorkoutType.RUNNING, 45, 350, LocalDate.of(2025, 3, 29));

        Workout workout2 = new Workout(WorkoutType.CYCLING, 45, 350, LocalDate.of(2025, 3, 29));


        // Create fitness goal object
        FitnessGoal goal = new FitnessGoal("Cardio", 150.00, LocalDate.of(2025, 3, 29), LocalDate.of(2025, 4, 5));

        // Create fitness progress tracker and add workouts
        FitnessProgress progress = new FitnessProgress(goal);
        progress.addProgress(workout1.getDate(), workout1.getCaloriesBurned());
        progress.addProgress(workout2.getDate(), workout2.getCaloriesBurned());

        // Predicate: Check if a workout burns more than a specific number of calories
        Predicate<Workout> isHighIntensity = workout -> workout.getCaloriesBurned() > 300;
        System.out.println("Is workout 1 high intensity? " + isHighIntensity.test(workout1));

        // Supplier: Supply a random workout
        Supplier<Workout> randomWorkout = () -> new Workout(WorkoutType.YOGA, 60, 150, LocalDate.now());

        Workout random = randomWorkout.get();
        System.out.println("Random Workout: Type = " + random.getType() + ", Duration = " + random.getDuration() + " minutes");

        // Consumer: Print workout details
        Consumer<Workout> printWorkoutDetails = workout -> {
            System.out.println("Workout Type: " + workout.getType());
            System.out.println("Workout Duration: " + workout.getDuration() + " minutes");
            System.out.println("Calories Burned: " + workout.getCaloriesBurned());
            System.out.println("Date: " + workout.getDate());
        };
        printWorkoutDetails.accept(workout1);

        // Display the fitness tracker details
        printFitnessTracker(workout1, workout2, goal, progress);
    }

    private static void printFitnessTracker(Workout workout1, Workout workout2, FitnessGoal goal, FitnessProgress progress) {
        System.out.println("==================================================");
        System.out.println("                 FITNESS WORKOUT TRACKER");
        System.out.println("--------------------------------------------------");

        System.out.println("WORKOUT 1:");
        System.out.println("  Type: " + workout1.getType());
        System.out.println("  Duration: " + workout1.getDuration() + " minutes");
        System.out.println("  Calories Burned: " + workout1.getCaloriesBurned());
        System.out.println("  Date: " + workout1.getDate());

        System.out.println("--------------------------------------------------");

        System.out.println("WORKOUT 2:");
        System.out.println("  Type: " + workout2.getType());
        System.out.println("  Duration: " + workout2.getDuration() + " minutes");
        System.out.println("  Calories Burned: " + workout2.getCaloriesBurned());
        System.out.println("  Date: " + workout2.getDate());

        System.out.println("--------------------------------------------------");

        System.out.println("               FITNESS GOAL SUMMARY");
        System.out.println("--------------------------------------------------");
        System.out.println("Goal Type: " + goal.goalType());
        System.out.println("Target Calories: " + goal.targetValue());
        System.out.println("Goal Start: " + goal.startDate());
        System.out.println("Goal End: " + goal.endDate());

        System.out.println("--------------------------------------------------");

        System.out.println("              PROGRESS TOWARDS GOAL");
        System.out.println("--------------------------------------------------");
        System.out.println("Total Calories Burned: " + progress.getTotalProgress() + " (from 2 workouts)");
        System.out.println("Progress: " + progress.getGoalAchievementPercentage() + "%");

        System.out.println("--------------------------------------------------");

        System.out.println("       CALORIE ESTIMATION FOR 30 MINUTES");
        System.out.println("--------------------------------------------------");
        System.out.println("Estimated Calories Burned in 30 minutes: " + progress.estimateCalories(30)+ " calories");

        System.out.println("==================================================");
    }
}
*/
package com.fitness.tracker.model.app;

import com.fitness.tracker.model.goals.FitnessGoal;
import com.fitness.tracker.model.goals.FitnessProgress;
import com.fitness.tracker.model.workout.Workout;
import com.fitness.tracker.model.workout.WorkoutType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FitnessTrackerApp {
    public static void main(String[] args) {
        // Workouts List
        List<Workout> workouts = List.of(
                new Workout(WorkoutType.RUNNING, 45, 350, LocalDate.of(2025, 3, 29)),
                new Workout(WorkoutType.CYCLING, 60, 400, LocalDate.of(2025, 3, 30)),
                new Workout(WorkoutType.YOGA, 30, 150, LocalDate.of(2025, 3, 31))
        );

        // Fitness Goal
        FitnessGoal goal = new FitnessGoal("Cardio", 150.00, LocalDate.of(2025, 3, 29), LocalDate.of(2025, 4, 5));
        FitnessProgress progress = new FitnessProgress(goal);
        workouts.forEach(w -> progress.addProgress(w.getDate(), w.getCaloriesBurned()));

        //  Lambdas
        Predicate<Workout> isHighIntensity = w -> w.getCaloriesBurned() > 300;
        Supplier<Workout> randomWorkout = () -> new Workout(WorkoutType.YOGA, 60, 150, LocalDate.now());
        Consumer<Workout> printWorkout = w -> System.out.println("Workout: " + w.getType() + " | " + w.getCaloriesBurned() + " cal");
        Function<Workout, String> workoutSummary = w -> w.getType() + " - " + w.getCaloriesBurned() + " cal";

        //  Streams - Terminal Operations
        Optional<Workout> minCalWorkout = workouts.stream().min((w1, w2) -> Double.compare(w1.getCaloriesBurned(), w2.getCaloriesBurned()));
        Optional<Workout> maxCalWorkout = workouts.stream().max((w1, w2) -> Double.compare(w1.getCaloriesBurned(), w2.getCaloriesBurned()));
        long workoutCount = workouts.stream().count();
        Optional<Workout> anyWorkout = workouts.stream().findAny();
        Optional<Workout> firstWorkout = workouts.stream().findFirst();
        boolean allHighIntensity = workouts.stream().allMatch(isHighIntensity);
        boolean anyVeryHighIntensity = workouts.stream().anyMatch(w -> w.getCaloriesBurned() > 400);
        boolean noneTooLow = workouts.stream().noneMatch(w -> w.getCaloriesBurned() < 100);

        // ✅Streams - Intermediate Operations
        List<Workout> filteredWorkouts = workouts.stream().filter(isHighIntensity).collect(Collectors.toList());
        List<WorkoutType> uniqueWorkoutTypes = workouts.stream().map(Workout::getType).distinct().collect(Collectors.toList());
        List<Workout> sortedWorkouts = workouts.stream().sorted((w1, w2) -> Double.compare(w2.getCaloriesBurned(), w1.getCaloriesBurned())).collect(Collectors.toList());
        List<String> workoutSummaries = workouts.stream().map(workoutSummary).collect(Collectors.toList());
        // Adding limit() to get the top 2 workouts based on calories burned
        List<Workout> topTwoWorkouts = workouts.stream()
                .sorted((w1, w2) -> Double.compare(w2.getCaloriesBurned(), w1.getCaloriesBurned())) // Sort in descending order
                .limit(2) // Take only the top 2 workouts
                .collect(Collectors.toList());

        System.out.println("Top 2 Workouts: " + topTwoWorkouts);

        // Collectors
        Map<WorkoutType, Double> workoutCaloriesMap = workouts.stream().collect(Collectors.toMap(Workout::getType, Workout::getCaloriesBurned, Double::sum));
        Map<WorkoutType, List<Workout>> groupedByType = workouts.stream().collect(Collectors.groupingBy(Workout::getType));
        Map<Boolean, List<Workout>> partitionedByCalorie = workouts.stream().collect(Collectors.partitioningBy(w -> w.getCaloriesBurned() > 300));

        // Output
        System.out.println("Minimum Calorie Workout: " + minCalWorkout);
        System.out.println("Maximum Calorie Workout: " + maxCalWorkout);
        System.out.println("Total Workouts: " + workoutCount);
        System.out.println("Any Workout: " + anyWorkout);
        System.out.println("First Workout: " + firstWorkout);
        System.out.println("All Workouts High Intensity? " + allHighIntensity);
        System.out.println("Any Workout Above 400 Calories? " + anyVeryHighIntensity);
        System.out.println("No Workouts Below 100 Calories? " + noneTooLow);
        System.out.println("Filtered Workouts (>300 cal): " + filteredWorkouts);
        filteredWorkouts.forEach(w -> System.out.println("Filtered Workout: " + w.getType() + ", " + w.getCaloriesBurned() + " cal"));

        System.out.println("Unique Workout Types: " + uniqueWorkoutTypes);
        System.out.println("Sorted Workouts: " + sortedWorkouts);
        System.out.println("Workout Summaries: " + workoutSummaries);
        System.out.println("Workout Calories Map: " + workoutCaloriesMap);
        System.out.println("Grouped By Type: " + groupedByType);
        System.out.println("Partitioned By Calorie (>300 cal): " + partitionedByCalorie);
    }
}


