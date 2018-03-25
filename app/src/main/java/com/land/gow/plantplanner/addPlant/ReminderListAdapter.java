package com.land.gow.plantplanner.addPlant;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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

import com.land.gow.plantplanner.BR;
import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.model.DiffCallback;
import com.land.gow.plantplanner.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderListAdapter extends RecyclerView.Adapter<CardReminderView> {

    private static final String LOG_TAG = ReminderListAdapter.class.getSimpleName();

    public interface OnAddReminder {
        public void onAddReminderListener(CardReminderView holder, Reminder position);
    }

    private List<Reminder> mDataset;
    private List<Reminder> mOrigDatasett;

    private Button clickedButton;
    private Reminder clickedReminder;
    private OnAddReminder callback;


    public ReminderListAdapter(List<Reminder> myDataset, Fragment aFragment) {
        mDataset = myDataset;
        try {
            callback = (OnAddReminder) aFragment;
        } catch (Exception e) {
            throw new ClassCastException("Calling Fragment must implement OnAddReminder");
        }
    }

    @Override
    public CardReminderView onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        Log.d(LOG_TAG, "oncreteviewholder adapter");
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.add_reminder_card, parent, false);

        return new CardReminderView(binding);
    }

    @Override
    public void onBindViewHolder(CardReminderView holder, int position) {
        Log.d(LOG_TAG, "----------on bindviewholder adapter");

        ViewDataBinding viewDataBinding = holder.getViewDataBinding();

        viewDataBinding.setVariable(com.land.gow.plantplanner.BR.reminder, mDataset.get(position));

        callback.onAddReminderListener(holder, mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateList(List<Reminder> newList) {
     //   DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(mDataset, newList));
//
//        mDataset.clear();
//        mDataset.addAll(newList);
Log.d(LOG_TAG, "--------------update list " + mDataset);
        //TODO: everything im reading seems to say i can and should use: diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

}