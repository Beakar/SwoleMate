package edu.up.swolemate;

import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public abstract class BaseWorkout implements Comparable<BaseWorkout> {
    /**
     * Id of workout for database usage
     */
    protected int id;

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
                s += "\n" + weightString + " x " + set.getNumReps();
            }
            s += "\n\n";
        }

        return s;
    }

    private String cardioToString(CardioWorkout workout) {
        String s = "";
        SharedPreferences prefs = context.getSharedPreferences("user_settings", 0);

        double duration = workout.getDuration();
        double distance = workout.getDistance();

        //getting individual pieces to build string with time information
        int intDuration = (int)(Math.floor(duration));
        int hours = intDuration / 3600;
        int remainder = intDuration - (hours * 3600);
        int minutes = remainder / 60;
        double seconds = duration % 60.0;

        DecimalFormat secsAndMills = new DecimalFormat("00.##");

        String durationString = String.format("%02d", hours)
                                + ":"
                                + String.format("%02d", minutes)
                                + ":"
                                + secsAndMills.format(seconds);


        s += "Duration: " + durationString;

        String paceString = "";
        String unitString = "";

        //distance is based on units
        if(prefs.getString("units", "").equals("metric")) {
            unitString = "km";
            distance *= 1.609;
        } else {
            unitString = "mi";
        }

        //doubles are never zero, need to check this. Sometimes the user doesn't initialize a double
        if(!isZero(distance, 0.01)) {
            //distance textView
            s += "\nDistance: " + new DecimalFormat("##.##").format(distance) + unitString;

            //calculate pace
            double splitDuration = duration / distance;
            hours = (int)(splitDuration / 3600);
            remainder = (int)(splitDuration - (hours * 3600));
            minutes = remainder / 60;
            seconds = splitDuration % 60.0;
            durationString = String.format("%02d", minutes)
                            + ":"
                            + secsAndMills.format(seconds);

            //pace textView
            paceString = "\nAvg Pace: " + durationString + "/" + unitString;
        }

        s += paceString;


        return s;
    }

    /**
     * Checks to see if a double value is zero
     * @param val
     * @param threshold
     * @return
     */
    private boolean isZero(double val, double threshold) {
        return val >= -threshold && val <= threshold;
    }

    /**
     * For sorting purposes
     * @param workout
     * @return
     */
    @Override
    public int compareTo(BaseWorkout workout) {
        Integer date1 = this.dateCompleted;
        Integer date2 = workout.getDateCompleted();
        return date1.compareTo(date2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}