package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HistoryTrackingActivity extends Activity {

    protected List<BaseWorkout> workouts;

    protected WorkoutAdapter workoutAdapter;


    protected ListView workoutListView;

    protected Spinner dateSelector;

    protected Spinner workoutTypeSelector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_tracking);

        //enables back button on app icon
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        //initialize
        workouts = new ArrayList<BaseWorkout>();
        setupSpinners();

        //for testing purposes only
        initTestWorkouts();

        //initialize workoutListView and its adapter
        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, workouts);
        workoutListView = (ListView)findViewById(R.id.lv_history);
        setHistoryListener();
        workoutListView.setAdapter(workoutAdapter);

    }

    /**
     * Initializes workouts for testing
     */
    private void initTestWorkouts() {

        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);

        //insertTestWorkouts(db);

        workouts.addAll(db.selectAllWorkouts());
    }

    private void insertTestWorkouts(FitnessDatabaseHelper db) {
        StrengthWorkout s = new StrengthWorkout().initTestValues();
        CardioWorkout ca = new CardioWorkout().initTestValues();
        CustomWorkout cu = new CustomWorkout().initTestValues();

        db.insertWorkout(s);
        db.insertWorkout(ca);
        db.insertWorkout(cu);

        db.insertWorkout(s);
        db.insertWorkout(ca);
        db.insertWorkout(cu);

        db.insertWorkout(s);
        db.insertWorkout(ca);
        db.insertWorkout(cu);
    }


    /**
     * Sets up spinners in the application
     *
     */
    private void setupSpinners() {
        dateSelector = (Spinner)findViewById(R.id.spin_dateSelector);

        ArrayList<String> strings = new ArrayList<String>();

        strings.add("Today");
        strings.add("This week");
        strings.add("This month");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        dateSelector.setAdapter(adapter);


        workoutTypeSelector = (Spinner)findViewById(R.id.spin_workoutTypes);
        ArrayList<String> workoutTypes = new ArrayList<String>();

        workoutTypes.add("All");
        workoutTypes.add("Strength");
        workoutTypes.add("Cardio");
        workoutTypes.add("Custom");

        ArrayAdapter<String> workoutTypesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, workoutTypes);
        workoutTypeSelector.setAdapter(workoutTypesAdapter);


        setSpinnerListeners();
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

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Handles the click event for a new workout
     * @param v
     */
    public void onNewWorkoutClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater li = LayoutInflater.from(this);

        View root = li.inflate(R.layout.dialog_choose_workout_type, null);

        //get buttons from custom dialog
        Button strengthWorkout = (Button)root.findViewById(R.id.btn_choose_strength);
        Button cardioWorkout = (Button)root.findViewById(R.id.btn_choose_cardio);
        Button customWorkout = (Button)root.findViewById(R.id.btn_choose_custom);

        strengthWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWorkoutActivity(StrengthWorkoutActivity.class);
            }
        });

        cardioWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWorkoutActivity(CardioWorkoutActivity.class);
            }
        });

        customWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWorkoutActivity(CustomWorkoutActivity.class);
            }
        });

        builder.setView(root);

        builder.setTitle("Choose workout type");

        builder.create().show();
    }


    /**
     * Redirects the user to a specified workout activity.
     * @param activity
     */
    private void goToWorkoutActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }


    /**
     * Initialize the listener for the history item click
     */
    private void setHistoryListener() {
        workoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BaseWorkout workout = (BaseWorkout)adapterView.getItemAtPosition(i);

                //Safely cast workout object
                if(workout instanceof StrengthWorkout) {
                    workout = (StrengthWorkout)workout;
                } else if(workout instanceof CardioWorkout) {
                    workout = (CardioWorkout)workout;
                } else if(workout instanceof CustomWorkout) {
                    workout = (CustomWorkout)workout;
                } else {

                }

                Log.d("Item clicked", "");
                popupViewWorkout(workout);

            }
        });
    }


    private void setSpinnerListeners() {
        workoutTypeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = (int)l;
                switch(pos) {
                    case 0:
                        showAllWorkouts();
                        break;
                    case 1:
                        showStrengthWorkouts();
                        break;
                    case 2:
                        showCardioWorkouts();
                        break;
                    case 3:
                        showCustomWorkouts();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    /**
     * Pops up when the user selects a workout from the ListView
     * @param workout
     */
    public void popupViewWorkout(BaseWorkout workout) {
        workout.setContext(this);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View root = inflater.inflate(R.layout.dialog_view_workout, null);

        TextView titleTextView = (TextView)root.findViewById(R.id.tv_view_workout_title);
        TextView descriptionTextView = (TextView)root.findViewById(R.id.tv_view_workout_description);

        titleTextView.setText(workout.getDisplayName());
        descriptionTextView.setText(workout.toString());

        dialog.setTitle("View Workout");
        dialog.setView(root);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //don't do anything
            }
        });

        final int id = workout.getId();
        final String type = workout.getClass().getName();
        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteWorkout(id, type);
            }
        });

        dialog.create().show();


    }


    /**
     * Dialog for workout deletion popup
     * @param id
     * @param type
     */
    public void deleteWorkout(final int id, final String type) {
        AlertDialog.Builder delete = new AlertDialog.Builder(this);
        delete.setTitle("Delete");
        delete.setMessage("Are you sure you want to delete this workout?");
        delete.setNegativeButton("Cancel", null);
        delete.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(type.contains("StrengthWorkout")) {
                    dbDeleteStrength(id);
                } else if(type.contains("CardioWorkout")) {
                    dbDeleteCardio(id);
                } else if(type.contains("CustomWorkout")) {
                    dbDeleteCustom(id);
                }

            }
        });

        delete.create().show();
    }


    /**
     * Helper method that deletes a cardio workout from the database
     * @param id
     */
    private void dbDeleteCardio(int id) {
        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);
        db.deleteCardioWorkout(id);

        for(BaseWorkout workout : workouts) {
            if(workout.getId() == id && workout instanceof CardioWorkout) {
                workoutAdapter.remove(workout);
                //remove it from the list as well
                workouts.remove(workout);
                workoutAdapter.notifyDataSetChanged();
                break;
            }
        }
    }


    /**
     * Helper method that deletes a cardio workout from the database
     * @param id
     */
    private void dbDeleteCustom(int id) {
        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);
        db.deleteCustomWorkout(id);

        for(BaseWorkout workout : workouts) {
            if(workout.getId() == id && workout instanceof CustomWorkout) {
                workoutAdapter.remove(workout);
                workouts.remove(workout);
                workoutAdapter.notifyDataSetChanged();
                break;
            }
        }
    }


    /**
     * Helper method that deletes a custom workout from the database
     * @param id
     */
    private void dbDeleteStrength(int id) {
        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);
        db.deleteStrengthWorkout(id);

        for(BaseWorkout workout : workouts) {
            if(workout.getId() == id && workout instanceof StrengthWorkout) {
                workoutAdapter.remove(workout);
                workouts.remove(workout);
                workoutAdapter.notifyDataSetChanged();
                break;
            }
        }
    }


    /**
     * Updates the display adapter to show only strength workouts
     */
    private void showStrengthWorkouts() {
        ArrayList<BaseWorkout> strengthWorkouts = new ArrayList<BaseWorkout>();
        for(BaseWorkout workout : workouts) {
            if(workout instanceof StrengthWorkout) {
                strengthWorkouts.add(workout);
            }
        }

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, strengthWorkouts);
        workoutListView.setAdapter(workoutAdapter);
    }

    /**
     * Updates the display adapter to show only cardio workouts
     */
    private void showCardioWorkouts() {
        ArrayList<BaseWorkout> cardioWorkouts = new ArrayList<BaseWorkout>();
        for(BaseWorkout workout : workouts) {
            if(workout instanceof CardioWorkout) {
                cardioWorkouts.add(workout);
            }
        }

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, cardioWorkouts);
        workoutListView.setAdapter(workoutAdapter);
    }

    /**
     * Updates the display adapter to show only custom workouts
     */
    private void showCustomWorkouts() {

        ArrayList<BaseWorkout> customWorkouts = new ArrayList<BaseWorkout>();
        for(BaseWorkout workout : workouts) {
            if(workout instanceof CustomWorkout) {
                customWorkouts.add(workout);
            }
        }

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, customWorkouts);
        workoutListView.setAdapter(workoutAdapter);
    }


    private void showAllWorkouts() {
        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, workouts);
        workoutListView.setAdapter(workoutAdapter);
    }
}
