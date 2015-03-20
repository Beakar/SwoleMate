package edu.up.swolemate;

/**
 * Created by Carl Lulay on 3/8/2015.
 */
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ExpandableSetListAdapter extends BaseExpandableListAdapter {

    private Context context;

    Exercise currentExercise;

    public ExpandableSetListAdapter(Context mContext, Exercise mExercise) {
        this.context = mContext;
        currentExercise = mExercise;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
//        Log.e("Log method call", "getChild");
        return this.currentExercise.sets.get(childPosititon);
        //return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
//        Log.e("Log method call", "getChildID");
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_strength_exercise_sets, null);
        }

        EditText weightInput = (EditText) convertView.findViewById(R.id.weight_of_set_input);
        Log.e("double value: ","" + currentExercise.sets.get(groupPosition).getWeight());
//        if (currentExercise.sets.get(groupPosition).getWeight()!=null)
//        {
//            weightInput.setText("" + currentExercise.sets.get(groupPosition).getWeight());
//        }
        weightInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,int before, int count) {
                if(s.length()!=0){
                    int weight = Integer.parseInt(s.toString());
                    currentExercise.sets.get(groupPosition).setWeight(weight);
                }
            }
        });

        EditText repsInput = (EditText) convertView.findViewById(R.id.rep_input);
        repsInput.setText(""+currentExercise.sets.get(groupPosition).getNumReps());
        repsInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,int before, int count) {
                if(s.length()!=0){
                    int reps = Integer.parseInt(s.toString());
                    currentExercise.sets.get(groupPosition).setNumReps(reps);
                }
            }
        });

        //currentExercise.sets.get(groupPosition).setWeight(weightOfSet);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        Log.e("Log method call", "getChildrenCount");
        return 1;
        //return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
//        Log.e("Log method call", "getGroup");
        return this.currentExercise.sets.get(groupPosition).setNum;
    }

    @Override
    public int getGroupCount() {
        Log.e("Group count", " " + this.currentExercise.sets.size());

        return this.currentExercise.sets.size();
//        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
//        Log.e("Log method call", "getGroupID");
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
//        Log.e("Log method call", "getGroupView");
        String headerTitle = ("Set: " + getGroup(groupPosition));
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_sets, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.setListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}