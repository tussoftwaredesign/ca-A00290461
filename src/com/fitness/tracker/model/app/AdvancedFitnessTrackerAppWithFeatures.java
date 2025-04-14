package com.fitness.tracker.model.app;

import com.fitness.tracker.model.workout.Workout;
import com.fitness.tracker.model.workout.WorkoutType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class AdvancedFitnessTrackerAppWithFeatures {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        // Now attempt to load ResourceBundle
        try {
            ResourceBundle messages = ResourceBundle.getBundle("Messages", Locale.getDefault());
            System.out.println("Successfully loaded resource bundle!");

            // Example: Print localized workout type names
            System.out.println("Localized Running: " + messages.getString("RUNNING"));
        } catch (MissingResourceException e) {
            System.out.println("Failed to load Messages.properties: " + e.getMessage());
        }
        // Workouts List
        List<Workout> workouts = List.of(
                new Workout(WorkoutType.RUNNING, 45, 350, LocalDate.of(2025, 3, 29)),
                new Workout(WorkoutType.CYCLING, 60, 400, LocalDate.of(2025, 3, 30)),
                new Workout(WorkoutType.YOGA, 30, 150, LocalDate.of(2025, 3, 31))
        );

        // Collections/Generics - Sorting by Workout Type
        List<Workout> sortedByType = workouts.stream()
                .sorted(Comparator.comparing(Workout::getType)) // Sorting by workout type
                .collect(Collectors.toList());

        System.out.println("Sorted Workouts by Type: " + sortedByType);

        //  ExecutorService - Using ExecutorService to process a list of Callables (simulate async tasks)
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<String>> tasks = List.of(
                () -> "Running workout done",
                () -> "Cycling workout done",
                () -> "Yoga workout done"
        );

        List<Future<String>> results = executorService.invokeAll(tasks);

        for (Future<String> result : results) {
            System.out.println(result.get()); // Print the results of async tasks
        }

        executorService.shutdown(); // Shutdown the executor service

        //  NIO2 - Writing workout details to a file
        Path filePath = Paths.get("workouts.txt");
        StringBuilder workoutDetails = new StringBuilder("Workout Details:\n");

        workouts.forEach(workout -> workoutDetails.append("Type: ").append(workout.getType())
                .append(", Calories Burned: ").append(workout.getCaloriesBurned())
                .append(", Date: ").append(workout.getDate()).append("\n"));

        try {
            Files.write(filePath, workoutDetails.toString().getBytes());
            System.out.println("Workout details saved to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        //  Localization - Using Locale for different regions
        Locale defaultLocale = Locale.getDefault();
        System.out.println("Default Locale: " + defaultLocale.getDisplayName());

        //  Localizing Workout Type (simulating localization based on language)
       // ResourceBundle messages = ResourceBundle.getBundle("Messages", defaultLocale);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", Locale.getDefault());


        workouts.forEach(workout -> {
            String localizedType = messages.getString(workout.getType().name());
            System.out.println("Localized workout type for " + workout.getType() + ": " + localizedType);
        });

        //  Java 22 - Pattern Matching using `instanceof` with unnamed variable
        for (Workout workout : workouts) {
            if (workout instanceof Workout _) {
                System.out.println("Pattern matched workout: " + workout.getType());
            }
        }
        //  Java 22 - Switch with unnamed variable
        for (Workout workout : workouts) {
            switch (workout.getType()) {
                case RUNNING -> System.out.println("Keep running!");
                case CYCLING -> System.out.println("Push those pedals!");
                case YOGA -> System.out.println("Relax and stretch!");
                default -> {
                    // `_` is used when we ignore the actual matched object
                    WorkoutType _ = workout.getType();
                    System.out.println("Other workout type.");
                }
            }
        }


    }
}
