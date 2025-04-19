# Fitness Tracker

The Fitness Tracker is a Java-based application designed to help users monitor and achieve their fitness goals effectively. It supports personalized profiles, goal tracking, and detailed workout logging.

## Functional User Stories

1. **Filter High-Intensity Workouts**  
   I limit my workouts to only show those that are high-intensity (burning over 300 calories, for example) so that I can concentrate on the most strenuous exercises I have ever done.

2. **View Unique Workout Types**  
   I would want to see a list of different workout categories (such as yoga, cycling, and running) so that I can know exactly what kinds of workouts I've been performing.

3. **Sort Workouts by Calories Burned**  
   I would like to be able to easily see which workouts had the largest calorie burn by sorting my workouts by calorie burn in descending order.

4. **Display Top 2 Workouts**  
   I would like to be able to view my most intensive exercises by seeing the top two workouts based on calories burned.

5. **Count Total Workouts**  
   I would like to see how many workouts have been recorded overall so that I can monitor my level of activity over time.

6. **Sorting Workouts by Type**  
   I want my workouts to be organised by kind (yoga, cycling, running, etc.) so I can quickly assess and evaluate my progress.

7. **Processing Workouts Concurrently**  
   I would like my exercise records to be processed concurrently so that I may receive immediate feedback without any delays.

8. **Storing Workout Data Using NIO2**  
   I would like my workout information to be stored in a file so I may review it at a later time and monitor my progress.

9. **Localizing Workout Types**  
   I would want to see the many types of workouts displayed in my native language so that I can understand the details in my chosen language.

## Technical User Stories

1. **Use Streams for Data Processing**
   
   I want to rapidly process and analyse exercise data using Java Streams. So that I can carry out tasks like sorting, counting, and filtering without changing the original data.

2. **Implement Collectors for Grouping and Partitioning**
   
   I would like to separate and group workout data according to various parameters using Java Collectors. So that I can classify exercises according to their type or calorie ranges.

3. **Implement Limit Functionality**

   I want to limit the number of results in Streams by implementing the limit() function. So that I can show only the best workouts according to a particular criterion (eg., 2 top workouts with the highest calories burned).

4. **Implement Predicate for High-Intensity Workouts**
   
   I would like to implement a predicate that would filter very strenuous workouts  according to a criterion (e.g., calories burned > 300). So that only intense exercises can be shown on the app when necessary.

5. **Implement Sorting with Java Streams & Generics**
   
   I want to take advantage of Java's functional programming features, So that I can effectively sort workouts in a type-safe manner.

6. **Implement Multi-Threading with ExecutorService**

   I want to perform several workout completion tasks asynchronously. So that the app keep the program responsive.

7. **Store Workouts Using Java NIO2**
   
   I want to efficiently write exercise logs to a file. So that the application makes use of modern file input/output techniques.

8. **Implement Localization with ResourceBundle**
   
   I want to load localized strings dynamically, So that the app can support many languages.
