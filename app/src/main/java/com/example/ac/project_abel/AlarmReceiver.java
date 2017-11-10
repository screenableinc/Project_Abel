package com.example.ac.project_abel;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.VolumeProvider;
import android.os.PowerManager;
import android.provider.*;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Wise on 10/24/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent)
    {


        if (intent.getAction()!=null){
        if(intent
                .getAction().equals("android.intent.action.TIME_SET")|| intent.getAction().equals(Intent.ACTION_TIME_TICK)||intent.getAction().equals(Intent.ACTION_TIME_CHANGED)){
            cancelAlarm(context);
            setAlarm(context);
//            Toast.makeText(context,"changedtiem",Toast.LENGTH_LONG).show();

        }}

        String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        int day = new Date().getDay();
        SharedPreferences preferences = context.getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
        String jstring_classes = preferences.getString("classes",null);
        try{
            JSONObject classes_of_day = new JSONObject(jstring_classes);
            JSONArray classes = new JSONArray(classes_of_day.getString(days[day]));

            for (int i = 0; i < classes.length(); i++) {
                JSONObject _class = classes.getJSONObject(i);
                String time = _class.getString("time");
                String program = _class.getString("program");
                int start_hr = Integer.parseInt( time.split(":",-1)[0]);
                int curr_hr = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//                Toast.makeText()
                if (curr_hr+1==start_hr){
                    if (Calendar.getInstance().get(Calendar.MINUTE)>=45){
//                        MediaPlayer player = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
//                        player.start();

                        new Notifications().Notifications(context,"Class in 15 minutes",program);
                        setAlarm(context);
//                        show 15 min to class notification



                    }
                }else if(curr_hr == start_hr){
                    new Notifications().Notifications(context,"Time for class",program);
                    setAlarm(context);
                }
//                Log.w("CC",start_hr+"ppppppppp"+Calendar);

            }
        }catch (Exception e){
//            Toast.makeText(context,"error"+e,Toast.LENGTH_LONG).show();

        }




    }

public void setAlarm(Context context)
{

    AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    Intent i = new Intent(context, AlarmReceiver.class);
    PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
    Calendar c = Calendar.getInstance();

    if(c.get(Calendar.MINUTE)>=45) {

        c.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
    }else {
        c.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, 45);
        c.set(Calendar.SECOND, 0);
    }
    am.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);


}

public void cancelAlarm(Context context)
{
    Intent intent = new Intent(context, Reminders.class);
    PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    alarmManager.cancel(sender);
}

}
