package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 2/2/2015.
 */
public class FirstTimeActivity extends Activity implements OnClickListener {

    private EditText nameEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private Button createButton;

    /*
    * The user's name
    * */
    private String name;

    /*
    * The user's height
    * */
    private String height;

    /*
    * The user's weight
    * */
    private String weight;


    /**
     * alertName()
     *
     * Description: Alerts the user that they must enter their name.
     */
    private void alertName(){
        //create the builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Enter name");
        alertBuilder.setMessage("In order to use SwoleMate, you must enter your name.");
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


    /**
     * alertEmptyHeightWeight()
     *
     * Description: Alerts the user they entered the weight in the wrong format.
     */
    private void alertEmptyHeightWeight(final String name, final String heightText, final String weightText){
        //create the builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Height/weight not entered");
        alertBuilder.setMessage("Your height or weight has not been entered. You may enter this " +
                "information at a later time on the profile page.\n\nDo you wish to proceed?");

        //make the "OK" button
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                saveUserInput(name, heightText, weightText);
                Intent myIntent = new Intent(FirstTimeActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        //make the "Cancel" button
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });

        //create the alert
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    /**
     * alertProfile()
     *
     * Description: Alerts the user they cannot view their profile until all required information
     * has been entered.
     */
    private void alertProfile(){
        //create the builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Please finish entering information");
        alertBuilder.setMessage("To view your profile, you must first enter and save your name.");

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
     * checkHeightFormat()
     *
     * Description: Checks the height input against a regex and reports its success.
     * @param height -- The user-entered height
     * @return success/failure
     */
    private boolean checkHeightFormat(String height){
        //regex: 1-2 digits, any whitespace, '-', any whitespace, 1-2 digits.
        Pattern regex = Pattern.compile("\\d{1,2}\\s*\\-\\s*\\d{1,2}");
        Matcher matcher = regex.matcher(height.trim());
        if(!height.equals("")){
            return matcher.matches();
        }
        //The user is allowed to enter an empty height
        return true;
    }


    /**
     * checkWeightFormat
     *
     * Description: Checks the weight input against a regex and reports its success.
     * @param weight -- The user-entered weight.
     * @return success/failure
     */
    private boolean checkWeightFormat(String weight){
        //regex: 1-3 digits
        Pattern regex = Pattern.compile("\\d{1,3}");
        Matcher matcher = regex.matcher(weight.trim());
        if(!weight.equals("")){
            return matcher.matches();
        }
        //the user is allowed to enter an empty weight
        return true;
    }


    @Override
    public void onClick(View view) {
        String nameText = nameEditText.getText().toString().trim();
        String heightText = heightEditText.getText().toString().trim();
        String weightText = weightEditText.getText().toString().trim();

        if(view.getId() == R.id.createButton){
            //The name field is empty
            if(nameText.equals("")){
                alertName();
            }
            else {
                //The height is in the wrong format
                if (!checkHeightFormat(heightText)) {
                        alertHeightWrong();
                }
                //The weight is in the wrong format
                else if (!checkWeightFormat(weightText)) {
                        alertWeightWrong();
                }
                //if either the height or weight fields are empty, alert the
                //user and ask to proceed.
                else if(heightText.equals("") || weightText.equals("")) {
                    alertEmptyHeightWeight(nameText, heightText,weightText);
                }
                else {
                    saveUserInput(nameText, heightText, weightText);
                    Intent myIntent = new Intent(FirstTimeActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_activity);

        nameEditText = (EditText)findViewById(R.id.nameEntry);
        heightEditText = (EditText)findViewById(R.id.heightEntry);
        weightEditText = (EditText)findViewById(R.id.weightEntry);
        createButton = (Button)findViewById(R.id.createButton);
        createButton.setOnClickListener(this);
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
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.profile){
            alertProfile();
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveUserInput(String name, String height, String weight){
        boolean success = false;

        SharedPreferences settings = getSharedPreferences("user_settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", name);
        editor.putString("height", height);
        editor.putString("weight", weight);

        editor.commit();
    }
}
