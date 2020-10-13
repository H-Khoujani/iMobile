package pando.iMobile.utils.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by hoang8f on 5/5/14.
 */

public class TextViewFarsiTitles extends AppCompatTextView {

    public TextViewFarsiTitles(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.ttf"));
    }

    public TextViewFarsiTitles(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.ttf"));
    }

    public TextViewFarsiTitles(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.ttf"));
    }

}
