package com.land.gow.plantplanner.activities.common;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {
    private static final String LOG_TAG = DatePickerFragment.class.getSimpleName();
    private OnPickDateListener callback;
    public interface OnPickDateListener {
        public void onPickDateListener(int year, int month, int day);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use the current date as the default date in the picker
        final Calendar todaysDate = Calendar.getInstance();
        int year = todaysDate.get(Calendar.YEAR);
        int month = todaysDate.get(Calendar.MONTH);
        int day = todaysDate.get(Calendar.DAY_OF_MONTH);

        try {
            callback = (OnPickDateListener) getTargetFragment();
        } catch (Exception e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d(LOG_TAG, "-----------------date picker frag " + this.getTag());

        callback.onPickDateListener(year, month, day);
    }
}