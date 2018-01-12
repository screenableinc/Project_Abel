package com.example.ac.project_abel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wise on 1/11/2018.
 */

public class FinalGrade extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String course = bundle.getString("course_code");
        SharedPreferences preferences = getContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        String details = preferences.getString("final",null);

        View rootView = inflater.inflate(R.layout.final_grade, container, false);
        try {
            JSONObject grades = new JSONObject(details);
            String grade = grades.getJSONObject(course).getString("grade");
            TextView gradeview = (TextView) rootView.findViewById(R.id.grade);
            gradeview.setText(grade);

        } catch (JSONException e) {
            TextView posted = (TextView) rootView.findViewById(R.id.posted);
            posted.setText("Not yet posted");
        }
        return rootView;
    }
}
