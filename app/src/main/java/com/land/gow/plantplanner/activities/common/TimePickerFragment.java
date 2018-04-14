package com.land.gow.plantplanner.activities.common;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment
                            implements TimePickerDialog.OnTimeSetListener {
    private static final String LOG_TAG = TimePickerFragment.class.getSimpleName();
    private OnPickTimeListener callback;
    Calendar iInitialDate;
    private Integer id;

    public void setDate(Calendar initalDate) {
        iInitialDate = initalDate;
    }

    public void setId(int aId) {
        id = aId;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        iInitialDate.set(Calendar.HOUR, hour);
        iInitialDate.set(Calendar.MINUTE, minute);
        callback.onPickTimeListener(hour, minute);
    }

    public interface OnPickTimeListener {
        public void onPickTimeListener(int hour, int minute);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (iInitialDate == null) {
            iInitialDate = Calendar.getInstance();
        }
        int hour = iInitialDate.get(Calendar.HOUR);
        int minute = iInitialDate.get(Calendar.MINUTE);

        try {
            callback = (OnPickTimeListener) getTargetFragment();
        } catch (Exception e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

}