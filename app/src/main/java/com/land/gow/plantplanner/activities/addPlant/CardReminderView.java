package com.land.gow.plantplanner.activities.addPlant;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.land.gow.plantplanner.R;

/**
 * Created by becky on 2018-03-23.
 */
public class CardReminderView extends RecyclerView.ViewHolder {

    private static final String LOG_TAG = CardReminderView.class.getSimpleName();

    private ViewDataBinding mViewDataBinding;

    private EditText name;
    private EditText dailyRepeat;
    private Button startDate;
    private Button startTime;
    private Button endDate;

    public CardReminderView( ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());

        mViewDataBinding = viewDataBinding;
        mViewDataBinding.executePendingBindings();

        name = (EditText) viewDataBinding.getRoot().findViewById(R.id.input_reminder_name);
        dailyRepeat = (EditText) viewDataBinding.getRoot().findViewById(R.id.input_repeat);

        startDate = (Button) viewDataBinding.getRoot().findViewById(R.id.button_start_date);
        startTime = (Button) viewDataBinding.getRoot().findViewById(R.id.button_start_time);
        endDate = (Button) viewDataBinding.getRoot().findViewById(R.id.button_end_date);
    }

    public Object getViewById(int id){
        switch (id) {
            case R.id.input_reminder_name:
                return name;
            case R.id.input_repeat:
                return dailyRepeat;
            case R.id.button_start_date:
                return startDate;
            case R.id.button_start_time:
                return startTime;
            case R.id.button_end_date:
                return endDate;
            default:
                return null;
        }
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }
}
