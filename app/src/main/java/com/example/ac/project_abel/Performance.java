package com.example.ac.project_abel;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Wise on 1/10/2018.
 */

public class Performance extends Fragment {
    protected JSONObject results;
    protected View rootView;
    protected String course;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.performance, container, false);

        this.course = getArguments().getString("course_code");
        LoadView(course);




        return rootView;
    }
    private void LoadView(final String course){

//        get CA for program
        if (getContext()== null){
            return;
        }

        SharedPreferences preferences =getContext().getSharedPreferences("details",MODE_PRIVATE);
        String ca = preferences.getString("ca",null);
        Log.w("CC",ca.toString());




        FloatingActionButton refresh = (FloatingActionButton) rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Refreshing",Toast.LENGTH_SHORT).show();
                new Refresh().execute(rootView,course);
            }
        });




//wise remember to opttimize this code its horrible and amatuerish
        final CircularProgressBar circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.progress_ca);
        final CircularProgressBar circularProgressBarass1 = (CircularProgressBar)rootView.findViewById(R.id.progress_ass1);
//        final CircularProgressBar circularProgressBarass2 = (CircularProgressBar)rootView.findViewById(R.id.progress_ass2);
        final CircularProgressBar circularProgressBarprac = (CircularProgressBar)rootView.findViewById(R.id.progress_practical);
//        final CircularProgressBar circularProgressBartest = (CircularProgressBar)rootView.findViewById(R.id.progress_test);
        final CircularProgressBar circularProgressBarmid = (CircularProgressBar)rootView.findViewById(R.id.progress_mid);
        String grade="0.0";String test="0.0";
        String gradepct="0.0";
        String ass1 = "0.0";String ass1pct="0.0";
        String ass2="0.0";String ass2pct="0.0";String practical="0.0";String practicalpct = "0.0";
        String testpct="0.0";
        String mid="0.0";String midpct="0.0";
        try {
            results = new JSONObject(ca).getJSONObject(course);
            grade = results.getString("ca");gradepct=((Float.parseFloat(grade)/40)*100)+"";
             ass1= results.getString("ass1"); ass1pct= ((Float.parseFloat(ass1)/10)*100)+"";
//            ass2= results.getString("ass2"); ass2pct= ((Float.parseFloat(ass2)/10)*100)+"";
             practical= results.getString("practical");practicalpct = ((Float.parseFloat(practical)/10)*100)+"";
//            test = results.getString("test"); testpct= ((Float.parseFloat(test)/10)*100)+"";
           mid  = results.getString("mid");midpct = ((Float.parseFloat(mid)/20)*100)+"";
        } catch (Exception e) {
            Log.w("CC",e+" lllll "+course+" "+results);
            Toast.makeText(getActivity(),"Check registration",Toast.LENGTH_LONG).show();
        }
//
// optimize this section in next version
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


            final ObjectAnimator animationprac = ObjectAnimator.ofFloat (circularProgressBarprac, "progress", 0, Float.parseFloat(practicalpct)); // see this max value coming back here, we animale towards that value
            animationprac.setDuration (5000); //in milliseconds
            animationprac.setInterpolator (new DecelerateInterpolator());
            final TextView textViewprac = (TextView) rootView.findViewById(R.id.grdpractical);


            animationprac.start();textViewprac.setText(practical);textViewprac.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());


            final ObjectAnimator animationmids = ObjectAnimator.ofFloat (circularProgressBarmid, "progress", 0, Float.parseFloat(midpct)); // see this max value coming back here, we animale towards that value
            animationmids.setDuration (5000); //in milliseconds
            animationmids.setInterpolator (new DecelerateInterpolator());
            final TextView textViewmids = (TextView) rootView.findViewById(R.id.grdmid);


            animationmids.start();textViewmids.setText(mid);textViewmids.animate().alpha(1).setDuration(6000).setInterpolator(new DecelerateInterpolator());


    }

    public class Refresh extends AsyncTask<Object,Integer,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            rootView.animate().alpha(0);


            LoadView(course);

        }

        @Override
        protected String doInBackground(Object... params) {

            View root = (View) params[0];
            String course = (String) params[1];
            new MiscEvents().GetCA(getActivity(),root,course, getActivity());
            return null;
        }
    }
}
