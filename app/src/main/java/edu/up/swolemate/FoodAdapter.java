package edu.up.swolemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class FoodAdapter extends ArrayAdapter<FoodMeal> {

    protected int resId;
    protected Context ctx;

    public FoodAdapter(Context ctx, int resId, List<FoodMeal> meals) {
        super(ctx, resId, meals);
        this.resId = resId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout root;
        FoodMeal meal = getItem(position);

        if(convertView == null) {
            root = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater inf = (LayoutInflater)getContext().getSystemService(inflater);
            inf.inflate(resId, root, true);
        } else {
            root = (LinearLayout)convertView;
        }

        TextView displayName = (TextView)(root.findViewById(R.id.food_list_display_name));
        TextView dateCompleted = (TextView)(root.findViewById(R.id.food_list_date_completed));

        //set the items for the layout
        displayName.setText(meal.getName());

        //tried to do this inline, for some reason it didn't agree
        long date = meal.getDateCompleted();
        date = date * 1000;
        Date dateObject = new Date(date);
        String dateString = dateObject.toString().substring(0,10);
        dateCompleted.setText(dateString);

        return root;
    }
}