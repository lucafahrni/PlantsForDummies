package zli.ch.lf.testplants;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.app.Notification;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 * @class: ReminderNotifyChannel
 *
 * @quelle: https://developer.android.com/reference/android/app/NotificationChannel
 *          https://developer.android.com/reference/android/os/Bundle
 */
public class ReminderNotifyChannel extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        String platzhalter = bundle.getString("reminder");
        String datum = bundle.getString("datum") + " " + bundle.getString("zeit");

        Intent intent2 = new Intent(context, NotificationText.class);
        intent2.putExtra("platzhalter", platzhalter);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent2, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification");

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.flashButton, pendingSwitchIntent);
        remoteViews.setTextViewText(R.id.message, platzhalter);
        remoteViews.setTextViewText(R.id.date, datum);
        //Icon VIew Notificaiton
        builder.setSmallIcon(R.drawable.ic_alarm_white_24dp);
        builder.setAutoCancel(true);
        builder.setOngoing(true);
        builder.setOnlyAlertOnce(true);
        builder.setContent(remoteViews);
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel_name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            nManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        nManager.notify(1, notification);


    }
}
