package edu.up.swolemate;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;


public class HistoryTrackingActivity extends Activity {

    protected List<BaseWorkout> workouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_tracking);

        setupSpinner();

        //for testing purposes only
        initTestWorkouts();
    }

    private void initTestWorkouts() {
        StrengthWorkout s = new StrengthWorkout();
        s.initTestValues();
        workouts.add(s);

        CardioWorkout ca = new CardioWorkout();
        ca.initTestValues();
        workouts.add(ca);

        CustomWorkout cu = new CustomWorkout();
        cu.initTestValues();
        workouts.add(cu);
    }

    private void setupSpinner() {
        Spinner s = (Spinner)findViewById(R.id.spin_dateSelector);

        ArrayList<String> strings = new ArrayList<String>();

        strings.add("Today");
        strings.add("This week");
        strings.add("This month");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        s.setAdapter(adapter);
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

    public void onNewWorkoutClick(View v) {
        Intent i = new Intent(this, CreateWorkoutActivity.class);
        startActivity(i);
    }
}
