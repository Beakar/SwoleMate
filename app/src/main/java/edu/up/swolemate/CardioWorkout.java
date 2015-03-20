package edu.up.swolemate;

import android.content.SharedPreferences;

import java.text.DecimalFormat;

/**
 * Created by Nathan on 1/21/2015.
 */
public class CardioWorkout extends BaseWorkout {
    /**
     * The duration of the workout in seconds
     */
    protected double duration;

    /**
     * The distance of the workout in miles
     */
    protected double distance;


    /**
     * Initializes an empty CardioWorkout object
     */
    public CardioWorkout() {
        super();
        this.src = R.drawable.ic_cardio_workout;
    }

    /**
     * Initializes a CardioWorkout with the specified name
     * @param name
     */
    public CardioWorkout(String name) {
        super(name);
        this.src = R.drawable.ic_cardio_workout;
    }

    /**
     * gets the duration of the cardio workout
     * @return
     */
    public double getDuration() {
        return duration;
    }

    /**
     * sets the duration of the workout
     * @param duration
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Gets the distance of the workout
     * @return
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the distance of the workout
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Initializes a CardioWorkout with the specified name and duration

     * @param name
     * @param duration
     */
    public CardioWorkout(String name, double duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public CardioWorkout initTestValues() {
        this.displayName = "Spinning workout";
        this.duration = 2713.53;
        this.distance = 4.2;
        this.dateCompleted = 1425867692;

        return this;
    }


    @Override
    public String toString() {
        String s = "";
        SharedPreferences prefs = context.getSharedPreferences("user_settings", 0);

        double duration = this.getDuration();
        double distance = this.getDistance();

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
}
