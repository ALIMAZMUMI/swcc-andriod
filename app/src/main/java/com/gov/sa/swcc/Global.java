package com.gov.sa.swcc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gov.sa.swcc.Adapter.SearchAdapter;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.SearchEmpItem;
import com.gov.sa.swcc.model.SearchItem;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Global {

    static Context context;
    public static Activity activity;



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

    public void ShowMessageT(String Message){


        Toast.makeText(context,Message,Toast.LENGTH_SHORT).show();



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

    public void ShowMessageLogout(String Message){



        AlertDialog alertDialog1 = new AlertDialog.Builder(
                context).create();

        // Setting Dialog Title
        alertDialog1.setTitle("");

        // Setting Dialog Message
        alertDialog1.setMessage(Message);

        alertDialog1.setButton("موافق", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                SaveValue("Home","N");
                SaveValue("Authentication","YY");
                SaveValue("Username","");
                SaveValue("Password","");
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
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



    public void ShowMessageNF1(String Message, Activity activity){


        this.activity=activity;
        Intent intent=new Intent(context,RequestMessageActivity1.class);
        intent.putExtra("Message",Message);
        context.startActivity(intent);

    }


    public void ShowMessageNF(String Message, Activity activity){


        this.activity=activity;
        Intent intent=new Intent(context,RequestMessageActivity.class);
        intent.putExtra("Message",Message);
        context.startActivity(intent);

    }

    public void ShowMessageNFH(String Message, Activity activity,String Header){


        this.activity=activity;
        Intent intent=new Intent(context,RequestMessageActivity.class);
        intent.putExtra("Message",Message);
        intent.putExtra("Header",Header);

        context.startActivity(intent);

    }

    public void ShowMessageNF(String Message,String Lan, Activity activity){


        this.activity=activity;
        Intent intent=new Intent(context,RequestMessageActivity.class);
        intent.putExtra("Message",Message);
        intent.putExtra("Lan",Message);

        context.startActivity(intent);

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


String Email="";
if(GetValue("Email").length()>5){
    return GetValue("Email");
}else{
    CallSearch(GetPData("PersonalResult").getResultObject().getFullName(),context);
}



return "";

    }

    private void CallSearch(String word,Context context) {



        Call<SearchEmpItem> call = RetrofitClient.getInstance(Api.Global).getMyApi().SwccEmps(word,"lg33fHafsghgd");
        PorgressDilog dialog =  new PorgressDilog((Activity) context);
        dialog.show();
        call.enqueue(new Callback<SearchEmpItem>() {
            @Override
            public void onResponse(Call<SearchEmpItem> call, Response<SearchEmpItem> response) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();

                if(response.isSuccessful())
                {

                    if(response.body().getResult().size()>0){


                        List<SearchItem> Detiles=new ArrayList<>();
                        for(int i=0;i<response.body().getResult().size();i++)
                        {
                          if(response.body().getResult().get(i).getUid().contains(GetValue("Username").replaceAll("u",""))){
                           SaveValue("Email",response.body().getResult().get(i).getEmail());
                        }
                        }

                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SearchEmpItem>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");
            }


        });
    }


    public String GetDateFormat(String Date){
        try {

            String strDate = Date;

            //current date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date objDate = dateFormat.parse(strDate);

            //Expected date format
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

            String finalDate = dateFormat2.format(objDate);
return finalDate;

        } catch (Exception e) {
            e.printStackTrace();
            return Date;

        }
    }

    public String GetTime(String Date){
        try {

            String strDate = Date;

            //current date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date objDate = dateFormat.parse(strDate);

            //Expected date format
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");

            String finalDate = dateFormat2.format(objDate);
            return finalDate;

        } catch (Exception e) {
            e.printStackTrace();
            return Date;

        }
    }


    public String GetTimess(String Date){
        try {

            String strDate = Date;

            //current date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date objDate = dateFormat.parse(strDate);

            //Expected date format
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");

            String finalDate = dateFormat2.format(objDate);
            return finalDate;

        } catch (Exception e) {
            e.printStackTrace();
            return Date;

        }
    }

    public boolean checktimeattend(String start, String end,String current) {

        String pattern = "HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(start);
            Date date2 = sdf.parse(end);
            Date date3 = sdf.parse(current);

            if(date3.after(date1) && date3.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException  e){
            e.printStackTrace();
        }
        return false;
    }

    public String DTxt(int days){
        if(days<0)
        days=Math.abs(days);

        if (days==0){
            return " يوم ";
        }else if (days<2){
            return " يومين ";

        }else if (days>2&&days<11){
            return " "+(days+" أيام ").replaceAll("-","");

        } else {
            return " "+(days+" يوم ").replaceAll("-","");
        }

    }

}

