package edu.up.swolemate;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CustomWorkoutActivity extends Activity {

    protected CustomWorkout currentWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);
        currentWorkout = new CustomWorkout();

        getActionBar().setDisplayHomeAsUpEnabled(true);
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
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inserts the current workout into the CustomWorkouts database
     */
    public void insertCurrentWorkout() {
        SQLiteDatabase db = new FitnessDatabaseHelper(this).getWritableDatabase();

        ContentValues vals = new ContentValues();
        vals.put("displayName", currentWorkout.getDisplayName());
        vals.put("workoutData", currentWorkout.getWorkoutData());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);

        db.insert("CustomWorkouts", null, vals);
    }
}
