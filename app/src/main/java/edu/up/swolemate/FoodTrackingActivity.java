package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class FoodTrackingActivity extends Activity implements OnClickListener, AdapterView.OnItemClickListener {
    AutoCompleteTextView foodAutoEditText;
    EditText srvSizeEditText;
    EditText calEditText;
    EditText fatEditText;
    EditText carbsEditText;
    EditText proteinEditText;
    EditText servingsEditText;
    ArrayAdapter arrayAdapter;
    ArrayList<String> names;
    Button saveButton;
    Button delPresetButton;


    private boolean alertUnsaved(String foodName){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Unsaved information");
        alertBuilder.setMessage("You have unsaved information. Are you sure you want to leave this page?");
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_tracking_activity);

        /*
         * This section creates an array adapter, which populates the AutoCompleteEditText
         * with the value from the FoodPresets
         */
        foodAutoEditText = (AutoCompleteTextView)findViewById(R.id.foodAutoEditText);
        FoodPresets presets = new FoodPresets();
        names = presets.getFoodTypes();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names);
        foodAutoEditText.setAdapter(arrayAdapter);
        foodAutoEditText.setOnItemClickListener(this);

        //initialize all of the EditTexts
        srvSizeEditText = (EditText)findViewById(R.id.servingSizeEditText);
        calEditText = (EditText)findViewById(R.id.caloriesEditText);
        fatEditText = (EditText)findViewById(R.id.fatEditText);
        carbsEditText = (EditText)findViewById(R.id.carbsEditText);
        proteinEditText = (EditText)findViewById(R.id.proteinEditText);
        servingsEditText = (EditText)findViewById(R.id.servingsEditText);

        //initialize the buttons and set the onClickListener
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        delPresetButton = (Button)findViewById(R.id.deletePresetButton);
        delPresetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.saveButton){

        }
        else if(v.getId() == R.id.deletePresetButton){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg4){
        FoodPresets presets = new FoodPresets();
        //get the selected Food Item
        FoodItem food = presets.foodPresets.get(position);
        //set the text fields
        srvSizeEditText.setText("" + food.getServingSize() + "oz.");
        calEditText.setText("" + food.getCalories());
        fatEditText.setText("" + food.getFat());
        carbsEditText.setText("" + food.getCarbs());
        proteinEditText.setText("" + food.getProtein());
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
            //CHECK IF THERE IS UNENTERED INFORMATION HERE
        }

        return super.onOptionsItemSelected(item);
    }
}
