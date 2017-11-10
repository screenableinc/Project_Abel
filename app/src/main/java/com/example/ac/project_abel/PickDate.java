package com.example.ac.project_abel;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Wise on 11/4/2017.
 */

public class PickDate extends DialogFragment implements DatePicker.OnDateChangedListener{
    Context context;
    public PickDate(){
        this.context = context;
    }

    @NonNull
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return super.onCreateDialog(savedInstanceState);


    }

    @Override
    public void onDateChanged(DatePicker view, int _year, int monthOfYear, int dayOfMonth) {
        // Use the current date as the default date in the picker


        // Create a new instance of DatePickerDialog and return it
        return;
    }

}
