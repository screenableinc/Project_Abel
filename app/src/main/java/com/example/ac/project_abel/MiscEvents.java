package com.example.ac.project_abel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Wise on 30/10/2017.
 */

public class MiscEvents {
//    Context context;


    public boolean Reset(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("abel_file_key", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", null);
            editor.putString("classes", null);
            editor.putString("selection",null);
            editor.putString("theme","");
            editor.commit();

        }catch (Exception e){
            Log.w("CC","faileddddd"+e);
            return false;
        }



        return true;
    }

//            return null;

        }


