package edu.up.swolemate;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Daniel on 2/5/2015.
 */
public class FoodPresets {
    /**
     * The ArrayList containing the preset food items.
     */
    protected ArrayList<FoodItem> foodPresets;
    protected Hashtable<String, FoodItem> presetsTable;

    /**
     * Initializes an empty FoodPresets object
     */
    public FoodPresets(){
        foodPresets = new ArrayList<FoodItem>();
        /**
         * Creates all of the FoodItem presets, and sets their values for the FoodItem(s) (by calling setFoodPresetValues()),
         *     and then stores the FoodItem in the foodPresets ArrayList.
         * The parameters passed in order are name (FoodItem), name (display), calories, fat,
         *     carbs, protein and serving size (in oz.).
         */
        FoodItem chickenBreast = new FoodItem();
        chickenBreast = setFoodPresetValues(chickenBreast, "Chicken Breast", 31, 0.76, 0.0, 5.76, 1.0);
        foodPresets.add(chickenBreast);

        FoodItem steakTopSirloin = new FoodItem();
        steakTopSirloin = setFoodPresetValues(steakTopSirloin, "Steak (Top Sirloin, trimmed to 0\"\" fat)",
                60, 2.74, 0.0, 8.34, 1.0);
        foodPresets.add(steakTopSirloin);

        FoodItem steakRibeye = new FoodItem();
        steakRibeye = setFoodPresetValues(steakRibeye, "Steak (Ribeye, trimmed to 0\"\" fat)", 51,
                3.0, 0.43, 8.34, 1.0);
        foodPresets.add(steakRibeye);

        FoodItem codAtlantic = new FoodItem();
        codAtlantic = setFoodPresetValues(codAtlantic, "Cod (Atlantic)", 23, 0.19, 0.0, 5.05, 1.0);
        foodPresets.add(codAtlantic);

        FoodItem codPacific = new FoodItem();
        codPacific = setFoodPresetValues(codPacific, "Cod (Pacific)", 24, 0.12, 0.0, 4.33, 1.0);
        foodPresets.add(codPacific);

        FoodItem tilapia = new FoodItem();
        tilapia = setFoodPresetValues(tilapia, "Tilapia", 36, 1.0, 0.0, 7.0, 1.0);
        foodPresets.add(tilapia);

        FoodItem salmonAtlantic = new FoodItem();
        salmonAtlantic = setFoodPresetValues(salmonAtlantic, "Salmon (Atlantic, wild)", 40, 1.8,
                0.0, 5.62, 1.0);
        foodPresets.add(salmonAtlantic);

        FoodItem salmonSockeye = new FoodItem();
        salmonSockeye = setFoodPresetValues(salmonSockeye, "Salmon (Sockeye)", 40, 1.59, 0.0,
                6.0, 1.0);
        foodPresets.add(salmonSockeye);

        FoodItem salmonPink = new FoodItem();
        salmonPink = setFoodPresetValues(salmonPink, "Salmon (Pink)", 36, 1.25, 0.0, 5.81, 1.0);
        foodPresets.add(salmonPink);

        FoodItem salmonKing = new FoodItem();
        salmonKing = setFoodPresetValues(salmonKing, "Salmon (King, Chinook, Alaska Native)",
                52, 3, 0.0, 6.0, 1.0);
        foodPresets.add(salmonKing);

        FoodItem appleFuji = new FoodItem();
        appleFuji = setFoodPresetValues(appleFuji, "Apple (Fuji, large (236g)", 149, 0.42,
                35.92, 0.47, 8.32);
        foodPresets.add(appleFuji);

        FoodItem appleGala = new FoodItem();
        appleGala = setFoodPresetValues(appleGala, "Apple (Gala, large (200g)", 114, 0.24,
                27.36, 0.7, 7.05);
        foodPresets.add(appleGala);

        FoodItem appleGoldenDelicious = new FoodItem();
        appleGoldenDelicious = setFoodPresetValues(appleGoldenDelicious, "Apple (Golden Delicious, large (215g))",
                123, 0.32, 29.24, 0.6, 7.58);
        foodPresets.add(appleGoldenDelicious);

        FoodItem appleGrannySmith = new FoodItem();
        appleGrannySmith = setFoodPresetValues(appleGrannySmith, "Apple (Granny Smith, large (206g)",
                119, 0.39, 28.04, 0.91, 7.27);
        foodPresets.add(appleGrannySmith);

        FoodItem strawberries = new FoodItem();
        strawberries = setFoodPresetValues(strawberries, "Strawberries, 1 cup (4 oz.)", 46, 0.43,
                11.06, 0.96, 4.02);
        foodPresets.add(strawberries);

        FoodItem banana = new FoodItem();
        banana = setFoodPresetValues(banana, "Banana, large (136g)", 121, 0.45, 31.06, 1.48,
                4.8);
        foodPresets.add(banana);

        FoodItem orangeNavel = new FoodItem();
        orangeNavel = setFoodPresetValues(orangeNavel, "Orange (Navel, whole)", 81, 0.25, 20.69,
                1.5, 4.94);
        foodPresets.add(orangeNavel);

        FoodItem orangeFlorida = new FoodItem();
        orangeFlorida = setFoodPresetValues(orangeFlorida, "Orange (Florida, whole (140g)", 85,
                0.39, 21.35, 1.3, 4.97);
        foodPresets.add(orangeFlorida);

        FoodItem basil = new FoodItem();
        basil = setFoodPresetValues(basil, "Basil (fresh, 1 leaf)", 1, 0.02, 0.07, 0.08,
                0.09);
        foodPresets.add(basil);

        FoodItem beansBlack = new FoodItem();
        beansBlack = setFoodPresetValues(beansBlack, "Beans (black, 1 cup (172g)", 227, 0.93,
                40.78, 8.86, 6.07);
        foodPresets.add(beansBlack);

        FoodItem beansKidney = new FoodItem();
        beansKidney = setFoodPresetValues(beansKidney, "Beans (Kidney, 100g)", 33, 0.58, 4.72,
                4.82, 3.53);
        foodPresets.add(beansKidney);

        FoodItem beansPinto = new FoodItem();
        beansPinto = setFoodPresetValues(beansPinto, "Beans (Pinto, 1 cup (171g)", 108, 1.11,
                44.84, 15.41, 6.03);
        foodPresets.add(beansPinto);

        FoodItem bellPepperGreen = new FoodItem();
        bellPepperGreen = setFoodPresetValues(bellPepperGreen, "Bell Pepper (red, large (164g)", 33,
                0.28, 7.61, 1.41, 5.78);
        foodPresets.add(bellPepperGreen);

        FoodItem bellPepperRed = new FoodItem();
        bellPepperRed = setFoodPresetValues(bellPepperRed, "Bell Pepper (red, large (164g)", 51,
                0.49, 9.89, 1.62, 5.78);
        foodPresets.add(bellPepperRed);

        FoodItem bellPepperYellow = new FoodItem();
        bellPepperYellow = setFoodPresetValues(bellPepperYellow, "Bell Pepper (yellow, large (186g)",
                50, 0.39, 11.76, 1.86, 6.56);
        foodPresets.add(bellPepperYellow);

        FoodItem broccoli = new FoodItem();
        broccoli = setFoodPresetValues(broccoli, "Broccoli, 1 cup (91g)", 31, 0.34, 6.04,
                2.57, 3.2);
        foodPresets.add(broccoli);

        FoodItem corn = new FoodItem();
        corn = setFoodPresetValues(corn, "Corn (sweet, yellow)", 125, 1.96, 27.12,
                4.74, 5.11);
        foodPresets.add(corn);

        FoodItem garlic = new FoodItem();
        garlic = setFoodPresetValues(garlic, "Garlic (raw, 1 clove)", 4, 0.02, 0.99,
                0.19, 0.11);
        foodPresets.add(garlic);

        FoodItem lettuceIceberg = new FoodItem();
        lettuceIceberg = setFoodPresetValues(lettuceIceberg, "Lettuce (Iceberg, 1 cup chopped (57g)",
                8, 0.08, 1.69, 0.51, 2.01);
        foodPresets.add(lettuceIceberg);

        FoodItem lettuceRomaine = new FoodItem();
        lettuceRomaine = setFoodPresetValues(lettuceRomaine, "Lettuce (Romaine, 1 cup shredded(47g))",
                8, 0.14, 1.55, 0.58, 1.66);
        foodPresets.add(lettuceRomaine);

        FoodItem onion = new FoodItem();
        onion = setFoodPresetValues(onion, "Onion, 1 whole (150g)", 60, 0.15, 14.01,
                1.65, 5.29);
        foodPresets.add(onion);

        FoodItem onionSweet = new FoodItem();
        onionSweet = setFoodPresetValues(onionSweet, "Onion (sweet, 1 whole (331g)", 106,
                0.26, 24.99, 2.62, 5.29);
        foodPresets.add(onionSweet);

        FoodItem parsley = new FoodItem();
        parsley = setFoodPresetValues(parsley, "Parsley (fresh (1 tbsp)", 1, 0.03, 0.24,
                0.11, 0.13);
        foodPresets.add(parsley);

        FoodItem potatoRusset = new FoodItem();
        potatoRusset = setFoodPresetValues(potatoRusset, "Potato (Russet (100g)", 79, 0.08,
                18.07, 2.14, 3.53);
        foodPresets.add(potatoRusset);

        FoodItem potatoRed = new FoodItem();
        potatoRed = setFoodPresetValues(potatoRed, "Potato (red (100g)", 70, 0.14, 15.9,
                1.89, 3.53);
        foodPresets.add(potatoRed);

        FoodItem spinach = new FoodItem();
        spinach = setFoodPresetValues(spinach, "Spinach (raw, 1 cup (30g)", 7, 0.12,
                1.09, 0.86, 1.06);
        foodPresets.add(spinach);

        FoodItem sweetPotato = new FoodItem();
        sweetPotato = setFoodPresetValues(sweetPotato, "Sweet Potato (100g)", 86, 0.05,
                20.12, 1.57, 3.53);
        foodPresets.add(sweetPotato);

        FoodItem tomato = new FoodItem();
        tomato = setFoodPresetValues(tomato, "Tomato (1 large (182g))", 33, 0.36,
                7.08, 1.6, 6.42);
        foodPresets.add(tomato);

        FoodItem cayennePepper = new FoodItem();
        cayennePepper = setFoodPresetValues(cayennePepper, "Cayenne Pepper (1 tsp))", 6,
                0.31, 1.02, 0.22, 0.06);
        foodPresets.add(cayennePepper);

        FoodItem egg = new FoodItem();
        egg = setFoodPresetValues(egg, "Egg (whole, large (50g))", 72, 4.76,
                0.36, 6.28, 1.76);
        foodPresets.add(egg);

        FoodItem eggWhite = new FoodItem();
        eggWhite = setFoodPresetValues(eggWhite, "Egg White (large (33g))", 17,
                0.06, 0.27, 3.6, 1.64);
        foodPresets.add(eggWhite);

        FoodItem garlicPowder = new FoodItem();
        garlicPowder = setFoodPresetValues(garlicPowder, "Garlic Powder", 10, 0.02,
                2.25, 0.0, 0.11);
        foodPresets.add(garlicPowder);

        FoodItem blackPepper = new FoodItem();
        blackPepper = setFoodPresetValues(blackPepper, "Black Pepper (ground)", 5,
                0.07, 1.47, 0.24, 0.08);
        foodPresets.add(blackPepper);

        FoodItem whitePepper = new FoodItem();
        whitePepper = setFoodPresetValues(whitePepper, "White Pepper (ground)", 7,
                0.05, 1.65, 0.25, 0.08);
        foodPresets.add(whitePepper);

        FoodItem salt = new FoodItem();
         salt = setFoodPresetValues(salt, "Salt (table)", 0, 0.0, 0.0, 0.0, 0.21);
        foodPresets.add(salt);
    }


    /**
     * Creates the food presets and stores them into the foodPresets ArrayList.
     */
    protected void createFoodPresets(){

    }


    /**
     * Returns the ArrayList of foodType names of foodPresets.
     * @return -- The ArrayList of foodTypes (String)
     */
    protected ArrayList<String> getFoodTypes(){
        ArrayList<String> list = new ArrayList<String>();
        for(FoodItem item : foodPresets){
            list.add(item.getFoodType());
        }
        return list;
    }


    /**
     * Returns the requested FoodItem
     * @param item -- The FoodItem requested
     * @return -- The requested FoodItem
     */
    protected FoodItem getFoodPreset(FoodItem item){
        //index of the food item
        int index = foodPresets.indexOf(item);

       return this.foodPresets.get(index);
    }


    /**
     * Gets the FoodPresets table.
     * @return -- The FoodPresets Hashtable.
     */
    protected Hashtable getPresetsTable(){
        return this.presetsTable;
    }


    /**
     * Initializes the FoodPresets table.
     */
    protected void initPresetsTable(){
        presetsTable = new Hashtable<String, FoodItem>();
        for(FoodItem food : foodPresets){
            presetsTable.put(food.getFoodType(), food);
        }
    }


    /**
     * Sets the values for the FoodItem.
     *
     * @param food -- The FoodItem
     * @param name -- The display name of the FoodItem
     * @param cal -- Calorie amount
     * @param fat -- Fat amount
     * @param carbs -- Carbs amount
     * @param protein -- Protein amount
     * @param servingSize -- Serving size( in oz.)
     */
    protected FoodItem setFoodPresetValues(FoodItem food, String name, int cal, double fat, double carbs, double protein, double servingSize){
        //Set all of the valus
        food.setCalories(cal);
        food.setFoodType(name);
        food.setFat(fat);
        food.setCarbs(carbs);
        food.setProtein(protein);
        food.setServingSize(servingSize);
        return food;
    }
}
