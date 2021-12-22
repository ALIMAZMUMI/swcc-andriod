package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.LeaveAdapter;
import com.gov.sa.swcc.Adapter.PaysilpAdapter;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.PayslipItem;
import com.gov.sa.swcc.model.PersonalResult;

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

public class PayslipActivity extends AppCompatActivity {

    TextView DocType99,DateinHigri99,EmpName99,Payrole99,NatID99,header99;
    String DocT99,DateinH99,EName99,Payr99,NID99,NetPay;
LinearLayout sectionpay99;
    ListView Payrolelist99;
    ArrayList<PayslipItem> payslipItems;


    TextView DocType0001,DateinHigri0001,EmpName0001,Payrole0001,NatID0001,header0001;
    String DocT0001,DateinH0001,EName0001,Payr0001,NID0001;
    LinearLayout sectionpay0001;
    ListView Payrolelist0001;
    ArrayList<PayslipItem> payslipItems0001;

    TextView DocType0002,DateinHigri0002,EmpName0002,Payrole0002,NatID0002,header0002;
    String DocT0002,DateinH0002,EName0002,Payr0002,NID0002;
    LinearLayout sectionpay0002;
    ListView Payrolelist0002;
    ArrayList<PayslipItem> payslipItems0002;


    ImageView prev,next;
    TextView saldate;



    Global global;
    PersonalResult per;




    String SelDate;
    Calendar c,Current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip);
        global=new Global(PayslipActivity.this);

        DocType99=(TextView) findViewById(R.id.DocType99);
        DateinHigri99=(TextView) findViewById(R.id.DateinHigri99);
        EmpName99=(TextView) findViewById(R.id.EmpName99);
        Payrole99=(TextView) findViewById(R.id.Payrole99);
        NatID99=(TextView) findViewById(R.id.NatID99);
        Payrolelist99=(ListView) findViewById(R.id.Payrolelist99);
        sectionpay99=(LinearLayout)findViewById(R.id.sectionpay99);
        header99=(TextView)findViewById(R.id.header99);
        //----------------------------
        DocType0001=(TextView) findViewById(R.id.DocType0001);
        DateinHigri0001=(TextView) findViewById(R.id.DateinHigri0001);
        EmpName0001=(TextView) findViewById(R.id.EmpName0001);
        Payrole0001=(TextView) findViewById(R.id.Payrole0001);
        NatID0001=(TextView) findViewById(R.id.NatID0001);
        Payrolelist0001=(ListView) findViewById(R.id.Payrolelist0001);
        sectionpay0001=(LinearLayout)findViewById(R.id.sectionpay0001);
        header0001=(TextView)findViewById(R.id.header0001);
        //---------------------------
        DocType0002=(TextView) findViewById(R.id.DocType0002);
        DateinHigri0002=(TextView) findViewById(R.id.DateinHigri0002);
        EmpName0002=(TextView) findViewById(R.id.EmpName0002);
        Payrole0002=(TextView) findViewById(R.id.Payrole0002);
        NatID0002=(TextView) findViewById(R.id.NatID0002);
        Payrolelist0002=(ListView) findViewById(R.id.Payrolelist0002);
        sectionpay0002=(LinearLayout)findViewById(R.id.sectionpay0002);
        header0002=(TextView)findViewById(R.id.header0002);

         c = Calendar.getInstance();
        Current=Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(day>20){
            SelDate=sdf.format(c.getTime());
        }else {
            c.add(Calendar.MONTH,-1);
            SelDate=sdf.format(c.getTime());
        }
        String date = day + "/" + (month+1) + "/" + year;

        saldate=(TextView)findViewById(R.id.saldate);
        next=(ImageView) findViewById(R.id.next);
        prev=(ImageView) findViewById(R.id.prev);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.MONTH,1);
                SelDate=sdf.format(c.getTime());
                if(Current.getTimeInMillis()>=c.getTimeInMillis()){
                    try {
                        sectionpay99.setVisibility(View.GONE);
                        sectionpay0001.setVisibility(View.GONE);
                        sectionpay0002.setVisibility(View.GONE);

                        CallPayslip("99",SelDate);
                        CallPayslip("0001",SelDate);
                        CallPayslip("0002",SelDate);
                        saldate.setText(SelDate.substring(0,SelDate.length()-3));
                    }catch (Exception e){
                        Log.d("Error --------",e.toString());
                    }

                }else {
                    c.add(Calendar.MONTH,-1);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.MONTH,-1);
                SelDate=sdf.format(c.getTime());
                    try {
                        sectionpay99.setVisibility(View.GONE);
                        sectionpay0001.setVisibility(View.GONE);
                        sectionpay0002.setVisibility(View.GONE);

                        CallPayslip("99",SelDate);
                        CallPayslip("0001",SelDate);
                        CallPayslip("0002",SelDate);
                        saldate.setText(SelDate.substring(0,SelDate.length()-3));

                    }catch (Exception e){
                        Log.d("Error --------",e.toString());
                    }
            }
        });
        per=global.GetPData("PersonalResult");




        try {
            sectionpay99.setVisibility(View.GONE);
            sectionpay0001.setVisibility(View.GONE);
            sectionpay0002.setVisibility(View.GONE);

            CallPayslip("99",SelDate);
            CallPayslip("0001",SelDate);
            CallPayslip("0002",SelDate);
            saldate.setText(SelDate.substring(0,SelDate.length()-3));

        }catch (Exception e){
            Log.d("Error --------",e.toString());
        }



    }

    private void CallPayslip(String Type,String Date) throws IOException, XmlPullParserException {
        Date cDate = new Date();

        ProgressDialog dialog = ProgressDialog.show(PayslipActivity.this, "", "يرجى الإنتظار", true);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {

                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String request = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">\n" +
                            "   <soap:Header/>\n" +
                            "   <soap:Body>\n" +
                            "      <urn:ZhrEmpPayslip>\n" +
                            "         <EmployeeId>" + global.GetValue("Username") + "</EmployeeId>\n" +
                            "         <PayrollDate>"+Date+"</PayrollDate>\n" +
                            "         <!--Optional:-->\n" +
                            "         <PayrollType>"+Type+"</PayrollType>\n" +
                            "      </urn:ZhrEmpPayslip>\n" +
                            "   </soap:Body>\n" +
                            "</soap:Envelope>";


                    URL url = new URL("https://l650075-iflmap.hcisbp.sa1.hana.ondemand.com/cxf/employeePayslipService2MobileApp");
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

                    if(Type.equals("99")){

                    payslipItems = new ArrayList<>();
                    PayslipItem item = null;
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
                                    item = new PayslipItem();
                                break;
                            case XmlPullParser.TEXT:
                                text = parser.getText();
                                break;
                            case XmlPullParser.END_TAG:
                                switch (tag) {
                                    case "EmployeeName":
                                        EName99=text;
                                        break;
                                    case "IdentityNo":
                                        NID99=text;
                                        break;
                                    case "HijriYear":
                                       DateinH99=text;
                                        break;
                                    case "ConsolidationId":
                                        DocT99=text;
                                        break;
                                    case "NetPay":
                                        NetPay=text;
                                        break;
                                    case "ElementCode":
                                        item.setElementCode(text);
                                        break;
                                    case "ElementText":
                                        Log.d("ElementText",text);
                                        item.setElementText(text);
                                        break;
                                    case "Amount":
                                        item.setAmount(text);
                                        break;
                                    case "Classification":
                                        item.setClassification(text);
                                        break;

                                    case "item":
                                        if (item != null)
                                            payslipItems.add(item);
                                        break;
                                }
                                break;
                        }
                        event = parser.next();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(payslipItems.size()>0){

//                            PayslipItem item=new PayslipItem();
//                            item.setElementCode("المجموع");
//                            item.setElementText("المجموع");
//                            item.setAmount(NetPay);
//                            item.setClassification("TOTAL");

                           // payslipItems.add(item);
                            payslipItems=Arrange(payslipItems);
                            PaysilpAdapter adp = new PaysilpAdapter(PayslipActivity.this, payslipItems);
                            DocType99.setText("الراتب مسير");
                            DateinHigri99.setText(DateinH99);
                            EmpName99.setText(per.getResultObject().getFullName());
                            Payrole99.setText("فارغ");
                            NatID99.setText(NID99);



                            Payrolelist99.setAdapter(adp);

                                getHeight(adp,Payrolelist99);
                            header99.setText("مسير الراتب :"+Date.substring(0,Date.length()-3));
                            sectionpay99.setVisibility(View.VISIBLE);}
                        }
                    });

                    }else if(Type.equals("0001")){

                        payslipItems0001 = new ArrayList<>();
                        PayslipItem item = null;
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
                                        item = new PayslipItem();
                                    break;
                                case XmlPullParser.TEXT:
                                    text = parser.getText();
                                    break;
                                case XmlPullParser.END_TAG:
                                    switch (tag) {
                                        case "EmployeeName":
                                            EName0001=text;
                                            break;
                                        case "IdentityNo":
                                            NID0001=text;
                                            break;
                                        case "HijriYear":
                                            DateinH0001=text;
                                            break;
                                        case "ConsolidationId":
                                            DocT0001=text;
                                            break;
                                        case "NetPay":
                                            NetPay=text;
                                            break;
                                        case "ElementCode":
                                            item.setElementCode(text);
                                            break;
                                        case "ElementText":
                                            Log.d("ElementText",text);
                                            item.setElementText(text);
                                            break;
                                        case "Amount":
                                            item.setAmount(text);
                                            break;
                                        case "Classification":
                                            item.setClassification(text);
                                            break;

                                        case "item":
                                            if (item != null)
                                                payslipItems0001.add(item);
                                            break;
                                    }
                                    break;
                            }
                            event = parser.next();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(payslipItems0001.size()>0){
//                            PayslipItem item=new PayslipItem();
//                            item.setElementCode("المجموع");
//                            item.setElementText("المجموع");
//                            item.setAmount(NetPay);
//                            item.setClassification("TOTAL");

                                // payslipItems.add(item);
                                payslipItems0001=Arrange(payslipItems0001);
                                PaysilpAdapter adp = new PaysilpAdapter(PayslipActivity.this, payslipItems0001);
                                DocType0001.setText("العمل الإضافي مسير");
                                DateinHigri0001.setText(DateinH0001);
                                EmpName0001.setText(per.getResultObject().getFullName());
                                Payrole0001.setText("فارغ");
                                NatID0001.setText(NID0001);

                                Payrolelist0001.setAdapter(adp);
                                    getHeight(adp,Payrolelist0001);

                                    sectionpay0001.setVisibility(View.VISIBLE);
                                header0001.setText("مسير العمل الإضافي :"+Date.substring(0,Date.length()-3));}
                            }
                        });

                    }else if(Type.equals("0002")){

                        payslipItems0002 = new ArrayList<>();
                        PayslipItem item = null;
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
                                        item = new PayslipItem();
                                    break;
                                case XmlPullParser.TEXT:
                                    text = parser.getText();
                                    break;
                                case XmlPullParser.END_TAG:
                                    switch (tag) {
                                        case "EmployeeName":
                                            EName0002=text;
                                            break;
                                        case "IdentityNo":
                                            NID0002=text;
                                            break;
                                        case "HijriYear":
                                            DateinH0002=text;
                                            break;
                                        case "ConsolidationId":
                                            DocT0002=text;
                                            break;
                                        case "NetPay":
                                            NetPay=text;
                                            break;
                                        case "ElementCode":
                                            item.setElementCode(text);
                                            break;
                                        case "ElementText":
                                            Log.d("ElementText",text);
                                            item.setElementText(text);
                                            break;
                                        case "Amount":
                                            item.setAmount(text);
                                            break;
                                        case "Classification":
                                            item.setClassification(text);
                                            break;

                                        case "item":
                                            if (item != null)
                                                payslipItems.add(item);
                                            break;
                                    }
                                    break;
                            }
                            event = parser.next();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(payslipItems0002.size()>0){

//                            PayslipItem item=new PayslipItem();
//                            item.setElementCode("المجموع");
//                            item.setElementText("المجموع");
//                            item.setAmount(NetPay);
//                            item.setClassification("TOTAL");

                                // payslipItems.add(item);
                                payslipItems=Arrange(payslipItems);
                                PaysilpAdapter adp = new PaysilpAdapter(PayslipActivity.this, payslipItems);
                                DocType0002.setText("الإنتدابات مسير");
                                DateinHigri0002.setText(DateinH0002);
                                EmpName0002.setText(per.getResultObject().getFullName());
                                Payrole0002.setText("فارغ");
                                NatID0002.setText(NID0002);

                                Payrolelist0002.setAdapter(adp);
                                    getHeight(adp,Payrolelist0002);

                                    header0002.setText("مسير الإنتدابات : "+ Date.substring(0,Date.length()-3));}
                            }
                        });

                    }


                }catch (Exception ex){

                    Log.d("Ex--++",ex.toString());
                }

            }
        });

    }


    public ArrayList<PayslipItem> Arrange(ArrayList<PayslipItem> Payin){
        ArrayList<PayslipItem> payout=new ArrayList<>(Payin.size());

        for (PayslipItem pay:Payin) {
            if(pay.getClassification().equals("Paid")&&!pay.getElementText().equals("Payment")){
                payout.add(pay);
            }
        }
        for (PayslipItem pay:Payin) {
            if(pay.getClassification().equals("Deduction")){
                payout.add(pay);
            }
        }
        for (PayslipItem pay:Payin) {
            if(pay.getElementText().equals("Payment")){
                payout.add(pay);
            }
        }
return payout;
    }


    public void getHeight(PaysilpAdapter listadp,ListView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (listadp.getCount() - 1));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }






}