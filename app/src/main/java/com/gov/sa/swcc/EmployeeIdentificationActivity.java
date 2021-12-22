package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.gov.sa.swcc.Adapter.LeaveAdapter;
import com.gov.sa.swcc.model.LeaveItems;
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

public class EmployeeIdentificationActivity extends Activity {


    String EmployeeId,
            EmployeeName,EmpNameAr
            ,IdentityNo
            ,Position,PositionAr
            ,Nationality,
            NatioAr,JoinDate
            ,Department,DepartmentAr;
    ImageView imageView2;
    Global global;
    PersonalResult per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_identification);
        global=new Global(EmployeeIdentificationActivity.this);
imageView2=(ImageView)findViewById(R.id.imageView2);


         per=global.GetPData("PersonalResult");

        try {
            CallGetSalary();

        }catch (Exception e){
            Log.d("Error --------",e.toString());
        }
    }

    private void CallGetSalary() throws IOException, XmlPullParserException {
        Date cDate = new Date();

        ProgressDialog dialog = ProgressDialog.show(EmployeeIdentificationActivity.this, "", "يرجى الإنتظار", true);

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
                            "      <urn:ZhrEmpIdentity>\n" +
                            "         <EmployeeId>"+global.GetValue("Username")+"</EmployeeId>\n" +
                            "      </urn:ZhrEmpIdentity>\n" +
                            "   </soap:Body>\n" +
                            "</soap:Envelope>";
                    URL url = new URL("https://l650075-iflmap.hcisbp.sa1.hana.ondemand.com/cxf/employeeIdentificationLetter2MobileApp");
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
//                                    item = new LeaveItems();
                                break;
                            case XmlPullParser.TEXT:
                                text = parser.getText();
                                break;
                            case XmlPullParser.END_TAG:
                                switch (tag) {

                                    case "EmployeeId":
                                        EmployeeId=text;
                                        break;
                                    case "EmployeeName":
                                        EmployeeName=text;
                                        break;
                                    case "EmpNameAr":
                                        EmpNameAr=text;
                                        break;
                                    case "IdentityNo":
                                        IdentityNo=text;
                                        break;
                                    case "Position":
                                        Position=text;
                                        break;
                                    case "PositionAr":
                                        PositionAr=text;
                                        break;
                                    case "Nationality":
                                        Nationality=text;
                                        break;
                                    case "NatioAr":
                                        NatioAr=text;
                                        break;
                                    case "JoinDate":
                                        JoinDate=text;
                                        break;
                                    case "Department":
                                        Department=text;
                                        break;
                                    case "DepartmentAr":
                                        DepartmentAr=text;
                                        break;




//                                    case "PositionAr":
//                                        if (item != null)
//                                            leaveItems.add(item);
//                                        break;
                                }
                                break;
                        }
                        event = parser.next();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            LeaveAdapter adp = new LeaveAdapter(LeaveActivity.this, leaveItems);
//
//                            LeaveList.setAdapter(adp);


                            BitmapDrawable b=  writeTextOnDrawable(R.drawable.selfservice1);

                            imageView2.setBackground(b);
                        }
                    });


                }catch (Exception ex){

                    Log.d("Ex--++",ex.toString());
                }

            }
        });

    }



    private BitmapDrawable writeTextOnDrawable(int drawableId) {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = ResourcesCompat.getFont(this, R.font.swcc);

        //Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.RIGHT);

        paint.setTextSize(convertToPixels(EmployeeIdentificationActivity.this, 21));

        Log.d("Image Size",bm.getHeight()+"------"+bm.getWidth());
        Rect textRect = new Rect();
        //paint.getTextBounds(text, 0, text.length(), textRect);

        Canvas canvas = new Canvas(bm);

        //If the text is bigger than the canvas , reduce the font size
//        if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
//            paint.setTextSize(convertToPixels(EmployeeIdentificationActivity.this, 7));        //Scaling needs to be used for different dpi's

        //Calculate the positions
        int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset

        //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        //Arabic Name

        canvas.drawText(EmpNameAr,3294, 1270, paint);
        canvas.drawText(global.GetValue("Username").replaceAll("u",""),3294, 1350, paint);
        canvas.drawText(IdentityNo,3294, 1450, paint);
        canvas.drawText(PositionAr,3294, 1550, paint);
        canvas.drawText(NatioAr,3294, 1650, paint);
        canvas.drawText(JoinDate,3294, 1750, paint);
        canvas.drawText("الموقع : "+per.getResultObject().getLocationAr(),3780, 798, paint);
        canvas.drawText("الإدارة : "+DepartmentAr,3780, 868, paint);


        Calendar Current=Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        canvas.drawText(sdf.format(Current.getTime()),3400, 470, paint);


        //English Name

        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(EmployeeName,670, 1270, paint);
        canvas.drawText(global.GetValue("Username").replaceAll("u",""),670, 1350, paint);
        canvas.drawText(IdentityNo,670, 1450, paint);
        canvas.drawText(Position,670, 1550, paint);
        canvas.drawText(Nationality,670, 1650, paint);
        canvas.drawText(JoinDate,670, 1750, paint);
        canvas.drawText("Location : "+per.getResultObject().getLocationEn(),50, 798, paint);
        canvas.drawText("Department : "+Department,50, 868, paint);
        float x=(float)1891.0;
        float y=(float)26.0;
        canvas.drawBitmap(writeTextOnRow(),y,x,null);
        return new BitmapDrawable(getResources(), bm);
    }




    private Bitmap writeTextOnRow() {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.selfservice2)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = ResourcesCompat.getFont(this, R.font.swcc);

        //Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.RIGHT);

        paint.setTextSize(convertToPixels(EmployeeIdentificationActivity.this, 21));

        Canvas canvas = new Canvas(bm);


        paint.setTextAlign(Paint.Align.RIGHT);

        canvas.drawText("بدل السكن",3780, 53, paint);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("4900",1930, 53, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Housing Allowance",50, 53, paint);

        return bm;
    }

    public static int convertToPixels(Context context, int nDP)
    {
        final float conversionScale = context.getResources().getDisplayMetrics().density;

        return (int) ((nDP * conversionScale) + 0.5f) ;

    }


    public float getdp(int pix){
        return pix / ((float) EmployeeIdentificationActivity.this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}