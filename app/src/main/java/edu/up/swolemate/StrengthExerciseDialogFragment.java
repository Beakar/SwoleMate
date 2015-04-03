package edu.up.swolemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carl Lulay on 3/8/2015.
 */
public class StrengthExerciseDialogFragment extends DialogFragment implements OnClickListener, AdapterView.OnItemClickListener {
    ExpandableSetListAdapter setsListAdapter;
    ExpandableListView setsListView;
    LayoutInflater inflater;
    AutoCompleteTextView exSearchEditText;
    View view;
    Exercise currentExercise;
    ArrayAdapter arrayAdapter;

    public void onClick(View v){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_create_new_exercise, null);
        final StrengthWorkoutActivity currentActivity = (StrengthWorkoutActivity) getActivity();
        //final
        StrengthWorkout currentWorkout = currentActivity.currentWorkout;
        currentExercise = currentWorkout.dummyExercise();

        setsListView = (ExpandableListView) view.findViewById(R.id.sets_list);
        setsListAdapter = new ExpandableSetListAdapter(getActivity(),currentExercise);
        setsListView.setAdapter(setsListAdapter);

        ExercisePresets presets = new ExercisePresets();
        ArrayList<String> names = presets.getExerciseNames(presets.exercisePresets);

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, names);
        exSearchEditText = (AutoCompleteTextView)view.findViewById(R.id.exercise_search);
        exSearchEditText.setAdapter(arrayAdapter);
        exSearchEditText.setOnClickListener(this);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // set the view
        builder.setView(view);

        Button addSetButton = (Button) view.findViewById(R.id.new_set_button);
        addSetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentExercise.addSet(new ExerciseSubset(0,0));
                setsListAdapter.notifyDataSetChanged();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg4){
        ExercisePresets presets = new ExercisePresets();
        Exercise ex = presets.strengthPresets.get(position);
        exSearchEditText.setText(ex.displayName);
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.dialog_create_new_exercise, container, false);
        exSearchEditText = (EditText)v.findViewById(R.id.exercise_search);
        exSearchEditText.setOnClickListener(this);

        return v;
    }*/

}
