package com.land.gow.plantplanner.model;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by becky on 2018-03-18.
 */

public class DiffCallback extends DiffUtil.Callback{

    List<Reminder> oldPersons;
    List<Reminder> newPersons;

    public DiffCallback(List<Reminder> newPersons, List<Reminder> oldPersons) {
        this.newPersons = newPersons;
        this.oldPersons = oldPersons;
    }

    @Override
    public int getOldListSize() {
        return oldPersons.size();
    }

    @Override
    public int getNewListSize() {
        return newPersons.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).getId() == newPersons.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}