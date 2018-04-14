package com.land.gow.plantplanner.activities.addPlant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.databinding.FragmentAddPlantBinding;
import com.land.gow.plantplanner.activities.common.DatePickerFragment;
import com.land.gow.plantplanner.activities.common.TimePickerFragment;
import com.land.gow.plantplanner.model.Plant;
import com.land.gow.plantplanner.model.Reminder;
import com.land.gow.plantplanner.services.PlantService;
import com.land.gow.plantplanner.util.PlantIcons;

import java.util.Calendar;

public class AddPlantFragment extends Fragment implements DatePickerFragment.OnPickDateListener, TimePickerFragment.OnPickTimeListener, ReminderListAdapter.OnAddReminder {

    private static final String LOG_TAG = AddPlantFragment.class.getSimpleName();
    private static final String NEW_PLANT_STATE = "new_plant_state";

    private Plant newPlant;

    private ReminderListAdapter recyclerViewAdapter;
    private String clickedReminderId;
    private AlertDialog plantIconDialog;
    private View alertView;
    private Button changeIconButton;
    private IconListAdapter iconListAdapter;
    private DatePickerFragment startDatePicker = new DatePickerFragment();
    private DatePickerFragment endDatePicker = new DatePickerFragment();
    private TimePickerFragment startTimePicker = new TimePickerFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // recovering the instance state
        if (savedInstanceState != null) {
            newPlant = (Plant) savedInstanceState.getSerializable(NEW_PLANT_STATE);
        } else {
            newPlant = new Plant();
            Reminder waterReminder = new Reminder(getString(R.string.water_reminder));
            newPlant.addReminder(waterReminder);
            newPlant.setIconDrawble(PlantIcons.flowerList.get(0));
        }

        FragmentAddPlantBinding binding = FragmentAddPlantBinding.inflate(getLayoutInflater(), container, false);
        View v = binding.getRoot();
        binding.setNewPlant(newPlant); // generated setter based on the data in the layout file

        setupReminderRecyclerView(v);
        setupIconDialog(v);
        initAddReminderButtonClickListener(v);
        initFinishPlantButtonClickListener(v);
        return v;
    }


    private void setupIconDialog(View view) {
        initIconPickerDialog(view);
        changeIconButton = view.findViewById(R.id.button_change_icon);
        changeIconButton.setBackgroundResource(newPlant.getIconDrawble());
        changeIconButton.setOnClickListener((View v) -> {
            plantIconDialog.show();
        });
    }

    private void initIconPickerDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        alertView = inflater.inflate(R.layout.icon_recycler_view, null);
        builder.setView(alertView);

        RecyclerView mRecyclerView = (RecyclerView) alertView.findViewById(R.id.recycler_view_icon);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 5);
        mRecyclerView.setLayoutManager(mLayoutManager);

        iconListAdapter = new IconListAdapter(PlantIcons.flowerList, 0);
        mRecyclerView.setAdapter(iconListAdapter);
        builder.setTitle("Choose Icon");

        builder.setPositiveButton("OK", (DialogInterface dialog, int which) -> {
            newPlant.setIconDrawble(PlantIcons.flowerList.get(iconListAdapter.getClickedPosition()));
            changeIconButton.setBackgroundResource(newPlant.getIconDrawble());
        }).setNegativeButton("Cancel", null);
        plantIconDialog = builder.create();
    }

    private void setupReminderRecyclerView(View v) {
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_add_plant);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        recyclerViewAdapter = new ReminderListAdapter(newPlant.getReminders(), this);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initAddReminderButtonClickListener(View v) {
        Button addReminder = (Button) v.findViewById(R.id.button_add_reminder);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPlant.addReminder(new Reminder(""));
                recyclerViewAdapter.updateList();
            }
        });
    }

    /**
     *  Individual Reminder component setup
     */

    @Override
    public void onAddReminderListener(final CardReminderView holder, Reminder reminder) {
        createStartDatePickerListener(holder, reminder);
        createStartTimePickerListener(holder, reminder);
        createEndDatePickerListener(holder, reminder);
        createDeleteListener(holder, reminder);
        EditText numInput = (EditText) holder.getViewById(R.id.input_repeat);
        numInput.setText(reminder.getDailyRepeat().toString());
        numInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    reminder.setDailyRepeat(Integer.valueOf(editable.toString()));
                }
            }
        });
    }

    private void createDeleteListener(CardReminderView holder, Reminder reminder) {
        Button deleteButton = (Button) holder.getViewById(R.id.button_delete_reminder);
        deleteButton.setOnClickListener((view) -> {
            newPlant.deleteReminder(reminder.getId());
            recyclerViewAdapter.updateList();
        });
    }

    private void initFinishPlantButtonClickListener(View v) {
        Button addReminder = (Button) v.findViewById(R.id.button_finish);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantService.addPlant(getContext(), newPlant);
                getActivity().onBackPressed();
            }
        });
    }

    private void createStartTimePickerListener(CardReminderView holder, Reminder reminder) {
        final Fragment thisFrag = this;
        final String id = reminder.getId();
        Button button = (Button) holder.getViewById(R.id.button_start_time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedReminderId = id;
                startTimePicker.setTargetFragment(thisFrag, 0);
                startTimePicker.show(getActivity().getSupportFragmentManager(), reminder.getId() + "TimePickerFragment");
            }
        });
    }

    private void createStartDatePickerListener(final CardReminderView holder, Reminder reminder) {
        startDatePicker.setTargetFragment(this, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(reminder.getStartDate());
        startDatePicker.setDate(cal);
        startDatePicker.setId(R.id.button_start_date);

        Button button = (Button) holder.getViewById(R.id.button_start_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedReminderId = reminder.getId();
                startDatePicker.show(getActivity().getSupportFragmentManager(), clickedReminderId + "StartDatePicker");
            }
        });
    }

    private void createEndDatePickerListener(final CardReminderView holder, Reminder reminder) {
        endDatePicker.setTargetFragment(this, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(reminder.getEndDate());
        endDatePicker.setDate(cal);
        endDatePicker.setId(R.id.button_end_date);

        Button button = (Button) holder.getViewById(R.id.button_end_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedReminderId = reminder.getId();
                endDatePicker.show(getActivity().getSupportFragmentManager(), clickedReminderId + "EndDatePicker");
            }
        });
    }

    /**
     * DATE AND TIME PICKER LISTENERS
     */

    @Override
    public void onPickTimeListener(int hour, int minute) {
        Reminder reminder = newPlant.getReminderById(clickedReminderId);
        if (reminder != null) {
            reminder.setStartTime(hour, minute);
        }
    }

    @Override
    public void onPickDateListener(int year, int month, int day, int id) {
        Reminder reminder = newPlant.getReminderById(clickedReminderId);
        if (reminder != null) {
            switch (id) {
                case R.id.button_start_date:
                    reminder.setStartDate(day, month, year);
                    break;
                case R.id.button_end_date:
                    reminder.setEndDate(day, month, year);
                    break;
                default:
            }
        }
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(NEW_PLANT_STATE, newPlant);
        super.onSaveInstanceState(outState);
    }
}