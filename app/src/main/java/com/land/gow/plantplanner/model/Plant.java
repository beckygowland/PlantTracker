package com.land.gow.plantplanner.model;

import com.land.gow.plantplanner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by becky on 2018-03-18.
 */

public class Plant {
    private String name;
    private Reminder wateringReminder;
    private List<Reminder> otherReminders;

    public Plant(String waterReminderName){
        wateringReminder = new Reminder(waterReminderName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reminder getWateringReminder() {
        return wateringReminder;
    }

    public void setWateringReminder(Reminder wateringReminder) {
        this.wateringReminder = wateringReminder;
    }

    public List<Reminder> getOtherReminders() {
        return otherReminders;
    }

    public void setOtherReminders(List<Reminder> otherReminders) {
        this.otherReminders = otherReminders;
    }

    public List<Reminder> addOtherReminder(Reminder newReminder) {
        if (otherReminders == null) {
            otherReminders = new ArrayList<>();
        }
        otherReminders.add(newReminder);
        return otherReminders;
    }

    public List<Reminder> getAllReminders() {
        List<Reminder> all = new ArrayList<>();
        all.add(wateringReminder);
        if (otherReminders != null) {
            all.addAll(otherReminders);
        }
        return all;
    }
}
