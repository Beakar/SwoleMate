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
import android.widget.ListView;
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

        workouts = new ArrayList<BaseWorkout>();
        setupSpinners();

        //for testing purposes only
        initTestWorkouts();

        WorkoutAdapter workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, workouts);
        ListView workoutListView = (ListView)findViewById(R.id.lv_history);
        workoutListView.setAdapter(workoutAdapter);
    }

    private void initTestWorkouts() {
        StrengthWorkout s = new StrengthWorkout().initTestValues();
        workouts.add(s);

        CardioWorkout ca = new CardioWorkout().initTestValues();
        workouts.add(ca);

        CustomWorkout cu = new CustomWorkout().initTestValues();
        workouts.add(cu);
    }

    private void setupSpinners() {
        Spinner s = (Spinner)findViewById(R.id.spin_dateSelector);

        ArrayList<String> strings = new ArrayList<String>();

        strings.add("Today");
        strings.add("This week");
        strings.add("This month");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        s.setAdapter(adapter);


        Spinner types = (Spinner)findViewById(R.id.spin_workoutTypes);
        ArrayList<String> workoutTypes = new ArrayList<String>();

        workoutTypes.add("All");
        workoutTypes.add("Strength");
        workoutTypes.add("Cardio");
        workoutTypes.add("Custom");

        ArrayAdapter<String> workoutTypesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, workoutTypes);
        types.setAdapter(workoutTypesAdapter);
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
