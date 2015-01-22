package edu.up.swolemate;

import java.util.Date;

/**
 * Created by Nathan on 1/21/2015.
 */
public class WorkoutHistoryItem {
    protected int id;

    protected Date dateCompleted;

    protected BaseWorkout workout;

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

    public Date getDateCompleted() {
        return this.dateCompleted;
    }
}
