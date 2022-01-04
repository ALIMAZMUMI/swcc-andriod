package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.gov.sa.swcc.Adapter.LeaveAdapter;
import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.ITRequest;
import com.gov.sa.swcc.model.ITRoot;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.TransactionsApiResult;

import org.json.JSONArray;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ITComActivity extends AppCompatActivity {
Global global;
EditText title,detials;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itcom);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        global=new Global(ITComActivity.this);
        title=(EditText)findViewById(R.id.title);
        detials=(EditText)findViewById(R.id.detials);


        Button submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(title.getText().toString().length()>0&&detials.getText().toString().length()>0){

}
                try{
                    ITCom(title.getText().toString(),detials.getText().toString());
                }catch (Exception e){

                }
            }
        });


        TextView back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ITComActivity.this.finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });









    }



    private void CallITCom(String Title,String Detiles) {
        String user=global.GetValue("Username");
        PersonalResult per=global.GetPData("PersonalResult");
        ITRequest itRequest=new ITRequest();
        itRequest.setUserId(user);
        itRequest.setCity(per.getResultObject().getLocationAr());
        itRequest.setDepartment(per.getResultObject().getDepartment());

        itRequest.setFirstName(per.getResultObject().getFirstNameEn());
        itRequest.setLastName(per.getResultObject().getLastNameEn());
        itRequest.setPhone(per.getResultObject().getMobile());
        itRequest.setEmail("SAlHarbi780e@swcc.gov.sa");
itRequest.setTitle(Title);
        itRequest.setDescription(Detiles);


        JsonObject obj=new JsonObject();
        obj.addProperty("UserId", user);
        obj.addProperty("Description", Detiles);
        obj.addProperty("Phone", per.getResultObject().getMobile());
        obj.addProperty("FirstName", per.getResultObject().getFirstNameEn());
        obj.addProperty("LastName", per.getResultObject().getLastNameEn());
        obj.addProperty("Title", Title);
        obj.addProperty("Department", per.getResultObject().getDepartment());
        obj.addProperty("Email", "SAlHarbi780e@swcc.gov.sa");
        obj.addProperty("City", per.getResultObject().getLocationAr());

        String post="[{'UserId' : 'u106346'," +
                "'Description' : 'وصصصصصصغ'," +
                "'Phone' : '966507349987'," +
                "'FirstName' : 'SALMAN'," +
                "'LastName' : 'ALHARBI'," +
                "'Title' : 'عنواااااان'," +
                "'Department' : 'منظومة إنتاج الجبيل'," +
                "'Email' : 'SAlHarbi780e@swcc.gov.sa'," +
                "'City' : 'الرياض'}]";
        post=post.replaceAll("'","\"");
        //obj1.add("[]",);


        Call<String> call = RetrofitClient.getInstance(Api.Ticket).getMyApi().ITRequest(post);
        ProgressDialog dialog = ProgressDialog.show(ITComActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccessful())
                {

                    if(response.body().length()>0){
                        global.ShowMessage("تم ارسال الطلب بنجاح رقم الطلب "+response.body()+" تم ارسال نسخة لبريدك ");


                    }else {

                        global.ShowMessage("");
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


    private void ITCom(String Title,String Detiles) throws IOException, XmlPullParserException {


        String user=global.GetValue("Username");
        PersonalResult per=global.GetPData("PersonalResult");

        ProgressDialog dialog = ProgressDialog.show(ITComActivity.this, "", "يرجى الإنتظار", true);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {


                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String post="[{'UserId' : '"+user+"'," +
                            "'Description' : '"+detials+"'," +
                            "'Phone' : '"+per.getResultObject().getMobile()+"'," +
                            "'FirstName' : '"+per.getResultObject().getFirstNameEn()+"'," +
                            "'LastName' : '"+per.getResultObject().getLastNameEn()+"'," +
                            "'Title' : '"+Title+"'," +
                            "'Department' : '"+per.getResultObject().getDepartment()+"'," +
                            "'Email' : 'SAlHarbi780e@swcc.gov.sa'," +
                            "'City' : '"+per.getResultObject().getLocationAr()+"'}]";
                    post=post.replaceAll("'","\"");

                    URL url = new URL("https://apitest.swcc.gov.sa/swccmobile/api/FootPrint/CreateITRequest");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set timeout as per needs
                    connection.setConnectTimeout(60000);
                    connection.setReadTimeout(60000);

                    // Set DoOutput to true if you want to use URLConnection for output.
                    // Default is false
                    connection.setDoOutput(true);

                    connection.setUseCaches(true);
                    connection.setRequestMethod("POST");

                    // Set Headers
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("apitoken", "26EAADIT-310F-48E4-87C6-C827E73A4A00");

                    // Write XML
                    OutputStream outputStream = connection.getOutputStream();
                    byte[] b = post.getBytes("UTF-8");
                    outputStream.write(b);
                    outputStream.flush();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    dialog.dismiss();



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String line = "";
                            try {
                                line = bufferedReader.readLine();
                                if(connection.getResponseCode()==200){
                                    global.ShowMessageF("تم ارسال الطلب بنجاح رقم الطلب "+line+" تم ارسال نسخة لبريدك ",ITComActivity.this);
                                }else {
                                    global.ShowMessage("حدث مشكلة اثناء الاتصال");

                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });


                }catch (Exception ex){

                    Log.d("Ex--++",ex.toString());
                }

            }
        });

    }
}