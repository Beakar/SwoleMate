package edu.up.swolemate;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.net.ContentHandler;
import java.sql.SQLException;


public class StrengthWorkoutActivity extends Activity {

    /**
     * References the current workout being created
     */
    protected StrengthWorkout currentWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_workout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inserts the current workout into the database
     */
    private void insertCurrentWorkout() {

        SQLiteDatabase db = new FitnessDatabaseHelper(this).getWritableDatabase();

        //ContentValues links field names to values for each field
        ContentValues vals = new ContentValues();

        vals.put("displayName", currentWorkout.getDisplayName());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);
        long workoutId = db.insert("StrengthWorkouts", null, vals);

        //there was some error
        if(workoutId == -1) {
            Log.e("Invalid database query", "Something went wrong with the database query");
            return;
        }

        //add each exercise for the workout
        for(Exercise e : currentWorkout.getExercises()) {
            vals.clear();
            vals.put("displayName", e.getName());
            long exerciseId = db.insert("Exercises", null, vals);


            for(ExerciseSubset set : e.getSets()) {
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

    }



    /**
     * Deletes all records from the StrengthWorkouts table.
     * Test function
     */
    private void deleteAll() {
        SQLiteDatabase db = new FitnessDatabaseHelper(this).getReadableDatabase();

        String query = "DELETE FROM StrengthWorkouts";
        db.execSQL(query);

        Log.d("Spacer", "------------------------------------");
        selectAll();
        Log.d("Spacer", "------------------------------------");
    }

    /**
     * Prints out records from the database
     * Test function
     */
    private void selectAll() {
        SQLiteDatabase db = new FitnessDatabaseHelper(this).getReadableDatabase();

        String query = "SELECT * FROM StrengthWorkouts";
        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext()) {
            Log.d("Workouts in database", c.getString(0) + " : " + c.getString(1));
        }
    }
}
