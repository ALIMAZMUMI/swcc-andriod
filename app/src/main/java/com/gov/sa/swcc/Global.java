package com.gov.sa.swcc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.gov.sa.swcc.model.PersonalResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

    public void ShowMessageF(String Message, Activity activity){

        AlertDialog alertDialog1 = new AlertDialog.Builder(
                context).create();

        // Setting Dialog Title
        alertDialog1.setTitle("");

        // Setting Dialog Message
        alertDialog1.setMessage(Message);

        alertDialog1.setButton("موافق", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });

        // Showing Alert Message
        alertDialog1.show();

    }

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    private static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }



    public  Long getDate(String key) {
        SharedPreferences prefs = context.getSharedPreferences("SWCCFile", Context.MODE_PRIVATE);

        return  prefs.getLong(key + "_value", 0);
    }

    public  void putDate(String key,Long date) {
        SharedPreferences.Editor editor = context.getSharedPreferences("SWCCFile", Context.MODE_PRIVATE).edit();
        editor.putLong(key + "_value", date).apply();
        editor.commit();
    }


    public boolean CheckInternet( Activity activity){

        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
return false;
        } else {
            ShowMessageF("فضلا تأكد من اتصالك بالإنترنت", activity);
return true;
        }
    }

    public boolean CheckInternet(){

        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            return false;
        } else {
            ShowMessage("فضلا تأكد من اتصالك بالإنترنت");
            return true;
        }
    }




    public String GetEmail(String Bagde){


        try {
            InputStream is = context.getAssets().open("accounts_and_emails.txt");

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer);
String [] Emp=text.split("#%#");

for(int i=0;i<Emp.length;i++){
    String [] E=Emp[i].split("#");
    if(E.length==3){
    String Ba=E[0].replaceAll("#","");
    String Email=E[2].replaceAll("#","");

        if(Ba.toLowerCase().contains(Bagde.toLowerCase())){

            return Email;
    }

    }

}
            // Finally stick the string into the text view.
            // Replace with whatever you need to have the text into.

return "";

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public List<String> GetEmps(String Name){
        List<String> Employee=new ArrayList<>();

        try {
            InputStream is = context.getAssets().open("name_and_phone.txt");

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer);
            String [] Emp=text.split("#%#");

            for(int i=0;i<Emp.length;i++){
                if(Emp[i].contains(Name)){
                    Employee.add(Emp[i]);
                }
            }
            // Finally stick the string into the text view.
            // Replace with whatever you need to have the text into.

            return Employee;

        } catch (IOException e) {
            return Employee;
        }
    }


}
