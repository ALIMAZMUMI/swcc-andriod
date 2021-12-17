package com.gov.sa.swcc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.gov.sa.swcc.model.PersonalResult;

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


    public void SavePData(String key, PersonalResult value){

        SharedPreferences.Editor editor = context.getSharedPreferences("SWCCFile", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.commit();
        editor.apply();
    }


    public  PersonalResult GetPData(String key){

        SharedPreferences prefs = context.getSharedPreferences("SWCCFile", Context.MODE_PRIVATE);
        String name = prefs.getString(key, "");
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        PersonalResult obj = gson.fromJson(json, PersonalResult.class);

        return obj;
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
