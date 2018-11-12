package com.example.ac.project_abel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    JSONArray sunday;JSONArray monday;JSONArray tuesday;JSONArray wednesday;JSONArray thursday;JSONArray friday;
    JSONObject f_sunday;JSONObject f_monday;JSONObject f_tuesday;JSONObject f_wednesday;JSONObject f_thursday;JSONObject f_friday;JSONObject f_saturday;
    JSONArray saturday;
    JSONObject json_of_classes;
    JSONObject json_of_free_classes;
    String greeting;
    String selection;
    String selectedItemCode;
    protected String APP_VERSION_NUMBER=new Globals().APP_VERSION_NUMBER;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));

        super.setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);



        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Main2Activity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                check if item has been clicked
                if(selectedItemCode!=null){
                    getApplicationContext().startActivity(new Intent(Main2Activity.this,CourseInfo.class).putExtra("course",selectedItemCode));


                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                set the selectedCodeto null
                selectedItemCode=null;
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        Intent intent = new Intent(this,AlarmReceiver.class);
//        sendBroadcast(new Intent(Intent.ACTION_HEADSET_PLUG));
//        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        startService(new Intent(this,Reminders.class));
//        setTheme(R.style.AppThemeJudy);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        SharedPreferences credentials = getApplicationContext().getSharedPreferences("credentials", Context.MODE_PRIVATE);

        String name = credentials.getString("name", null);
        if (name == null) {
            name="";
        }
        String classes = sharedPref.getString("classes", null);
        String f_classes = sharedPref.getString("free_classes", null);
        String t_a = sharedPref.getString("tests_and_ass",null);
        Log.w("CC",classes+" "+ credentials.getString("studentId",null)+" "+sharedPref.getString("program",null)+credentials.getString("password",null));


//        display reg error


        if (t_a==null){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("tests_and_ass",new JSONArray().toString());
            editor.commit();
        }
        String prefix = new MiscEvents().GetProgramPrefix(sharedPref.getString("program",null),getApplicationContext());
        selection = "undergraduate---"+sharedPref.getString("mode",null)+"---"+prefix+sharedPref.getString("year",null)+sharedPref.getString("semester",null);
//        Log.w("CC",selection);

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
        if (name==null){
            startActivity(new Intent(Main2Activity.this,Login.class));
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

//                startActivity(new Intent(Main2Activity.this,Login.class));
//                finish();
            }
        }
        if(classes==null){
            TextView reg_error = (TextView) findViewById(R.id.registration_error);
            reg_error.setVisibility(View.VISIBLE);
        }

//        check if installed version has programs dict...if not.....force re-auth




//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hi "+name);
        Menu menu = navigationView.getMenu();
//        manipulate menu
        MenuItem coursesItem = menu.findItem(R.id.courses);
        SubMenu coursesSub = coursesItem.getSubMenu();



        try {

            JSONArray programs_dict = new JSONArray(sharedPref.getString("programs_dict", null));
            for (int i = 0;i<programs_dict.length();i++){
                String title = programs_dict.getString(i).split("---",-1)[1];
                final String code = programs_dict.getString(i).split("---",-1)[0];
                coursesSub.add(title).setTitleCondensed(code).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                        save clicked id
                        selectedItemCode = code;
//                        getApplicationContext().startActivity(new Intent(Main2Activity.this,CourseInfo.class).putExtra("course",code));
                       return false;
                    }
                });

            }
        }catch (Exception e){

        }




        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


//        mViewPager.getLayoutParams().height = displaywidth;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        int day = new Date().getDay();
        mViewPager.setCurrentItem(day);






        new check_for_app_version().execute();

    }
    public void populate_drawer_menu(){

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation static view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (id == R.id.nav_update_app) {
            Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://raw.githubusercontent.com/screenableinc/Project_Abel/master/app/app-release.apk"));
            startActivity(openBrowser);

        } else if (id == R.id.nav_update_classes) {
            new Update(Main2Activity.this).execute(selection);
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(Main2Activity.this, Settings.class));
        } else if (id == R.id.nav_reset) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(Main2Activity.this, new ThemeSettings().ThemeSettings(Main2Activity.this));
            } else {
                builder = new AlertDialog.Builder(Main2Activity.this);
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

        }


        return true;
    }


    public class check_for_app_version extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            try{
                URL url = new URL("https://raw.githubusercontent.com/screenableinc/Project_Abel/master/app/src/main/version_nf.cmv");
                URLConnection connection = url.openConnection();
//            connection.setConnectTimeout(5000);
                connection.setDefaultUseCaches(false);connection.setUseCaches(false);



                InputStream response = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(response);
                BufferedReader reader1 = new BufferedReader(reader);
                StringBuilder _result = new StringBuilder();
                String line;
                while((line = reader1.readLine()) != null) {
                    _result.append(line);
                }
                final String result=_result.toString();
                JSONObject object = new JSONObject(result);
                JSONArray fixes = object.getJSONArray("fixes");
                String fix="";
                final String newVersion = object.getString("version");
                for (int i = 0; i <fixes.length();i++ ){
                    fix = fix + "\n - " +fixes.getString(i);
                }

                if(!APP_VERSION_NUMBER.equals(newVersion)){
//                    show dialogue
                    final String fix_var = fix;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                            builder.setTitle("Update to version "+ newVersion)

                                    .setMessage( fix_var)

                                    .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                            Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://raw.githubusercontent.com/screenableinc/Project_Abel/master/app/app-release.apk"));
                                            startActivity(openBrowser);


                                        }
                                    })

                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                    });


                }
            }catch (Exception e){

            }

            return null;
        }
    }

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

    public class check_reg_status extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
//            new E
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

            if (miscEvents.Reset(getApplicationContext())){
                startActivity(new Intent(Main2Activity.this,Login.class));
                finish();
            }

            return null;
        }
    }
}
