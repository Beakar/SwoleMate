package edu.up.swolemate;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Nathan on 3/18/2015.
 */
public class FontEditText extends EditText{
    public FontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Walkaway.ttf"));
    }
}
