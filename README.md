# Fitness Tracker Application

This is a Java-based fitness tracker application that allows users to set fitness goals, track progress, and view summaries of their progress. It demonstrates various Java features, including object-oriented principles, core Java APIs, defensive copying, and records.

## Features

### 1. **User Profile and Goals**

- **User Profile:** Allows users to input their basic information like name, age, weight, and height.
- **Fitness Goals:** Users can set fitness goals (e.g., daily calorie target, weekly workout duration) and monitor progress towards these goals.

#### **Technologies Used:**
- Java 23 (LTS)
- Core Java APIs (`String`, `StringBuilder`, `List`, `ArrayList`, `Date API`)

### 2. **Set Fitness Goals**

As a user, I want to set fitness goals (e.g., daily calorie target, weekly workout duration) and monitor my progress towards these goals.

- **Records**: Used for defining `FitnessGoal` as an immutable record type.
- **Defensive Copying**: Ensures that the `FitnessGoal` object is safely copied when accessed, preventing external modifications.
- **Core Java APIs (Date/Time)**: Used for handling dates and times (goal start date, end date) and calculating goal progress.

### 3. **View Goal Summary and Progress**

As a user, I want to view a summary of my fitness goals and their status (e.g., "50% of your weekly goal achieved").

- Displays the percentage of the goal achieved so far.
- Provides feedback on whether the user has met their goal or not.

#### **Technologies Used:**
- **Java Records**: Used for defining `FitnessGoal` and `FitnessProgress` as immutable objects.
- **Defensive Copying**: Ensures that the goal object is protected from external modification.
- **Core Java APIs (Date/Time)**: Used for calculating and tracking goal progress over time.

## Setup Instructions

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/tussoftwaredesign/ca-A00290461.git
    ```

2. **Open in IntelliJ IDEA**:
    - Open the project in IntelliJ IDEA.
    - Ensure you're using JDK 23.0.1 (or a compatible version).

3. **Run the Application**:
    - Right-click on `Main.java` and select **Run**.

## Directory Structure


