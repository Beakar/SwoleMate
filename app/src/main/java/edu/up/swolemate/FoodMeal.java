package edu.up.swolemate;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/21/2015.
 */
public class FoodMeal {
    protected int id;

    protected String name;

    protected ArrayList<FoodItem> foodItems;

    public FoodMeal() {

    }

    public FoodMeal(String name) {
        this.name = name;
    }

    public FoodMeal(String name, ArrayList<FoodItem> foodItems) {
        this.name = name;
        this.foodItems = foodItems;
    }

    public void addFood(FoodItem foodItem) {
        this.foodItems.add(foodItem);
    }

    public void foodItem(String foodName) {
        for(FoodItem item : foodItems) {
            if(item.getFoodType().equals(foodName)) {
                foodItems.remove(item);
                break;
            }
        }
    }
    public void removeFood(FoodItem foodItem) {

    }
}
