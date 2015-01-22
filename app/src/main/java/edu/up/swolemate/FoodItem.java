package edu.up.swolemate;

/**
 * Created by Nathan on 1/21/2015.
 */
public class FoodItem {
    protected int id;

    protected String foodType;

    /**
     * Serving size of food item in oz
     */
    protected double servingSize;

    /**
     * number of calories in food item
     */
    protected int calories;

    /**
     * amount of fat in food item, in grams
     */
    protected double fat;

    /**
     * amount of carbs in food item, in grams
     */
    protected double carbs;

    /**
     * amount of protein in food item protein, in grams
     */
    protected double protein;

    /**
     * Initializes an empty food item
     */
    public FoodItem() {

    }

    /**
     * Initialisesa food item with the specified name
     * @param name
     */
    public FoodItem(String name) {
        this.foodType = name;
    }

    public void setNutrient(NutrientType nutrientType, double amount) {

    }

    public String getFoodType() {
        return this.foodType;
    }
}
