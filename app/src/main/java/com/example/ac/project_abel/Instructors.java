package com.example.ac.project_abel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wise on 1/10/2018.
 */

public class Instructors extends Fragment{
    protected View rootView;
    protected String course;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        course = bundle.getString("course_code");


        rootView = inflater.inflate(R.layout.instructors, container, false);
        LoadView();


        return rootView;
    }
    private void LoadView(){
        SharedPreferences preferences = getContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        String details = preferences.getString("contact",null);
        FloatingActionButton refresh = (FloatingActionButton) rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Refreshing",Toast.LENGTH_SHORT).show();
                new Refresh().execute();

            }
        });
        try {
            JSONArray jsonObject = new JSONArray(details);
//            LinearLayout linearLayout =(LinearLayout) inflater.inflate(R.layout.lect_contact,null);
            LinearLayout contactarea = (LinearLayout) rootView.findViewById(R.id.contact_area);
//            loop through this array
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            contactarea.removeAllViews();
            for (int i = 0; i < jsonObject.length(); i++) {
                if(jsonObject.getJSONObject(i).getString("course").equals(course)){
                    final String phone_number = jsonObject.getJSONObject(i).getString("cell");
                    final String email_addr = jsonObject.getJSONObject(i).getString("email");
                    LinearLayout linearLayout =(LinearLayout) inflater.inflate(R.layout.lect_contact,null);
                    TextView name = (TextView) linearLayout.findViewById(R.id.lectsname);
                    TextView cell = (TextView) linearLayout.findViewById(R.id.cell);
                    TextView email = (TextView) linearLayout.findViewById(R.id.email);
                    ImageView phone_icon = (ImageView) linearLayout.findViewById(R.id.phone_icon);
                    phone_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+phone_number));
                            startActivity(intent);
                        }
                    });
                    ImageView email_icon = (ImageView) linearLayout.findViewById(R.id.email_icon);
                    email_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("plain/text");
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email_addr });
                            intent.putExtra(Intent.EXTRA_SUBJECT, " ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            startActivity(Intent.createChooser(intent, ""));
                        }
                    });
                    name.setText(jsonObject.getJSONObject(i).getString("name"));
                    cell.setText(phone_number);
                    email.setText(email_addr);
                    contactarea.addView(linearLayout);
                }
            }
            Log.w("CC",jsonObject.toString());
        } catch (JSONException e) {
            Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }
    }
    public class Refresh extends AsyncTask<Object,Integer,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            rootView.animate().alpha(0);


            LoadView();

        }

        @Override
        protected String doInBackground(Object... params) {

            new MiscEvents().GetLectContacts(getActivity(),getActivity());
            return null;
        }
    }
}
