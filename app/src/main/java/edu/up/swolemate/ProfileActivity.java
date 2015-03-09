package edu.up.swolemate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class ProfileActivity extends Activity {

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
        TextView nameView = (TextView)findViewById(R.id.userNameText);
        TextView heightView = (TextView)findViewById(R.id.userHeight);
        TextView weightView = (TextView)findViewById(R.id.userWeight);

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
