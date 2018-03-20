package com.land.gow.plantplanner.addPlant;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.dialogFragments.DatePickerFragment;
import com.land.gow.plantplanner.dialogFragments.TimePickerFragment;
import com.land.gow.plantplanner.model.Plant;
import com.land.gow.plantplanner.model.Reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlantFragment extends Fragment implements DatePickerFragment.OnPickDateListener, TimePickerFragment.OnPickTimeListener, ReminderListAdapter.OnAddReminder {

    private static final String LOG_TAG = AddPlantFragment.class.getSimpleName();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
    Plant newPlant = new Plant("");

    private RecyclerView mRecyclerView;
    private ReminderListAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button clickedButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_plant, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        recyclerViewAdapter = new ReminderListAdapter(newPlant.getAllReminders(), this);
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
        newPlant.addOtherReminder(new Reminder(""));
        recyclerViewAdapter.updateList(newPlant.getAllReminders());
    }

    private void initSpinner(Spinner spinner) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
        R.array.repeat_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private View.OnClickListener createTimePickerListener(final Button buttonId) {
        final Fragment thisFrag = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton = buttonId;
                DialogFragment dialog = new TimePickerFragment();
                dialog.setTargetFragment(thisFrag, 0);
                dialog.show(getActivity().getSupportFragmentManager(), "TimePickerFragment");
            }
        };
    }

    @Override
    public void onPickTimeListener(int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, hour);
        date.set(Calendar.MINUTE, minute);
        clickedButton.setText(timeFormat.format(date.getTime()));
    }

    private View.OnClickListener createDatePickerListener(final Button buttonId) {
        final Fragment thisFrag = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton = buttonId;
                DialogFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(thisFrag, 0);
                dialog.show(getActivity().getSupportFragmentManager(), "DatePickerFragment");
            }
        };
    }

    @Override
    public void onPickDateListener(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        clickedButton.setText(dateFormat.format(date.getTime()));
    }

    @Override
    public void onAddReminderListener(ReminderListAdapter.ViewHolder holder, Reminder position) {
        holder.startDate.setOnClickListener(createDatePickerListener(holder.startDate));
        holder.startTime.setOnClickListener(createTimePickerListener(holder.startTime));
        initSpinner(holder.repeat);
        holder.endDate.setOnClickListener(createDatePickerListener(holder.endDate));
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
