package edu.up.swolemate;

import java.util.Date;

public abstract class BaseWorkout {
    /**
     * Id of workout for database usage
     */
    protected String id;

    /**
     * Display name for the workout specified by the user
     */
    protected String displayName;

    public int getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(int dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * Date the workout was completed on
     */
    protected int dateCompleted;

    /**
     * Source for the workout icon
     */
    protected int src;

    /**
     * defines an empty workout object
     */
    public BaseWorkout() {

    }

    /**
     * defines a workout with a specific display name
     * @param displayName
     */
    public BaseWorkout(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets the display name for the workout object
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the workout
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    public int getSrc() {
        return this.src;
    }

    public abstract BaseWorkout initTestValues();

    @Override
    public String toString() {
        String stringRep = "";

        if(this instanceof StrengthWorkout) {
            stringRep += strengthToString((StrengthWorkout)this);
        } else if(this instanceof CardioWorkout) {
            stringRep += cardioToString((CardioWorkout)this);
        } else if(this instanceof CustomWorkout) {
            CustomWorkout workout = (CustomWorkout)this;
            stringRep += workout.getWorkoutData();
        }

        return stringRep;
    }

    private String strengthToString(StrengthWorkout workout) {
        String s = "";

        for(Exercise e : workout.getExercises()) {
            s += e.getName();
            for(ExerciseSubset set : e.getSets()) {
                s += "\n" + set.getNumReps() + " @ " + set.getWeight();
            }
            s += "\n\n";
        }

        return s;
    }

    private String cardioToString(CardioWorkout workout) {
        String s = "";

        s += String.valueOf(workout.getDuration()) + "\n";
        s += String.valueOf(workout.getDistance());

        return s;
    }
}