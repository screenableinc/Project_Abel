package com.example.ac.project_abel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.defaultValue;
import static android.R.attr.keyEdgeFlags;

public class Login extends AppCompatActivity{

    private static final String[] levels = {"Level","Undergraduate"};
    private static final String[] modes ={"Mode of Study","Full-Time","Part-Time","distance"};
    private String[] years = {"Year","1","2","3","4"};
    private String[] semesters = {"Semester","1","2"};


    private JSONObject fulltime_programs;
    private JSONObject parttime_programs;
    private JSONObject postgrad_programs;
    private JSONObject distance_programs;
    private JSONObject all_programs;
    private String selected_mode = "";
    private String selected_level = "";
    private String selected_program="";
    private String selected_year="";
    private String selected_semester = "";
    private String free_classes;
//    keep json of course codes and thier fullnames as values
    private JSONObject course_codes;
    private List<String> programs = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
//hide status bar ( this code is commented out because its messing with spinners)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//
//        }
        setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
        super.onCreate(savedInstanceState);
//        should put this in an async task to show user that data is loading

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);

        String name = sharedPref.getString("name", null);
        String classes = sharedPref.getString("classes", null);
        String selection = sharedPref.getString("selection",null);
//        Activity activity = this;
        Log.w("CC",selection+"jjjjjj");

        if (name!=null && classes!=null && selection!=null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }


        setContentView(R.layout.activity_login);


        final Spinner level = (Spinner) findViewById(R.id.level);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Login.this,android.R.layout.simple_spinner_item,levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        level.setAdapter(adapter);
        final Spinner mode = (Spinner) findViewById(R.id.mode);
        ArrayAdapter<String> mode_adapter = new ArrayAdapter<String>(Login.this,android.R.layout.simple_spinner_item,modes);
        mode_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mode.setAdapter(mode_adapter);

        final Spinner program = (Spinner) findViewById(R.id.program);
        final ArrayAdapter<String> program_adapter = new ArrayAdapter<String>(Login.this,android.R.layout.simple_spinner_item,programs);
        program_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        program.setAdapter(program_adapter);


        try{
            Resources resources = getResources();
            InputStreamReader read = new InputStreamReader(resources.openRawResource(R.raw.programs_scheds));
            BufferedReader reader = new BufferedReader(read);
            String string_json = reader.readLine();
//            free classes
            InputStreamReader read_f = new InputStreamReader(resources.openRawResource(R.raw.free_classes));
            BufferedReader reader_f = new BufferedReader(read_f);
            String string_json_f = reader_f.readLine();
            all_programs = new JSONObject(string_json);
            String[]keys = {"undergraduate","fulltime"};
            String[]ug_pt_keys = {"undergraduate","parttime"};
            String[]distance_keys={"undergraduate","distance"};
            String[]pg_keys = {"postgraduate"};
            fulltime_programs = new JSONObject(new JSONParser().parse(all_programs,keys));
            parttime_programs = new JSONObject(new JSONParser().parse(all_programs,ug_pt_keys));
            postgrad_programs = new JSONObject(new JSONParser().parse(all_programs,pg_keys));
            distance_programs = new JSONObject(new JSONParser().parse(all_programs,distance_keys));

            free_classes = string_json_f;

        }catch (Exception e){

        }
        final EditText _name = (EditText) findViewById(R.id.name);

        final LinearLayout year_sem = (LinearLayout) findViewById(R.id.year_sem);



        final Spinner year = (Spinner) findViewById(R.id.year);
        final Spinner semester = (Spinner) findViewById(R.id.semester);

        final ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(Login.this,android.R.layout.simple_spinner_item,years);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        year.setAdapter(year_adapter);

        final ArrayAdapter<String> semester_adapter = new ArrayAdapter<String>(Login.this,android.R.layout.simple_spinner_item,semesters);
        semester_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        semester.setAdapter(semester_adapter);
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_year = years[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_semester=semesters[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (levels[position]=="Undergraduate"){

                    mode.setVisibility(View.VISIBLE);
                    selected_level="Undergraduate";

                }else if (levels[position]=="Postgraduate(Masters)"){
                    mode.setVisibility(View.GONE);

                    program.setVisibility(View.VISIBLE);
                    programs.removeAll(programs);
//                    populate list
                    Iterator keys = postgrad_programs.keys();

                    while (keys.hasNext()){
                        String key = keys.next().toString();


                        key = new JSONParser().remove_last_chars(key, 1);

                        if(!programs.contains(key)){
                            programs.add(key);
                        }

//                         Log.w("CC",keys.next()+"kkkk");
                    }


//                    program.setOnItemClickListener();
//                    try{
//                        JSONObject jsonObject = new JSONObject();
//
//                    }
                }
                else {
                    mode.setVisibility(View.GONE);
                    program.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        View[] views = {program, mode,year,semester};
        for (int i = 0;i<views.length;i++){
            views[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    set_edit_text_to_inactive(_name);
                    return false;
                }
            });
        }

        mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                clear programs list

                programs.removeAll(programs);
                programs.add("Select program ...");
                if(modes[position] !="Mode of Study"){
                    if (modes[position]=="Full-Time"){
                        Iterator keys = fulltime_programs.keys();
                        selected_mode="fulltime";
                        while (keys.hasNext()){
                            String key = keys.next().toString();

                            if(!key.contains("ACCA")) {
                                key = new JSONParser().remove_last_chars(key, 2);

                                if(!programs.contains(key)){
                                programs.add(key);
                                }
                            }else {
                                if(!programs.contains(key)){
                                programs.add(key);}
                            }
                            program_adapter.notifyDataSetChanged();
//                         Log.w("CC",keys.next()+"kkkk");
                        }
                    }else if(modes[position]=="Part-Time"){

                            Iterator keys = parttime_programs.keys();
                            selected_mode="parttime";
                            while (keys.hasNext()){
                                String key = keys.next().toString();

                                if(!key.contains("ACCA")) {
                                    key = new JSONParser().remove_last_chars(key, 2);

                                    if(!programs.contains(key)){
                                        programs.add(key);
                                    }
                                }else {
                                    if(!programs.contains(key)){
                                        programs.add(key);}
                                }
//                         Log.w("CC",keys.next()+"kkkk");
                            }

                    }else if(modes[position]=="distance"){

                        Iterator keys = distance_programs.keys();
                        selected_mode="distance";
                        while (keys.hasNext()){
                            String key = keys.next().toString();
                            Log.w("CC",key);

                            if(!key.contains("ACCA")) {
                                key = new JSONParser().remove_last_chars(key, 2);

                                if(!programs.contains(key)){
                                    programs.add(key);
                                }
                            }else {
                                if(!programs.contains(key)){
                                    programs.add(key);}
                            }

//                            programs.notifyAll();
                            program_adapter.notifyDataSetChanged();
                         Log.w("CC",programs+"kkkk");
                        }

                    }
                    program.setVisibility(View.VISIBLE);

                }else{
                    program.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                set year and semester to ""

                selected_year="";selected_semester="";
                Log.w("CC",programs.get(position));
                if (!programs.get(position).contains("ACCA") && !programs.get(position).contains("Select")){
                    year_sem.setVisibility(View.VISIBLE);
                }else {
                    year_sem.setVisibility(View.GONE);
                }
                selected_program = programs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        login

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_name.getText().toString()!=""){
//                if key isnt found...........problem should be info
                if(selected_level=="Undergraduate" && selected_mode!=""&& selected_program!=""){

//                    pass json keys to get to object
                    String[] keys = {selected_level.toLowerCase(),selected_mode.toLowerCase(),selected_program+selected_year+selected_semester};

                    String returned_data = new JSONParser().parse(all_programs,keys);
                    if (returned_data!=null){
                        create_pref(_name.getText().toString(),returned_data,free_classes);
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    }else {


                        Toast.makeText(Login.this, "Field Error, please make corrections", Toast.LENGTH_SHORT).show();
                    }

                }


            }else{
                Toast.makeText(Login.this,"Enter name",Toast.LENGTH_SHORT).show();
            }
            }
        });

           }
    public void create_pref(String name, String json, String free_classes){
        Context context = getApplicationContext();

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("abel_file_key",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name",name);
        editor.putString("classes",json);
//        save keys to feed to api during update
        editor.putString("selection",selected_level.toLowerCase()+"---"+selected_mode.toLowerCase()+"---"+selected_program+selected_year+selected_semester);
        editor.putString("free_classes",free_classes);
        editor.commit();

    }
    public void set_edit_text_to_inactive(EditText v){
        v.setActivated(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
