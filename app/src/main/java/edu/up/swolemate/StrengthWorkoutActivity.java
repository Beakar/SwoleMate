package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.net.ContentHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StrengthWorkoutActivity extends Activity  implements View.OnClickListener {

    //    #######################################################################################
//    DELETE THESE EVENTUALLY, BUT THEY'RE HELPFUL FOR NOW
//    http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_inputtype
//    http://www.codelearn.org/android-tutorial/android-listview
//    #######################################################################################
    Button createExerciseButton;
    Button finishButton;
    StrengthExerciseDialogFragment createDialog;
    FragmentManager fragManager;
    Context context = this;
    //References the current workout being created
    protected StrengthWorkout currentWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_strength_workout);
        super.onCreate(savedInstanceState);
        fragManager = getFragmentManager();

        createDialog = new StrengthExerciseDialogFragment();
        createExerciseButton = (Button)findViewById(R.id.newStrengthExercise);
        finishButton = (Button)findViewById(R.id.finishButton1);
        finishButton.setOnClickListener(this);
        createExerciseButton.setOnClickListener(this);

        currentWorkout = new StrengthWorkout("Test exercise");
        currentWorkout.initTestValues();
    }



    @Override
    public void onClick(View v){
        if(v.getId() == R.id.finishButton1){
            Log.e("Logged Button pressed","finish");

        }
        if(v.getId() == R.id.newStrengthExercise){
            Log.e("logged Button pressed","Create new str exercise");
            createDialog.show(fragManager,"Create new exercise");
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
