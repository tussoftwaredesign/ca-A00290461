package com.fitness.tracker.model.goals;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
public class FitnessProgress {
    private final FitnessGoal goal;
    private final Map<LocalDate, Double> dailyProgress; // Maps date to progress value (e.g., calories burned)

    // Constructor
    public FitnessProgress(FitnessGoal goal) {
        this.goal = goal;
        this.dailyProgress = new HashMap<>();
    }

    // Defensive copy of the goal
    public FitnessGoal getGoal() {
        return new FitnessGoal(
                goal.goalType(),
                goal.targetValue(),
                goal.startDate(),
                goal.endDate()
        );
    }

    // Add progress for a specific date
    public void addProgress(LocalDate date, double value) {
        if (date.isBefore(goal.startDate()) || date.isAfter(goal.endDate())) {
            throw new IllegalArgumentException("Date must be within the goal period.");
        }
        dailyProgress.put(date, dailyProgress.getOrDefault(date, 0.0) + value);
    }

    // Get total progress so far
    public double getTotalProgress() {
        return dailyProgress.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Check if goal is met
    public boolean isGoalMet() {
        return getTotalProgress() >= goal.targetValue();
    }

    // Calculate percentage of goal achieved
    public double getGoalAchievementPercentage() {
        double totalProgress = getTotalProgress();
        return (totalProgress / goal.targetValue()) * 100;
    }

    // Get goal summary message
    public String getGoalSummary() {
        double percentage = getGoalAchievementPercentage();
        return String.format("You have achieved %.2f%% of your weekly goal", percentage);
    }
    // Show progress bar for the goal achievement
    public void showProgressBar() {
        double totalProgress = getTotalProgress();
        double goalCalories = goal.targetValue();
        int progress = (int) ((totalProgress / goalCalories) * 100);

        // Displaying the progress bar
        StringBuilder bar = new StringBuilder("[");
        int completed = progress / 2; // To scale up to 50 characters (each '#' represents 2%)
        for (int i = 0; i < 50; i++) {
            if (i < completed) {
                bar.append("#");
            } else {
                bar.append(" ");
            }
        }
        bar.append("] " + progress + "%");

        // Print the progress bar
        System.out.println(bar.toString());
    }

    @Override
    public String toString() {
        return String.format("Goal: %s, Total Progress: %.2f, Goal Met: %b",
                goal.goalType(), getTotalProgress(), isGoalMet());
    }
}
