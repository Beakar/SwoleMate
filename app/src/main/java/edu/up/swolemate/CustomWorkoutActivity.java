package edu.up.swolemate;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class CustomWorkoutActivity extends Activity {

    protected CustomWorkout currentWorkout;

    EditText nameText;
    EditText dataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);
        currentWorkout = new CustomWorkout();
        //lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nameText = (EditText)findViewById(R.id.tv_custom_title);
        dataText = (EditText)findViewById(R.id.tv_custom_data);

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
        currentWorkout.setDisplayName(nameText.getText().toString());
        currentWorkout.setWorkoutData(dataText.getText().toString());

        new FitnessDatabaseHelper(this).insertWorkout(currentWorkout);
    }

    public void onFinishWorkoutClick(View v) {
        insertCurrentWorkout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
