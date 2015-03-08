package edu.up.swolemate;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nathan on 1/21/2015.
 */
public class FoodMeal {
    /**
     * Id, for database usage
     */
    protected int id;

    /**
     * name of the food meal
     */
    protected String name;

    /**
     * Date meal was eaten on
     */
    protected Date dateCompleted;

    /**
     * Collection of food items in the meal object
     */
    protected ArrayList<FoodItem> foodItems;

    /**
     * Initializes an empty food meal
     */
    public FoodMeal() {

    }

    /**
     * Initializes a food meal with the specified name
     * @param name
     */
    public FoodMeal(String name) {
        this.name = name;
    }

    /**
     * Initializes a food meal with the specified name and collection of food items
     * @param name
     * @param foodItems
     */
    public FoodMeal(String name, ArrayList<FoodItem> foodItems) {
        this.name = name;
        this.foodItems = foodItems;
    }

    /**
     * Adds a food to the list of food items
     * @param foodItem
     */
    public void addFood(FoodItem foodItem) {
        this.foodItems.add(foodItem);
    }

    /**
     * Removes a food item with the specified name from the list
     * @param foodName
     */
    public void removeFood(String foodName) {
        for(FoodItem item : foodItems) {
            if(item.getFoodType().equals(foodName)) {
                foodItems.remove(item);
                break;
            }
        }
    }

    /**
     * removes a food item from the list
     * @param foodItem
     */
    public void removeFood(FoodItem foodItem) {
        this.foodItems.remove(foodItem);
    }
}
