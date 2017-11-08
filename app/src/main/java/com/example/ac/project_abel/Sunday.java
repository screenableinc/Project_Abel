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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by AC on 9/16/2017.
 */

public class Sunday extends Fragment {
    JSONArray classes;
    JSONObject free_classes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        return new LoadView(getActivity(),"fragment_layout",bundle,inflater,toolbar,getActivity()).ReturnRootView();

    }
}