package com.example.ac.project_abel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    Context context;
    LayoutInflater layoutInflater;
    public void setListener(View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup viewGroup = (ViewGroup) v.getParent();
//                viewGroup.removeView(v);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final SharedPreferences sharedPreferences = context.getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
                LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.test_assignm8_dialog,null);
                final View view = (View) v;
                try {
                    final JSONArray jsonArray = new JSONArray(sharedPreferences.getString("tests_and_ass",null));
                    final int index = (int) v.getTag();
                    JSONObject object = jsonArray.getJSONObject(index);

                    builder.setTitle("Delete ?")

//                            .setView(layout)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    jsonArray.remove(index);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("tests_and_ass",jsonArray.toString());
                                    editor.commit();
                                    viewGroup.removeView(view);

                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }catch (Exception e){

                }
            }
        });
    }
    public Toggle(View rootView, final Context context, int displayheight,
                  String room, String lecturer, final String course_text, final String code, String type){
//        int displayheight =manager.getWindowManager().getDefaultDisplay().getHeight();
        this.context = context;

        ScrollView scroll1 = (ScrollView) rootView.findViewById(R.id.scrollview1);
        final SharedPreferences sharedPreferences = context.getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);


        final LinearLayout class_dets = (LinearLayout) rootView.findViewById(R.id.class_dets);
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        if(class_dets.getVisibility()== View.VISIBLE){
            class_dets.animate().alpha(0);
            class_dets.setVisibility(View.GONE);

            fab.setVisibility(View.VISIBLE);
            fab.animate().alpha(1);
            return;
        }
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        this.layoutInflater = layoutInflater;
        final LinearLayout class_details=(LinearLayout) layoutInflater.inflate(R.layout.class_details,null);
        final LinearLayout tests = (LinearLayout) class_details.findViewById(R.id.tests);

        TextView roomnumb = (TextView) class_details.findViewById(R.id.roomnumb);
        TextView lect = (TextView) class_details.findViewById(R.id.lect);
        TextView course = (TextView) class_details.findViewById(R.id.classname);
        ImageView close = (ImageView) class_details.findViewById(R.id.close);
        ImageView add = (ImageView) class_details.findViewById(R.id.add_t_a);
        TextView code_ = (TextView) class_details.findViewById(R.id.code);
        TextView type_ = (TextView) class_details.findViewById(R.id.type);
        ScrollView scroll2 = (ScrollView) class_details.findViewById(R.id.scroll2);
        Button viewCourse = (Button) class_details.findViewById(R.id.viewcourse);
//        Calendar calendar = Calendar.getInstance();
//        this.year=calendar.get(Calendar.YEAR);
//        this.month=calendar.get(Calendar.MONTH);
//        this.day=calendar.get(Calendar.DAY_OF_MONTH);
        viewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CourseInfo.class).putExtra("course",code));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//        class_details.getLayoutParams().height=displayheight-(displayheight/4);
        scroll1.getLayoutParams().height=displayheight/4;
        ViewGroup.LayoutParams params = scroll2.getLayoutParams();
        params.height=displayheight-(displayheight/4);
        scroll2.setLayoutParams(params);
        LinearLayout words = (LinearLayout) class_details.findViewById(R.id.words);
//        Log.w("CC",words.getLayoutParams().height+"ppppppppp");
        ScrollView tests_scroll = (ScrollView) class_details.findViewById(R.id.tests_scroll);
        tests_scroll.getLayoutParams().height=displayheight - (displayheight-(displayheight/4));



        class_dets.setVisibility(View.VISIBLE);
        class_dets.animate().alpha(1);
        fab.animate().alpha(0);
        fab.setVisibility(View.INVISIBLE);



    }

    }

