package com.example.ac.project_abel;

/**
 * Created by Wise on 3/25/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Wise on 1/2/2018.
 */


public class AccessPortal {
    //    perform get request first to get from data
    HttpsURLConnection conn;
    private String cookie;
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
    private JSONObject data = new JSONObject();


    protected String username;
    protected String password;
    protected String url;
    protected Context context;
    public AccessPortal(String username, String password, String url, Context context){
        super();
        this.username=username;
        this.password=password;
        this.url=url;
        this.context=context;


    }
    public void AccessPage(String url){

    }
    public String run() throws Exception{
//        instantiate
//        AccessPortal accessPortal = new AccessPortal();


        String markup = this.GetPageContentForLogin("https://www.unilus.ac.zm/Students/Login.aspx");
        Document html = Jsoup.parse(markup);

//        get vierwstate values and shit




        String params = this.getFormParams(markup,username,password);
        this.post("https://www.unilus.ac.zm/Students/Login.aspx",params);
        String portal = this.GetPageContentForLogin(this.url);
        Document returned_page = Jsoup.parse(portal);
        Element login= returned_page.getElementById("MainContent_Button1");

        if(login==null){
//            return login success and store cookie to sharedpref
            SharedPreferences preferences = context.getSharedPreferences("request_params",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("cookie",cookie);
            editor.apply();
            return "success";
        }else {
            return "failed";
        }


//        now compare retured data






    }

    public String GetPageContent(String url) throws Exception {

        URL obj = new URL(url);

//        Log.w("CC",URLEncoder.encode(new JSONArray("['/wee']").toString(),"UTF-8"));
        conn = (HttpsURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");


        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//            read request propert
        SharedPreferences preferences = context.getSharedPreferences("request_params",MODE_PRIVATE);
        String stored_cookie = preferences.getString("cookie",null);
        conn.addRequestProperty("Cookie",stored_cookie);


        int responseCode = conn.getResponseCode();
        Log.w("CC","\nSending 'GET' request to URL : " + url+" "+stored_cookie);
        Log.w("CC","Bytes sent : " + conn.getHeaderFields().get("content-Length"));


        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));


        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        // Get the response cookies
//        try {
//
//            setCookies(conn.getHeaderFields().get("Set-Cookie").toString().split(";", -1)[0].split("\\[", -1)[1]);
////            setCookies();
//            Log.w("CC",cookie.split(";", 1)[0]+"cookie");
//        }catch (NullPointerException e){
//            Log.w("CC","returned null "+e);
//        }


        return response.toString();

    }

    public String GetPageContentForLogin(String url) throws Exception {

        URL obj = new URL(url);

//        Log.w("CC",URLEncoder.encode(new JSONArray("['/wee']").toString(),"UTF-8"));
        conn = (HttpsURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");


        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        if (cookie!=null){
            conn.addRequestProperty("Cookie",cookie);
        }else {
            setCookies(conn.getHeaderFields().get("Set-Cookie").toString().split(";", -1)[0].split("\\[", -1)[1]);

        }
        int responseCode = conn.getResponseCode();
        Log.w("CC","\nSending 'GET' request to URL : " + url);
        Log.w("CC","Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Get the response cookies

//        try {
//
//
//            Log.w("CC",cookie.split(";", 1)[0]+"cookie");
//        }catch (NullPointerException e){
//            Log.w("CC","returned null "+e);
//        }
        Log.w("CC",response.toString());

        return response.toString();

    }
    public void setCookies(String cookie) {
        this.cookie = cookie;
        Log.w("CC",cookie+"");
    }
    public void post(String url,String postParams) throws Exception{
        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();
//        SSLContext sc;
//        sc = SSLContext.getInstance("TLS");
//        sc.init(null, null, new java.security.SecureRandom());
//        conn.setSSLSocketFactory(sc.getSocketFactory());


        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Host", "www.unilus.ac.zm");
        conn.setRequestProperty("User-Agent", "Mozilla/ ( compatible )" );
        conn.setRequestProperty("Accept",
                "*/*");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        conn.setInstanceFollowRedirects(false);

        conn.addRequestProperty("Cookie", cookie);

        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "https://www.unilus.ac.zm/Students/Login.aspx");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


        Log.w("CC","Post parameters : "+postParams);
        Log.w("CC",Integer.toString(postParams.length()));

        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
        Log.w("CC","\n hi...Sending 'POST' request size to URL : "+conn.getRequestProperties() + postParams.length());



        // Send post request

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();


//        int responseCode = conn.getResponseCode();
        Log.w("CC","Stage one");
        Log.w("CC","\nSending 'POST' request size to URL : "+conn.getErrorStream() + postParams.length());


        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        Log.w("CC","Stage two");
    }
    public String postforMaterial(String url,String postParams,String cookie) throws Exception{
        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();


        // Acts like a browser
        conn.setUseCaches(false);
//        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Host", "www.unilus.ac.zm");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        conn.addRequestProperty("Cookie", cookie);

        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", url);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


        Log.w("CC","Post parameters : "+postParams);
        Log.w("CC",Integer.toString(postParams.length()));

        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));



        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();


        int responseCode = conn.getResponseCode();
        Log.w("CC","\nSending 'POST' request to URL : " + url);


        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
    }

    public String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("Form1");
        Elements inputElements = loginform.getElementsByTag("input");

        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("__VIEWSTATE"))
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            else if (key.equals("__VIEWSTATEGENERATOR"))
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            else if(key.equals("__EVENTVALIDATION")){
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }else if (key.equals("ctl00$MainContent$UserName")){
                paramList.add(key + "=" + URLEncoder.encode(username, "UTF-8"));
            }else if (key.equals("ctl00$MainContent$Password")) {
                paramList.add(key + "=" + URLEncoder.encode(password, "UTF-8"));
            }
            else if (key.equals("ctl00$MainContent$Button1")) {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }

        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result.toString();
    }

    public String getFormParamsForMaterials(String html, String course)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("Form1");
        Elements inputElements = loginform.getElementsByTag("input");

        List<String> paramList = new ArrayList<String>();
        paramList.add("ctl00$ctl00$MainContent$MainContent$Course" + "=" + URLEncoder.encode(course, "UTF-8"));
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("__VIEWSTATE"))
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            else if (key.equals("__VIEWSTATEGENERATOR"))
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            else if(key.equals("__EVENTVALIDATION")){
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }else if (key.equals("ctl00$ctl00$MainContent$MainContent$Course")){
                paramList.add(key + "=" + URLEncoder.encode(course, "UTF-8"));
            }else if (key.equals("__EVENTTARGET")) {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }
            else if (key.equals("ctl00$ctl00$MainContent$MainContent$Button1")) {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }
            else if (key.equals("__EVENTARGUMENT")) {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }
            else if (key.equals("__LASTFOCUS")) {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            }
        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result.toString();
    }

}
