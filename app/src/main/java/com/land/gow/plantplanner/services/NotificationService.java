package com.land.gow.plantplanner.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.land.gow.plantplanner.model.Reminder;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by becky on 2018-03-31.
 */

public class NotificationService extends ContextWrapper {

    private static final String LOG_TAG = NotificationService.class.getSimpleName();
    public static final String REMINDER_CHANNEL_ID = "com.land.gow.reminder";
    public static final String REMINDER_CHANNEL_NAME = "REMINDER CHANNEL";

    private NotificationManager mManager;


    public NotificationService(Context base) {
        super(base);
        createChannels();
    }

    public void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // create android channel
            NotificationChannel androidChannel = new NotificationChannel(REMINDER_CHANNEL_ID,
                    REMINDER_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            // Sets whether notifications posted to this channel should display notification lights
            androidChannel.enableLights(true);
            // Sets whether notification posted to this channel should vibrate.
            androidChannel.enableVibration(true);
            // Sets the notification light color for notifications posted to this channel
            androidChannel.setLightColor(Color.GREEN);
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            getManager().createNotificationChannel(androidChannel);
        }
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public static void setReminder(Context context, Class<?> cls, Reminder reminder) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reminder.getStartDate());
        // cancel already scheduled reminders
        //cancelReminder(context,cls);
        int uniqueReminderId = reminder.getId().hashCode();
        // Enable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                uniqueReminderId, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * reminder.getDailyRepeat(), pendingIntent);
    }

    public static void cancelReminder(Context context,Class<?> cls, Reminder reminder)
    {
        int uniqueReminderId = reminder.getId().hashCode();

        // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                uniqueReminderId, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public Notification.Builder getAndroidChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), REMINDER_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }
}
