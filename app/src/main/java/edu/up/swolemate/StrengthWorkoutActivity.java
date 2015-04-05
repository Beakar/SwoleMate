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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
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


public class StrengthWorkoutActivity extends Activity  implements OnClickListener {

//    #######################################################################################
//    DELETE THESE EVENTUALLY, BUT THEY'RE HELPFUL FOR NOW
//    http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_inputtype
//    http://www.codelearn.org/android-tutorial/android-listview
//    #######################################################################################
    private Button createExerciseButton;
    private Button finishButton;
    private StrengthExerciseDialogFragment createDialog;
    private FragmentManager fragManager;
    private Context context = this;
    private EditText workoutNameEditText;
    //References the current workout being created
    protected StrengthWorkout currentWorkout;
    protected ListView exListView;
    protected ArrayList<Exercise> strengthList;
    protected ArrayAdapter<Exercise> listAdapter;


    /**
     * Alerts the user that they are trying to save a workout
     * with an empty title.
     */
    private void alertUnsaved(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Empty workout name");
        alertBuilder.setMessage("Your must enter a workout name before saving.");
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_strength_workout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        fragManager = getFragmentManager();

        createDialog = new StrengthExerciseDialogFragment();
        createExerciseButton = (Button)findViewById(R.id.newStrengthExercise);
        finishButton = (Button)findViewById(R.id.finishButton1);
        finishButton.setOnClickListener(this);
        createExerciseButton.setOnClickListener(this);

        currentWorkout = new StrengthWorkout("Test exercise");

        workoutNameEditText = (EditText)findViewById(R.id.enter_workout_name);

        exListView = (ListView)findViewById(R.id.strengthList);
        strengthList = new ArrayList<Exercise>();
        listAdapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_dropdown_item_1line, strengthList);
        exListView.setAdapter(listAdapter);
        //currentWorkout.initTestValues();
    }



    @Override
    public void onClick(View v){
        if(v.getId() == R.id.finishButton1){
            //if the workout name is empty, alert the user
            if(workoutNameEditText.getText().toString().equals("")){
                alertUnsaved();
                return;
            }

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
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inserts the current workout into the database
     */
    private void insertCurrentWorkout() {
        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);
        db.insertWorkout(currentWorkout);
    }



    /**
     * Deletes all records from the StrengthWorkouts table.
     * Test function
     */
    private void deleteAll() {
        SQLiteDatabase db = new FitnessDatabaseHelper(this).getReadableDatabase();

        String query = "DELETE FROM StrengthWorkouts";
        db.execSQL(query);
    }



}
