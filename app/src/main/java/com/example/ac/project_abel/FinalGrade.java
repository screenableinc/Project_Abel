package com.example.ac.project_abel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wise on 1/11/2018.
 */

public class FinalGrade extends Fragment {
    protected View rootView;
    protected String course;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        course = bundle.getString("course_code");
        rootView = inflater.inflate(R.layout.final_grade, container, false);
        LoadView();

        return rootView;
    }
    private void LoadView(){
        SharedPreferences preferences = getContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        String details = preferences.getString("final",null);
        FloatingActionButton refresh = (FloatingActionButton) rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Refreshing",Toast.LENGTH_SHORT).show();
                new Refresh().execute();

            }
        });

        try {
            JSONObject grades = new JSONObject(details);
            String grade = grades.getJSONObject(course).getString("grade");
            TextView gradeview = (TextView) rootView.findViewById(R.id.grade);
            gradeview.setText(grade);

        } catch (JSONException e) {
            TextView posted = (TextView) rootView.findViewById(R.id.posted);
            posted.setText("Not yet posted");
        }
    }
    public class Refresh extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LoadView();

        }

        @Override
        protected String doInBackground(String... params) {
            new MiscEvents().GetFinalResults(getActivity(),getActivity());
            return null;
        }
    }
}
