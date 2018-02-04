package com.example.ac.project_abel;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.security.spec.ECField;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Wise on 30/10/2017.
 */

public class MiscEvents {
//    Context context;
    Globals globals = new Globals();
    String studentId;
    String password;
    String course_code;
    protected String ERROR_MESSAGE="Something went wrong, check your internet connection";
    View view;
    Activity activity;
    boolean triggered = false;
//    public MiscEvents(Context context){
//
//    }

//    public String begin(Context context,String course){
//        zz
//
//    }

    public String GetProgramPrefix(String id){
        String prefix;
        String returnVal=id;
        int count=id.length()-1;
        for (int i = 0; i < id.length(); i++) {
            try {
                Integer.parseInt(id.charAt(count)+"");
                returnVal=returnVal.replace(id.charAt(count)+"","");
            }catch (Exception e){

            }
            count=count-1;
        }

        return returnVal;
    }

    public String Fail_safe(Context context,String caller){
        try {
            String access = new AccessPortal(studentId, password, globals.materials_url, context).run();
            triggered=true;
            if(access.equals("success")){
                switch (caller){
                    case "ca":
                        GetCA(context,view,course_code,activity);
                    case "final":
                        GetFinalResults(context,this.activity);
                    case "contacts":
                        GetLectContacts(context,activity);
                    case "assignments":
                        GetAssignments(context,course_code,activity);
                    case "material":
                        GetMaterial(context,course_code,activity);

                }
            }
        }catch (Exception e){

        }
            return null;
    }

    public String GetCA(Context context, View view,String course,Activity activity){
        try {
            this.view=view;
            this.activity=activity;
            this.course_code=course;



            SharedPreferences preferences = context.getSharedPreferences("credentials", MODE_PRIVATE);
            studentId = preferences.getString("studentId",null);
            password = preferences.getString("password",null);
            String access = new AccessPortal(studentId,password,globals.viewca_url,context).GetPageContent(globals.viewca_url);
            Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
            if (element!=null){
//                if fail safe hasnt yet been triggered call it
                if (triggered){
                    ShowMessage("Error with credentials\n Please try again",context);
                    return "failed";
                }else {
//                    check with fail safe
                    Fail_safe(context,"ca");
                    return "";

                }


            }else {
//                    success continue
                Elements tds = Jsoup.parse(access).getElementsByAttributeValueStarting("class","grd");
                JSONObject details = new JSONObject();
                String [] keys = {"code","ass1","ass2","practical","test","mid","ca"};

                for(Element _element: tds ){
                    Elements innerTds = _element.getElementsByTag("td");

                    try{
                        JSONObject object = new JSONObject();
                        int count = 0;


                        for(Element innerTd:innerTds){

                            object.put(keys[count],innerTd.text());
                            count++;
                        }
                        details.put(innerTds.get(0).text(),object);

                    }catch (Exception e){
                        ShowMessage(ERROR_MESSAGE,context);
                        return "failed "+e;
                    }


                }


                SharedPreferences details_prefs = context.getSharedPreferences("details",MODE_PRIVATE);
                SharedPreferences.Editor details_edit= details_prefs.edit();
                details_edit.putString("ca",details.toString());
                details_edit.apply();


                ShowMessage("Success",context);

                Log.w("CC","success ");
                return "success";
            }
        } catch (Exception e) {
//                e.printStackTrace();
            ShowMessage(ERROR_MESSAGE,context);;
            return "failed "+e;
        }




    }
    public String GetFinalResults(final Context context,Activity activity){
        try {
            SharedPreferences preferences = context.getSharedPreferences("credentials", MODE_PRIVATE);
            studentId = preferences.getString("studentId",null);
            password = preferences.getString("password",null);
            this.activity=activity;




//            String access = new AccessPortal(studentId,password,globals.viewca_url,context).GetPageContent(globals.viewca_url);
            String access = new AccessPortal(studentId,password,globals.viewfinal_url,context).GetPageContent(globals.viewfinal_url);
            Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
            if (element!=null){
                if (triggered){
                    ShowMessage("Error with credentials\n please try again",context);
                    return "failed";
                }else {
//                    check with fail safe
                    Fail_safe(context,"final");
                    return "";

                }

            }else {
//                    success continue
                Elements tds = Jsoup.parse(access).getElementsByAttributeValueStarting("class","grd");
                JSONObject details = new JSONObject();
                String [] keys = {"code","name","grade"};

                for(Element _element: tds ){
                    Elements innerTds = _element.getElementsByTag("td");

                    try{
                        JSONObject object = new JSONObject();
                        int count = 0;


                        for(Element innerTd:innerTds){

                            object.put(keys[count],innerTd.text());
                            count++;
                        }
                        details.put(innerTds.get(0).text(),object);

                    }catch (Exception e){
                        return "failed "+e;
                    }


                }


                SharedPreferences details_prefs = context.getSharedPreferences("details",MODE_PRIVATE);
                SharedPreferences.Editor details_edit= details_prefs.edit();
                details_edit.putString("final",details.toString());
                details_edit.apply();
                ShowMessage("Success",context);
//                new Performance().LoadView(view,course_code);
                return "success";
            }
        } catch (Exception e) {
//                e.printStackTrace();
            ShowMessage(ERROR_MESSAGE,context);
            Log.w("CC","faield ar material get fin "+e);
        }

        return "";
    }
    public void ShowMessage(final String message,final Context context){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,message, Toast.LENGTH_LONG).show();

            }
        });
    }

    public String GetLectContacts(Context context,Activity activity){
        this.activity = activity;
        try{
                SharedPreferences preferences = context.getSharedPreferences("credentials", MODE_PRIVATE);
                studentId = preferences.getString("studentId",null);
                password = preferences.getString("password",null);
//                String access = new AccessPortal(studentId,password,globals.viewca_url,context).GetPageContent(globals.viewca_url);

            String access = new AccessPortal(studentId,password,globals.lectscon_url,context).GetPageContent(globals.lectscon_url);
            Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
            if (element!=null){
                if (triggered){
                    ShowMessage("Error with credentials\n please try again",context);
                    return "failedee";
                }else {
//                    check with fail safe
                    Fail_safe(context,"contacts");
                    return "";

                }


            }else {
                String[] keys = {"course","name","cell","email"};
                Elements tds = Jsoup.parse(access).getElementsByAttributeValueStarting("class","grd");
                JSONArray details = new JSONArray();
                for (Element td : tds) {
                    int count = 0;
                    Elements innerTds = td.getElementsByTag("td");

                    JSONObject object = new JSONObject();
                    for (Element innertd : innerTds) {
                        object.put(keys[count], innertd.text());
                        count++;
                    }
                    details.put(object);
                }



                SharedPreferences details_prefs = context.getSharedPreferences("details", MODE_PRIVATE);
                SharedPreferences.Editor details_edit = details_prefs.edit();
                details_edit.putString("contact", details.toString());
                details_edit.apply();
                ShowMessage("Success",context);
                return "success";
            }
        }catch (Exception e){
//                Toast.makeText(context,, Toast.LENGTH_LONG).show();
            ShowMessage(ERROR_MESSAGE,context);
            return "failed";
        }

    }
    public String GetMaterial(Context context,String course,Activity  activity){
        this.activity=activity;
        try{
            SharedPreferences preferences = context.getSharedPreferences("credentials", MODE_PRIVATE);
            course_code = course;
            studentId = preferences.getString("studentId",null);
            password = preferences.getString("password",null);
            SharedPreferences request_params = context.getSharedPreferences("request_params",MODE_PRIVATE);

//                String access = new AccessPortal(studentId,password,globals.viewca_url,context).GetPageContent(globals.viewca_url);

            String access = new AccessPortal(studentId,password,globals.materials_url,context).GetPageContent(globals.materials_url);
            Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
            if (element!=null){
                if (triggered){
                    ShowMessage("Error with credentials \n Please try again",context);
                    return "failedee";
                }else {
//                    check with fail safe
                    Fail_safe(context,"material");
                    return "";

                }


            }else {
//                send post request to server to get material
                AccessPortal accessPortal = new AccessPortal(studentId,password,globals.materials_url,context);
                String params =  accessPortal.getFormParamsForMaterials(access,course);
                String post = accessPortal.postforMaterial(globals.materials_url,params,request_params.getString("cookie",null));
                Document document = Jsoup.parse(post);
                Elements elements = document.getElementsByAttributeValueStarting("class","grd");
                Log.w("CC",elements.toString());
                String[] keys = {"year","number","course","description","submitted"};
                JSONArray arr = new JSONArray();
                for (Element td : elements) {
                    int count = 0;
                    Elements innerTds = td.getElementsByTag("td");

                    JSONObject object = new JSONObject();
                    object.put(keys[0],innerTds.get(0).text());
                    object.put("link",innerTds.get(2).getElementsByAttribute("href").attr("href"));
                    object.put(keys[3],innerTds.get(3).text());
                    object.put(keys[4],innerTds.get(4).text());
//                    for (Element innertd : innerTds) {
//                        object.put(keys[count], innertd.text());
//                        count++;
//                    }


                    arr.put(object);
                }
                SharedPreferences details_prefs = context.getSharedPreferences("details",MODE_PRIVATE);
                SharedPreferences.Editor details_edit= details_prefs.edit();
                details_edit.putString(course+"_material",arr.toString());
                details_edit.apply();
                ShowMessage("Success",context);


                return "success";
            }
        }catch (Exception e){
            ShowMessage(ERROR_MESSAGE,context);
            return "failed";
        }
//    return null;
    }

    public String GetAssignments(Context context,String course, Activity activity){
        this.activity = activity;
        try{
            SharedPreferences preferences = context.getSharedPreferences("credentials", MODE_PRIVATE);
            course_code = course;
            studentId = preferences.getString("studentId",null);
            password = preferences.getString("password",null);
//                String access = new AccessPortal(studentId,password,globals.viewca_url,context).GetPageContent(globals.viewca_url);

            String access = new AccessPortal(studentId,password,globals.assignments_url,context).GetPageContent(globals.assignments_url);
            Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
            if (element!=null){
                if (triggered){
                    ShowMessage("Error with credentials", context);
                    return "failedee";
                }else {
//                    check with fail safe
                    Fail_safe(context,"assignments");
                    return "";

                }


            }else {
                String[] keys = {"year","number","description","sub_date","due_date"};
                Elements tds = Jsoup.parse(access).getElementsByAttributeValueStarting("class","grd");
                JSONArray details = new JSONArray();
                for (Element td : tds) {
                    int count = 0;
                    Elements innerTds = td.getElementsByTag("td");

                    JSONObject object = new JSONObject();
                    object.put(keys[0],innerTds.get(0).text());
                    object.put(keys[1],innerTds.get(1).text());
                    object.put("link",innerTds.get(2).getElementsByAttribute("href").attr("href"));
                    object.put(keys[2],innerTds.get(3).text());
                    object.put(keys[3],innerTds.get(4).text());
                    object.put(keys[4],innerTds.get(5).text());
                    object.put("course",innerTds.get(2).getElementsByAttribute("href").text());
                    details.put(object);
                }
                Log.w("CC", details.toString());


                SharedPreferences details_prefs = context.getSharedPreferences("details", MODE_PRIVATE);
                SharedPreferences.Editor details_edit = details_prefs.edit();
                details_edit.putString("assignments", details.toString());
                details_edit.apply();
                ShowMessage("Success",context);

                return "success";
            }
        }catch (Exception e){
            ShowMessage(ERROR_MESSAGE,context);
            return "failed";
        }

    }

    public boolean Reset(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("flags", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("ca", false);
            editor.putBoolean("final",false);
            editor.putBoolean("year_sem",false);
            editor.putBoolean("contacts",false);
            editor.apply();
            context.getSharedPreferences("credentials",MODE_PRIVATE).edit().clear().apply();
            context.getSharedPreferences("details",MODE_PRIVATE).edit().clear().apply();


        }catch (Exception e){
            Log.w("CC","faileddddd"+e);
            return false;
        }



        return true;
    }

//            return null;
    public void Update_view(Context context){

    }

        }


