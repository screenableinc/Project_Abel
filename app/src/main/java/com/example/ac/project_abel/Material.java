package com.example.ac.project_abel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wise on 1/10/2018.
 */

public class Material extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.material, container, false);
        Bundle bundle = getArguments();
        SharedPreferences preferences  = getContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        String material = preferences.getString(bundle.getString("course_code")+"_material",null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout holder = (LinearLayout) rootView.findViewById(R.id.holder);
        try {
            if(material!=null) {
                JSONArray array = new JSONArray(material);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    LinearLayout ass_lay = (LinearLayout) inflater.inflate(R.layout.material_ass, null);
                    final LinearLayout dialogue = (LinearLayout) inflater.inflate(R.layout.file_info,null);
                    String link = object.getString("link");
                    String year = object.getString("year");
//                    String _number = object.getString("number");
                    String description = object.getString("description");
                    String submitted = object.getString("submitted");
                    TextView number = (TextView) ass_lay.findViewById(R.id.number);
                    TextView filename = (TextView) ass_lay.findViewById(R.id.file_name);
//                    Button button = (Button) ass_lay.findViewById(R.id.button2);
                    TextView descript = (TextView) dialogue.findViewById(R.id.description);
                    TextView uploaded = (TextView) dialogue.findViewById(R.id.submitted);
//                    TextView h_number = (TextView) dialogue.findViewById(R.id.number);
                    descript.setText(description);uploaded.setText(submitted);
                    String file_name = link.split("/",-1)[link.split("/",-1).length-1];
                    ass_lay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.setTitle("Info")

                            .setView(dialogue)
                                    .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                        }
                                    })

                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
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
        return rootView;
    }
}
