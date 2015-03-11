package edu.up.swolemate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carl Lulay on 3/8/2015.
 */
public class StrengthExerciseDialogFragment extends DialogFragment {
    ExpandableSetListAdapter setsListAdapter;
    ExpandableListView setsListView;
    LayoutInflater inflater;
    View view;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_create_new_exercise, null);
        StrengthWorkoutActivity currentActivity = (StrengthWorkoutActivity) getActivity();
        StrengthWorkout current = currentActivity.currentWorkout;
        prepareListData();
        setsListView = (ExpandableListView) view.findViewById(R.id.sets_list);
        setsListAdapter = new ExpandableSetListAdapter(getActivity(), listDataHeader, listDataChild,current);
        setsListView.setAdapter(setsListAdapter);


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // set the view
        builder.setView(view)
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

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    public void init(Context context, ExpandableSetListAdapter mSetListAdapter){
//        if (view == null){
//            Log.e("log null", "view");
//        }
//        if (view.findViewById(R.id.set_list) == null){
//            Log.e("log null", "set list view");
//        }
        if (setsListView == null){
            Log.e("log null", "set list");
        }
        if (mSetListAdapter == null){
            Log.e("log null", "adapter");
        }
        //setListView.setAdapter(mSetListAdapter);
    }
}
