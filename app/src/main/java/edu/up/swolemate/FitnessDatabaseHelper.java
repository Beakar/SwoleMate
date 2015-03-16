package edu.up.swolemate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 3/2/2015.
 */
public class FitnessDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "SwoleMate";
    private static final int DB_VERSION = 1;

    public FitnessDatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        //enable foreign key constraints
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    /**
     * when the database is created, we want to create all the tables we'll need
     *
     * @param db database object
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createWorkoutTables(db);
        createFoodTables(db);
    }

    /**
     * Creates workout-related tables in the database
     *
     * @param db database object
     */
    private void createWorkoutTables(SQLiteDatabase db) {
        //create StrengthWorkouts table
        createTable(db,
                "StrengthWorkouts",
                new String[]{
                        "id INTEGER",
                        "displayName TEXT",
                        "dateCompleted INTEGER"
                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //Create CardioWorkouts table
        createTable(db,
                "CardioWorkouts",
                new String[]{
                        "id INTEGER",
                        "displayName TEXT",
                        "duration REAL",
                        "distance REAL",
                        "dateCompleted INTEGER"

                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //Create CustomWorkouts table
        createTable(db,
                "CustomWorkouts",
                new String[]{
                        "id INTEGER",
                        "displayName TEXT",
                        "workoutData TEXT",
                        "dateCompleted INTEGER"
                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //Create Exercises table
        createTable(db,
                "Exercises",
                new String[]{
                        "id INTEGER",
                        "displayName TEXT"
                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //Create ExerciseSubsets table
        createTable(db,
                "ExerciseSubsets",
                new String[]{
                        "id INTEGER",
                        "weight REAL",
                        "numReps INTEGER"
                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //Create SubsetsToExercises table
        createTable(db,
                "SubsetsToExercises",
                new String[]{
                        "id INTEGER",
                        "subsetId INTEGER",
                        "exerciseId INTEGER"
                },
                new PrimaryKeyHelper("id", "ASC"),
                new ForeignKeyHelper[]{
                        new ForeignKeyHelper("subsetId", "ExerciseSubsets", "id", "ON DELETE CASCADE"),
                        new ForeignKeyHelper("exerciseId", "Exercises", "id", "ON DELETE CASCADE")
                });

        //Create ExercisesToWorkouts table
        createTable(db,
                "ExercisesToWorkouts",
                new String[]{
                        "id INTEGER",
                        "exerciseId INTEGER",
                        "workoutId INTEGER"
                },
                new PrimaryKeyHelper("id", "ASC"),
                new ForeignKeyHelper[]{
                        new ForeignKeyHelper("exerciseId", "Exercises", "id", "ON DELETE CASCADE"),
                        new ForeignKeyHelper("workoutId", "StrengthWorkouts", "id", "ON DELETE CASCADE")
                });

        String workoutDeleteTrigger =
                "CREATE TRIGGER workoutDeleteTrigger AFTER DELETE ON ExercisesToWorkouts " +
                        "FOR EACH ROW " +
                        "BEGIN " +
                        "DELETE FROM SubsetsToExercises WHERE exerciseId=OLD.exerciseId; " +
                        "END;";


        db.execSQL(workoutDeleteTrigger);

    }


    /**
     * Helper method that creates food-related database tables
     * cleans up code in the onCreate method
     *
     * @param db datbase
     */
    private void createFoodTables(SQLiteDatabase db) {
        //create FoodItems table
        createTable(db,
                "FoodItems",
                new String[]{
                        "id INTEGER",
                        "foodType TEXT",
                        "servingSize REAL",
                        "calories INTEGER",
                        "fat REAL",
                        "carbs REAL",
                        "protein REAL"
                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //create FoodMeals table
        createTable(db,
                "FoodMeals",
                new String[]{
                        "id INTEGER",
                        "displayName TEXT",
                        "dateCompleted INTEGER"
                },
                new PrimaryKeyHelper("id", "ASC"),
                null);

        //link items to meals
        createTable(db,
                "ItemsToMeals",
                new String[]{
                        "id INTEGER",
                        "mealId INTEGER",
                        "itemId INTEGER",
                        "amountEaten REAL"//amountEaten is specific to each meal
                },
                new PrimaryKeyHelper("id", "ASC"),
                new ForeignKeyHelper[]{
                        new ForeignKeyHelper("mealId", "FoodMeals", "id", "ON DELETE CASCADE"),
                        new ForeignKeyHelper("itemId", "FoodItems", "id", "ON DELETE CASCADE")
                });
    }

    /**
     * Creates a table in the SQLite database with the specified properties
     *
     * @param tableName name of the table we're creating
     * @param fields    list of the fields to add to the table
     * @param pk        field to create as primary key
     * @param fks       list of information about foreign keys.
     */
    private void createTable(SQLiteDatabase db, String tableName, String[] fields, PrimaryKeyHelper pk, ForeignKeyHelper[] fks) {
        String query = "CREATE TABLE " + tableName;

        String columns = "";
        //add fields to column declaration
        for (String field : fields) {
            columns = columns + field + ", ";
        }

        //add primary keys
        if (pk != null)
            columns = columns + "PRIMARY KEY(" + pk.fieldName + " " + pk.order + "), ";

        //add foreign keys
        if (fks != null) {
            for (ForeignKeyHelper key : fks) {
                columns = columns + "FOREIGN KEY(" + key.fieldName + ") REFERENCES " + key.homeTable + "(" + key.homeProperty + ") " + key.constraints + ", ";
            }
        }

        columns = columns.replaceAll(", $", "");

        query = query + "(" + columns + ");";


        db.execSQL(query);
    }

    /**
     * Tests to make sure entries were deleted from a database
     */
    public void testExerciseSelect() {
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM ExercisesToWorkouts", null);

        while (c.moveToNext()) {
            Log.d("ExercisesToWorkouts entry", c.getInt(1) + " : " + c.getInt(2));
        }

    }

    /**
     * Inserts a food meal object into the database
     * @param meal meal to insert
     * @return id of inserted meal
     */
    public int insertMeal(FoodMeal meal) {
        SQLiteDatabase db = getWritableDatabase();
        int id = -1;
        for(FoodItem item : meal.getFoodItems()) {
            insertFoodItem(item);
        }

        ContentValues vals = new ContentValues();
        vals.put("displayName", meal.getName());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);

        return (int)(db.insert("FoodMeals", null, vals));
    }

    /**
     * Inserts a food item object into the database
     * TODO: return id of food if it already exists in database
     * @param item item to insert
     * @return id of inserted food
     */
    private int insertFoodItem(FoodItem item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues vals = new ContentValues();

        vals.put("foodType", item.getFoodType());
        vals.put("servingSize", item.getServingSize());
        vals.put("calories", item.getCalories());
        vals.put("fat", item.getFat());
        vals.put("carbs", item.getCarbs());
        vals.put("protein", item.getProtein());


        return (int)(db.insert("FoodItems", null, vals));
    }

    /**
     * Retrives a list of food names
     * @return list of food names for autocorrect control
     */
    public List<String> selectFoodNames() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> names = new ArrayList<String>();

        String query = "SELECT foodType FROM FoodItems;";

        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext()) {
            names.add(c.getString(0));
        }

        return names;
    }

    /**
     * Retrieves all meal items from the database.
     * @return meal items
     */
    public List<FoodMeal> selectAllMeals() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<FoodMeal> meals = new ArrayList<FoodMeal>();

        String query = "SELECT * From FoodMeal";

        Cursor c = db.rawQuery(query, null);
        while(c.moveToNext()) {
            FoodMeal meal = new FoodMeal();
            meal.setName(c.getString(1));
            meal.setDateCompleted(c.getInt(2));
            meal.setFoodItems(selectFoodItems(c.getInt(0)));

            meals.add(meal);
        }

        return meals;
    }

    /**
     * Retrieves a list of FoodItems associated with a meal
     * @param mealId id of meal to select food items for
     * @return list of food items
     */
    public List<FoodItem> selectFoodItems(int mealId) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<FoodItem> items = new ArrayList<FoodItem>();

        String query = "SELECT FoodItems.foodType, FoodItems.servingSize, foodItems.calories, foodItems.fat, foodItems.carbs, foodItems.protein " +
                       "FROM FoodItems " +
                       "LEFT OUTER JOIN ItemsToMeals ON FoodItems.id=ItemsToMeals.itemId " +
                       "LEFT OUTER JOIN FoodMeals ON ItemsToMeals.mealId=FoodMeals.id " +
                       "WHERE FoodMeals.id=" + mealId;

        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext()) {
            FoodItem item = new FoodItem();

            item.setFoodType(c.getString(0));
            item.setServingSize(c.getDouble(1));
            item.setCalories(c.getInt(2));
            item.setFat(c.getDouble(3));
            item.setCarbs(c.getDouble(4));
            item.setProtein(c.getDouble(5));

            items.add(item);
        }

        return items;
    }

    /**
     * Retrieves a food item from the database with the specified name
     * @param name name of food to retrieve
     * @return FoodItem object with information from the database. Returns null if not found.
     *
     */
    public FoodItem selectFoodByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        FoodItem item = new FoodItem();

        String query = "SELECT * FROM FoodItem WHERE foodType=" + name;

        Cursor c = db.rawQuery(query, null);

        if(c.moveToNext()) {
            item.setFoodType(c.getString(1));
            item.setServingSize(c.getDouble(2));
            item.setCalories(c.getInt(3));
            item.setFat(c.getDouble(4));
            item.setCarbs(c.getDouble(5));
            item.setProtein(c.getDouble(6));
            return item;
        }

        return null;
    }

    /**
     * Inserts a workout to its respective database
     *
     * @param workout workout to insert
     * @return id of inserted workout
     */
    public int insertWorkout(BaseWorkout workout) {
        int id = -1;

        //safe cast
        if (workout instanceof StrengthWorkout) {
            id = insertStrength((StrengthWorkout) workout);
        } else if(workout instanceof CardioWorkout) {
            id = insertCardio((CardioWorkout)workout);
        } else if(workout instanceof CustomWorkout) {
            id = insertCustom((CustomWorkout)workout);
        }

        return id;
    }

    /**
     * Inserts a strength workout into its respective table
     * @param workout workout to insert
     * @return id of inserted workout
     */
    private int insertStrength(StrengthWorkout workout) {
        SQLiteDatabase db = getWritableDatabase();

        //ContentValues links field names to values for each field
        ContentValues vals = new ContentValues();

        vals.put("displayName", workout.getDisplayName());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);
        long workoutId = db.insert("StrengthWorkouts", null, vals);

        //there was some error
        if (workoutId == -1) {
            Log.e("Invalid database query", "Something went wrong with the database query");
            return -1;
        }

        //add each exercise for the workout
        for (Exercise e : workout.getExercises()) {
            vals.clear();
            vals.put("displayName", e.getName());
            long exerciseId = db.insert("Exercises", null, vals);


            for (ExerciseSubset set : e.getSets()) {
                //add each set for the exercise
                vals.clear();
                vals.put("weight", set.getWeight());
                vals.put("numReps", set.getNumReps());
                long subsetId = db.insert("ExerciseSubsets", null, vals);

                //link subset to exercise it came from
                vals.clear();
                vals.put("subsetId", subsetId);
                vals.put("exerciseId", exerciseId);
                db.insert("SubsetsToExercises", null, vals);
            }

            //link exercise to workout
            vals.clear();
            vals.put("exerciseId", exerciseId);
            vals.put("workoutId", workoutId);
            db.insert("ExercisesToWorkouts", null, vals);
        }

        return (int) workoutId;
    }

    /**
     * Inserts a cardio workout into the database
     * @param workout workout to insert
     * @return id of inserted workout
     */
    private int insertCardio(CardioWorkout workout) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put("displayName", workout.getDisplayName());
        vals.put("duration", workout.getDuration());
        vals.put("distance", workout.getDistance());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);

        return (int)(db.insert("CardioWorkouts", null, vals));
    }

    /**
     * Inserts a custom workout into the database
     * @param workout workout to insert
     * @return id of inserted workout
     */
    private int insertCustom(CustomWorkout workout) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put("displayName", workout.getDisplayName());
        vals.put("workoutData", workout.getWorkoutData());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);

        return (int)(db.insert("CustomWorkouts", null, vals));
    }

    /**
     * Gets all workouts
     * @return list of all workouts
     */
    public List<BaseWorkout> getAllWorkouts() {
        ArrayList<BaseWorkout> workouts = new ArrayList<BaseWorkout>();
        workouts.addAll(selectStrengthWorkouts());
        workouts.addAll(selectCardioWorkouts());
        workouts.addAll(selectCustomWorkouts());

        return workouts;
    }

    /**
     * Deletes a StrengthWorkout
     * @param id id of workout to delete
     */
    public void deleteStrengthWorkout(int id) {
        SQLiteDatabase db = getWritableDatabase();

        //delete from strength workouts
        db.delete("StrengthWorkouts", "id=" + id, null);
    }

    /**
     * Deletes a Cardio Workout
     * @param id id of workout to delete
     */
    public void deleteCardioWorkout(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("CardioWorkouts", "id=" + id, null);
    }

    /**
     * Deletes a Custom workout
     * @param id id of workout to delete
     */
    public void deleteCustomWorkout(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("CustomWorkouts", "id=" + id, null);
    }

    /**
     * Selects all CardioWorkout objects from the database
     * @return
     */
    private List<CardioWorkout> selectCardioWorkouts() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CardioWorkout> workouts = new ArrayList<CardioWorkout>();

        String query = "SELECT *" +
                "FROM CardioWorkouts;";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            CardioWorkout cw = new CardioWorkout();
            cw.setId(c.getInt(0));
            cw.setDisplayName(c.getString(1));
            cw.setDuration(c.getDouble(2));
            cw.setDistance(c.getDouble(3));
            cw.setDateCompleted(c.getInt(4));

            workouts.add(cw);
        }

        return workouts;
    }

    /**
     * Select query for custom workouts
     *
     * @return list of all custom workouts
     */
    private List<CustomWorkout> selectCustomWorkouts() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CustomWorkout> workouts = new ArrayList<CustomWorkout>();

        String query = "SELECT * " +
                "FROM CustomWorkouts;";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            CustomWorkout cw = new CustomWorkout();
            cw.setId(c.getInt(0));
            cw.setDisplayName(c.getString(1));
            cw.setWorkoutData(c.getString(2));
            cw.setDateCompleted(c.getInt(3));

            workouts.add(cw);
        }

        return workouts;
    }

    /**
     * Gets StrengthWorkout records from the database
     * Test function
     */
    private List<StrengthWorkout> selectStrengthWorkouts() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<StrengthWorkout> workouts = new ArrayList<StrengthWorkout>();

        String query = "SELECT * " +
                "FROM StrengthWorkouts;";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            StrengthWorkout sw = new StrengthWorkout();
            sw.setId(c.getInt(0));
            sw.setDisplayName(c.getString(1));
            sw.setDateCompleted(c.getInt(2));
            sw.setExercises(selectExercises(sw.getId()));

            workouts.add(sw);
        }

        return workouts;
    }

    /**
     * Retrieves exercises associated with a database
     * @param workoutId id of workout to get exercises for
     * @return list of exercises
     */
    private List<Exercise> selectExercises(int workoutId) {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        String query = "SELECT Exercises.id, Exercises.displayName " +
                "FROM Exercises " +
                "LEFT OUTER JOIN ExercisesToWorkouts ON Exercises.id=ExercisesToWorkouts.exerciseId " +
                "LEFT OUTER JOIN StrengthWorkouts ON ExercisesToWorkouts.workoutId=StrengthWorkouts.id " +
                "WHERE StrengthWorkouts.id=" + workoutId + " " +
                "ORDER BY Exercises.id;";

        Cursor c = getReadableDatabase().rawQuery(query, null);

        while (c.moveToNext()) {
            Exercise e = new Exercise();
            e.setName(c.getString(1));
            e.setSets(selectSets(c.getInt(0)));
            e.setId(c.getInt(0));
            exercises.add(e);
        }

        return exercises;
    }

    /**
     * Helper method that returns the sets associated with an exercise
     *
     * @param exerciseId exercise to retrieve sets for
     * @return list of sets
     */
    private List<ExerciseSubset> selectSets(int exerciseId) {
        ArrayList<ExerciseSubset> sets = new ArrayList<ExerciseSubset>();

        String query = "SELECT ExerciseSubsets.id, ExerciseSubsets.weight, ExerciseSubsets.numReps " +
                "FROM ExerciseSubsets " +
                "LEFT OUTER JOIN SubsetsToExercises ON ExerciseSubsets.id=SubsetsToExercises.subsetId " +
                "LEFT OUTER JOIN Exercises ON SubsetsToExercises.exerciseId=Exercises.id " +
                "WHERE Exercises.id=" + exerciseId + " " +
                "ORDER BY ExerciseSubsets.id;";

        Cursor c = getReadableDatabase().rawQuery(query, null);

        //populate set fields
        while (c.moveToNext()) {
            ExerciseSubset set = new ExerciseSubset(c.getDouble(1), c.getInt(2));
            set.setId(c.getInt(1));
            sets.add(set);
        }

        return sets;
    }

    /**
     * Checks if a record exists.
     *
     * @param e exercise
     * @return whether the exercise exists
     */
    private boolean exerciseExists(Exercise e) {
        String query = "SELECT EXISTS(SELECT 1 FROM Exercises WHERE displayName=" + e.getName() + " LIMIT 1);";
        Cursor c = getReadableDatabase().rawQuery(query, null);

        while (c.moveToNext()) {
            return c.getInt(0) != 0;
        }

        return false;
    }

    /**
     * Checks if an exercise exists
     *
     * @param set set
     * @return whether the set exists
     */
    private boolean setExists(ExerciseSubset set) {
        String query = "SELECT EXISTS(SELECT 1 FROM ExerciseSubsets" +
                "WHERE weight=" + set.getWeight() + " AND numReps=" + set.getNumReps() +
                " LIMIT 1);";

        Cursor c = getReadableDatabase().rawQuery(query, null);

        while (c.moveToNext()) {
            return c.getInt(0) != 0;
        }

        return false;
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param s String to check
     * @return whether the string is null or empty
     */
    private boolean isNullOrEmpty(String s) {
        if (s.equals("") || s == null) {
            return true;
        }

        return false;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

    }
}