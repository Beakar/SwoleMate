package edu.up.swolemate;

import java.util.Date;

/**
 * Created by Nathan on 1/21/2015.
 */
public class WorkoutHistoryItem {
    /**
     * Unique identifier, for database usage
     */
    protected int id;

    /**
     * date the workout was completed on
     */
    protected Date dateCompleted;

    /**
     * Workout object attached to the workout history item
     */
    protected BaseWorkout workout;

    /**
     * Sets the date the workout was completed on
     * @param dateCompleted
     */
    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * Gets the workout associated with this object
     * @return
     */
    public BaseWorkout getWorkout() {
        return workout;
    }

    /**
     * Sets the workout associated witht he workout
     * @param workout
     */
    public void setWorkout(BaseWorkout workout) {
        this.workout = workout;
    }

    /**
     * Initializes an empty WorkoutHistoryItem object
     */
    public WorkoutHistoryItem() {

    }

    /**
     * Initializes an empty WorkoutHistoryItem
     * @param workout
     */
    public WorkoutHistoryItem(BaseWorkout workout) {
        this.workout = workout;
    }

    /**
     * Gets the date the workout was completed on
     * @return
     */
    public Date getDateCompleted() {
        return this.dateCompleted;
    }
}
