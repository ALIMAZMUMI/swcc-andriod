package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ShowLinkActivity extends AppCompatActivity {
Global global;
    String url="";
    boolean Share,Auth;
TextView Shear,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_link);

        global=new Global(ShowLinkActivity.this);

        WebView mWebview  = (WebView) findViewById(R.id.web);
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.getSettings().setJavaScriptEnabled(true);

        Shear=(TextView)findViewById(R.id.Shear);
        back=(TextView)findViewById(R.id.back);

        Shear.setVisibility(View.GONE);
        Shear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                /*This will be the actual content you wish you share.*/
                String shareBody = url.substring(url.indexOf("url=")+4,url.length());
                /*The type of the content is text, obviously.*/
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                /*Fire!*/
                startActivity(Intent.createChooser(intent,""));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowLinkActivity.this.finish();
            }
        });


//        mWebview.setWebViewClient(new WebViewClient() {
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(ShowLinkActivity.this, description, Toast.LENGTH_SHORT).show();
//            }
//            @TargetApi(android.os.Build.VERSION_CODES.M)
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
//                // Redirect to deprecated method, so you can use it in all SDK versions
//                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
//            }
//        });
        ProgressDialog dialog = ProgressDialog.show(ShowLinkActivity.this, "",
                "يرجى الإنتظار", true);


        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(ShowLinkActivity.this, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });
        mWebview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

//                HashMap<String, String> headerMap = new HashMap<>();
//                //put all headers in this header map
//
//                final String basicAuth = "Basic " + Base64.encodeToString("u190250:SA.RUH.1409".getBytes(), Base64.NO_WRAP);
//
//                headerMap.put("Authorization", basicAuth);
//
//
//                view.loadUrl(url, headerMap);

                return false;

            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
               if(Auth) {
                   handler.proceed(global.GetValue("Username"), global.GetValue("Password"));
               }
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
               // String Java="javascript:(function() {document.getElementsByClassName('header_absolute')[0].style.display = 'none';document.getElementsByTagName('section')[0].style.height = '0px';document.getElementById('bottomMenu').style.display = 'none';document.getElementsByClassName('container')[1].style.display = 'none';document.getElementsByClassName('blue-main-hover')[4].style.display = 'none';document.getElementsByClassName('entry-footer')[0].style.display = 'none';})()";
               // mWebview.loadUrl(Java);

            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            url= null;
        } else {

            url= extras.getString("URL_LINK");
            Share=false;
            if(extras.getString("Share").equals("1")){
                Share=true;
                Shear.setVisibility(View.VISIBLE);
            }
            Auth=false;
            if(extras.getString("Auth").equals("T")){
                Auth=true;
            }
        }


        mWebview.loadUrl(url);


    }
}