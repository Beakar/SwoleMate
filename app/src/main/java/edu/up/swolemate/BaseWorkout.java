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

    /**
     * Date the workout was completed on
     */
    protected Date dateCompleted;

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

    public abstract void initTestValues();
}