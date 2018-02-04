package com.example.ac.project_abel;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.defaultValue;
import static android.R.attr.keyEdgeFlags;
import static android.R.attr.mode;

public class Login extends AppCompatActivity{







    private String free_classes;
//    keep json of course codes and thier fullnames as values


    protected void onCreate(Bundle savedInstanceState) {
//hide status bar ( this code is commented out because its messing with spinners)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//
//        }

        setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
        super.onCreate(savedInstanceState);













//        should put this in an async task to show user that data is loading
//        startActivity(new Intent(Login.this,MainActivity.class));
//        finish();




        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("details",Context.MODE_PRIVATE);
        SharedPreferences flags = getApplicationContext().getSharedPreferences("flags",Context.MODE_PRIVATE);
        String progs = sharedPref.getString("programs",null);
        Log.w("CC",flags.getBoolean("ca",false)+" "+flags.getBoolean("final",false)+" "+ flags.getBoolean("year_sem",false)+" "+progs);
        if(flags.getBoolean("ca",false)&&flags.getBoolean("final",false)&&flags.getBoolean("contacts",false))
        {
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
        String ca = sharedPref.getString("ca", null);
        String classes = sharedPref.getString("classes", null);
        String aFinal = sharedPref.getString("final",null);
//        Activity activity = this;
        Log.w("CC",classes+"jjjjjj");

//        if (name!=null && classes!=null && selection!=null){
//            startActivity(new Intent(Login.this, MainActivity.class));
//            finish();
//        }


        setContentView(R.layout.activity_login);





        try{
            Resources resources = getResources();

//            free classes
            InputStreamReader read_f = new InputStreamReader(resources.openRawResource(R.raw.free_classes));
            BufferedReader reader_f = new BufferedReader(read_f);
            String string_json_f = reader_f.readLine();


            free_classes = string_json_f;

        }catch (Exception e){

        }
        final EditText _name = (EditText) findViewById(R.id.name);


//        login
        final EditText studentid = (EditText) findViewById(R.id.studentId);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText name = (EditText) findViewById(R.id.name);


        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AttemptLogin().execute(studentid.getText().toString().toUpperCase(),password.getText().toString(),name.getText().toString());
            }
        });

           }
    public void create_pref(String name, String json, String free_classes){
        Context context = getApplicationContext();

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name",name);
        editor.putString("classes",json);
//        save keys to feed to api during update

        editor.putString("free_classes",free_classes);
        editor.commit();

    }
    public void set_edit_text_to_inactive(EditText v){
        v.setActivated(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class AttemptLogin extends AsyncTask<String, Integer, String>{
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Login.this);
            dialog.setTitle("Logging In");
            dialog.setMessage("Please Wait");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String login = new AccessPortal(params[0], params[1], "http://www.unilus.ac.zm/Students/StudentPortal.aspx",Login.this).run();

                if (login.equals("success")){
//                    store username in sharedpref
                    SharedPreferences preferences = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("studentId",params[0]);
                    editor.putString("password",params[1]);
                    editor.putString("name",params[2]);
                    editor.apply();
                    startActivity(new Intent(Login.this,Setup.class));
                    finish();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getApplicationContext(),"Error with credentials",Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }catch (Exception e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Check internet connection",Toast.LENGTH_LONG).show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
        }
    }
}
