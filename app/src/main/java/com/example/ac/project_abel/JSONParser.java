package com.example.ac.project_abel;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AC on 9/16/2017.
 */

public class JSONParser {
    public String parse(JSONObject jsonObject,String[] keys){
        String response = "";
        for (int i = 0;i<keys.length;i++){
            try{
            jsonObject = jsonObject.getJSONObject(keys[i]);
            }catch (JSONException e){
                Log.w("CC",e+"eeeeeeeee");
                return null;
            }
        }
        return jsonObject.toString();
    }
    public String remove_last_chars(String string, int limit){
        int length = string.length();

        string = string.substring(0,length-limit);

        return string;
    }
}
