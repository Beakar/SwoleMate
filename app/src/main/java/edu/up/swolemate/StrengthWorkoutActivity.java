package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


public class StrengthWorkoutActivity extends Activity  implements View.OnClickListener {

    //    #######################################################################################
//    DELETE THESE EVENTUALLY, BUT THEY'RE HELPFUL FOR NOW
//    http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_inputtype
//    http://www.codelearn.org/android-tutorial/android-listview
//    #######################################################################################
    Button createExerciseButton;
    Button finishButton;
    ExerciseDialogFragment createDialog;
    FragmentManager fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_strength_workout);
        super.onCreate(savedInstanceState);
        fragManager = getFragmentManager();
        if (fragManager == null){
            Log.e("Logged", "FRAG MANAGER IS NULL");
        }
        createDialog = new ExerciseDialogFragment();
        createExerciseButton = (Button)findViewById(R.id.newStrengthExercise);
        finishButton = (Button)findViewById(R.id.finishButton1);
        finishButton.setOnClickListener(this);
        createExerciseButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.finishButton1){
            Log.e("Logged Button pressed","finish");

        }
        if(v.getId() == R.id.newStrengthExercise){
            Log.e("logged Button pressed","Create new str exercise");
            createDialog.show(fragManager,"Create new exercise");
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

        return super.onOptionsItemSelected(item);
    }

    public static class ExerciseDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // set the view
            builder.setView(inflater.inflate(R.layout.list_item_strength_exercise_sets, null))
                    //buttons
                    .setPositiveButton("placeholder 2", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .setNegativeButton("placeholder 1", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }

        public void showDialog(){
            //this.show(getFragmentManager(),"exerciseDialogFragment");
        }
    }

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
