package edu.up.swolemate;
import java.util.ArrayList;

/**
 * Created by Nathan on 1/21/2015.
 */
public class StrengthWorkout extends BaseWorkout {
    protected ArrayList<Exercise> exercises;

    public StrengthWorkout() {
        super();
    }

    public StrengthWorkout(String displayName) {
        this.displayName = displayName;
    }

    public StrengthWorkout(String displayName, ArrayList<Exercise> exercises) {
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
