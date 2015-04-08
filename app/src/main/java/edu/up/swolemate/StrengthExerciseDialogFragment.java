package edu.up.swolemate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
public class StrengthExerciseDialogFragment extends DialogFragment {
    ExpandableSetListAdapter setsListAdapter;
    ExpandableListView setsListView;
    LayoutInflater inflater;
    View view;
    Exercise currentExercise;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_create_new_exercise, null);
        final StrengthWorkoutActivity currentActivity = (StrengthWorkoutActivity) getActivity();

        StrengthWorkout currentWorkout = currentActivity.currentWorkout;
        currentExercise = new Exercise();
        //currentWorkout.dummyExercise();

        setsListView = (ExpandableListView) view.findViewById(R.id.sets_list);
        setsListAdapter = new ExpandableSetListAdapter(getActivity(),currentExercise, setsListView);
        setsListView.setAdapter(setsListAdapter);

        EditText exerciseName = (EditText) view.findViewById(R.id.exercise_name);
        exerciseName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    currentExercise.setName(s.toString());
                }
            }
        });

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
        Button saveButton = (Button) view.findViewById(R.id.save_new_strength_exercise_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(currentExercise.getName()==null){
                    currentExercise.setName("Unnamed Exercise");
                }

                currentActivity.addExerciseToWorkout(currentExercise);

                dismiss();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
