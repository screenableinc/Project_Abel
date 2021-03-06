package com.example.ac.project_abel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;


/**
 * Created by Wise on 11/6/2017.
 */

public class LoadView {
    Bundle bundle;
    Context context;
    JSONArray classes;
    JSONObject free_classes;
    View rootView;
    LayoutInflater inflater;

    FragmentActivity activity;
//    ViewPager pager;

    public LoadView(Context context, String day, Bundle bundle, FragmentActivity activity){

        this.context = context;
        this.bundle = bundle;
        this.activity=activity;

        this.inflater = LayoutInflater.from(context);

        this.rootView=LayoutInflater.from(context).inflate(R.layout.fragment_layout,null);
//        this.barLayout=barLayout;









//        Fragment fragment;


    }

    public View ReturnRootView(){
//        bundle = getArguments();

//        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

//        final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        String hour = new Date().getHours()+"";
        if (hour.length()==1){
            hour = "0"+hour;
        }

        if (Integer.parseInt(hour)<8){
            hour = "08";
        }
        if (Integer.parseInt(hour)>=17){
            hour = hour+":30";

        }else {
            hour = hour+":00";
        }
        try {
            free_classes = new JSONObject(bundle.get("free_classes").toString());
            JSONArray list_of_free_classes = new JSONArray(free_classes.getJSONArray(hour).toString());
        }catch (JSONException e){
            hour = "08:00";
        }
        final String[] spinner_times = {hour,"08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:30","17:30","18:30","19:30","20:30"};

        final String final_hr = hour;
        final Spinner atwhattime = (Spinner) rootView.findViewById(R.id.atwhattime);
        final ArrayAdapter<String> time_adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,spinner_times);
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        atwhattime.setAdapter(time_adapter);
        try {

            classes = new JSONArray(bundle.get("classes").toString());
            free_classes = new JSONObject(bundle.get("free_classes").toString());

            JSONArray list_of_free_classes = new JSONArray(free_classes.getJSONArray(final_hr).toString());
            final LinearLayout freetimes = (LinearLayout) rootView.findViewById(R.id.freetimes);
            freetimes.removeAllViews();
            for (int i = 0; i <list_of_free_classes.length() ; i++) {
                LinearLayout room_template = (LinearLayout) inflater.inflate(R.layout.room,null);
                TextView whatroom = (TextView) room_template.findViewById(R.id.whatroom);
                whatroom.setText(list_of_free_classes.get(i).toString());
                freetimes.addView(room_template);


            }
            atwhattime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    freetimes.removeAllViews();
                    LayoutInflater inflater2= LayoutInflater.from(context);
                    try {
                        JSONArray list_of_free_classes = new JSONArray(free_classes.getJSONArray(spinner_times[position]).toString());
//                        Collections.sort(list_of_free_classes,null);
                        for (int i = 0; i < list_of_free_classes.length(); i++) {
                            LinearLayout room_template = (LinearLayout) inflater2.inflate(R.layout.room, null);
                            TextView whatroom = (TextView) room_template.findViewById(R.id.whatroom);
                            whatroom.setText(list_of_free_classes.get(i).toString());
                            freetimes.addView(room_template);


                        }
                    }catch (Exception e){
                        Log.w("CC",";;;;"+e);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            SharedPreferences preferences = context.getSharedPreferences("details",Context.MODE_PRIVATE);
            String registered_programs = preferences.getString("programs",null);




            for (int i = 0;i<classes.length();i++){
                final String course_text = classes.getJSONObject(i).get("program").toString();
                if (registered_programs.contains(course_text)) {


                    LinearLayout l_layout = (LinearLayout) rootView.findViewById(R.id.l_layout);
                    LinearLayout hr = (LinearLayout) inflater.inflate(R.layout.hr, null);
                    LinearLayout template = (LinearLayout) inflater.inflate(R.layout.class_layout, null);

                    TextView time = (TextView) template.findViewById(R.id.time);
                    TextView course = (TextView) template.findViewById(R.id.course);
                    String text = classes.getJSONObject(i).get("time").toString();

                    final String lecturer = classes.getJSONObject(i).get("lecturer").toString();
                    final String room = classes.getJSONObject(i).get("room").toString();
                    time.setText(text);
                    course.setText(course_text);
                    l_layout.addView(template);
                    LayoutInflater layoutInflater = LayoutInflater.from(context);


                    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                    final int displayheight = manager.getDefaultDisplay().getHeight();
                    final String code = classes.getJSONObject(i).get("code").toString();
                    final String type = classes.getJSONObject(i).get("type").toString();
                    template.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            new Toggle(rootView, context, displayheight, room, lecturer, course_text, code, type);
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.w("CC","failed on get"+e);
//            wise fix this and run in update
        }


        final LinearLayout free_classes = (LinearLayout) rootView.findViewById(R.id.free_classes);
        final LinearLayout l_layout = (LinearLayout) rootView.findViewById(R.id.l_layout);

//        if (free_classes.getVisibility()==View.GONE){
//            toolbar.setTitle(bundle.get("name").toString());
//        }else {
//            toolbar.setTitle("Free Classes");
//        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);

                if (free_classes.getVisibility()==View.GONE){
                    l_layout.animate().alpha(0);
                    l_layout.setVisibility(View.GONE);
                    free_classes.setVisibility(View.VISIBLE);
                    free_classes.animate().alpha(1);
//                    toolbar.setTitle("Free Classes");
                    Toast.makeText(context,"Free Classes",Toast.LENGTH_SHORT).show();
                }else {
                    free_classes.animate().alpha(0);
                    free_classes.setVisibility(View.GONE);
                    l_layout.setVisibility(View.VISIBLE);
                    l_layout.animate().alpha(1);
//                    toolbar.setTitle(bundle.get("name").toString());

                }
            }
        });

        LinearLayout holder = (LinearLayout) rootView.findViewById(R.id.holder);

//        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Log.w("CC",manager.getDefaultDisplay().getHeight()+" "+ barLayout.getLayoutParams().height+""+pager.getLayoutParams().height+"sad");

        return rootView;


    }



}
