package edu.up.swolemate;

import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Context object for units
     */
    protected Context context;

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
        SharedPreferences prefs = context.getSharedPreferences("user_settings", 0);

        for(Exercise e : workout.getExercises()) {
            s += e.getName();
            for(ExerciseSubset set : e.getSets()) {

                double weight = set.getWeight();
                String weightString = "";

                if(prefs.getString("units", "").equals("metric")) {
                    weight = weight * 2.20462;
                    weightString = new DecimalFormat("##.##").format(weight) + "kg";
                } else {
                    weightString = new DecimalFormat("##.##").format(weight) + "lb";
                }
                s += "\n" + set.getNumReps() + " @ " + weightString;
            }
            s += "\n\n";
        }

        return s;
    }

    private String cardioToString(CardioWorkout workout) {
        String s = "";

        double duration = workout.getDuration();
        int intDuration = (int)(Math.floor(duration));
        int hours = intDuration / 3600;
        int remainder = intDuration - (hours * 3600);
        int minutes = remainder / 60;
        double seconds = duration % 60.0;

        String durationString = String.format("%02d", hours)
                                + ":"
                                + String.format("%02d", minutes)
                                + ":"
                                + new DecimalFormat("00.##").format(seconds);


        s += durationString + "\n";
        s += String.valueOf(workout.getDistance());

        return s;
    }

}