package com.chootdev.csnackbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Choota on 1/25/17.
 */

public class Snacky {
    // main context items
    private static Context snackContext;
    private static Snackbar snackbar;
    private static Snacky singleton;

    // variables
    private static int colorCode = Type.getColorCode(Type.SUCCESS);
    private static String snackMessage = "Hi there !";
    private static int snackDuration = Duration.getDuration(Duration.SHORT);
    private static View view;

    private static boolean isCustomView;
    private static boolean isFillParent;
    private static Align textAlign;

    public static Snacky with(Activity activity, View fab) {
        snackContext = activity.getBaseContext();
        if (singleton == null)
            singleton = new Snacky();

        if (fab == null) {
            View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);
            view = rootView;
            snackbar = Snackbar
                    .make(view, "", snackDuration);
        } else {
            view = fab;
            snackbar = Snackbar
                    .make(view, "", snackDuration);
        }

        isCustomView = false;
        isFillParent = false;
        textAlign = Align.LEFT;

        return singleton;
    }

    public static Snacky type(Type type) {
        colorCode = Type.getColorCode(type);
        return singleton;
    }

    public static Snacky type(Type type, int color) {
        if (type == Type.CUSTOM)
            colorCode = color;
        else
            colorCode = Type.getColorCode(type);
        return singleton;
    }

    public static Snacky message(CharSequence displayingMessage) {
        snackMessage = displayingMessage.toString();
        return singleton;
    }

    public static Snacky duration(Duration duration) {
        if (duration != Duration.CUSTOM) {
            snackDuration = Duration.getDuration(duration);
        }
        return singleton;
    }

    public static Snacky duration(Duration durationType, int duration) {
        if (durationType == Duration.CUSTOM) {
            snackDuration = duration;
        }
        return singleton;
    }

    public static Snacky contentView(final View view, int heightInDp) {
        isCustomView = true;

        final Snackbar.SnackbarLayout snackLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        Snackbar.SnackbarLayout.LayoutParams params =
                (Snackbar.SnackbarLayout.LayoutParams) snackLayout.getLayoutParams();

        params.height = (int) pxFromDp(heightInDp);

        TextView textView = (TextView)
                snackLayout.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        snackLayout.addView(view, 0, params);
        return singleton;
    }

    public static Snacky fillParent(boolean fillParent) {
        isFillParent = fillParent;
        return singleton;
    }

    public static Snacky textAlign(Align align) {
        textAlign = align;
        return singleton;
    }

    private static View getSnackBarLayout() {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    public static Snacky setColor(int colorId) {
        View snackBarView = getSnackBarLayout();
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
        }

        return singleton;
    }

    private static void setTextAlignment(Snackbar snackbar) {
        TextView textView = (TextView) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        textView.setLayoutParams(params);

        switch (textAlign) {
            case CENTER:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                else
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case RIGHT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                else
                    textView.setGravity(Gravity.RIGHT);
                break;
            case LEFT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                else
                    textView.setGravity(Gravity.LEFT);
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                else
                    textView.setGravity(Gravity.LEFT);
                break;
        }
    }

    public static void show() {
        if (isCustomView) {
            snackbar.setDuration(snackDuration);
            snackbar.show();
        } else {
            snackbar = Snackbar
                    .make(view, snackMessage, snackDuration)
                    .setDuration(snackDuration);

//            if (isFillParent)
                snackbar.getView().getLayoutParams().width = AppBarLayout.LayoutParams.MATCH_PARENT;

            setTextAlignment(snackbar);

            setColor(colorCode);

            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setMaxLines(10);
            textView.setTypeface(Typeface.createFromAsset(snackContext.getAssets() , "ic_fo2.TTF"));
        }
        snackbar.show();
    }

    private static float pxFromDp(int dp) {
        return dp * snackContext.getResources().getDisplayMetrics().density;
    }

    public static void dismiss() {
        if (snackbar != null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }
}
