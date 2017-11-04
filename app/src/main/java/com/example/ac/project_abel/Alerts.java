package com.example.ac.project_abel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;

/**
 * Created by Wise on 30/10/2017.
 */

public class Alerts {
    String reset_status = "unconfirmed";
    public String Reset(Context context){

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, new ThemeSettings().ThemeSettings(context));
        } else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setTitle("Reset")
                .setMessage("Are you sure you want to Reset?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        reset_status="confirmed";

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)


                .show();
    return reset_status;
    }

}
