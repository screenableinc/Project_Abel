package com.example.ac.project_abel;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import java.util.Date;

/**
 * Created by AC on 9/16/2017.
 */

public class Wednesday extends Fragment {
    JSONArray classes;
    JSONObject free_classes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Bundle bundle = getArguments();


//        FloatingActionButton toggle = (FloatingActionButton)getActivity().findViewById(R.id.togg);
//        toggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                FrameLayout frameLayout  =(FrameLayout) mViewPager.getChildAt(0);
//                final LinearLayout free_classes = (LinearLayout) rootView.findViewById(R.id.free_classes);
//                final LinearLayout l_layout = (LinearLayout) rootView.findViewById(R.id.l_layout);
//                if (free_classes.getVisibility()==View.GONE){
//                    l_layout.animate().alpha(0);
//                    l_layout.setVisibility(View.GONE);
//                    free_classes.setVisibility(View.VISIBLE);
//                    free_classes.animate().alpha(1);
////                    toolbar.setTitle("Free Classes");
//                    Toast.makeText(getContext(),"Free Classes",Toast.LENGTH_SHORT).show();
//                }else {
//                    free_classes.animate().alpha(0);
//                    free_classes.setVisibility(View.GONE);
//                    l_layout.setVisibility(View.VISIBLE);
//                    l_layout.animate().alpha(1);
////                    toolbar.setTitle(bundle.get("name").toString());
//
//                }
//
//
//            }
//        });
        return Return_view(getContext(),bundle);

    }

    public View Return_view(Context context,Bundle bundle){
//        final Bundle bundle = getArguments();

//        ViewPager pager =(ViewPager) getActivity().findViewById(R.id.container);
//        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);



        final View rootView = new LoadView(context,"fragment_layout",bundle,getActivity()).ReturnRootView();;
        return rootView;
    }
}
