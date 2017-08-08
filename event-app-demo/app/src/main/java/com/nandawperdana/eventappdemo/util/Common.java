package com.nandawperdana.eventappdemo.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NwP.
 */

public class Common {

    private static Common mInstance = new Common();

    /**
     * singleton class sInstance
     *
     * @return Common
     */
    public static Common getInstance() {
        if (mInstance == null) {
            synchronized (Common.class) {
                if (mInstance == null) {
                    mInstance = new Common();
                }
            }
        }
        return mInstance;
    }

    public void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager i = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            i.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDate(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate.getDate();
    }
}
