package com.land.gow.plantplanner.addPlant;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.databinding.FragmentAddPlantBinding;
import com.land.gow.plantplanner.dialogFragments.DatePickerFragment;
import com.land.gow.plantplanner.dialogFragments.TimePickerFragment;
import com.land.gow.plantplanner.model.Plant;
import com.land.gow.plantplanner.model.Reminder;

import java.text.SimpleDateFormat;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlantFragment extends Fragment implements DatePickerFragment.OnPickDateListener, TimePickerFragment.OnPickTimeListener, ReminderListAdapter.OnAddReminder {

    private static final String LOG_TAG = AddPlantFragment.class.getSimpleName();

    private Plant newPlant;

    private ReminderListAdapter recyclerViewAdapter;
    private String clickedReminderId;
    private int clickedButtonId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAddPlantBinding binding = FragmentAddPlantBinding.inflate(getLayoutInflater(), container, false);
        // get the root view
        newPlant  = new Plant();
        Reminder waterReminder = new Reminder(getString(R.string.water_reminder));
        newPlant.addReminder(waterReminder);
        View v = binding.getRoot();
                binding.setNewPlant(newPlant); // generated setter based on the data in the layout file

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        recyclerViewAdapter = new ReminderListAdapter(newPlant.getReminders(), this);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        Button addReminder = (Button) v.findViewById(R.id.button_add_reminder);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddReminderButtonClick(view);
            }
        });

        return v;
    }

    public void onAddReminderButtonClick(View v) {
        newPlant.addReminder(new Reminder(""));
        recyclerViewAdapter.updateList(newPlant.getReminders());
    }

    private void initSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
        R.array.repeat_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void createTimePickerListener(CardReminderView holder, Reminder reminder, int buttonId) {
        final Fragment thisFrag = this;
        final String id = reminder.getId();
        Button button = (Button) holder.getViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedReminderId = id;
                DialogFragment dialog = new TimePickerFragment();
                dialog.setTargetFragment(thisFrag, 0);
                dialog.show(getActivity().getSupportFragmentManager(), "TimePickerFragment");
            }
        });
    }

    @Override
    public void onPickTimeListener(int hour, int minute) {
        Reminder reminder = newPlant.getReminderById(clickedReminderId);
        if (reminder != null) {
            reminder.setStartTime(hour, minute);
        }
    }
    private void createDatePickerListener(final CardReminderView holder, Reminder reminder, int buttonId) {
        final Fragment thisFrag = this;
        final String id = reminder.getId();
        Button button = (Button) holder.getViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedReminderId = id;
                clickedButtonId = buttonId;
                DialogFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(thisFrag, 0);
                dialog.show(getActivity().getSupportFragmentManager(), String.valueOf(buttonId));
            }
        });
    }

    @Override
    public void onPickDateListener(int year, int month, int day) {
        Reminder reminder = newPlant.getReminderById(clickedReminderId);
        if (reminder != null) {
            switch (clickedButtonId) {
                case R.id.button_start_date:
                    reminder.setStartDate(day, month, year);
                    break;
                case R.id.button_end_date:
                    reminder.setEndDate(day, month, year);
                    break;
            }
        }
    }

    @Override
    public void onAddReminderListener(CardReminderView holder, Reminder reminder) {
        createDatePickerListener(holder, reminder, R.id.button_start_date);
        createTimePickerListener(holder, reminder, R.id.button_start_time);
        initSpinner((Spinner) holder.getViewById(R.id.spinner_repeat));
        createDatePickerListener(holder, reminder, R.id.button_end_date);
    }
//
//    public void setIconPicture() {
//        AssetManager manager = getAssets();
//
//        // read a Bitmap from Assets
//        InputStream open = null;
//        try {
//            open = manager.open("logo.png");
//            Bitmap bitmap = BitmapFactory.decodeStream(open);
//            // Assign the bitmap to an ImageView in this layout
//            ImageView view = (ImageView) getView().findViewById(R.id.imageView1);
//            view.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (open != null) {
//                try {
//                    open.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
