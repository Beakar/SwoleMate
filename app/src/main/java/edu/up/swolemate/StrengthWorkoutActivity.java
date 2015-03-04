package edu.up.swolemate;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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
        FitnessDatabaseHelper helper = new FitnessDatabaseHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        String query = "INSERT INTO StrengthWorkouts (displayName, dateCompleted) VALUES ('testWorkout', 1425511564)";
        db.execSQL(query);

        db = helper.getReadableDatabase();

        
    }

    public void onFinishWorkoutClick(View v) {
        insertCurrentWorkout();
    }
}
