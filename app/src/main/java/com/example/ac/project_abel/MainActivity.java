package com.example.ac.project_abel;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    JSONArray sunday;JSONArray monday;JSONArray tuesday;JSONArray wednesday;JSONArray thursday;JSONArray friday;
    JSONObject f_sunday;JSONObject f_monday;JSONObject f_tuesday;JSONObject f_wednesday;JSONObject f_thursday;JSONObject f_friday;JSONObject f_saturday;
    JSONArray saturday;
    JSONObject json_of_classes;
    JSONObject json_of_free_classes;
    String greeting;
    String selection;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        theme
//        hide status bar( this code is commented out because its messing with spinners)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//
//        }
        setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
        super.onCreate(savedInstanceState);
        super.setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
//        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

//        setTheme(R.style.AppThemeJudy);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("abel_file_key", Context.MODE_PRIVATE);

        String name = sharedPref.getString("name", null);
        String classes = sharedPref.getString("classes", null);
        String f_classes = sharedPref.getString("free_classes", null);
        String t_a = sharedPref.getString("tests_and_ass",null);
        if (t_a==null){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("tests_and_ass",new JSONArray().toString());
            editor.commit();
        }
        selection = sharedPref.getString("selection",null);
        int displaywidth = getWindowManager().getDefaultDisplay().getHeight();
//        BroadcastReceiver alarmreceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Toast.makeText(getApplicationContext(),"sdsdsd",Toast.LENGTH_LONG);
//            }
//        };


        if (f_classes==null){
            try {
                Resources resources = getResources();
                InputStreamReader read = new InputStreamReader(resources.openRawResource(R.raw.free_classes));
                BufferedReader reader = new BufferedReader(read);
                String string_json = reader.readLine();
                json_of_free_classes = new JSONObject(string_json);
                f_sunday = new JSONObject(json_of_free_classes.get("Sunday").toString());
                f_monday = new JSONObject(json_of_free_classes.get("Monday").toString());
                f_tuesday = new JSONObject(json_of_free_classes.get("Tuesday").toString());
                f_wednesday = new JSONObject(json_of_free_classes.get("Wednesday").toString());
                f_thursday = new JSONObject(json_of_free_classes.get("Thursday").toString());
                f_friday = new JSONObject(json_of_free_classes.get("Friday").toString());
                f_saturday = new JSONObject(json_of_free_classes.get("Saturday").toString());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("free_classes",string_json);
                editor.commit();
            }catch (Exception e){
                Log.w("CC","faaaaa"+e);
            }
        }else {
            try{
                json_of_free_classes = new JSONObject(f_classes);
                f_sunday = new JSONObject(json_of_free_classes.get("Sunday").toString());
                f_monday = new JSONObject(json_of_free_classes.get("Monday").toString());
                f_tuesday = new JSONObject(json_of_free_classes.get("Tuesday").toString());
                f_wednesday = new JSONObject(json_of_free_classes.get("Wednesday").toString());
                f_thursday = new JSONObject(json_of_free_classes.get("Thursday").toString());
                f_friday = new JSONObject(json_of_free_classes.get("Friday").toString());
                f_saturday = new JSONObject(json_of_free_classes.get("Saturday").toString());
            }catch (Exception e){}
        }
//        class reminder service for next version
//        Intent class_reminder_service = new Intent(this, Reminders.class);
//        startService(class_reminder_service);

//        in 2.0 move this to an async task
        if (name==null || classes==null){
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();


        }else {

            try {
                json_of_classes = new JSONObject(classes);

                sunday =(JSONArray) json_of_classes.get("Sunday");
                monday = (JSONArray) json_of_classes.get("Monday");
                tuesday =(JSONArray) json_of_classes.get("Tuesday");
                wednesday =(JSONArray) json_of_classes.get("Wednesday");
                thursday = (JSONArray) json_of_classes.get("Thursday");
                friday = (JSONArray) json_of_classes.get("Friday");
                saturday =(JSONArray) json_of_classes.get("Saturday");
                greeting = "Hi "+name;
            }catch (Exception e){
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        }

        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hi "+name);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.getLayoutParams().height = displaywidth;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        int day = new Date().getDay();
        mViewPager.setCurrentItem(day);

//        beginAction();

    }
    public void beginAction(){
//        read from file
        try{
            Resources resources = getResources();
            InputStreamReader read = new InputStreamReader(resources.openRawResource(R.raw.programs_scheds));
            BufferedReader reader = new BufferedReader(read);
            String string_json = reader.readLine();
            JSONObject object = new JSONObject(string_json);



        }catch (Exception e){

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, new ThemeSettings().ThemeSettings(MainActivity.this));
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }

            builder.setTitle("Reset")
                    .setMessage("Are you sure you want to Reset?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            new log_out().execute();


                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)


                    .show();



        }else if(id ==R.id.action_settings) {

            startActivity(new Intent(MainActivity.this, Settings.class));
            finish();
        }else if(id==R.id.update){
            new Update(MainActivity.this).execute(selection);
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Bundle bundle = new Bundle();
            switch (position){

                case 0:
                    Fragment sun_frag = new Sunday();

                    bundle.putString("classes",sunday+"");bundle.putString("free_classes",f_sunday.toString());bundle.putString("name",greeting);
                    sun_frag.setArguments(bundle);
                    return sun_frag;
                case 1:
                    Fragment mon_frag = new Monday();
                    bundle.putString("classes",monday+"");bundle.putString("free_classes",f_monday.toString());bundle.putString("name",greeting);


                    mon_frag.setArguments(bundle);
                    return mon_frag;
                case 2:
                    Fragment tue_frag = new Tuesday();
                    bundle.putString("classes",tuesday+"");bundle.putString("free_classes",f_tuesday+"");bundle.putString("name",greeting);
                    tue_frag.setArguments(bundle);
                    return tue_frag;
                case 3:

                    Fragment wed_frag = new Wednesday();
                    bundle.putString("classes",wednesday+"");bundle.putString("free_classes",f_wednesday+"");bundle.putString("name",greeting);
                    wed_frag.setArguments(bundle);
                    return wed_frag;
                case 4:
                    Fragment thur_frag = new Thursday();
                    bundle.putString("classes",thursday+"");bundle.putString("free_classes",f_thursday+"");bundle.putString("name",greeting);
                    thur_frag.setArguments(bundle);
                    return thur_frag;
                case 5:
                    Fragment fri_frag = new Friday();
                    bundle.putString("classes",friday+"");bundle.putString("free_classes",f_friday+"");bundle.putString("name",greeting);
                    fri_frag.setArguments(bundle);
                    return fri_frag;
                case 6:
                    Fragment sat_frag = new Saturday();
                    bundle.putString("classes",saturday+"");bundle.putString("free_classes",f_saturday+"");bundle.putString("name",greeting);
                    sat_frag.setArguments(bundle);
                    return sat_frag;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Sunday";
                case 1:
                    return "Monday";
                case 2:
                    return "Tuesday";
                case 3:
                    return "Wednesday";
                case 4:
                    return "Thursday";
                case 5:
                    return "Friday";
                case 6:
                    return "Saturday";
            }
            return null;
        }
    }
    public class log_out extends AsyncTask<String, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Dialog message = new Dialog(getApplicationContext());
            message.setTitle("Logging Out");
//            message.show();

        }


        @Override
        protected String doInBackground(String... params) {

                MiscEvents miscEvents = new MiscEvents();
                Log.w("CC","calleddfffff");
                if (miscEvents.Reset(getApplicationContext())){
                    startActivity(new Intent(MainActivity.this,Login.class));
                    finish();
                }
//            Context context = getApplicationContext();
//
//            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("name",null);
//            editor.putString("classes",null);
//            editor.commit();
//            startActivity(new Intent(MainActivity.this,Login.class));
//            finish();
            return null;
        }
    }
}
