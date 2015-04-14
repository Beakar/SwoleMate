package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity{

    private Button createWorkoutButton;
    private Button addFoodItemButton;
    private Button viewWorkoutHistoryButton;
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences settings = getSharedPreferences("user_settings", 0);
        TextView greeting = (TextView)findViewById(R.id.userGreeting);
        greeting.setText(settings.getString("greeting", "Hi, user"));
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
        else if(id == R.id.profile){
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * OnClick method for history button click
     * @param v
     */
    public void onViewWorkoutHistoryClick(View v) {
        Intent intent = new Intent(this, HistoryTrackingActivity.class);
        intent.putExtra("display_mode", HistoryTrackingActivity.DISPLAY_WORKOUTS_ALL);

        startActivity(intent);
    }

    public void onViewFoodHistoryClick(View v) {
        Intent intent = new Intent (this, HistoryTrackingActivity.class);
        intent.putExtra("display_mode", HistoryTrackingActivity.DISPLAY_MEALS);
        startActivity(intent);
    }

    /**
     * Fired when user selects to create a new workout
     * @param v
     */
    public void onCreateWorkoutClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater li = LayoutInflater.from(this);

        View root = li.inflate(R.layout.dialog_choose_workout_type, null);

        //get buttons from custom dialog
        Button strengthWorkout = (Button)root.findViewById(R.id.btn_choose_strength);
        Button cardioWorkout = (Button)root.findViewById(R.id.btn_choose_cardio);
        Button customWorkout = (Button)root.findViewById(R.id.btn_choose_custom);

        strengthWorkout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWorkoutActivity(StrengthWorkoutActivity.class);
            }
        });

        cardioWorkout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWorkoutActivity(CardioWorkoutActivity.class);
            }
        });

        customWorkout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWorkoutActivity(CustomWorkoutActivity.class);
            }
        });

        builder.setView(root);

        builder.create().show();

    }

    public void onAddFoodClick(View v) {
        Intent intent = new Intent(this, MealEntryActivity.class);
        startActivity(intent);
    }

    public void onViewProfileClick(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Takes the user to a workout activity
     */
    private void goToWorkoutActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
