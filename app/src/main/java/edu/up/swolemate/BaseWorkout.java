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

        return stringRep;
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
        return date2.compareTo(date1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}