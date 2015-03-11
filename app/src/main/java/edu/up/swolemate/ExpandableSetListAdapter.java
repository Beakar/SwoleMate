package edu.up.swolemate;

/**
 * Created by Carl Lulay on 3/8/2015.
 */
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableSetListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> listDataChild;
    StrengthWorkout workout;
    Exercise currentExercise;

    public ExpandableSetListAdapter(Context mContext, List<String> mListDataHeader,HashMap<String, List<String>> mListChildData, StrengthWorkout mWorkout) {
        this.context = mContext;
        workout = mWorkout;
        currentExercise = workout.dummyExercise();
        //
        this.listDataHeader = mListDataHeader;
        this.listDataChild = mListChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        Log.e("Log method call", "getChild");
        return this.currentExercise.sets.get(childPosititon);
        //return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.e("Log method call", "getChildID");
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
        Log.e("Log method call", "getChildView");
        //final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_strength_exercise_sets, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.setListHeader);

        //txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.e("Log method call", "getChildrenCount");
        return 1;
        //return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.e("Log method call", "getGroup");
        return this.currentExercise.sets.get(groupPosition).setNum;
    }

    @Override
    public int getGroupCount() {
        Log.e("Log method call", "getGroupCount");
        return this.currentExercise.sets.size();
//        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.e("Log method call", "getGroupID");
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
        Log.e("Log method call", "getGroupView");
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