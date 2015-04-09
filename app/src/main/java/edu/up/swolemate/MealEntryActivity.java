package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MealEntryActivity extends Activity implements OnClickListener{
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
    Button addFoodButton;
    FoodMeal currentMeal;
    FoodItem currentFood;
    MealListAdapter mealListAdapter;


    public void addFoodToMeal(FoodItem food){
        currentMeal.addFood(food);
        mealListAdapter.notifyDataSetChanged();;
        //dismiss();
    }


    public void alertAddFood(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Add new food item");
        LayoutInflater inflater = LayoutInflater.from(this);
        final View addFoodView = inflater.inflate(R.layout.dialog_add_food, null);
/*        final EditText srvSizeEdit = (EditText)addFoodView.findViewById(R.id.servingSizeEditText);
        final EditText calEdit = (EditText)addFoodView.findViewById(R.id.caloriesEditText);
        final EditText fatEdit = (EditText)addFoodView.findViewById(R.id.fatEditText);
        final EditText proteinEdit = (EditText)addFoodView.findViewById(R.id.proteinEditText);
        final EditText servingsEdit = (EditText)addFoodView.findViewById(R.id.servingsEditText);*/

        foodAutoEditText = (AutoCompleteTextView)addFoodView.findViewById(R.id.foodAutoEditText);
        FoodPresets presets = new FoodPresets();
        names = presets.getFoodTypes();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names);
        foodAutoEditText.setAdapter(arrayAdapter);

        foodAutoEditText.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg4){
                FoodPresets presets = new FoodPresets();
                //get the selected Food Item
                String item = (String)arg0.getItemAtPosition(position);
                currentFood = presets.foodPresetsTable.get(item);
                //set the text fields
                srvSizeEditText.setText("" + currentFood.getServingSize() + "oz.");
                calEditText.setText("" + currentFood.getCalories());
                fatEditText.setText("" + currentFood.getFat());
                carbsEditText.setText("" + currentFood.getCarbs());
                proteinEditText.setText("" + currentFood.getProtein());

                //addFoodToMeal(food);
            }
        });

        //initialize all of the EditTexts
        srvSizeEditText = (EditText)addFoodView.findViewById(R.id.servingSizeEditText);
        calEditText = (EditText)addFoodView.findViewById(R.id.caloriesEditText);
        fatEditText = (EditText)addFoodView.findViewById(R.id.fatEditText);
        carbsEditText = (EditText)addFoodView.findViewById(R.id.carbsEditText);
        proteinEditText = (EditText)addFoodView.findViewById(R.id.proteinEditText);
        servingsEditText = (EditText)addFoodView.findViewById(R.id.servingsEditText);

        //initialize the buttons and set the onClickListener
        saveButton = (Button)addFoodView.findViewById(R.id.saveButton);

        alertBuilder.setView(addFoodView);

        final AlertDialog alert = alertBuilder.create();
        alert.show();

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addFoodToMeal(currentFood);
                System.out.println("ADDED THE FOOD");
                alert.dismiss();
               // dismiss();
            }
        });
        delPresetButton = (Button)addFoodView.findViewById(R.id.deletePresetButton);
        delPresetButton.setOnClickListener(this);
    }


    /**
     * Alerts the user that they have unsaved information, and
     * asks if the user is sure that they want to leave the page.
     * @param foodName
     * @return
     */
    private boolean alertUnsaved(String foodName){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Unsaved information");
        alertBuilder.setMessage("You have unsaved information. Are you sure you want to leave this page?");
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_entry_temp);

        currentMeal = new FoodMeal();

        addFoodButton = (Button)findViewById(R.id.newFoodItemButton);
        addFoodButton.setOnClickListener(this);

        ListView mealList = (ListView)findViewById(R.id.mealListView);
        mealListAdapter = new MealListAdapter(this, currentMeal.foodItems);
        mealList.setAdapter(mealListAdapter);


        /*//lock the device in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        *//*
         * This section creates an array adapter, which populates the AutoCompleteEditText
         * with the value from the FoodPresets
         *//*
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
        delPresetButton.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.deletePresetButton){

        }
       /* else if(v.getId() == R.id.saveButton){
            alertAddFood();
        }*/
        else if(v.getId() == R.id.newFoodItemButton){
            alertAddFood();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    /*@Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg4){
        FoodPresets presets = new FoodPresets();
        //get the selected Food Item
        String item = (String)arg0.getItemAtPosition(position);
        FoodItem food = presets.foodPresetsTable.get(item);
        //set the text fields
        srvSizeEditText.setText("" + food.getServingSize() + "oz.");
        calEditText.setText("" + food.getCalories());
        fatEditText.setText("" + food.getFat());
        carbsEditText.setText("" + food.getCarbs());
        proteinEditText.setText("" + food.getProtein());

        addFoodToMeal(food);
    }*/

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


    public class MealListAdapter extends BaseAdapter {

        List<FoodItem> foodList;
        LayoutInflater inflater;
        Context context;


        public MealListAdapter(Context context, List<FoodItem> myList) {
            this.foodList = myList;
            this.context = context;
            inflater = LayoutInflater.from(this.context);        // only context can also be used
        }

        @Override
        public int getCount() {
            return foodList.size();
        }

        @Override
        public String getItem(int position) {
            return foodList.get(position).getFoodType();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_meal_foods, null);
            }
            TextView label = (TextView) convertView.findViewById(R.id.meal_food_list_item);
            Button removeButton = (Button) convertView.findViewById(R.id.remove_food_button);
            removeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    currentMeal.removeFood(getItem(position));
                    notifyDataSetChanged();
                }
            });
            label.setText(foodList.get(position).getFoodType());
            return convertView;
        }
    }
}
