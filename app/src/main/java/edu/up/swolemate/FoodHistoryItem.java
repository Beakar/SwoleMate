package edu.up.swolemate;

import java.util.Date;

/**
 * Created by Nathan on 1/21/2015.
 */
public class FoodHistoryItem {
    protected int id;

    /**
     * food item being recorded by this history item
     */
    protected FoodItem foodItem;

    /**
     * Date the food item was recorded on
     */
    protected Date dateRecorded;

    /**
     * Amount of servings eaten during this time period
     */
    protected double servingsEaten;

    /**
     * initializes an empty food history item
     */
    public FoodHistoryItem() {}

    /**
     * Initializes a FoodHistoryItem with the specified food item
     * @param foodItem
     */
    public FoodHistoryItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    /**
     * Initializes a food history item with the specified food item and servings eaten
     * @param foodItem
     * @param servingsEaten
     */
    public FoodHistoryItem(FoodItem foodItem, double servingsEaten) {
        this.foodItem = foodItem;
        this.servingsEaten = servingsEaten;
    }

    /**
     * Gets the food item associated with this history item
     * @return
     */
    public FoodItem getFoodItem() {
        return foodItem;
    }

    /**
     * Sets the food item associated with this history item
     * @param foodItem
     */
    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    /**
     * Gets the date the food was recorded on on
     * @return
     */
    public Date getDateRecorded() {
        return dateRecorded;
    }

    /**
     * Sets the date the food was recorded on
     * @param dateRecorded
     */
    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    /**
     * Gets the number of servings eaten
     * @return
     */
    public double getServingsEaten() {
        return servingsEaten;
    }

    /**
     * Sets the number of servings eaten
     * @param servingsEaten
     */
    public void setServingsEaten(double servingsEaten) {
        this.servingsEaten = servingsEaten;
    }


}
