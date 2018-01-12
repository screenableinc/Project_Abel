package com.example.ac.project_abel;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;


import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClassDetails extends AppCompatActivity {

    protected JSONObject results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_class_details);
        String course = getIntent().getStringExtra("course");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(course);
        setSupportActionBar(toolbar);
//        get CA for program
        SharedPreferences preferences = getSharedPreferences("details",MODE_PRIVATE);
        String ca = preferences.getString("ca",null);

        final CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.progress_ca);
        try {
            results = new JSONObject(ca).getJSONObject(course);
            final String grade = results.getString("ca");

            final ObjectAnimator animation = ObjectAnimator.ofFloat (circularProgressBar, "progress", 0, Float.parseFloat(grade)); // see this max value coming back here, we animale towards that value
            animation.setDuration (5000); //in milliseconds
            animation.setInterpolator (new DecelerateInterpolator ());
            final TextView textView = (TextView) findViewById(R.id.grdtext);

//            float dur =(float) 5000/(Float.parseFloat(grade)*10);
//            for (int i = 0; i < Float.parseFloat(grade)*10; i++) {
//                textView.setText((i/10)+"");textView.animate().alpha(0).setDuration(100);
//                textView.animate().alpha(1).setDuration(100);
//
//            }

            animation.start();textView.setText(grade);textView.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());


//            circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
//            circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
//            circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
//            int animationDuration = 2500; // 2500ms = 2,5s
//            circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms
//            circularProgressBar.addOnAttachStateChangeListener(new On);

        } catch (Exception e) {
            Log.w("CC", "failed "+e);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
