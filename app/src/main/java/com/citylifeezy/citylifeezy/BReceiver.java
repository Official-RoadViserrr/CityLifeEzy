package com.citylifeezy.citylifeezy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Asif on 12/28/2015.
 */
public class BReceiver extends BroadcastReceiver {
    //static String notifys = Main.remindCollect;
    static String contentText;
    SharedPreferences sp;
    String value;



    @Override
    public void onReceive(Context context, Intent intent) {

        sp = PreferenceManager.getDefaultSharedPreferences(context);
        value = sp.getString("remind","-->Something went wrong<--");
        Intent service = new Intent(context, Notification.class);
        //Intent intent = getIntent();
        //Bundle bundle = intent.getExtras();
        context.startService(service);

       /* notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifiactionIntent = new Intent(context,Adialog.class);
        pendingIntent = PendingIntent.getActivity(context, 0, notifiactionIntent, 0);
        int icon = R.drawable.refreshrr;
        String tickerText = "You have a reminder";
        long when = System.currentTimeMillis();
        android.app.Notification notification = new android.app.Notification(icon,tickerText,when);
        String contentTitle = "Reminder";
        contentText = notifys;
        notification.setLatestEventInfo(context, contentTitle, contentText,pendingIntent);
        notification.defaults = android.app.Notification.DEFAULT_ALL;
        notificationManager.notify(123, notification);*/
    }
}

