package com.example.ac.project_abel;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wise on 29/10/2017.
 */

public class Toggle {
    public Toggle(View rootView, final Context context, int displayheight,
                  String room, String lecturer, String course_text,String code, String type){
//        int displayheight =manager.getWindowManager().getDefaultDisplay().getHeight();
        ScrollView scroll1 = (ScrollView) rootView.findViewById(R.id.scrollview1);


        final ScrollView class_dets = (ScrollView) rootView.findViewById(R.id.class_dets);
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        if(class_dets.getVisibility()== View.VISIBLE){
            class_dets.animate().alpha(0);
            class_dets.setVisibility(View.GONE);

            fab.setVisibility(View.VISIBLE);
            fab.animate().alpha(1);
            return;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        LinearLayout class_details=(LinearLayout) layoutInflater.inflate(R.layout.class_details,null);


        TextView roomnumb = (TextView) class_details.findViewById(R.id.roomnumb);
        TextView lect = (TextView) class_details.findViewById(R.id.lect);
        TextView course = (TextView) class_details.findViewById(R.id.classname);
        ImageView close = (ImageView) class_details.findViewById(R.id.close);
        ImageView add = (ImageView) class_details.findViewById(R.id.add_t_a);
        TextView code_ = (TextView) class_details.findViewById(R.id.code);
        TextView type_ = (TextView) class_details.findViewById(R.id.type);
        ScrollView scroll2 = (ScrollView) class_details.findViewById(R.id.scroll2);
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
        class_details.getLayoutParams().height=displayheight-(displayheight/4);
        scroll1.getLayoutParams().height=displayheight/4;


        class_dets.setVisibility(View.VISIBLE);
        class_dets.animate().alpha(1);
        fab.animate().alpha(0);
        fab.setVisibility(View.INVISIBLE);



    }

    }

