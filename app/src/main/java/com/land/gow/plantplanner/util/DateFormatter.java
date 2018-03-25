package com.land.gow.plantplanner.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by becky on 2018-03-25.
 */

public class DateFormatter {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public static String getDateString(Date date) {
        return date != null ? dateFormat.format(date) : "";
    }

    public static String getTimeString(Date date) {
        return date != null ? timeFormat.format(date) : "";
    }
}
