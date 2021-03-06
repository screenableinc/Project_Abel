package com.example.ac.project_abel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //check programs_dit from here
//        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
//        String jj = sharedPref.getString("programs_dict", "no");
//        Log.w("CCCV",jj+"iiiii");
//        if(jj.equals("no")){
////            Toast.makeText(getApplicationContext(),"Sorry, please login again", Toast.LENGTH_SHORT);
//            Intent mainIntent = new Intent(Splash.this,Login.class);
//            Splash.this.finish();
//            Splash.this.startActivity(mainIntent);

//        }

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,Login.class);
                Splash.this.finish();
                Splash.this.startActivity(mainIntent);

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }

