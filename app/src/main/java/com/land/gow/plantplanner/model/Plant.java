package com.land.gow.plantplanner.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.land.gow.plantplanner.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by becky on 2018-03-18.
 */
@Entity(tableName = "plant")
public class Plant extends BaseObservable {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "icon_file_path")
    private Integer iconDrawble;

    @Ignore
    private List<Reminder> reminders;

    public Plant(){
        id = UUID.randomUUID().toString();
    }

    public void setId(String aId) {
        this.id = aId;
    }

    public String getId() {
        return this.id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public Integer getIconDrawble() {
        return iconDrawble;
    }

    public void setIconDrawble(Integer iconFilePath) {
        this.iconDrawble = iconFilePath;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<Reminder> addReminder(Reminder newReminder) {
        if (reminders == null) {
            reminders = new ArrayList<>();
        }
        reminders.add(newReminder);
        return reminders;
    }

    public Reminder getReminderById(String id) {
        Optional<Reminder> optional = getReminders().stream().filter(reminder -> {
            return reminder.getId().equals(id);
        }).findFirst();
        return optional.orElse(null);
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconDrawable='" + iconDrawble+ '\'' +
                ", reminders=" + reminders +
                '}';
    }
}
