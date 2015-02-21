package edu.up.swolemate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Daniel on 2/2/2015.
 */
public class FirstTimeActivity extends Activity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_activity);

        nameEditText = (EditText)findViewById(R.id.nameEntry);
        heightEditText = (EditText)findViewById(R.id.heightEntry);
        weightEditText = (EditText)findViewById(R.id.weightEntry);
        createButton = (Button)findViewById(R.id.createButton);


        name = nameEditText.getText().toString();
System.out.println("name: " + name);
        height = heightEditText.getText().toString();
System.out.println("height: " + height);
        weight = weightEditText.getText().toString();
System.out.println("weight: " + weight);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.createButton){
            System.out.println("ASASDFASDFASDFASDFASDF");
        }
    }
}
