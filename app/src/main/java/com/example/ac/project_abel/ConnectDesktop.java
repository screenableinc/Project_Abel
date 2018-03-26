package com.example.ac.project_abel;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

/**
 * Created by Wise on 3/3/2018.
 */

public class ConnectDesktop extends AsyncTask<String, Integer, String>{
    protected String ssid;
    protected String key;
    protected Context context;
    public ConnectDesktop(Context context,String ssid, String key){
        this.context=context;
        this.key = key;
        this.ssid=ssid;
    }
    @Override
    protected String doInBackground(String... params) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", key);

        WifiManager wifiManager = (WifiManager)context.getApplicationContext().getSystemService(context.getApplicationContext().WIFI_SERVICE);
//remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();

        return "success";
    }

    public void start(String ssid, String key, Context context){

    }
}
