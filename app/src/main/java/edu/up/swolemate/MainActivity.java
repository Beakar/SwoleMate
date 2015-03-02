package edu.up.swolemate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

    private Button createWorkoutButton;
    private Button addFoodItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createWorkoutButton = (Button)findViewById(R.id.createNewWorkoutButton);
        createWorkoutButton.setOnClickListener(this);
        addFoodItemButton = (Button)findViewById(R.id.addNewFoodButton);
        addFoodItemButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        if(v.getId() == R.id.createNewWorkoutButton){
            Intent myIntent = new Intent(this, CreateWorkoutActivity.class);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.addNewFoodButton){
            Intent myIntent = new Intent(this, FoodTrackingActivity.class);
            startActivity(myIntent);
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
        else if(id == R.id.profile){
            Intent myIntent = new Intent(this, ProfileActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
