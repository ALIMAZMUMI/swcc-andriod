package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.LeaveAdapter;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.PersonalResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
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

    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;

    String EmployeeId,
            EmployeeName,EmpNameAr
            ,IdentityNo
            ,Position,PositionAr
            ,Nationality,
            NatioAr,JoinDate
            ,Department,DepartmentAr,TotalSal;
    ArrayList<String> BArabic;
    ArrayList<String> BEnglish;
    ArrayList<String> BValue;

    ImageView imageView2;
    Global global;
    PersonalResult per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_identification);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        global=new Global(EmployeeIdentificationActivity.this);
imageView2=(ImageView)findViewById(R.id.imageView2);



        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });


         per=global.GetPData("PersonalResult");

        try {
            if(global.CheckInternet(EmployeeIdentificationActivity.this)) {
            }else {
            CallGetSalary();
            }

        }catch (Exception e){
            Log.d("Error --------",e.toString());
        }


        TextView share=(TextView) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStoragePermissionGranted()) {

                    if(EmployeeId!=null) {
                        // create a new document
                        PdfDocument document = new PdfDocument();

                        // create a page description
                        View content = findViewById(R.id.imageView2);

                        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(), content.getHeight(), 1).create();

                        // start a page
                        PdfDocument.Page page = document.startPage(pageInfo);


                        content.draw(page.getCanvas());


//                    // Draw the bitmap onto the page
//                    Canvas canvas = page.getCanvas();
//                    Bitmap bm = ((BitmapDrawable) imageView2.getBackground()).getBitmap();
//
//                    Paint paint = new Paint();
//                    canvas.drawBitmap(bm, 10f, 10f, paint);

                        document.finishPage(page);
                        File dir = new File(Environment.getExternalStoragePublicDirectory
                                (Environment.DIRECTORY_DOWNLOADS), "pdf");
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        try {
                            document.writeTo(new FileOutputStream(dir.getPath() + "/emp_Identification.pdf"));
                        } catch (IOException e) {
                            Log.d("FileLocation", dir.getPath() + "/emp_Identification.pdf");
                            e.printStackTrace();
                        }
                        document.close();
                        Log.d("FileLocation09", dir.getPath() + "/emp_Identification.pdf");

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        Uri screenshotUri = Uri.parse(dir.getPath() + "/emp_Identification.pdf");
                        sharingIntent.setType("*/*");
                        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                }
            }
        });


    }

    private void CallGetSalary() throws IOException, XmlPullParserException {
        Date cDate = new Date();

        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
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
                            "         <EmployeeId>" + global.GetValue("Username") + "</EmployeeId>\n" +
                            "      </urn:ZhrEmpIdentity>\n" +
                            "   </soap:Body>\n" +
                            "</soap:Envelope>";
                    URL url = new URL("https://" + Api.Domain + "/GatewayControlPanel/EmployeePayroll/EmployeeIdentificationLetterService");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    Log.d("req---", request);
                    Log.d("URL---", "https://" + Api.Domain + "/GatewayControlPanel/EmployeePayroll/EmployeeIdentificationLetterService");



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
                    BArabic = new ArrayList<String>();
                    BEnglish = new ArrayList<String>();
                    BValue = new ArrayList<String>();
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
                                        EmployeeId = text;
                                        break;
                                    case "EmployeeName":
                                        EmployeeName = text;
                                        break;
                                    case "EmpNameAr":
                                        EmpNameAr = text;
                                        break;
                                    case "IdentityNo":
                                        IdentityNo = text;
                                        break;
                                    case "Position":
                                        Position = text;
                                        break;
                                    case "PositionAr":
                                        PositionAr = text;
                                        break;
                                    case "Nationality":
                                        Nationality = text;
                                        break;
                                    case "NatioAr":
                                        NatioAr = text;
                                        break;
                                    case "JoinDate":
                                        JoinDate = text;
                                        break;
                                    case "Department":
                                        Department = text;
                                        break;
                                    case "DepartmentAr":
                                        DepartmentAr = text;
                                        break;
                                    case "TotalSal":
                                        TotalSal = text;
                                        break;


//                                    case "PositionAr":
//                                        if (item != null)
//                                            leaveItems.add(item);
//                                        break;
                                }

                                if (tag.startsWith("Lgtxt") && tag.endsWith("Ar")) {
                                    if (text != null && text.length() > 0)
                                        BArabic.add(text);
                                } else if (tag.startsWith("Lgtxt") && tag.endsWith("En")) {
                                    if (text != null && text.length() > 0)
                                        BEnglish.add(text);
                                } else if (tag.startsWith("Bet")) {
                                    float v = Float.parseFloat(text);
                                    if (v > 0)
                                        BValue.add(text);
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
if(EmployeeId!=null){

                            BitmapDrawable b = writeTextOnDrawable(R.drawable.selfservice1);

                            imageView2.setBackground(b);
}
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


        float h= (float) (bm.getHeight()/1868.0);
        float w= (float) (bm.getWidth()/1468.0);
        Canvas canvas = new Canvas(bm);

        //If the text is bigger than the canvas , reduce the font size
//        if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
//            paint.setTextSize(convertToPixels(EmployeeIdentificationActivity.this, 7));        //Scaling needs to be used for different dpi's

        //Calculate the positions
        int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset

        //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        //Arabic Name

        canvas.drawText(EmpNameAr,1252*w, 480*h, paint);
        canvas.drawText(global.GetValue("Username").replaceAll("u",""),1252*w, 520*h, paint);
        canvas.drawText(IdentityNo,1252*w, 560*h, paint);
        canvas.drawText(PositionAr,1252*w, 595*h, paint);
        canvas.drawText(NatioAr,1252*w, 630*h, paint);
        canvas.drawText(JoinDate,1252*w, 665*h, paint);
        canvas.drawText("الموقع : "+per.getResultObject().getLocationAr(),1448*w, 310*h, paint);
        canvas.drawText("الإدارة : "+DepartmentAr,1448*w, 340*h, paint);


        Calendar Current=Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String date=arabicToDecimal(sdf.format(Current.getTime()));

        canvas.drawText(date,1295*w, 178*h, paint);


        //English Name

        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(EmployeeName,255*w, 480*h, paint);
        canvas.drawText(global.GetValue("Username").replaceAll("u",""),255*w, 520*h, paint);
        canvas.drawText(IdentityNo,255*w, 560*h, paint);
        canvas.drawText(Position,255*w, 595*h, paint);
        canvas.drawText(Nationality,255*w, 630*h, paint);
        canvas.drawText(JoinDate,255*w, 665*h, paint);
        canvas.drawText("Location : "+per.getResultObject().getLocationEn(),30*w, 310*h, paint);
        canvas.drawText("Department : "+Department,30*w, 340*h, paint);
        float x=(float)711*h;
        float y=(float)0;



        for (int i=0;i<BArabic.size();i++){
            canvas.drawBitmap(writeTextOnRow(BArabic.get(i),BEnglish.get(i),BValue.get(i)),y,x+((i)*(38*h)),null);
        }
        canvas.drawBitmap(writeTextOnEnd(),y,x+((BArabic.size())*(38*h))-3,null);


        Bitmap stamp = BitmapFactory.decodeResource(getResources(), R.drawable.stamp)
                .copy(Bitmap.Config.ARGB_8888, true);
        canvas.drawBitmap(stamp.createScaledBitmap(stamp, (int)(250*h), (int)(200*h), false),canvas.getWidth()/2-(125*w),(250*h)+x+((BArabic.size())*(38*h))-3,null);



        return new BitmapDrawable(getResources(), bm);
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





    private Bitmap writeTextOnRow(String Arabic,String English,String Value) {

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
        float h= (float) (bm.getHeight()/38.0);
        float w= (float) (bm.getWidth()/1468.0);

        paint.setTextAlign(Paint.Align.RIGHT);

        canvas.drawText(Arabic,1450*w, 24*h, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(Value,734*w, 24*h, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(English,26*w, 24*h, paint);

        return bm;
    }


    private Bitmap writeTextOnEnd() {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.selfservice3)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = ResourcesCompat.getFont(this, R.font.swcc);

        //Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.RIGHT);

        float h= (float) (bm.getHeight()/606.0);
        float w= (float) (bm.getWidth()/1468.0);


        paint.setTextSize(convertToPixels(EmployeeIdentificationActivity.this, 21));

        Canvas canvas = new Canvas(bm);


        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(TotalSal+" ريال ",734*w, 24*h, paint);
        paint.setTextAlign(Paint.Align.LEFT);

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



    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Swcc","Permission is granted");
                return true;
            } else {

                Log.v("SWCC","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SWCC","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("SWCC","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

}