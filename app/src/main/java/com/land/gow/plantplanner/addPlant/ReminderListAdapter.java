package com.land.gow.plantplanner.addPlant;


import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.model.DiffCallback;
import com.land.gow.plantplanner.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderListAdapter.ViewHolder> {
    private static final String LOG_TAG = ReminderListAdapter.class.getSimpleName();

    public interface OnAddReminder {
        public void onAddReminderListener(ViewHolder holder, Reminder position);
    }

    private List<Reminder> mDataset = new ArrayList<>();
    private Button clickedButton;
    private Reminder clickedReminder;
    private OnAddReminder callback;
    private ViewHolder viewHolder;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        Button startDate;
        Button startTime;
        Spinner repeat;
        Button endDate;

        ViewHolder(View itemView) {
            super(itemView);
            name = (EditText) itemView.findViewById(R.id.input_reminder_name);
            startDate = (Button) itemView.findViewById(R.id.button_start_date);
            startTime = (Button) itemView.findViewById(R.id.button_start_time);
            repeat = (Spinner) itemView.findViewById(R.id.spinner_repeat);
            endDate = (Button) itemView.findViewById(R.id.button_end_date);
        }
    }

    public ReminderListAdapter(List<Reminder> myDataset, Fragment aFragment) {
        mDataset.addAll(myDataset);
        try {
            callback = (OnAddReminder) aFragment;
        } catch (Exception e) {
            throw new ClassCastException("Calling Fragment must implement OnAddReminder");
        }
    }

    @Override
    public ReminderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        Log.d(LOG_TAG, "oncreteviewholder adapter");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_reminder_card, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(LOG_TAG, "on bindviewholder adapter");
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset.get(position).getName());
        holder.startDate.setText(mDataset.get(position).getFormattedStartDate());
        callback.onAddReminderListener(holder, mDataset.get(position));

        holder.startTime.setText(mDataset.get(position).getFormattedStartTime());
        holder.endDate.setText(mDataset.get(position).getFormattedEndDate());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateList(List<Reminder> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(mDataset, newList));

        mDataset.clear();
        mDataset.addAll(newList);

        //TODO: everything im reading seems to say i can and should use: diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

}