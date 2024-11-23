package com.fitness.tracker.model;
import java.util.Objects;

public final class UserProfile {
    private final String name;
    private final int age;
    private final double weight; // in kilograms
    private final double height; // in meters

    // Constructor
    public UserProfile(String name, int age, double weight, double height) {
        if (age <= 0 || weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("Age, weight, and height must be positive.");
        }
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    // ToString Method
    @Override
    public String toString() {
        return String.format("User Profile: %s, Age: %d, Weight: %.2f kg, Height: %.2f m",
                name, age, weight, height);
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return age == that.age &&
                Double.compare(that.weight, weight) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, weight, height);
    }
}
