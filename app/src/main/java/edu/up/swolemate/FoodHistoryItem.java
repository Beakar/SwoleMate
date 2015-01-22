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


    public FoodHistoryItem() {

    }

    /**
     * Initializes a FoodHistoryItem
     * @param foodItem
     */
    public FoodHistoryItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public FoodHistoryItem(FoodItem foodItem, double servingsEaten) {
        this.foodItem = foodItem;
        this.servingsEaten = servingsEaten;
    }
}
