package br.com.dreambox.util;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ViewUtils {

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics;
    }
}
