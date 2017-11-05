package com.example.ac.project_abel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Wise on 29/10/2017.
 */

public class Toggle {
    int year;
    int month;
    int day;
    String type_spinner_val;
    public Toggle(View rootView, final Context context, int displayheight,
                  String room, String lecturer, final String course_text, String code, String type){
//        int displayheight =manager.getWindowManager().getDefaultDisplay().getHeight();
        ScrollView scroll1 = (ScrollView) rootView.findViewById(R.id.scrollview1);
        final SharedPreferences sharedPreferences = context.getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);


        final ScrollView class_dets = (ScrollView) rootView.findViewById(R.id.class_dets);
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        if(class_dets.getVisibility()== View.VISIBLE){
            class_dets.animate().alpha(0);
            class_dets.setVisibility(View.GONE);

            fab.setVisibility(View.VISIBLE);
            fab.animate().alpha(1);
            return;
        }
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final LinearLayout class_details=(LinearLayout) layoutInflater.inflate(R.layout.class_details,null);
        LinearLayout tests = (LinearLayout) class_details.findViewById(R.id.tests);
        try{
            JSONArray jsonArray = new JSONArray(sharedPreferences.getString("tests_and_ass",null));
            if(jsonArray.length()>0){
                TextView empty = (TextView) class_details.findViewById(R.id.textView6);
                empty.setVisibility(View.GONE);
            }

        for (int i = 0;i<jsonArray.length();i++){
            if(jsonArray.getJSONObject(i).getString("course").equals(course_text)){
                TextView textView = new TextView(context);

                String which = jsonArray.getJSONObject(i).getString("type");
                textView.setText(jsonArray.getJSONObject(i).getString("when")+" ("+which+")");
                tests.addView(textView);
            }
        }
        }
        catch (Exception e){
            Toast.makeText(context, "Loading error"+e, Toast.LENGTH_SHORT).show();
        }

        TextView roomnumb = (TextView) class_details.findViewById(R.id.roomnumb);
        TextView lect = (TextView) class_details.findViewById(R.id.lect);
        TextView course = (TextView) class_details.findViewById(R.id.classname);
        ImageView close = (ImageView) class_details.findViewById(R.id.close);
        ImageView add = (ImageView) class_details.findViewById(R.id.add_t_a);
        TextView code_ = (TextView) class_details.findViewById(R.id.code);
        TextView type_ = (TextView) class_details.findViewById(R.id.type);
        ScrollView scroll2 = (ScrollView) class_details.findViewById(R.id.scroll2);
        Calendar calendar = Calendar.getInstance();
        this.year=calendar.get(Calendar.YEAR);
        this.month=calendar.get(Calendar.MONTH);
        this.day=calendar.get(Calendar.DAY_OF_MONTH);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.test_assignm8_dialog,null);
                final EditText details = (EditText) layout.findViewById(R.id.optional_details);
                final TextView date = (TextView) layout.findViewById(R.id.textView3);
                final Spinner spinner = (Spinner) layout.findViewById(R.id.type);
                final String[] types = {"Assignment","Test"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,types);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type_spinner_val=types[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
//                date.setActivated(false);
                date.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        new DatePickerDialog(context, R.style.Theme_AppCompat_DayNight_Dialog, new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                view.getLayoutParams().height=200;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, day);

                                date.setText(day+"--"+month+"--"+year);
                                try{
                                    JSONArray jsonArray = new JSONArray(sharedPreferences.getString("tests_and_ass",null));
                                    JSONObject object = new JSONObject();
                                    object.put("when",day+"--"+month+"--"+year);
                                    object.put("type",type_spinner_val);
                                    object.put("course",course_text);
                                    object.put("details",details.getText().toString());
                                    jsonArray.put(object);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("tests_and_ass",jsonArray.toString());
                                    editor.commit();
                                }catch (Exception e){
                                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT);
                                }



                            }

                        }, year, month, day).show();
                        return false;
                    }


                });
                builder.setTitle("Details")

                        .setView(layout)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                add to preference key

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


                Toast.makeText(context,"Feature not yet available",Toast.LENGTH_SHORT).show();
            }
        });

        roomnumb.setText(room);lect.setText(lecturer);course.setText(course_text);type_.setText(type);code_.setText(code);
        class_dets.removeAllViews();
        class_dets.addView(class_details);
        close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                class_dets.animate().alpha(0);
                class_dets.setVisibility(View.GONE);

                fab.setVisibility(View.VISIBLE);
                fab.animate().alpha(1);
                return false;
            }
        });

//
//                        scroll1.animate().scaleY(-5);
        class_details.getLayoutParams().height=displayheight-(displayheight/4);
        scroll1.getLayoutParams().height=displayheight/4;


        class_dets.setVisibility(View.VISIBLE);
        class_dets.animate().alpha(1);
        fab.animate().alpha(0);
        fab.setVisibility(View.INVISIBLE);



    }

    }

