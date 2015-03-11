package edu.up.swolemate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        //get the user info TextViews
        TextView nameView = (TextView)findViewById(R.id.userNameText);
        TextView heightView = (TextView)findViewById(R.id.userHeight);
        TextView weightView = (TextView)findViewById(R.id.userWeight);
        //get the user's stored information
        SharedPreferences settings = getSharedPreferences("user_settings", 0);
        //display the user's information
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
}
