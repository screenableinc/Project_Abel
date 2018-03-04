package com.example.ac.project_abel;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.spec.ECField;

import static android.content.Context.MODE_PRIVATE;
import static com.google.android.gms.internal.zzahn.runOnUiThread;

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

    public String GetProgramPrefix(String id, Context context){
        try {
            Resources resources =context.getResources();
            InputStreamReader read = new InputStreamReader(resources.openRawResource(R.raw.all_progs));
            BufferedReader reader = new BufferedReader(read);
            String string_json = reader.readLine();
            JSONObject classes = new JSONObject(string_json);
            String prefix = classes.getString(id);
//                app currently for undergrad students
//            _classes = classes.getJSONObject("undergraduate").getJSONObject(mode).getJSONObject(new MiscEvents().GetProgramPrefix(studentId)+year+semester).toString();
            return prefix;
        }catch (Exception e){
            Log.w("CC","error wiseas "+e);
            return null;
        }
//        return null;
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
                    case "registration":
                        GetYearSem(course_code,context,activity);

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
                String [] keys = {"code","ass1","practical","mid","ca"};

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
                    return "failed";
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
    public String GetYearSem(String course, Context context, Activity activity){
        this.activity=activity;

        SharedPreferences preferences = context.getSharedPreferences("credentials", MODE_PRIVATE);
        course_code = course;
        studentId = preferences.getString("studentId",null);
        password = preferences.getString("password",null);
//                String access = new AccessPortal(studentId,password,globals.viewca_url,context).GetPageContent(globals.viewca_url);
        try{

        String access = new AccessPortal(studentId,password,globals.registration_url,context).GetPageContent(globals.registration_url);
        Element _element = Jsoup.parse(access).getElementById("MainContent_Button1");
        if (_element!=null){
            if (triggered){
                ShowMessage("Error with credentials", context);
                return "failed";
            }else {
//                    check with fail safe
                Fail_safe(context,"registration");
                return "";

            }}else {


            Document doc = Jsoup.parse(access);
            Element print = doc.getElementById("divPrint");
            Elements tds = print.getElementsByTag("td");
            JSONObject details = new JSONObject();

            Elements grds = print.getElementsByAttributeValueStarting("class", "grd");
            JSONArray programs = new JSONArray();
            for (Element grd : grds) {
                Elements innerGrds = grd.getElementsByTag("td");
                try {
                    programs.put(innerGrds.get(2).text());
//                    put this stuff in a

                } catch (Exception e) {
                    Log.w("CC", "error wisea " + e);
                    return "failed";
                }
            }
            String year = "";
            String semester = "";
            String mode = "";
            String program = "";
            for (Element element : tds) {

                if (element.text().contains("YEAR OF STUDY")) {
                    String string = element.text().replace("YEAR OF STUDY:", "").replace("SEMESTER:", "").replaceAll(" ", "");
                    year = string.charAt(0) + "";
                    semester = string.charAt(1) + "";
                    Log.w("CC", year + " " + semester + " " + element.text());
                    try {
                        details.put("year", year);
                        details.put("semester", semester);

                    } catch (Exception e) {
                        Log.w("CC", "error wise " + e);
                        return "failed";
                    }


                } else if (element.text().contains("MODE OF STUDY")) {
                    try {
                        mode = element.text().replace("MODE OF STUDY: ", "").replace(" ", "").toLowerCase();
                        details.put("year", mode);

                    } catch (Exception e) {
                        Log.w("CC", "error wiseac " + e);
                        return "failed";
                    }
                } else if (element.text().contains("PROGRAM OF STUDY")) {
                    try {
                        program = element.text().replace("PROGRAM OF STUDY: ", "");
                        details.put("program", program);

                    } catch (Exception e) {
                        Log.w("CC", "error wiseac " + e);
                        return "failed";
                    }
                }
            }
                String _classes = "";
                try {
                    Resources resources = context.getResources();
                    InputStreamReader read = new InputStreamReader(resources.openRawResource(R.raw.programs_scheds));
                    BufferedReader reader = new BufferedReader(read);
                    String string_json = reader.readLine();
                    JSONObject classes = new JSONObject(string_json);
//                app currently for undergrad students
                    _classes = classes.getJSONObject("undergraduate").getJSONObject(mode).getJSONObject(GetProgramPrefix(program, context) + year + semester).toString();

                } catch (Exception e) {
                    Log.w("CC", "error wiseas " + e);
                    return "failed";
                }
                SharedPreferences details_prefs = context.getSharedPreferences("details", MODE_PRIVATE);
                SharedPreferences.Editor details_edit = details_prefs.edit();
                details_edit.putString("programs", programs.toString());
                details_edit.putString("year", year);
                details_edit.putString("semester", semester);
                details_edit.putString("mode", mode);
                details_edit.putString("program", program);
                details_edit.putString("classes", _classes);
                details_edit.apply();
                SharedPreferences prefs = context.getSharedPreferences("flags", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean("year_sem", true);
                edit.apply();

//            GetPrograms();

                return "success";
            }

    }catch (Exception e){
            ShowMessage("Something went wrong",context);
        }




    return "failed";
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


