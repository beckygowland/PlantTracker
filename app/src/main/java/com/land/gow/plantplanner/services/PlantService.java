package com.land.gow.plantplanner.services;

import android.content.Context;

import com.land.gow.plantplanner.database.AppDatabase;
import com.land.gow.plantplanner.model.Plant;
import com.land.gow.plantplanner.model.Reminder;

import java.util.List;

/**
 * Business logic for maniupation on plants
 */

public class PlantService {

    public List<Integer> getAllPlants() {
        return  null;
    }

    public static void addPlant(Context context, Plant plant) {
        AppDatabase.getAppDatabase(context).plantDao().insertAll(plant);

        for (Reminder reminder: plant.getReminders()) {
            NotificationService.setReminder(context, ReminderAlarmReceiver.class, reminder.getStartDate());
        }
    }
}
