package com.cuberto.bubbleicontabbarandroid;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by hoang8f on 5/5/14.
 */

public class TextViewFarsi extends AppCompatTextView {

    public TextViewFarsi(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.TTF"));

    }

    public TextViewFarsi(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.TTF"));
    }

    public TextViewFarsi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.TTF"));
    }

}
