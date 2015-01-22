package edu.up.swolemate;

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


    public String getDisplayName() {
        return displayName;
    }
}