package edu.up.swolemate;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/21/2015.
 */
public class User {
    /**
     * Name of the user
     */
    protected String name;

    /**
     * 
     */
    protected ArrayList<Double> heightHistory;

    protected ArrayList<Double> weightHistory;

    protected ArrayList<FoodHistoryItem> foodHistory;

    protected ArrayList<WorkoutHistoryItem> workoutHistory;
}
