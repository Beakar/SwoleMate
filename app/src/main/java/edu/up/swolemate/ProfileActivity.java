package edu.up.swolemate;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


public class ProfileActivity extends Activity implements View.OnClickListener{
    protected View editProfile;
    //protected final View nameView = findViewById(R.id.userNameText);
    //protected final View heightView = findViewById(R.id.userHeight);
    //protected final View weightView = findViewById(R.id.userWeight);
    private SharedPreferences settings;
   // protected EditText nameEdit;

    private void alertEditProfile(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Edit profile");
        LayoutInflater inflater = LayoutInflater.from(this);
        final View profileEditView = inflater.inflate(R.layout.dialog_edit_profile, null);

        final EditText nameEdit = (EditText)profileEditView.findViewById(R.id.edit_name);
        final EditText heightEdit = (EditText)profileEditView.findViewById(R.id.edit_height);
        final EditText weightEdit = (EditText)profileEditView.findViewById(R.id.edit_weight);

        final FontTextView displayName = (FontTextView)findViewById(R.id.userNameText);
        final FontTextView displayHeight = (FontTextView)findViewById(R.id.userHeight);
        final FontTextView displayWeight = (FontTextView)findViewById(R.id.userWeight);

        final SharedPreferences settings = getSharedPreferences("user_settings", 0);
        final SharedPreferences.Editor editor = settings.edit();

        final FirstTimeActivity fta = new FirstTimeActivity();
        final ProfileActivity profile = new ProfileActivity();

        alertBuilder.setView(profileEditView);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                String name = nameEdit.getText().toString();
                String height = heightEdit.getText().toString();
                String weight = weightEdit.getText().toString();

                if(!name.equals("")) {
                    displayName.setText(name);
                    editor.putString("name", name);
                    editor.putString("greeting", "Hello, " + name);
                    editor.commit();
                }
                if(!height.equals("")) {
                    if(fta.checkHeightFormat(height)) {
                        displayHeight.setText(height);
                        editor.putString("height", height);
                        editor.commit();
                    }
                    else{
                        alertHeightWrong();
                    }
                }
                if(!weight.equals("")) {
                    if(fta.checkWeightFormat(weight)) {
                        displayWeight.setText(weightEdit.getText().toString());
                        editor.putString("weight", weightEdit.getText().toString());
                        editor.commit();
                    }
                    else{
                        alertWeightWrong();
                    }
                }
            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    /**
     * alertHeightWrong()
     *
     * Description: Alerts the user they entered the height in the wrong format.
     */
    private void alertHeightWrong(){
        //create the builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Incorrect height format.");
        alertBuilder.setMessage("Your height must be entered in the following format: (ie. 5-10");

        //make the "OK" button
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });

        //create the alert
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    /**
     * alertWeightWrong()
     *
     * Description: Alerts the user they entered the weight in the wrong format.
     */
    private void alertWeightWrong(){
        //create the builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Incorrect weight format.");
        alertBuilder.setMessage("Your weight must be entered in the following format: (ie. 180");

        //make the "OK" button
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });

        //create the alert
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.edit_profile){
            alertEditProfile();
            SharedPreferences DELETE_ME = getSharedPreferences("user_settings", 0);
        }
    }

    //changes the units stored in sharedpreferences
    protected CompoundButton.OnCheckedChangeListener unitChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton btnView, boolean on) {
            SharedPreferences settings = getSharedPreferences("user_settings", 0);
            SharedPreferences.Editor editor = settings.edit();

            if(on) {
                editor.putString("units", "metric");
            } else {
                editor.putString("units", "imperial");
            }

            editor.commit();
        }
    };

    /**
     * Switch for app units
     */
    Switch unitSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        //lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        settings = getSharedPreferences("user_settings", 0);
       // nameEdit = (EditText)findViewById(R.id.edit_name);
        TextView nameView = (TextView)findViewById(R.id.userNameText);
        TextView heightView = (TextView)findViewById(R.id.userHeight);
        TextView weightView = (TextView)findViewById(R.id.userWeight);
        editProfile = findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(this);

        unitSwitch = (Switch)findViewById(R.id.switch_unit);
        unitSwitch.setOnCheckedChangeListener(unitChangeListener);

        SharedPreferences settings = getSharedPreferences("user_settings", 0);

        if(settings.getString("units", "").equals("metric")) {
            unitSwitch.setChecked(true);
        }

        nameView.setText(settings.getString("name", ""));
        heightView.setText(settings.getString("height", ""));
        weightView.setText(settings.getString("weight", ""));
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
            return true;
        }
        else if(id == R.id.main_menu){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void onSeeMoreClick(View v) {
        Intent intent = new Intent(this, HistoryTrackingActivity.class);
        intent.putExtra("display_mode", HistoryTrackingActivity.DISPLAY_WORKOUTS_ALL);

        startActivity(intent);
    }
}
