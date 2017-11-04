package com.example.ac.project_abel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AsyncListUtil;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Settings extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Settings.this, MainActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//
//        }
//        Resources.Theme res = R.style.AppTheme;
        SharedPreferences sharedPreferences = getSharedPreferences("abel_file_key", Context.MODE_PRIVATE);
        String current = sharedPreferences.getString("theme","");
        Log.w("CC",current);
        if (current.equals("default") || current.equals("")){
            setTheme(R.style.AppTheme2);
        }else {setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));}


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        setTitle("Settings");
//        LinearLayout themes = (LinearLayout) findViewById(R.id.themes);
        final CheckBox lawrencium = (CheckBox) findViewById(R.id.lawrencium);
        CheckBox judy = (CheckBox) findViewById(R.id.judy);
        CheckBox default_theme = (CheckBox) findViewById(R.id.default_theme);
        CheckBox quepal = (CheckBox) findViewById(R.id.quepal);
        final ViewGroup theme_list = (ViewGroup) findViewById(R.id.themes);
        final int child_count = theme_list.getChildCount();
        for (int i = 0;i<child_count;i++){
            theme_list.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox ck = (CheckBox)v;
                    new ChangeTheme().execute(ck.getText().toString());
//                    for (int j = 0;j<child_count;j++){
//
//                        if(ck!=v){
//
//                            ck.setChecked(false);
//
//
//                        }else {
//                            ck.setChecked(true);
//
//                        }
//                    }
                }
            });
        }

        //        add event listener to child elements...in future use for loop




        switch (current){
            case "lawrencium":
                lawrencium.setChecked(true);
                return;
            case "judy":
                judy.setChecked(true);
                return;
            case "quepal":
                quepal.setChecked(true);
                return;
            case "":
                default_theme.setChecked(true);
                return;
            case "default":
                default_theme.setChecked(true);
                return;
        }





    }
    public class ChangeTheme extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            boolean changed = new ThemeSettings().SetTheme(getApplicationContext(),params[0].toLowerCase());
            if (changed){
                startActivity(new Intent(Settings.this, Settings.class));
                finish();
//                getApplicationContext().setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
            }
//            Toast.makeText(getApplicationContext(),"lllllll",Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
