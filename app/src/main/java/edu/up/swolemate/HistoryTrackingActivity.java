package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HistoryTrackingActivity extends Activity {
    public static final int DISPLAY_WORKOUTS_ALL = 0;
    public static final int DISPLAY_WORKOUTS_STRENGTH = 1;
    public static final int DISPLAY_WORKOUTS_CARDIO = 2;
    public static final int DISPLAY_WORKOUTS_CUSTOM = 3;
    public static final int DISPLAY_MEALS = 4;

    private final boolean DEBUG = false;

    private int displayMode = DISPLAY_WORKOUTS_ALL;

    protected List<BaseWorkout> workouts;
    protected List<FoodMeal> meals;

    protected WorkoutAdapter workoutAdapter;
    protected FoodAdapter foodAdapter;

    protected ListView historyListView;
    protected Button viewToggleButton;
    protected Button sortByWorkout;

    protected boolean isWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_tracking);

        //Possibly redirected from other activities, this provides
        //the capability of starting the activity in different display modes
        if(getIntent().getExtras() != null)
            displayMode = getIntent().getExtras().getInt("display_mode", 0);

        //enables back button on app icon
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //initialize
        workouts = new ArrayList<BaseWorkout>();
        meals = new ArrayList<FoodMeal>();

        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);
        workouts.addAll(db.selectAllWorkouts());
        Collections.sort(workouts);
        meals.addAll(db.selectAllMeals());
        Collections.sort(meals);

        //for testing purposes only
        initTestValues();

        //initialize historyListView and its adapters
        foodAdapter = new FoodAdapter(this, R.layout.list_item_food, meals);
        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, workouts);

        sortByWorkout = (Button)findViewById(R.id.btn_workout_type);
        viewToggleButton = (Button)findViewById(R.id.toggle_view);
        historyListView = (ListView)findViewById(R.id.lv_history);

        setHistoryListener();

        //sets the display based on what's passed in the savedinstancestate
        switch(displayMode) {
            case DISPLAY_WORKOUTS_ALL:
                isWorkout = true;
                showAllWorkouts();
                break;
            case DISPLAY_WORKOUTS_STRENGTH:
                isWorkout = true;
                showStrengthWorkouts();
                break;
            case DISPLAY_WORKOUTS_CARDIO:
                isWorkout = true;
                showCardioWorkouts();
                break;
            case DISPLAY_WORKOUTS_CUSTOM:
                isWorkout = true;
                showCustomWorkouts();
                break;
            case DISPLAY_MEALS:
                isWorkout = false;
                showAllFood();
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

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




    /*-------------------------------------------------------------
     * Initialization helpers
     --------------------------------------------------------------*/

    /**
     * Initializes workouts for testing
     */
    private void initTestValues() {

        FitnessDatabaseHelper db = new FitnessDatabaseHelper(this);

        if(DEBUG) {
            insertTestMeals(db);
            insertTestWorkouts(db);
        }
    }

    /**
     * Inserts test values for workouts
     * @param db
     */
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
     * Inserts test values for meals
     * @param db
     */
    private void insertTestMeals(FitnessDatabaseHelper db) {
        FoodMeal meal = new FoodMeal().initTestValues();

        db.insertMeal(meal);
        db.insertMeal(meal);
        db.insertMeal(meal);
    }




    /*-------------------------------------------------------------
     * Database helpers
     --------------------------------------------------------------*/

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




    /*-------------------------------------------------------------
     * Sorting helpers
     --------------------------------------------------------------*/

    /**
     * Updates the display adapter to show only strength workouts
     */
    private void showStrengthWorkouts() {
        ArrayList<BaseWorkout> strengthWorkouts = getStrengthWorkouts();

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, strengthWorkouts);
        historyListView.setAdapter(workoutAdapter);
        displayMode = DISPLAY_WORKOUTS_STRENGTH;
    }


    /**
     * Updates the display adapter to show only cardio workouts
     */
    private void showCardioWorkouts() {
        ArrayList<BaseWorkout> cardioWorkouts = getCardioWorkouts();

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, cardioWorkouts);
        historyListView.setAdapter(workoutAdapter);
        displayMode = DISPLAY_WORKOUTS_CARDIO;

    }


    /**
     * Updates the display adapter to show only custom workouts
     */
    private void showCustomWorkouts() {

        ArrayList<BaseWorkout> customWorkouts = getCustomWorkouts();

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, customWorkouts);
        historyListView.setAdapter(workoutAdapter);
        displayMode = DISPLAY_WORKOUTS_CUSTOM;
    }


    /**
     * Shows all workouts in list display
     */
    private void showAllWorkouts() {
        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, workouts);
        historyListView.setAdapter(workoutAdapter);

        viewToggleButton.setBackgroundColor(Color.parseColor("#000055"));
        viewToggleButton.setText("workouts");
        sortByWorkout.setClickable(true);
        displayMode = DISPLAY_WORKOUTS_ALL;
    }


    /**
     * Shows all food items in list display
     */
    private void showAllFood() {
        foodAdapter = new FoodAdapter(this, R.layout.list_item_food, meals);
        historyListView.setAdapter(foodAdapter);

        viewToggleButton.setBackgroundColor(Color.parseColor("#005500"));
        viewToggleButton.setText("food");
        sortByWorkout.setClickable(false);
        displayMode = DISPLAY_MEALS;
    }


    /**
     * Displays workouts occurring after a specified start date
     * @param start
     */
    private void showWorkoutsFrom(int start) {
        ArrayList<BaseWorkout> newWorkouts = null;

        switch(displayMode) {
            case DISPLAY_WORKOUTS_ALL:
                newWorkouts = (ArrayList<BaseWorkout>)workouts;
                break;
            case DISPLAY_WORKOUTS_STRENGTH:
                newWorkouts = getStrengthWorkouts();
                break;
            case DISPLAY_WORKOUTS_CARDIO:
                newWorkouts = getCardioWorkouts();
                break;
            case DISPLAY_WORKOUTS_CUSTOM:
                newWorkouts = getCustomWorkouts();
                break;
            default:
                newWorkouts = (ArrayList<BaseWorkout>)workouts;
                break;
        }

        ArrayList<BaseWorkout> sortedWorkouts = new ArrayList<BaseWorkout>();

        for(BaseWorkout workout : newWorkouts) {
            if(workout.getDateCompleted() >= start) {
                sortedWorkouts.add(workout);
            }
        }

        workoutAdapter = new WorkoutAdapter(this, R.layout.list_item_workout, sortedWorkouts);
        historyListView.setAdapter(workoutAdapter);
    }


    /**
     * Shows food eaten from a specified start date
     * @param start
     */
    private void showFoodFrom(int start) {
        //invalid case
        if(displayMode != DISPLAY_MEALS) {
            return;
        }

        ArrayList<FoodMeal> sortedMeals = new ArrayList<FoodMeal>();

        for(FoodMeal meal : meals) {
            if(meal.getDateCompleted() >= start) {
                sortedMeals.add(meal);
            }
        }

        foodAdapter = new FoodAdapter(this, R.layout.list_item_food, sortedMeals);
    }


    /**
     * Shows workouts between a start and an end date
     * @param start
     * @param end
     * @throws Exception
     */
    private void showWorkoutsFrom(int start, int end) {
    }


    /**
     * Shows the correct type of workout occurring after the specified start date
     * @param start
     */
    private void showDataFrom(int start) {
        if(isWorkout) {
            showWorkoutsFrom(start);
        } else {
            showFoodFrom(start);
        }
    }


    /**
     * Gets a list of strength workouts from the current list of workouts
     * @return
     */
    private ArrayList<BaseWorkout> getStrengthWorkouts() {
        ArrayList<BaseWorkout> sWorkouts = new ArrayList<BaseWorkout>();

        for(BaseWorkout workout : this.workouts) {
            if(workout instanceof StrengthWorkout) {
                sWorkouts.add(workout);
            }
        }
        return sWorkouts;
    }


    /**
     * Gets a list of cardio workouts from the current list of workouts
     * @return
     */
    private ArrayList<BaseWorkout> getCardioWorkouts() {
        ArrayList<BaseWorkout> cWorkouts = new ArrayList<BaseWorkout>();

        for(BaseWorkout workout : this.workouts) {
            if(workout instanceof CardioWorkout) {
                cWorkouts.add(workout);
            }
        }

        return cWorkouts;
    }


    /**
     * Gets a list of custom workouts from the current list of workouts
     * @return
     */
    private ArrayList<BaseWorkout> getCustomWorkouts() {
        ArrayList<BaseWorkout> cWorkouts = new ArrayList<BaseWorkout>();

        for(BaseWorkout workout : this.workouts) {
            if(workout instanceof CustomWorkout) {
                cWorkouts.add(workout);
            }
        }

        return cWorkouts;
    }


    /*-------------------------------------------------------------
     * Click behavior helpers
     --------------------------------------------------------------*/

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
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(isWorkout) {
                    BaseWorkout workout = (BaseWorkout) adapterView.getItemAtPosition(i);

                    //Safely cast workout object
                    if (workout instanceof StrengthWorkout) {
                        workout = (StrengthWorkout) workout;
                    } else if (workout instanceof CardioWorkout) {
                        workout = (CardioWorkout) workout;
                    } else if (workout instanceof CustomWorkout) {
                        workout = (CustomWorkout) workout;
                    } else {

                    }

                    Log.d("Item clicked", "");
                    popupViewWorkout(workout);
                } else {
                    FoodMeal meal = (FoodMeal)adapterView.getItemAtPosition(i);

                    Log.d("Meal clicked", "");
                    popupViewMeal(meal);
                }


            }
        });
    }


    /**
     * Pops up when the user selects a workout from the ListView
     * @param workout
     */
    private void popupViewWorkout(BaseWorkout workout) {
        workout.setContext(this);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View root = inflater.inflate(R.layout.dialog_view_workout, null);

        TextView titleTextView = (TextView)root.findViewById(R.id.tv_view_workout_title);
        TextView descriptionTextView = (TextView)root.findViewById(R.id.tv_view_workout_description);

        titleTextView.setText(workout.getDisplayName());
        descriptionTextView.setText(workout.toString());

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


    private void popupViewMeal(FoodMeal meal) {

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




    /*-------------------------------------------------------------
     * Click handlers
     --------------------------------------------------------------*/

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

        builder.create().show();
    }


    public void onNewMealClick(View v) {
        Intent intent = new Intent(this, MealEntryActivity.class);
        startActivity(intent);
    }


    public void onSortClick(View v) {
        Button clicked = (Button)v;

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View root = inflater.inflate(R.layout.dialog_history_sort, null);

        Button all = (Button)root.findViewById(R.id.btn_sort_all);
        Button day = (Button)root.findViewById(R.id.btn_sort_today);
        Button week = (Button)root.findViewById(R.id.btn_sort_week);
        Button month = (Button)root.findViewById(R.id.btn_sort_month);

        dialog.setView(root);

        Dialog dg = dialog.create();

        WindowManager.LayoutParams lp = dg.getWindow().getAttributes();

        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        //change
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.y = (int)(clicked.getY() + clicked.getHeight() + getActionBar().getHeight());

        dg.show();
        dg.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);



        final Dialog d = dg;

        //each of these buttons should change the display
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDataFrom(0);
                d.dismiss();
            }
        });

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int today = (int)((System.currentTimeMillis() / 1000) - 86400);
                showDataFrom(today);
                d.dismiss();
            }
        });

        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int thisWeek = (int)((System.currentTimeMillis() / 1000) - 604800);
                showDataFrom(thisWeek);
                d.dismiss();
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int thisMonth = (int)((System.currentTimeMillis() / 1000) - 2629740);
                showDataFrom(thisMonth);
                d.dismiss();
            }
        });

        d.show();
    }


    public void onWorkoutTypeClick(View v) {
        Button clicked = (Button)v;

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View root = inflater.inflate(R.layout.dialog_workout_type_sort, null);

        Button all = (Button)root.findViewById(R.id.btn_sort_workout_all);
        Button strength = (Button)root.findViewById(R.id.btn_sort_workout_strength);
        Button cardio = (Button)root.findViewById(R.id.btn_sort_workout_cardio);
        Button custom = (Button)root.findViewById(R.id.btn_sort_workout_custom);

        dialog.setView(root);

        Dialog dg = dialog.create();

        WindowManager.LayoutParams lp = dg.getWindow().getAttributes();

        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        //change
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.y = (int)(clicked.getY() + clicked.getHeight() + getActionBar().getHeight());

        dg.show();
        dg.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);



        final Dialog d = dg;

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllWorkouts();
                d.dismiss();
            }
        });

        strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStrengthWorkouts();
                d.dismiss();
            }
        });

        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCardioWorkouts();
                d.dismiss();
            }
        });

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomWorkouts();
                d.dismiss();
            }
        });

        d.show();
    }


    public void onToggleViewClick(View v) {


        if(isWorkout) {
            showAllFood();

        } else {
            showAllWorkouts();
        }
        isWorkout = !isWorkout;
    }
}
