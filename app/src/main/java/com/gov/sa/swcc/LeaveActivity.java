package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;


import com.gov.sa.swcc.Adapter.LeaveAdapter;
import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.LeaveItems;

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

public class LeaveActivity extends AppCompatActivity {
Global global;
ListView LeaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        global=new Global(LeaveActivity.this);
        LeaveList=(ListView) findViewById(R.id.LeaveList);
        try {
            CallLeaves();
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
        LeaveAdapter adp = new LeaveAdapter(LeaveActivity.this, leaveItems);

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
