package com.land.gow.plantplanner.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by becky on 2018-03-18.
 */

public class Reminder {

    private UUID id;
    private String name;
    private String iconFilePath;
    private Date startDate;
    private Date endDate;
    private RepeatFrequency repeatFrequency;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public Reminder(String aName) {
        id = UUID.randomUUID();
        name = aName;
        startDate = new Date();
        endDate = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconFilePath() {
        return iconFilePath;
    }

    public void setIconFilePath(String iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public RepeatFrequency getRepeatFrequency() {
        return repeatFrequency;
    }

    public void setRepeatFrequency(RepeatFrequency repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    public String getFormattedStartDate(){
        return dateFormat.format(startDate);
    }

    public String getFormattedStartTime(){
        return timeFormat.format(startDate);
    }

    public String getFormattedEndDate(){
        return dateFormat.format(endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reminder reminder = (Reminder) o;

        if (id != null ? !id.equals(reminder.id) : reminder.id != null) return false;
        if (name != null ? !name.equals(reminder.name) : reminder.name != null) return false;
        if (iconFilePath != null ? !iconFilePath.equals(reminder.iconFilePath) : reminder.iconFilePath != null)
            return false;
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
                ", iconFilePath='" + iconFilePath + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", repeatFrequency=" + repeatFrequency +
                ", dateFormat=" + dateFormat +
                ", timeFormat=" + timeFormat +
                '}';
    }
}
