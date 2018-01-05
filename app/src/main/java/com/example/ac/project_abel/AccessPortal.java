package com.example.ac.project_abel;

import android.util.Log;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Wise on 1/2/2018.
 */


public class AccessPortal {
//    perform get request first to get from data
    HttpURLConnection conn;
    private String cookie;
    private static String USER_AGENT = "Mozilla/5.0";
    private JSONObject data = new JSONObject();
    String loginpage;
    String acc_page;

    public AccessPortal(){

    }
    public void run() throws Exception{
//        instantiate
        AccessPortal accessPortal = new AccessPortal();

        String markup = accessPortal.GetPageContent("http://www.unilus.ac.zm/Students/Login.aspx","login");
        Document html = Jsoup.parse(markup);
        Log.w("CC",getFormParams(markup,"ECA1713710","w1se0977"));
//        get vierwstate values and shit



        JSONArray viewstate = new JSONArray();
        viewstate.put(html.getElementById("__VIEWSTATE").val().replace("\\",""));
//        JSONArray validator =new JSONArray(html.getElementById("__EVENTVALIDATION").val());
        JSONArray validator = new JSONArray();
        validator.put(html.getElementById("__EVENTVALIDATION").val());
//        JSONArray generator =new JSONArray("["+html.getElementById("__VIEWSTATEGENERATOR").val()+"]");
        JSONArray generator = new JSONArray();
        generator.put(html.getElementById("__VIEWSTATEGENERATOR").val());
        Log.w("CC","callllllllld"+html.getElementById("__EVENTVALIDATION").val());
        String params = accessPortal.getFormParams(markup,"ECA1713710","w1se0977");
        accessPortal.post("http://www.unilus.ac.zm/Students/Login.aspx",params);
        accessPortal.GetPageContent("http://www.unilus.ac.zm/Students/StudentPortal.aspx","portal");
//        now compare retured data
        if(acc_page.equals(loginpage)){
            Log.w("CC","access did not take");
        }else {
            Log.w("CC","access took");
        }





    }
    private String GetPageContent(String url,String accessed_page) throws Exception {

        URL obj = new URL(url);

//        Log.w("CC",URLEncoder.encode(new JSONArray("['/wee']").toString(),"UTF-8"));
        conn = (HttpURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");


        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        if (cookie != null) {
            Log.w("CC",cookie);

            conn.addRequestProperty("Cookie", cookie);

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
        try {
            setCookies(conn.getHeaderFields().get("Set-Cookie").toString().split(";", -1)[0].split("\\[", -1)[1]);
            Log.w("CC",cookie.split(";", 1)[0]+"coookie");
        }catch (NullPointerException e){
            Log.w("CC","returned null");
        }
        Log.w("CC",response.toString());
        if(accessed_page.equals("login")){
            this.loginpage=response.toString();
        }else {
            this.acc_page=response.toString();
        }

        return response.toString();

    }
    public void setCookies(String cookie) {
        this.cookie = cookie;
        Log.w("CC",cookie+"");
    }
    public void post(String url,String postParams) throws Exception{
        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();


        // Acts like a browser
        conn.setUseCaches(false);
//        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Host", "unilus.ac.zm");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        conn.addRequestProperty("Cookie", cookie);

        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "http://www.unilus.ac.zm/Students/Login.aspx");
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
    }
    public String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("Form1");
        Elements inputElements = loginform.getElementsByTag("input");
        String _username;
        String _password;
        String generator;
        String validator;
        String viewstate;
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

}
