package com.example.ac.project_abel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Wise on 30/10/2017.
 */

public class Update extends AsyncTask<String, Integer,String>{

    Activity activity;
    String classes;
    String free_classes;
    public Update(Activity activity){
        this.activity=activity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,"Updating...",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            String selection = (String) params[0];

            URL url = new URL("http://wise18.pythonanywhere.com/classmate/update/"+selection);
            URLConnection connection = url.openConnection();
//            connection.setConnectTimeout(5000);

            InputStream response = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(response);
            BufferedReader reader1 = new BufferedReader(reader);
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader1.readLine()) != null) {
                result.append(line);
            }

            JSONObject server_response = new JSONObject(result.toString());
            Log.w("CC",server_response+"llllllll");
            free_classes = server_response.getString("free_classes");
            classes = server_response.getString("courses");
            SharedPreferences preferences = activity.getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("free_classes",free_classes);
            editor.putString("classes",classes);
            editor.commit();


        }catch (Exception e){
            Log.w("CC","faielddddddd"+e);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,"update_failed",Toast.LENGTH_LONG).show();
                }
            });
            this.cancel(true);
//
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,"done",Toast.LENGTH_LONG).show();
            }
        });

    }
}
