package edu.up.swolemate;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nathan on 3/18/2015.
 */
public class FontBoldTextView extends TextView {
    public FontBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontBoldTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Walkway_Bold.ttf"));
    }
}
