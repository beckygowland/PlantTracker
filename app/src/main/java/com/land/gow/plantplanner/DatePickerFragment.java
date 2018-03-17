package com.land.gow.plantplanner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {
    private static final String LOG_TAG = DatePickerFragment.class.getSimpleName();
    private OnPickDateListener callback;
    public interface OnPickDateListener {
        public void onPickDateListener(int year, int month, int day);
    }
    Button lBut;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-----------oncreteiew ");
//
//        int pickerButtonId = getArguments().getInt("pickerButtonId");
//        Log.d(LOG_TAG, "-----------id " + pickerButtonId);
//        lBut = (Button) container.findViewById(pickerButtonId);
//        Log.d(LOG_TAG, "-----------lbut " + lBut);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        try {
            callback = (OnPickDateListener) getTargetFragment();
            Log.d(LOG_TAG, "-----------got target " + callback);

        } catch (Exception e) {
            Log.d(LOG_TAG, "-----------fuckin error ");

            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Log.d(LOG_TAG, "seelcted date " + year);
        callback.onPickDateListener(year, month, day);
//        Intent data = new Intent();
//        getTargetFragment().onActivityResult(getTargetRequestCode(), 0, data);
    }
}