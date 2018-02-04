package com.example.ac.project_abel;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

public class CourseInfo extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    protected String course_code;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(new ThemeSettings().ThemeSettings(getApplicationContext()));
        setContentView(R.layout.activity_course_info);

        course_code = getIntent().getStringExtra("course");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(course_code);
        setTitle(course_code);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
//        FloatingActionButton fab  = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int page = mViewPager.getCurrentItem();
////                new Refresh().execute(page);
//
//
//            }
//        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_info, menu);
        return true;
    }

    public class Refresh extends AsyncTask<Integer, Integer, String>{
        @Override
        protected String doInBackground(Integer... params) {

            int page =  params[0];
            Log.w("CC","caaled "+page);
//





            return null;
        }
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



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Bundle bundle = new Bundle();

            switch (position){

                case 0:
                    Performance performance = new Performance();

                    bundle.putString("course_code",course_code);
                    performance.setArguments(bundle);
                    return performance;
                case 1:
                    Material material = new Material();
                    bundle.putString("course_code",course_code);
                    material.setArguments(bundle);

                    return material;
                case 2:
                    Instructors in= new Instructors();
                    bundle.putString("course_code",course_code);
                    in.setArguments(bundle);
                    return in;
                case 3:
                    Assignments assignments= new Assignments();
                    bundle.putString("course_code",course_code);
                    assignments.setArguments(bundle);
                    return assignments;
                case 4:
                    FinalGrade finalGrade= new FinalGrade();
                    bundle.putString("course_code",course_code);
                    finalGrade.setArguments(bundle);
                    return finalGrade;
                default:
                    return null;
            }
//            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Performance";
                case 1:
                    return "Material";
                case 2:
                    return "Instructors";
                case 3:
                    return "Assignments";
                case 4:
                    return "Final Grade";
            }
            return null;
        }
    }
}
