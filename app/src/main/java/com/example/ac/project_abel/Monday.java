package com.example.ac.project_abel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AC on 9/16/2017.
 */

public class Monday extends Fragment {
    JSONArray classes;
    JSONObject free_classes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        final View rootView = inflater.inflate(R.layout.monday, container, false);

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
        final ArrayAdapter<String> time_adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,spinner_times);
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
                    LayoutInflater inflater2= LayoutInflater.from(getContext());
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



            for (int i = 0;i<classes.length();i++){
                LinearLayout l_layout = (LinearLayout) rootView.findViewById(R.id.l_layout);
                LinearLayout hr=(LinearLayout) inflater.inflate(R.layout.hr,null);
                LinearLayout template = (LinearLayout) inflater.inflate(R.layout.class_layout,null);

                TextView time = (TextView) template.findViewById(R.id.time);
                TextView course = (TextView) template.findViewById(R.id.course);
                String text = classes.getJSONObject(i).get("time").toString();
                final String course_text = classes.getJSONObject(i).get("program").toString();
                final String lecturer = classes.getJSONObject(i).get("lecturer").toString();
                final String room = classes.getJSONObject(i).get("room").toString();
                time.setText(text);
                course.setText(course_text);
                l_layout.addView(template);
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//                LinearLayout class_details=(LinearLayout) layoutInflater.inflate(R.layout.class_details,null);
//                ImageButton close = (ImageButton) class_details.findViewById(R.id.closed);
                final int displayheight = getActivity().getWindowManager().getDefaultDisplay().getHeight();

//
                final String code = classes.getJSONObject(i).get("code").toString();
                final String type = classes.getJSONObject(i).get("type").toString();
                template.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        ok

                        new Toggle(rootView,getContext(),displayheight,room,lecturer,course_text,code,type);
                    }
                });

            }
        }catch (Exception e){
            Log.w("CC","failed on get"+e);
//            wise fix this and run in update
        }
//        LinearLayout topdog = (LinearLayout) rootView.findViewById(R.id.free_classes);
//

        final LinearLayout free_classes = (LinearLayout) rootView.findViewById(R.id.free_classes);
        final LinearLayout l_layout = (LinearLayout) rootView.findViewById(R.id.l_layout);

        if (free_classes.getVisibility()==View.GONE){
            toolbar.setTitle(bundle.get("name").toString());
        }else {
            toolbar.setTitle("Free Classes");
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

                if (free_classes.getVisibility()==View.GONE){
                    l_layout.animate().alpha(0);
                    l_layout.setVisibility(View.GONE);
                    free_classes.setVisibility(View.VISIBLE);
                    free_classes.animate().alpha(1);
//                    toolbar.setTitle("Free Classes");
                    Toast.makeText(getContext(),"Free Classes",Toast.LENGTH_SHORT).show();
                }else {
                    free_classes.animate().alpha(0);
                    free_classes.setVisibility(View.GONE);
                    l_layout.setVisibility(View.VISIBLE);
                    l_layout.animate().alpha(1);
//                    toolbar.setTitle(bundle.get("name").toString());

                }
            }
        });
        return rootView;
    }
}
