package edu.up.swolemate;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
        //final EditText heightEdit = (EditText)findViewById(R.id.edit_height);
       // final EditText weightEdit = (EditText)findViewById(R.id.edit_weight);

        alertBuilder.setView(profileEditView);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                SharedPreferences.Editor editor = settings.edit();
                //ProfileActivity profile = new ProfileActivity();
                System.out.println(nameEdit.getText().toString());
                editor.putString("name", nameEdit.getText().toString());
                if(!nameEdit.getText().toString().equals("")) {
                    displayName.setText(nameEdit.getText().toString());
                }
                if(!heightEdit.getText().toString().equals("")) {
                    displayHeight.setText(heightEdit.getText().toString());
                }
                if(!weightEdit.getText().toString().equals("")) {
                    displayWeight.setText(weightEdit.getText().toString());
                }
            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.edit_profile){
System.out.println("EDIT PROFILE CLICKED");
            alertEditProfile();
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
        startActivity(intent);
    }
}
