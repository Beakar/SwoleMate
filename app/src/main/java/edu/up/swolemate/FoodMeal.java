package edu.up.swolemate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    protected int dateCompleted;

    /**
     * Collection of food items in the meal object
     */
    protected List<FoodItem> foodItems;

    /**
     * Initializes an empty food meal
     */
    public FoodMeal() {
        foodItems = new ArrayList<FoodItem>();
    }

    /**
     * Initializes a food meal with the specified name
     * @param name
     */
    public FoodMeal(String name) {
        this.name = name;
        foodItems = new ArrayList<FoodItem>();
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
     * Initializes the test values for a meal object
     * @return
     */
    public FoodMeal initTestValues() {
        FoodItem item = new FoodItem();
        item.setServingSize(8);
        item.setCalories(100);
        item.setFat(5);
        item.setCarbs(20);
        item.setProtein(7);
        item.setFoodType("Potato");
        addFood(item);

        FoodItem item2 = new FoodItem();
        item2.setServingSize(8);
        item2.setCalories(100);
        item2.setFat(5);
        item2.setCarbs(20);
        item2.setProtein(7);
        item2.setFoodType("Tomato");
        addFood(item2);

        FoodItem item3 = new FoodItem();
        item3.setServingSize(8);
        item3.setCalories(100);
        item3.setFat(5);
        item3.setCarbs(20);
        item3.setProtein(7);
        item3.setFoodType("Fritata");
        addFood(item3);


        FoodItem item4 = new FoodItem();
        item4.setServingSize(8);
        item4.setCalories(100);
        item4.setFat(5);
        item4.setCarbs(20);
        item4.setProtein(7);
        item4.setFoodType("Churro");
        addFood(item4);


        return this;
    }


    /**
     * removes a food item from the list
     * @param foodItem
     */
    public void removeFood(FoodItem foodItem) {
        this.foodItems.remove(foodItem);
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getDateCompleted() {
        return dateCompleted;
    }


    public void setDateCompleted(int dateCompleted) {
        this.dateCompleted = dateCompleted;
    }


    public List<FoodItem> getFoodItems() {
        return foodItems;
    }


    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }


    @Override
    public String toString() {
        String stringRep = "";

        return stringRep;
    }
}
