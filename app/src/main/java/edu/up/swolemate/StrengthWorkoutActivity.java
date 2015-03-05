package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


public class StrengthWorkoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setListAdapter setListAdapter = new setListAdapter();
//        ListView setList = (ListView)findViewById(R.id.strengthList);
//        setList.setAdapter(setListAdapter);
//        final Button button = (Button) findViewById(R.id.finishButton1);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Log.e("DERP","DERP");
//            }
//        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_workout);
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

        return super.onOptionsItemSelected(item);
    }

//    public static class createExerciseDialogFragment extends DialogFragment {
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the Builder class for convenient dialog construction
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setMessage("placeholder 1")
//                    .setPositiveButton("placeholder 2", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                        }
//                    })
//                    .setNegativeButton("placeholder 1", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//            // Create the AlertDialog object and return it
//            return builder.create();
//        }
//    }

//    public class setListAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            // TODO Auto-generated method stub
//            return 3; //will eventually replace with dynamic variable representing the number of sets
//        }
//
//        @Override
//        public Object getItem(int arg0) {
//            // TODO Auto-generated method stub
//            return null;
//        }
//
//        @Override
//        public long getItemId(int arg0) {
//            // TODO Auto-generated method stub
//            return arg0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            return null;
//        }
//
//    }

}
