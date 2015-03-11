package edu.up.swolemate;

import android.app.Activity;
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

import java.util.Hashtable;
import java.util.List;

public class FoodTrackingActivity extends Activity implements OnClickListener, AdapterView.OnItemClickListener {
    AutoCompleteTextView foodAutoEditText;
    Spinner foodSpinner;
    EditText srvSizeEditText;
    EditText calEditText;
    EditText fatEditText;
    EditText carbsEditText;
    EditText proteinEditText;
    EditText servingsEditText;
    ArrayAdapter arrayAdapter;
    List<String> names;
    Button saveButton;
    Button delPresetButton;

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
        if(v.getId() == R.id.foodAutoEditText && !foodAutoEditText.getText().toString().equals("")){
            FoodPresets presets = new FoodPresets();
            presets.initPresetsTable();
            Hashtable<String, FoodItem> table = presets.getPresetsTable();
            FoodItem food = table.get(foodAutoEditText.getText().toString());
            srvSizeEditText.setText("" + food.getServingSize());
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
        FoodItem food = presets.foodPresets.get(position);
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

        return super.onOptionsItemSelected(item);
    }
}
