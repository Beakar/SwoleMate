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
     * Contains the height history of the user
     */
    protected ArrayList<Double> heightHistory;

    /**
     * Contains the weight history of the user
     */
    protected ArrayList<Double> weightHistory;

    /**
     * Contains the eating history of the user
     */
    protected ArrayList<FoodHistoryItem> foodHistory;

    /**
     * Contains the workout history of the user
     */
    protected ArrayList<WorkoutHistoryItem> workoutHistory;


    /**
     * Returns the name of the User
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the height history of the user
     * @return
     */
    public ArrayList<Double> getHeightHistory() {
        return heightHistory;
    }

    /**
     * Returns the weight history of the uesr
     * @return
     */
    public ArrayList<Double> getWeightHistory() {
        return weightHistory;
    }

    /**
     * Returns the food history of the user
     * @return
     */
    public ArrayList<FoodHistoryItem> getFoodHistory() {
        return foodHistory;
    }

    /**
     * returns the workout history of the user
     * @return
     */
    public ArrayList<WorkoutHistoryItem> getWorkoutHistory() {
        return workoutHistory;
    }


    /**
     * Sets the name of the user
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the height history of the user
     * @param heightHistory
     */
    public void setHeightHistory(ArrayList<Double> heightHistory) {
        this.heightHistory = heightHistory;
    }

    /**
     * Sets the weight history of the user
     * @param weightHistory
     */
    public void setWeightHistory(ArrayList<Double> weightHistory) {
        this.weightHistory = weightHistory;
    }

    /**
     * Sets the food history of the user
     * @param foodHistory
     */
    public void setFoodHistory(ArrayList<FoodHistoryItem> foodHistory) {
        this.foodHistory = foodHistory;
    }

    /**
     * Sets the workout history of the user
     * @param workoutHistory
     */
    public void setWorkoutHistory(ArrayList<WorkoutHistoryItem> workoutHistory) {
        this.workoutHistory = workoutHistory;
    }


}
