package com.land.gow.plantplanner;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlantFragment extends Fragment implements DatePickerFragment.OnPickDateListener {
    private static final String LOG_TAG = AddPlantFragment.class.getSimpleName();
    private static int clickedButtonId;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

    public AddPlantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_plant, container, false);
        initDatePickerButton(v, R.id.button_water_date);
        initDatePickerButton(v, R.id.button_nutrient_date);

        return v;
    }

    private void initDatePickerButton(View v, int buttonId) {
        Calendar todaysDate = Calendar.getInstance();
        Button datePickerButton = (Button) v.findViewById(buttonId);
        datePickerButton.setText(dateFormat.format(todaysDate.getTime()));
        datePickerButton.setOnClickListener(createDatePickerListener(buttonId));
    }

    private View.OnClickListener createDatePickerListener(final int buttonId) {
        final Fragment thisFrag = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButtonId = buttonId;
                DialogFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(thisFrag, 0);
                dialog.show(getActivity().getSupportFragmentManager(), "DatePickerFragment");
            }
        };
    }

    @Override
    public void onPickDateListener(int year, int month, int day) {
        Log.d(LOG_TAG, "----------onpickdatelisten"  + year + " " + day + " " + month);
        Calendar ldate = Calendar.getInstance();
        ldate.set(year, month, day);
        Button lBut = (Button) getView().findViewById(clickedButtonId);
        lBut.setText(dateFormat.format(ldate.getTime()));
    }
}
