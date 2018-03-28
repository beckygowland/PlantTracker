package com.land.gow.plantplanner.model;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.land.gow.plantplanner.BR;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by becky on 2018-03-18.
 */
@Entity(tableName = "reminder")
public class Reminder extends BaseObservable {

    @NonNull
    private String plantId;

    @NonNull
    private String id;

    @NonNull
    @Bindable
    private String name;

    @Bindable
    private Date startDate;
    @Bindable
    private Date endDate;
    private RepeatFrequency repeatFrequency;

    public Reminder(String aName) {
        id = UUID.randomUUID().toString();
        name = aName;
        startDate = new Date();
        endDate = new Date();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        notifyPropertyChanged(com.land.gow.plantplanner.BR.startDate);
    }

    public void setStartDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        if (startDate != null) {
            calendar.setTime(startDate);
        }
        calendar.set(year, month, day);
        setStartDate(calendar.getTime());
    }

    public void setStartTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        if (startDate != null) {
            calendar.setTime(startDate);
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        setStartDate(calendar.getTime());
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        notifyPropertyChanged(com.land.gow.plantplanner.BR.endDate);
    }

    public void setEndDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        if (startDate != null) {
            calendar.setTime(endDate);
        }
        calendar.set(year, month, day);
        setEndDate(calendar.getTime());
    }

    public RepeatFrequency getRepeatFrequency() {
        return repeatFrequency;
    }

    public void setRepeatFrequency(RepeatFrequency repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reminder reminder = (Reminder) o;

        if (id != null ? !id.equals(reminder.id) : reminder.id != null) return false;
        if (name != null ? !name.equals(reminder.name) : reminder.name != null) return false;
        if (startDate != null ? !startDate.equals(reminder.startDate) : reminder.startDate != null)
            return false;
        if (endDate != null ? !endDate.equals(reminder.endDate) : reminder.endDate != null)
            return false;
        return repeatFrequency != null ? repeatFrequency.equals(reminder.repeatFrequency) : reminder.repeatFrequency == null;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", repeatFrequency=" + repeatFrequency +
                '}';
    }
}
