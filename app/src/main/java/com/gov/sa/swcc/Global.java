package com.gov.sa.swcc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

public class Global {

    static Context context;



    public Global(Context context){
        this.context=context;
    }


    public  void SaveValue(String key,String value){

        SharedPreferences.Editor editor = context.getSharedPreferences("SWCCFile", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }


    public  String GetValue(String key){

        SharedPreferences prefs = context.getSharedPreferences("SWCCFile", Context.MODE_PRIVATE);
        String name = prefs.getString(key, "");
        return name;
    }


    public void ShowMessage(String Message){



        AlertDialog alertDialog1 = new AlertDialog.Builder(
               context).create();

        // Setting Dialog Title
        alertDialog1.setTitle("");

        // Setting Dialog Message
        alertDialog1.setMessage(Message);

        alertDialog1.setButton("موافق", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Showing Alert Message
        alertDialog1.show();

    }



}
