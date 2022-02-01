package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.gov.sa.swcc.Adapter.LeaveAdapter;
import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.TransactionsApiResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LeaveActivity extends AppCompatActivity {
Global global;
ListView LeaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        global=new Global(LeaveActivity.this);


        TextView Header=(TextView)findViewById(R.id.header);
        String text = "<font color=#004C86>خدمات الموارد البشرية /</font> <font color=#0066CC>الاجازات ومهام العمل</font>";
        Header.setText(Html.fromHtml(text));

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });

        LeaveList=(ListView) findViewById(R.id.LeaveList);
        try {
            if(global.CheckInternet(LeaveActivity.this)) {
            }else{
            CallLeaves();
            }
        }catch (Exception e){
Log.d("Error --------",e.toString());
        }





    }





    private void CallLeaves() throws IOException, XmlPullParserException {
        Date cDate = new Date();

        ProgressDialog dialog = ProgressDialog.show(LeaveActivity.this, "", "يرجى الإنتظار", true);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {

                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String request = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">\n" +
                            "        <soap:Header/>\n" +
                            "        <soap:Body>\n" +
                            "           <urn:ZhrLeavesInfo>\n" +
                            "              <IBegda>" + (year - 1) + "-01-01</IBegda>\n" +
                            "              <IEndda>" + (year + 1) + "-12-31</IEndda>\n" +
                            "              <IPernr>" + global.GetValue("Username") + "</IPernr>\n" +
                            "           </urn:ZhrLeavesInfo>\n" +
                            "        </soap:Body>\n" +
                            "     </soap:Envelope>";
                    URL url = new URL("https://l650075-iflmap.hcisbp.sa1.hana.ondemand.com/cxf/employeeLeavesService2MobileApp");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set timeout as per needs
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);

                    // Set DoOutput to true if you want to use URLConnection for output.
                    // Default is false
                    connection.setDoOutput(true);

                    connection.setUseCaches(true);
                    connection.setRequestMethod("POST");

                    // Set Headers
                    connection.setRequestProperty("Accept", "application/xml");
                    connection.setRequestProperty("Content-Type", "application/xml");
                    connection.setRequestProperty("Authorization", "Basic UDIwMDE3MTMzMTY6c2FwQDEyMzQ=");

                    // Write XML
                    OutputStream outputStream = connection.getOutputStream();
                    byte[] b = request.getBytes("UTF-8");
                    outputStream.write(b);
                    outputStream.flush();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    dialog.dismiss();


                    ArrayList<LeaveItems> leaveItems;
                    leaveItems = new ArrayList<>();
                    LeaveItems item = null;
                    XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = parserFactory.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(inputStream, null);
                    String tag = "", text = "";
                    int event = parser.getEventType();
                    while (event != XmlPullParser.END_DOCUMENT) {
                        tag = parser.getName();
                        switch (event) {
                            case XmlPullParser.START_TAG:
                                if (tag.equals("item"))
                                    item = new LeaveItems();
                                break;
                            case XmlPullParser.TEXT:
                                text = parser.getText();
                                break;
                            case XmlPullParser.END_TAG:
                                switch (tag) {
                                    case "Pernr":
                                        item.setPernr(text);
                                        break;
                                    case "VacationCode":
                                        item.setVacationCode(text);
                                        break;
                                    case "Begda":
                                        item.setBegda(text);
                                        break;
                                    case "Endda":
                                        item.setEndda(text);
                                        break;
                                    case "NoDays":
                                        item.setNoDays(text);
                                        break;
                                    case "item":
                                        if (item != null)
                                            leaveItems.add(item);
                                        break;
                                }
                                break;
                        }
                        event = parser.next();
                    }
runOnUiThread(new Runnable() {
    @Override
    public void run() {

int bus=0,nor=0;
        List<LeaveItems> neworder =new ArrayList<>(leaveItems.size());
        for (int i=0;i<leaveItems.size();i++){
            if(leaveItems.get(i).getVacationCode().toLowerCase().equals("ANNUAL_LEAVE".toLowerCase())){
                nor++;
            }
            if(leaveItems.get(i).getVacationCode().toLowerCase().equals("BUSINESS_TRIP".toLowerCase())){
                bus++;
            }
            neworder.add(leaveItems.get(leaveItems.size()-(i+1)));
        }
        List<LeaveItems> newq =new ArrayList<>(leaveItems.size());

        if(nor>0){

            LeaveItems leaveItems1=new LeaveItems();
            leaveItems1.setVacationCode("الاجازات الاعتيادية");
            leaveItems1.setBegda("Header");
            newq.add(leaveItems1);
            for (int i=0;i<neworder.size();i++){
                if(neworder.get(i).getVacationCode().toLowerCase().equals("ANNUAL_LEAVE".toLowerCase())){
                    newq.add(neworder.get(i));
                }

            }

        }

        if(bus>0){

            LeaveItems leaveItems1=new LeaveItems();
            leaveItems1.setVacationCode("مهام عمل");
            leaveItems1.setBegda("Header");
            newq.add(leaveItems1);
            for (int i=0;i<neworder.size();i++){
                if(neworder.get(i).getVacationCode().toLowerCase().equals("BUSINESS_TRIP".toLowerCase())){
                    newq.add(neworder.get(i));
                }

            }

        }







        LeaveAdapter adp = new LeaveAdapter(LeaveActivity.this, newq);

        LeaveList.setAdapter(adp);
    }
});


                }catch (Exception ex){

                    Log.d("Ex--++",ex.toString());
                }

            }
        });

    }



}
