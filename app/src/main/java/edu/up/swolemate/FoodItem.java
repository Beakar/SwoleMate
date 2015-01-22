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

    /**
     * Sets the specified nutrient to the specified value
     * @param nutrientType
     * @param amount
     */
    public void setNutrient(NutrientType nutrientType, double amount) {
        switch(nutrientType) {
            case Calories:
                this.calories = (int)amount;
                break;
            case Fat:
                this.fat = amount;
                break;
            case Carbs:
                this.carbs = amount;
                break;
            case Protein:
                this.protein = amount;
                break;
            default:
                break;
        }
    }

    /**
     * Gets the name of the food associated with the food item
     * @return
     */
    public String getFoodType() {
        return this.foodType;
    }

    /**
     * sets the name of the food
     * @param foodType
     */
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    /**
     * Gets the serving size of the food
     * @return
     */
    public double getServingSize() {
        return servingSize;
    }

    /**
     * Set the serving size of the food
     * @param servingSize
     */
    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    /**
     * get the calories of the food item
     * @return
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Set the calories of the food item
     * @param calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Get the fat content of the food item
     * @return
     */
    public double getFat() {
        return fat;
    }

    /**
     * Set the fat content of the food item
     * @param fat
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Get the carb content of the food item
     * @return
     */
    public double getCarbs() {
        return carbs;
    }

    /**
     * Set the carb content of the food item
     * @param carbs
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    /**
     * Get the protein content of food item
     * @return
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Set protein content of food item
     * @param protein
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }
}
