package com.example.ac.project_abel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Wise on 10/25/2017.
 */

public class ThemeSettings {
    public int ThemeSettings(Context context){
        SharedPreferences themes = context.getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
        String theme = themes.getString("theme","");
        Log.w("CC",theme+"dddd");


        switch (theme){


            case "quepal":
                return R.style.AppThemeQuepal;
            case "judy":
                return R.style.AppThemeJudy;
            case "lawrencium":
                return R.style.AppThemeBluish;
            case "default":
                return R.style.AppTheme;
            default:
                return R.style.AppTheme;
        }


    }
    public boolean SetTheme(Context context, String theme){
//        store selected theme in shared preference file
        try{
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("theme",theme);

        editor.commit();


        }catch (Exception e){
            return false;
        }

        return true;
    }
}
