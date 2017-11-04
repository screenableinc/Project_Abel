package com.example.ac.project_abel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by Wise on 10/24/2017.
 */

public class Notifications {
    Context context;
    String message;
    String title;

    public void Notifications(Context context, String message, String title) {
        this.context = context;
        this.title = title;
        this.message = message;}

//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this.context)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle(this.title)
//                        .setContentText(this.message);
//        // Creates an explicit intent for an Activity in your app
//        Intent resultIntent = new Intent(this.context, MainActivity.class);
//
//        // The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.context);
//
//    public void setStackBuilder(TaskStackBuilder stackBuilder) {
//        this.stackBuilder = stackBuilder;
//
//        // Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(MainActivity.class);
//
//        // Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//    }
//
//    PendingIntent resultPendingIntent =
//            stackBuilder.getPendingIntent(
//                    0,
//                    PendingIntent.FLAG_UPDATE_CURRENT
//            );
//
//    public NotificationCompat.Builder getmBuilder() {
//        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        // mId allows you to update the notification later on.
//        mNotificationManager.notify(2, mBuilder.build());
//        return mBuilder;
//    }
//    NotificationCompat.Builder mBuilder =
//            new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("My notification")
//                    .setContentText("Hello World!");
//    //                            .setLargeIcon(R.mipmap.ic_launcher);
//// Creates an explicit intent for an Activity in your app
//    Intent resultIntent = new Intent(this, MainActivity.class);
//
//    // The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//            stackBuilder.addParentStack(MainActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//            stackBuilder.addNextIntent(resultIntent);
//    PendingIntent resultPendingIntent =
//            stackBuilder.getPendingIntent(
//                    0,
//                    PendingIntent.FLAG_UPDATE_CURRENT
//            );
//            mBuilder.setContentIntent(resultPendingIntent);
//    NotificationManager mNotificationManager =
//            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//
//            mNotificationManager.notify(2, mBuilder.build());


}
