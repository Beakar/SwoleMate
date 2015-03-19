package edu.up.swolemate;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Nathan on 3/18/2015.
 */
public class FontButton extends Button{

    public FontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Walkaway.ttf"));
    }
}
