package com.example.ac.project_abel;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Wise on 1/10/2018.
 */

public class Performance extends Fragment {
    protected JSONObject results;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.performance, container, false);
        String course = getArguments().getString("course_code");


//        get CA for program
        SharedPreferences preferences =getContext().getSharedPreferences("details",MODE_PRIVATE);
        String ca = preferences.getString("ca",null);

//wise remember to opttimize this code its horrible and amatuerish
        final CircularProgressBar circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.progress_ca);
        final CircularProgressBar circularProgressBarass1 = (CircularProgressBar)rootView.findViewById(R.id.progress_ass1);
        final CircularProgressBar circularProgressBarass2 = (CircularProgressBar)rootView.findViewById(R.id.progress_ass2);
        final CircularProgressBar circularProgressBarprac = (CircularProgressBar)rootView.findViewById(R.id.progress_practical);
        final CircularProgressBar circularProgressBartest = (CircularProgressBar)rootView.findViewById(R.id.progress_test);
        final CircularProgressBar circularProgressBarmid = (CircularProgressBar)rootView.findViewById(R.id.progress_mid);
        try {
            results = new JSONObject(ca).getJSONObject(course);
            final String grade = results.getString("ca");String gradepct = ((Float.parseFloat(grade)/40)*100)+"";
            final String ass1 = results.getString("ass1");String ass1pct = ((Float.parseFloat(ass1)/10)*100)+"";
            final String ass2 = results.getString("ass2");String ass2pct = ((Float.parseFloat(ass2)/10)*100)+"";
            final String practical = results.getString("practical");String practicalpct = ((Float.parseFloat(practical)/10)*100)+"";
            final String test = results.getString("test");String testpct = ((Float.parseFloat(test)/10)*100)+"";
            final String mid = results.getString("mid");String midpct = ((Float.parseFloat(mid)/20)*100)+"";
//        optimize this section in next version
//            int[] ids = {R.id.grdtext,R.id.ass1text,R.id.ass2text,R.id.grdpractical,R.id.grdtest,R.id.grdmid};

            final ObjectAnimator animation = ObjectAnimator.ofFloat (circularProgressBar, "progress", 0, Float.parseFloat(gradepct)); // see this max value coming back here, we animale towards that value

            animation.setDuration (5000); //in milliseconds
            animation.setInterpolator (new DecelerateInterpolator());
            final TextView textView = (TextView) rootView.findViewById(R.id.grdtext);


            animation.start();textView.setText(grade);textView.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());

            final ObjectAnimator animationass1 = ObjectAnimator.ofFloat (circularProgressBarass1, "progress", 0, Float.parseFloat(ass1pct)); // see this max value coming back here, we animale towards that value
            animationass1.setDuration (5000); //in milliseconds
            animationass1.setInterpolator (new DecelerateInterpolator());
            final TextView textViewass1 = (TextView) rootView.findViewById(R.id.ass1text);


            animationass1.start();textViewass1.setText(ass1);textViewass1.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());

            final ObjectAnimator animationass2 = ObjectAnimator.ofFloat (circularProgressBarass2, "progress", 0, Float.parseFloat(ass2pct)); // see this max value coming back here, we animale towards that value
            animationass2.setDuration (5000); //in milliseconds
            animationass2.setInterpolator (new DecelerateInterpolator());
            final TextView textViewass2 = (TextView) rootView.findViewById(R.id.ass2text);


            animationass2.start();textViewass2.setText(ass2);textViewass2.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());

            final ObjectAnimator animationprac = ObjectAnimator.ofFloat (circularProgressBarprac, "progress", 0, Float.parseFloat(practicalpct)); // see this max value coming back here, we animale towards that value
            animationprac.setDuration (5000); //in milliseconds
            animationprac.setInterpolator (new DecelerateInterpolator());
            final TextView textViewprac = (TextView) rootView.findViewById(R.id.grdpractical);


            animationprac.start();textViewprac.setText(practical);textViewprac.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());

            final ObjectAnimator animationtest = ObjectAnimator.ofFloat (circularProgressBartest, "progress", 0, Float.parseFloat(testpct)); // see this max value coming back here, we animale towards that value
            animationtest.setDuration (5000); //in milliseconds
            animationtest.setInterpolator (new DecelerateInterpolator());
            final TextView textViewtest = (TextView) rootView.findViewById(R.id.grdtest);


            animationtest.start();textViewtest.setText(test);textViewtest.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());

            final ObjectAnimator animationmids = ObjectAnimator.ofFloat (circularProgressBarmid, "progress", 0, Float.parseFloat(midpct)); // see this max value coming back here, we animale towards that value
            animationmids.setDuration (5000); //in milliseconds
            animationmids.setInterpolator (new DecelerateInterpolator());
            final TextView textViewmids = (TextView) rootView.findViewById(R.id.grdmid);


            animationmids.start();textViewmids.setText(mid);textViewmids.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());

//

//            circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
//            circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
//            circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
//            int animationDuration = 2500; // 2500ms = 2,5s
//            circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms
//            circularProgressBar.addOnAttachStateChangeListener(new On);

        } catch (Exception e) {
            Log.w("CC", "failed "+e);
        }



        return rootView;
    }
}
