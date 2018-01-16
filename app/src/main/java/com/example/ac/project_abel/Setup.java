package com.example.ac.project_abel;

import android.animation.*;
import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Setup extends AppCompatActivity {
    GifImageView gifView;
    Globals globals= new Globals();
    String studentId;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        new SetupAllCrap().execute();


//        gifView.setBytes(bitmapData);
    }
    public class SetupAllCrap extends AsyncTask<String, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            final ImageView imageView = (ImageView) findViewById(R.id.logo);
            Animation animation = AnimationUtils.loadAnimation(Setup.this,R.anim.logo);
            animation.setRepeatMode(Animation.REVERSE);
            animation.setRepeatCount(Animation.INFINITE);
//            imageView.setAnimation(animation);
            imageView.startAnimation(animation);






        }

        public String GetCA(){
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView) findViewById(R.id.setup_prog);
                        textView.setText("Getting CA data ");

                    }
                });
                String access = new AccessPortal(studentId,password,globals.viewca_url,Setup.this).GetPageContent(globals.viewca_url);
                Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
                if (element!=null){
                    Log.w("CC","login failed at material");
                    return "failed";

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
                            return "failed "+e;
                        }


                    }
                    //                            add to details file preference and set flag
                    SharedPreferences prefs = getSharedPreferences("flags",MODE_PRIVATE);
                    SharedPreferences.Editor edit= prefs.edit();
                    edit.putBoolean("ca",true);
                    edit.apply();

                    SharedPreferences details_prefs = getSharedPreferences("details",MODE_PRIVATE);
                    SharedPreferences.Editor details_edit= details_prefs.edit();
                    details_edit.putString("ca",details.toString());
                    details_edit.apply();
                    return "success";
                }
            } catch (Exception e) {
//                e.printStackTrace();
                Log.w("CC","faield ar material get kkkkkk");
                return "failed "+e;
            }




        }
        public String GetLectContacts(){

            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView) findViewById(R.id.setup_prog);
                        textView.setText("Getting lecturers' contacts ");

                    }
                });
                String access = new AccessPortal(studentId,password,globals.lectscon_url,Setup.this).GetPageContent(globals.lectscon_url);
                Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
                if (element!=null){
                    Log.w("CC","login failed at material ");
                    return "failed";

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
                    Log.w("CC", details.toString());
                    // add to details file preference and set flag
                    SharedPreferences prefs = getSharedPreferences("flags", MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean("contacts", true);
                    edit.apply();

                    SharedPreferences details_prefs = getSharedPreferences("details", MODE_PRIVATE);
                    SharedPreferences.Editor details_edit = details_prefs.edit();
                    details_edit.putString("contact", details.toString());
                    details_edit.apply();
                    return "success";
                }
            }catch (Exception e){
                Log.w("CC","epic"+ e);
                return "failed";
            }

        }
        public String GetFinalResults(){
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView) findViewById(R.id.setup_prog);
                        textView.setText("Getting additional info ");

                    }
                });
                String access = new AccessPortal(studentId,password,globals.viewfinal_url,Setup.this).GetPageContent(globals.viewfinal_url);
                Element element = Jsoup.parse(access).getElementById("MainContent_Button1");
                if (element!=null){
                    Log.w("CC","login failed at material ");
                    return "failed";

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
                    // add to details file preference and set flag
                    SharedPreferences prefs = getSharedPreferences("flags",MODE_PRIVATE);
                    SharedPreferences.Editor edit= prefs.edit();
                    edit.putBoolean("final",true);
                    edit.apply();

                    SharedPreferences details_prefs = getSharedPreferences("details",MODE_PRIVATE);
                    SharedPreferences.Editor details_edit= details_prefs.edit();
                    details_edit.putString("final",details.toString());
                    details_edit.apply();
                    return "success";
                }
            } catch (Exception e) {
//                e.printStackTrace();
                Log.w("CC","faield ar material get fin "+e);
            }

            return null;
        }
        public String GetYearSem(String res){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textView = (TextView) findViewById(R.id.setup_prog);
                    textView.setText("Getting your info ");
                }
            });

            Document doc = Jsoup.parse(res);
            Element print = doc.getElementById("divPrint");
            Elements tds = print.getElementsByTag("td");
            JSONObject details = new JSONObject();

            Elements grds = print.getElementsByAttributeValueStarting("class","grd");
            JSONArray programs = new JSONArray();
            for (Element grd:grds){
                Elements innerGrds = grd.getElementsByTag("td");
                try {
                    programs.put(innerGrds.get(2).text());
//                    put this stuff in a

                }catch (Exception e){
                    Log.w("CC","error wisea "+e);
                    return "failed";
                }
            }
            String year = "";
            String semester="";
            String mode = "";
            for(Element element: tds ){

                if (element.text().contains("YEAR OF STUDY")){
                    String string = element.text().replace("YEAR OF STUDY:","").replace("SEMESTER:","").replaceAll(" ","");
                    year = string.charAt(0)+"";
                    semester = string.charAt(1)+"";
                    Log.w("CC",year+" "+semester+" "+element.text());
                    try {
                        details.put("year",year);details.put("semester",semester);

                    }catch (Exception e){
                        Log.w("CC","error wise "+e);
                        return "failed";
                    }


                }else if(element.text().contains("MODE OF STUDY")){
                    try {
                        mode= element.text().replace("MODE OF STUDY: ","").replace(" ","").toLowerCase();
                        details.put("year",mode);

                    }catch (Exception e){
                        Log.w("CC","error wiseac "+e);
                        return "failed";
                    }
                }
            }
            String _classes="";
            try {
                Resources resources = getResources();
                InputStreamReader read = new InputStreamReader(resources.openRawResource(R.raw.programs_scheds));
                BufferedReader reader = new BufferedReader(read);
                String string_json = reader.readLine();
                JSONObject classes = new JSONObject(string_json);
//                app currently for undergrad students
                _classes = classes.getJSONObject("undergraduate").getJSONObject(mode).getJSONObject(new MiscEvents().GetProgramPrefix(studentId)+year+semester).toString();

            }catch (Exception e){
                Log.w("CC","error wiseas "+e);
                return "failed";
            }
            SharedPreferences details_prefs = getSharedPreferences("details",MODE_PRIVATE);
            SharedPreferences.Editor details_edit= details_prefs.edit();
            details_edit.putString("programs",programs.toString());
            details_edit.putString("year",year);
            details_edit.putString("semester",semester);
            details_edit.putString("mode",mode);
            details_edit.putString("classes",_classes);
            details_edit.apply();
            SharedPreferences prefs = getSharedPreferences("flags",MODE_PRIVATE);
            SharedPreferences.Editor edit= prefs.edit();
            edit.putBoolean("year_sem",true);
            edit.apply();

//            GetPrograms();

            return "success";
        }

        @Override
        protected String doInBackground(String... params) {
            try{
//                the following code is a house of cards.....optimise it int the next build
                SharedPreferences preferences = getSharedPreferences("credentials",MODE_PRIVATE);
                studentId=preferences.getString("studentId",null);
                password=preferences.getString("password",null);
                Log.w("CC",studentId+" "+password);
                String response = new AccessPortal(studentId,password,globals.registration_url,Setup.this).GetPageContent(globals.registration_url);
                Element element = Jsoup.parse(response).getElementById("MainContent_Button1");
                if(element==null){
//                    login was a success
                    String year_sem = GetYearSem(response);
                    String view_Ca =GetCA();
                    String viewFinal = GetFinalResults();
                    String contact = GetLectContacts();
                    if(view_Ca.equals("success")&&viewFinal.equals("success")&&contact.equals("success")){
                        startActivity(new Intent(Setup.this,MainActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(Setup.this,Login.class));
                        finish();
                    }
//                    Log.w("CC",year_sem+" "+view_Ca+" "+viewFinal+" "+contact);


                }else {
//                    attempt login by calling run
                    String login_attempt = new AccessPortal(studentId,password,globals.registration_url,Setup.this).run();
                    if (login_attempt.equals("success")){
//                        auth_successful
                        String sec_response = new AccessPortal(studentId,password,globals.registration_url,Setup.this).GetPageContent(globals.registration_url);
                        Element sec_element = Jsoup.parse(sec_response).getElementById("MainContent_Button1");
                        Log.w("CC",sec_response+"kkkpmm");
                        if (sec_element==null){
//                            success try again
                            String year_sem = GetYearSem(sec_response);
                            String view_Ca =GetCA();
                            String viewFinal = GetFinalResults();
                            String contact = GetLectContacts();
                            Log.w("CC",year_sem+" "+view_Ca+ " "+viewFinal+" "+contact);
                            if(view_Ca.equals("success")&&viewFinal.equals("success")&&contact.equals("success")){
                                startActivity(new Intent(Setup.this,MainActivity.class));
                                finish();
                            }else {

                                startActivity(new Intent(Setup.this,Login.class));
                                finish();
                            }
                            Log.w("CC",year_sem+" "+view_Ca+" "+viewFinal);


                        }else {
                            Log.w("CC","failed at sec");
                        }


                    }else {
//                        credentials must be wrong......called in the extreme
                        startActivity(new Intent(Setup.this,Login.class));
                        finish();
                    }

                }
                Log.w("CC","didnt fail"+response);
            }catch (Exception e){
                Log.w("CC","failed during excecute"+e);
            }
            return null;
        }
    }
}
