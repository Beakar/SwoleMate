package edu.up.swolemate;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CardioWorkoutActivity extends Activity {

    protected CardioWorkout currentWorkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentWorkout = new CardioWorkout();
        setContentView(R.layout.activity_cardio_workout);
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
     * Inserts the current workout object into the CardioWorkouts database
     */
    protected void insertCurrentWorkout() {
        SQLiteDatabase db = new FitnessDatabaseHelper(this).getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put("displayName", currentWorkout.getDisplayName());
        vals.put("duration", currentWorkout.getDuration());
        vals.put("distance", currentWorkout.getDistance());
        vals.put("dateCompleted", System.currentTimeMillis() / 1000);

        db.insert("CardioWorkouts", null, vals);
    }
}
