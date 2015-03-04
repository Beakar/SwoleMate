package edu.up.swolemate;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Nathan on 3/2/2015.
 */
public class FitnessDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "SwoleMate";
    private static final int DB_VERSION = 1;

    private static final String QUERY = "CREATE TABLE StrengthWorkouts    (id INTEGER PRIMARY KEY ASC, displayName);\n" +
            "CREATE TABLE FoodMeals           (id INTEGER PRIMARY KEY ASC, displayName);\n" +
            "CREATE TABLE FoodItems           (id INTEGER PRIMARY KEY ASC, foodType, servingSize, calories, fat, carbs, protein);\n" +
            "CREATE TABLE ItemsToMeals        (id INTEGER PRIMARY KEY ASC, mealId, itemId);\n" +
            "CREATE TABLE Exercises           (id INTEGER PRIMARY KEY ASC, displayName);\n" +
            "CREATE TABLE ExerciseSubsets     (id INTEGER PRIMARY KEY ASC, weight INTEGER, numReps INTEGER);\n" +
            "CREATE TABLE SubsetsToExercises  (id INTEGER PRIMARY KEY ASC, subsetId, exerciseId, FOREIGN KEY(subsetId) REFERENCES ExerciseSubsets(id), FOREIGN KEY(exerciseId) REFERENCES ExercisePresets(id));\n" +
            "CREATE TABLE ExercisesToWorkouts (id INTEGER PRIMARY KEY ASC, exerciseId INTEGER, workoutId INTEGER, FOREIGN KEY(workoutId) REFERENCES StrengthWorkouts(id));\n" +
            "CREATE TABLE WorkoutHistoryItems (id INTEGER PRIMARY KEY ASC, workoutId INTEGER, timeCompleted INTEGER, FOREIGN KEY(workoutId) REFERENCES StrengthWorkouts(id));\n" +
            "CREATE TABLE FoodHistoryItems    (id INTEGER PRIMARY KEY ASC, mealId INTEGER, timeCompleted INTEGER, FOREIGN KEY(mealId) REFERENCES FoodMeals(id));";

    public FitnessDatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    /**
     * when the database is created, we want to create all the tables we'll need
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createWorkoutTables(db);
        createFoodTables(db);
    }

    /**
     * Creates workout-related tables in the datbase
     *
     * @param db
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
                        new ForeignKeyHelper("subsetId", "ExerciseSubsets", "id"),
                        new ForeignKeyHelper("exerciseId", "Exercises", "id")
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
                        new ForeignKeyHelper("subsetId", "ExerciseSubsets", "id"),
                        new ForeignKeyHelper("workoutId", "StrengthWorkouts", "id")
                });
    }


        /**
         * Helper method that creates food-related database tables
         * cleans up code in the onCreate method
         *
         * @param db
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
                        new ForeignKeyHelper("mealId", "FoodMeals", "id"),
                        new ForeignKeyHelper("itemId", "FoodItems", "id")
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
            columns = columns + "PRIMARY KEY(" + pk.fieldName + " " + pk.order;

        //add foreign keys
        if (fks != null) {
            for (ForeignKeyHelper key : fks) {
                columns = columns + "FOREIGN KEY(" + key.fieldName + ") REFERENCES " + key.homeTable + "(" + key.homeProperty + "), ";
            }
        }

        columns = columns.replaceAll(", $", "");

        query = query + "(" + columns + ");";


        db.execSQL(query);
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param s
     * @return
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