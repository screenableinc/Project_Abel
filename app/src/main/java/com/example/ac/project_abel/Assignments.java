package com.example.ac.project_abel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Wise on 1/12/2018.
 */

public class Assignments extends Fragment {
    protected View rootView;
    protected Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.assignments, container, false);

        this.bundle = getArguments();
        LoadView();

        return rootView;
    }
    private void LoadView(){
        SharedPreferences preferences  = getContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        String material = preferences.getString("assignments",null);
        Log.w("CC",bundle.getString("course_code")+" "+material);
        FloatingActionButton refresh = (FloatingActionButton) rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Refreshing",Toast.LENGTH_SHORT).show();
                new Refresh().execute();
            }
        });

        LinearLayout holder = (LinearLayout) rootView.findViewById(R.id.holder);
        holder.removeAllViews();
        try {
            if(material!=null) {
                JSONArray array = new JSONArray(material);
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    LinearLayout ass_lay = (LinearLayout) inflater.inflate(R.layout.material_ass, null);

                    final String link = object.getString("link");
                    String year = object.getString("year");
//                    String _number = object.getString("number");
                    final String description = object.getString("description");
                    final String submitted = object.getString("sub_date");
                    final String due_date = object.getString("due_date");
                    TextView number = (TextView) ass_lay.findViewById(R.id.number);
                    TextView filename = (TextView) ass_lay.findViewById(R.id.file_name);
//                    Button button = (Button) ass_lay.findViewById(R.id.button2);

//                    TextView h_number = (TextView) dialogue.findViewById(R.id.number);
                    String file_name = link.split("/",-1)[link.split("/",-1).length-1];
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    ass_lay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            dialogue.removeView(sd);
                            final LinearLayout dialogue = (LinearLayout) layoutInflater.inflate(R.layout.file_info,null);
                            TextView descript = (TextView) dialogue.findViewById(R.id.description);
                            TextView uploaded = (TextView) dialogue.findViewById(R.id.submitted);
                            TextView due = (TextView) dialogue.findViewById(R.id.due);
                            due.setVisibility(View.VISIBLE);



                            descript.setText(description);uploaded.setText(submitted);due.setText(due_date);
                            try {


                                builder.setTitle("Info")

                                        .setView(dialogue)

                                        .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(new Globals().host+link.replace("../","/")));
                                                startActivity(openBrowser);


                                            }
                                        })

                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
                                            }
                                        }).show();
                            }catch (Exception e){

                            }
                        }
                    });

                    number.setText((i + 1) + "");
                    filename.setText(file_name);
                    holder.addView(ass_lay);


                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
            Log.w("CC",e.toString());
        }

    }
    public class Refresh extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            LoadView();
        }

        @Override
        protected String doInBackground(String... params) {
            new MiscEvents().GetAssignments(getActivity(),bundle.getString("course_code"),getActivity());
            return null;
        }
    }
}
