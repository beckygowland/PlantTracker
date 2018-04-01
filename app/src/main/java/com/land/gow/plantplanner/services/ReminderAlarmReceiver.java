package com.land.gow.plantplanner.services;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by becky on 2018-03-31.
 */

public class ReminderAlarmReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationService notificationService = new NotificationService(context);

            Notification.Builder nb = notificationService.
                    getAndroidChannelNotification("Time for some plant love!", "Water the cactus" );

            notificationService.getManager().notify(101, nb.build());
        }

}
