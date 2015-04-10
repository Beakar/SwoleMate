package edu.up.swolemate;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.List;


public class StrengthWorkoutActivity extends Activity  implements View.OnClickListener {

//    #######################################################################################
//    DELETE THESE EVENTUALLY, BUT THEY'RE HELPFUL FOR NOW
//    http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_inputtype
//    http://www.codelearn.org/android-tutorial/android-listview
//    #######################################################################################
    Button createExerciseButton;
    Button finishButton;
    EditText nameEditText;
    StrengthExerciseDialogFragment createDialog;
    FragmentManager fragManager;
    Context context = this;
    ExerciseListAdapter exerciseListAdapter;
    //References the current workout being created
    protected StrengthWorkout currentWorkout;
    protected Exercise selectedExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_strength_workout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        fragManager = getFragmentManager();

        //lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        createDialog = new StrengthExerciseDialogFragment();
        createExerciseButton = (Button)findViewById(R.id.newStrengthExercise);
        finishButton = (Button)findViewById(R.id.finishButton1);

        nameEditText = (EditText)findViewById(R.id.enter_workout_name);

        currentWorkout = new StrengthWorkout();

        ListView exerciseList = (ListView) findViewById(R.id.strength_exercise_list);
        exerciseListAdapter = new ExerciseListAdapter(this, currentWorkout.exercises);
        exerciseList.setAdapter(exerciseListAdapter);

        //if an exercise is selected from the list, open the dialog to edit it.
        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,long id) {
                selectedExercise = currentWorkout.exercises.get(position);
                createDialog.show(fragManager,"Edit exercise");
            }
        });
    }



    @Override
    public void onClick(View v){
        if(v.getId() == R.id.finishButton1){
            Log.e("Logged Button pressed","finish");

        }
        if(v.getId() == R.id.newStrengthExercise){
            Log.e("logged Button pressed","Create new str exercise");
            selectedExercise = new Exercise();
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

    public void addExerciseToWorkout(Exercise ex){
        currentWorkout.addExercise(ex);
        exerciseListAdapter.notifyDataSetChanged();
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

    public class ExerciseListAdapter extends BaseAdapter {

        List<Exercise> exerciseList;
        LayoutInflater inflater;
        Context context;


        public ExerciseListAdapter(Context context, List<Exercise> myList) {
            this.exerciseList = myList;
            this.context = context;
            inflater = LayoutInflater.from(this.context);        // only context can also be used
        }

        @Override
        public int getCount() {
            return exerciseList.size();
        }

        @Override
        public String getItem(int position) {
            return exerciseList.get(position).getName();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_strength_workout_exercises, null);
            }
            TextView label = (TextView) convertView.findViewById(R.id.strength_exercise_list_item);
            Button removeButton = (Button) convertView.findViewById(R.id.remove_exercise_button);
            removeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    currentWorkout.removeExercise(getItem(position));
                    notifyDataSetChanged();
                }
            });
            label.setText(exerciseList.get(position).getName());
            return convertView;
        }
    }

    public void onFinishWorkoutClick(View v) {
        saveWorkout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveWorkout() {
        currentWorkout.setDisplayName(nameEditText.getText().toString());
        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);
        db.insertWorkout(currentWorkout);
    }


}
