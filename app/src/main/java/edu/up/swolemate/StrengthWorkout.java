package edu.up.swolemate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 1/21/2015.
 */
public class StrengthWorkout extends BaseWorkout {
    /**
     * Id for this workout object (for database usage)
     */
    protected int id;

    protected List<Exercise> exercises;

    public StrengthWorkout() {
        super();
    }

    public StrengthWorkout(String displayName) {
        this.displayName = displayName;
    }

    public StrengthWorkout(String displayName, List<Exercise> exercises) {
        this(displayName);
        this.exercises = exercises;
    }

    /**
     * Adds an exercise to the workout
     * @param exercise
     */
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void addExercise(String exerciseName) {
        this.exercises.add(new Exercise(exerciseName));
    }

    /**
     * Removes an exercise from the workout
     * @param exercise
     */
    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }

    /**
     * Gets the list of exercises for the workout
     * @return
     */
    public List<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Set list of exercises for the workout
     * @param exercises
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    /**
     * Removes an exercise from the workout based on its name
     * @param exerciseName
     */
    public void removeExercise(String exerciseName) {
        for(Exercise e : exercises) {
            if(e.getName().equals(exerciseName)) {
                exercises.remove(e);
                break;
            }
        }
    }



}
