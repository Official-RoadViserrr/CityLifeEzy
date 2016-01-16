package com.citylifeezy.citylifeezy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.citylifeezy.citylifeezy.activity.R;

/**
 * Created by Asif on 12/28/2015.
 */
public class Notification extends Service {

    //String notifys = Main.remindCollect;
    //String notifys = BReceiver.notifys;
    static String contentText;
    NotificationManager notificationManager;
    Intent notifiactionIntent;
    PendingIntent pendingIntent;
    static int id = 123;
    SharedPreferences sp;
    String value;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        value = sp.getString("remind","Click on the notification to see your reminder");
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notifiactionIntent = new Intent(this,Pending_request.class);
        pendingIntent = PendingIntent.getActivity(this, 0, notifiactionIntent, 0);
        int icon = R.drawable.vip_icon;
        String tickerText = "You have a reminder";
        long when = System.currentTimeMillis();
        android.app.Notification notification = new android.app.Notification(icon,tickerText,when);
        String contentTitle = "Reminder";
        contentText = value;
        //notification.setLatestEventInfo(this, contentTitle, contentText,pendingIntent);
        notification.defaults = android.app.Notification.DEFAULT_ALL;
        notificationManager.notify(id, notification);
        //String notify = intent.getStringExtra("reminder");
    }



    //@Override
    //public int onStartCommand(Intent intent, int flags, int startId) {
       /* notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notifiactionIntent = new Intent(this,Adialog.class);
        pendingIntent = PendingIntent.getActivity(this, 0, notifiactionIntent, 0);
        int icon = R.drawable.refreshrr;
        String tickerText = "You have a reminder";
        long when = System.currentTimeMillis();
        android.app.Notification notification = new android.app.Notification(icon,tickerText,when);
        String contentTitle = "Reminder";
        contentText = notifys;
        notification.setLatestEventInfo(this, contentTitle, contentText,pendingIntent);
        notification.defaults = android.app.Notification.DEFAULT_ALL;
        notificationManager.notify(id, notification);
        //return super.onStartCommand(intent, flags, startId);*/
    //return START_STICKY;
    //}

    @Override
    public boolean stopService(Intent name) {

        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        //stopService(new Intent(Notification.this,Main.class));
        super.onDestroy();
    }
}

