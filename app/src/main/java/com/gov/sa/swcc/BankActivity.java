package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BankActivity extends AppCompatActivity {
    PorgressDilog dialog;
    PDFView pdfView;
    LinearLayout showres,Shear;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        TextView textView7 =(TextView) findViewById(R.id.textView7);
        EditText  banknum =(EditText) findViewById(R.id.banknum);
        EditText bankvalue =(EditText) findViewById(R.id.bankvalue);
         showres=(LinearLayout) findViewById(R.id.showres);
        //WebView web=(WebView) findViewById(R.id.web);
         Shear=(LinearLayout) findViewById(R.id.Shear);
        Button submit =(Button)findViewById(R.id.submit);
         pdfView=(PDFView)findViewById(R.id.pdfView);
        String text = "<font color=#004C86>* رقم الضمان</font> <font color=#CACCCE>(كتابة رقم الضمان كما هوا موضح في أصل الخطاب)</font>";
        textView7.setText(Html.fromHtml(text));
        //web.getSettings().setJavaScriptEnabled(true); // enable javascript
showres.setVisibility(View.GONE);
//        web.setWebChromeClient(new WebChromeClient());
//        //web.setWebViewClient(new WebViewClient());
//        web.getSettings().setJavaScriptEnabled(true);
//
//        web.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                dialog =   ProgressDialog.show(BankActivity.this, "",
//                        "يرجى الإنتظار", true);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //view.loadUrl(url);
//                return true;
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//
//                if(dialog!=null) {
//                    dialog.dismiss();
//                }
//            }
//
//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                super.onLoadResource(view, url);
//
////                String Java="javascript:(function() {document.getElementsByClassName('header_absolute')[0].style.display = 'none';document.getElementsByTagName('section')[0].style.height = '0px';document.getElementById('bottomMenu').style.display = 'none';document.getElementsByClassName('container')[1].style.display = 'none';document.getElementsByClassName('blue-main-hover')[4].style.display = 'none';document.getElementsByClassName('entry-footer')[0].style.display = 'none';})()";
////                web.loadUrl(Java);
//
//
//            }
//        });


        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        banknum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//
                if(banknum.getText().length()>3&&bankvalue.getText().length()>3){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }
            }
        });
        bankvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//
                if(banknum.getText().length()>3&&bankvalue.getText().length()>3){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }
            }
        });




        Shear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName="swcc";
                String fileExtension=".pdf";
                String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/swccpdf/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, fileName+fileExtension);

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri screenshotUri = Uri.parse(outputFile.getPath());
                sharingIntent.setType("*/*");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(sharingIntent, ""));


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(banknum.getText().length()>3&&bankvalue.getText().length()>3){
//web.loadUrl("https://www.swcc.gov.sa/ar");
                    //web.loadUrl("https://ext.swcc.gov.sa/BG/BGReports/BankGuranteeReport?BankGuranteeNumber=04762IGS1900229&Ammount=249999.80");
                   // download();
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ext.swcc.gov.sa/BG/BGReports/BankGuranteeReport?BankGuranteeNumber=04762IGS1900229&Ammount=249999.80"));
//                    startActivity(browserIntent);
                   // view();
                    //pdfView.fromUri(Uri.parse("https://ext.swcc.gov.sa/BG/BGReports/BankGuranteeReport?BankGuranteeNumber=04762IGS1900229&Ammount=249999.80")).load();
                    if(isStoragePermissionGranted()){
                        showres.setVisibility(View.GONE);
                        dialog =  new PorgressDilog(BankActivity.this);
                        dialog.show();
                        String str1=banknum.getText().toString(),str="";
                        try {
                            str= URLEncoder.encode(str1, "UTF-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        downloadPdfContent("https://"+Api.Domain+"/GatewayControlPanel/BankGurantee/BankGuranteeReport?BankGuranteeNumber="+str+"&Ammount="+bankvalue.getText()+"&secretToken=7565854723");

                    }


                }
            }
        });

        submit.setBackgroundResource(R.drawable.grayroundbtn);
        submit.setEnabled(false);

    }

    public void downloadPdfContent(String urlToDownload){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
        try {
            String fileName="swcc";
            String fileExtension=".pdf";

//           download pdf file.
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(urlToDownload);
            System.out.println("--pdf downloaded--ok--"+urlToDownload);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestProperty("Accept-Charset", "UTF-8");
            c.setRequestMethod("GET");
            //c.setDoOutput(true);
            c.setConnectTimeout(60000);
            c.connect();
            String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/swccpdf/";
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, fileName+fileExtension);
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();
            System.out.println("--pdf downloaded--ok--"+urlToDownload);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pdfView.fromFile(outputFile).load();
                    showres.setVisibility(View.VISIBLE);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new Global(BankActivity.this).ShowMessage("فضلا كتابة البيانات بشكل صحيح");
                }
            });
        }


                dialog.dismiss();
            }});
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Swcc","Permission is granted");
                return true;
            } else {
                Log.v("SWCC","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SWCC","Permission is granted");
            return true;
        }
    }

}