package edu.up.swolemate;
import android.content.SharedPreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 1/21/2015.
 * TODO: Add isTemplate field to class to test whether the workout object being created is a template or not
 */
public class StrengthWorkout extends BaseWorkout {
    protected List<Exercise> exercises;

    public StrengthWorkout() {
        super();
        exercises = new ArrayList<Exercise>();
        src = R.drawable.ic_strength_workout;
    }

    public StrengthWorkout(String displayName) {
        this.displayName = displayName;
        exercises = new ArrayList<Exercise>();
        src = R.drawable.ic_strength_workout;
    }

    public StrengthWorkout(String displayName, List<Exercise> exercises) {
        this(displayName);
        this.exercises = exercises;
        src = R.drawable.ic_strength_workout;
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

    //Creates and returns a strength workout object for testing purposes.
    @Override
    public StrengthWorkout initTestValues() {

        this.displayName = "Chest workout";
        this.dateCompleted = 1425867692;
        Exercise e = new Exercise();

        //add bench press
        e.setName("Bench press");
        ExerciseSubset set1 = new ExerciseSubset(100.0, 10);
        ExerciseSubset set2 = new ExerciseSubset(120.0, 12);
        ExerciseSubset set3 = new ExerciseSubset(140.0, 14);

        e.addSet(set1);
        e.addSet(set2);
        e.addSet(set3);
        this.addExercise(e);

//        e = new Exercise();
//        //add skullcrushers
//        e.setName("Skullcrushers");
//        e.addSet(new ExerciseSubset(25.0, 12));
//        e.addSet(new ExerciseSubset(30.0, 10));
//        e.addSet(new ExerciseSubset(35.0, 8));
//        this.addExercise(e);
//
//        //add tricep pulldown
//        e = new Exercise();
//        e.setName("Tricep pulldown");
//        e.addSet(new ExerciseSubset(35.0, 12));
//        e.addSet(new ExerciseSubset(40.0, 10));
//        e.addSet(new ExerciseSubset(45.0, 8));
//        this.addExercise(e);
//
//        //add shoulder press
//        e = new Exercise();
//        e.setName("Shoulder press");
//        e.addSet(new ExerciseSubset(25.0, 10));
//        e.addSet(new ExerciseSubset(25.0, 10));
//        e.addSet(new ExerciseSubset(25.0, 10));
//        this.addExercise(e);

        return this;
    }


    @Override
    public String toString() {
        String s = "";
        SharedPreferences prefs = context.getSharedPreferences("user_settings", 0);

        for(Exercise e : this.getExercises()) {
            s += e.getName();
            for(ExerciseSubset set : e.getSets()) {

                double weight = set.getWeight();
                String weightString = "";

                if(prefs.getString("units", "").equals("metric")) {
                    weight = weight / 2.20462;
                    weightString = new DecimalFormat("##.##").format(weight) + "kg";
                } else {
                    weightString = new DecimalFormat("##.##").format(weight) + "lb";
                }
                s += "\n" + weightString + " x " + set.getNumReps();
            }
            s += "\n\n";
        }

        return s;
    }

    public Exercise dummyExercise() {
        Exercise e = new Exercise();

        //add bench press
        e.setName("Bench press");
        ExerciseSubset set1 = new ExerciseSubset(100.0, 10);
        ExerciseSubset set2 = new ExerciseSubset(120.0, 12);
        ExerciseSubset set3 = new ExerciseSubset(140.0, 14);

        e.addSet(set1);
        e.addSet(set2);
        e.addSet(set3);
        return e;
    }

}
