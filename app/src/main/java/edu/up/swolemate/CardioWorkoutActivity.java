package edu.up.swolemate;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;

import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;


public class CardioWorkoutActivity extends Activity implements View.OnClickListener {

    FragmentManager fragManager;
    FontButton finishButton;
    Button startTimerButton;
    Button stopTimerButton;
    Context context = this;
    Chronometer chronometer;

    FontEditText workoutNameBox;
    FontEditText distanceBox;
    FontEditText durationBox;

    CheckBox cb_manualInput;
    CheckBox cb_recordDistance;

    private boolean timerStopped = true;
    private long stoppedTime = 0;

    protected CardioWorkout currentWorkout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cardio_workout);

        currentWorkout = new CardioWorkout();

        //lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        fragManager = getFragmentManager();

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = getTimerValue();
                chronometer.setText(String.format("%02d", time / 60) + ":" + String.format("%02d", time % 60));
            }
        });
        startTimerButton = (Button)findViewById(R.id.startButton);
        stopTimerButton = (Button)findViewById(R.id.stopButton);
        finishButton = (FontButton)findViewById(R.id.finishButton);

        distanceBox = (FontEditText)findViewById(R.id.et_cardioDistanceEditText);
        durationBox = (FontEditText)findViewById(R.id.durationEntry);
        workoutNameBox = (FontEditText)findViewById(R.id.cardioNameEntry);

        cb_manualInput = (CheckBox)findViewById(R.id.cb_manualInput);
        cb_recordDistance = (CheckBox)findViewById(R.id.cb_recordDistance);


        startTimerButton.setOnClickListener(this);
        stopTimerButton.setOnClickListener(this);
        distanceBox.setOnClickListener(this);
        durationBox.setOnClickListener(this);

        initFocusListeners();
    }

    /*
    *   Checks where the user clicks and then runs the corresponding action
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.startButton:
                if(timerStopped)
                    startTimer();
                else
                    stopTimer();
                break;
            case R.id.stopButton:
                resetTimer();
                break;
            case R.id.durationEntry:
                double duration = Double.parseDouble(durationBox.getText().toString());
                currentWorkout.setDuration(duration);
                break;
            case R.id.et_cardioDistanceEditText:
                double distance = Double.parseDouble(distanceBox.getText().toString());
                currentWorkout.setDistance(distance);
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
     * Initializes the listeners on the EditText fields in this activity
     */
    private void initFocusListeners() {

        durationBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    cb_manualInput.setChecked(true);
            }
        });

        distanceBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    cb_recordDistance.setChecked(true);
            }
        });
    }


    private void updateWorkout() {
        Editable duration = durationBox.getText();
        Editable distance = distanceBox.getText();
        Editable workoutName = workoutNameBox.getText();

        if(cb_manualInput.isChecked()) {
            if(duration != null)
                currentWorkout.setDuration(Double.parseDouble(duration.toString()));
        } else {
            currentWorkout.setDuration(getTimerValue());
        }

        if(cb_recordDistance.isChecked()) {
            if(distance != null)
                currentWorkout.setDistance(Double.parseDouble(distance.toString()));
        }

        if(workoutName != null) {
            currentWorkout.setDisplayName(workoutName.toString());
        }
    }

    /**
     * Gets the value of the timer (seconds)
     * @return
     */
    private long getTimerValue() {
        return (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000;
    }
    /**
     * Inserts the current workout object into the CardioWorkouts database
     */
    protected void insertCurrentWorkout() {
        updateWorkout();
        new FitnessDatabaseHelper(this).insertWorkout(currentWorkout);
    }

    /**
     * Handles operations associated with starting the timer
     */
    public void startTimer() {
        timerStopped = false;
        chronometer.setBase(SystemClock.elapsedRealtime() + stoppedTime);
        chronometer.start();

        //update button to reflect new functionality
        startTimerButton.setBackgroundColor(Color.parseColor("#ECF244"));
        startTimerButton.setTextColor(Color.parseColor("#000000"));
        startTimerButton.setText("Stop");
    }

    /**
     * Handles operations associated with stopping the timer
     */
    public void stopTimer() {
        timerStopped = true;
        stoppedTime = getTimerValue();
        chronometer.stop();

        //update button to reflect new functionality
        startTimerButton.setBackgroundColor(Color.parseColor("#009900"));
        startTimerButton.setTextColor(Color.parseColor("#ffffff"));
        startTimerButton.setText("Start");
    }

    /**
     * Handles operations associated with resetting the timer
     */
    public void resetTimer() {
        chronometer.setText("00:00");
        stoppedTime = 0;
    }

    public void onFinishClick(View v) {
        insertCurrentWorkout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
