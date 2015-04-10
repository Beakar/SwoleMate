package edu.up.swolemate;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;


public class CardioWorkoutActivity extends Activity implements View.OnClickListener {

    FragmentManager fragManager;
    Button finishButton;
    Button startTimerButton;
    Button stopTimerButton;
    Context context = this;
    Chronometer chronometer;
    FontEditText distanceBox;
    FontEditText durationBox;

    protected CardioWorkout currentWorkout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cardio_workout);

        //lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        fragManager = getFragmentManager();

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        startTimerButton = (Button)findViewById(R.id.startButton);
        stopTimerButton = (Button)findViewById(R.id.stopButton);
        finishButton = (Button)findViewById(R.id.finishButton);
        distanceBox = (FontEditText)findViewById(R.id.cardioDistanceEditText);
        durationBox = (FontEditText)findViewById(R.id.durationEntry);

        startTimerButton.setOnClickListener(this);
        stopTimerButton.setOnClickListener(this);
        finishButton.setOnClickListener(this);
        distanceBox.setOnClickListener(this);
        durationBox.setOnClickListener(this);

        currentWorkout = new CardioWorkout("Test exercise");
        currentWorkout.initTestValues();
    }

    /*
    *   Checks where the user clicks and then runs the corresponding action
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.startButton:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.stopButton:
                chronometer.stop();
                break;
            case R.id.durationEntry:
                double duration = Double.parseDouble(durationBox.getText().toString());
                currentWorkout.setDuration(duration);
                break;
            case R.id.cardioDistanceEditText:
                double distance = Double.parseDouble(distanceBox.getText().toString());
                currentWorkout.setDistance(distance);
                break;
            case R.id.finishButton:
                Log.e("Logged Button pressed","finish");
                break;
        }
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
     * Deletes all records from the CardioWorkouts table.
     * Test function
     */
    private void deleteAll() {
        SQLiteDatabase db = new FitnessDatabaseHelper(this).getReadableDatabase();

        String query = "DELETE FROM CardioWorkouts";
        db.execSQL(query);
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
