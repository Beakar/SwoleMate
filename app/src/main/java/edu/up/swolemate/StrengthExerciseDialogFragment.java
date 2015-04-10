package edu.up.swolemate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;

/**
 * Created by Carl Lulay on 3/8/2015.
 */
public class StrengthExerciseDialogFragment extends DialogFragment {
    ExpandableSetListAdapter setsListAdapter;
    ExpandableListView setsListView;
    LayoutInflater inflater;
    View view;
    Exercise currentExercise;
    AutoCompleteTextView exerciseNameView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_create_new_exercise, null);
        final StrengthWorkoutActivity currentActivity = (StrengthWorkoutActivity) getActivity();

        currentExercise = ((StrengthWorkoutActivity) getActivity()).selectedExercise;
        //currentWorkout.dummyExercise();

        setsListView = (ExpandableListView) view.findViewById(R.id.sets_list);
        setsListAdapter = new ExpandableSetListAdapter(getActivity(),currentExercise, setsListView);
        setsListView.setAdapter(setsListAdapter);

        ExercisePresets presets = new ExercisePresets();
        //get the names of all of the strength exercises
        ArrayList<String> names = presets.getExerciseNames(presets.strengthPresets);
        exerciseNameView = (AutoCompleteTextView)view.findViewById(R.id.exercise_name);
        //creates and then set the array adapter for the exercise names
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, names);
        exerciseNameView.setAdapter(arrayAdapter);
        //gets the name of the exercise in the text field and sets the
        //value of the text field and current exercise name to the chosen String.
        exerciseNameView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String exName = (String) parent.getItemAtPosition(position);
                exerciseNameView.setText(exName);
                currentExercise.setName(exName);
            }
        });

        exerciseNameView.addTextChangedListener(new TextWatcher() {
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

                boolean foundExerciseInWorkout = false;
                for (Exercise e : currentActivity.currentWorkout.exercises){
                    if(currentExercise.getName().equalsIgnoreCase(e.getName())){
                        e = currentExercise;
                        foundExerciseInWorkout = true;
                    }
                }
                if(!foundExerciseInWorkout){
                    currentActivity.addExerciseToWorkout(currentExercise);
                }

                dismiss();
            }
        });
        Button cancelButton = (Button) view.findViewById(R.id.cancel_new_exercise_dialog_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

/*
    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long arg4){
        {

        }
    }*/

}
