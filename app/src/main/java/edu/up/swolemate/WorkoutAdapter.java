package edu.up.swolemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by Nathan on 3/8/2015.
 */
public class WorkoutAdapter extends ArrayAdapter<BaseWorkout> {

    protected int resId;
    protected Context ctx;

    public WorkoutAdapter(Context ctx, int resId, List<BaseWorkout> workouts) {
        super(ctx, resId, workouts);
        this.resId = resId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout root;
        BaseWorkout workout = getItem(position);

        if(convertView == null) {
            root = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater inf = (LayoutInflater)getContext().getSystemService(inflater);
            inf.inflate(resId, root, true);
        } else {
            root = (LinearLayout)convertView;
        }

        ImageView icon = (ImageView)(root.findViewById(R.id.workout_list_icon));
        TextView displayName = (TextView)(root.findViewById(R.id.workout_list_display_name));
        TextView dateCompleted = (TextView)(root.findViewById(R.id.workout_list_date_completed));

        //set the items for the layout
        icon.setBackgroundResource(workout.getSrc());
        displayName.setText(workout.getDisplayName());

        //tried to do this inline, for some reason it didn't agree
        long date = workout.getDateCompleted();
        date = date * 1000;
        Date dateObject = new Date(date);
        String dateString = dateObject.toString().substring(0,10);
        dateCompleted.setText(dateString);

        return root;
    }
}
