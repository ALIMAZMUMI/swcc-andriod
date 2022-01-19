package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ShowLinkActivity extends AppCompatActivity {
Global global;
    String url="";
    boolean Share,Auth;
TextView Shear,back;
    WebView mWebview;
    int click=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_link);


        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        TextView back=(TextView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });


        global=new Global(ShowLinkActivity.this);

        mWebview  = (WebView) findViewById(R.id.web);
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        WebSettings ws = mWebview.getSettings() ;
        ws.setJavaScriptEnabled( true ) ;
        mWebview.addJavascriptInterface( new Object() {
            @JavascriptInterface // For API 17+
            public void share(String text,String image) {
                if (click == 0) {

                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                /*This will be the actual content you wish you share.*/
                String shareBody = text;
                /*The type of the content is text, obviously.*/
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                /*Fire!*/
                startActivity(Intent.createChooser(intent,""));
                click++;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            click=0;
                        }
                    }, 1000);

                }

            }
        } , "ok" ) ;

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
               // Toast.makeText(ShowLinkActivity.this, description, Toast.LENGTH_SHORT).show();
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

                if (mWebview.getContentHeight() == 0)
                {

                    mWebview.loadUrl(url);// RE-Loading

                }else {

                    dialog.dismiss();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                Toast.makeText(ShowLinkActivity.this,url,Toast.LENGTH_SHORT).show();
                view.loadUrl(url);
                return true;
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
               if(url.contains("https://ext.swcc.gov.sa/news")) {

                   String Java = "javascript:(function() {" +
                           "                        var x = document.getElementsByClassName('btn round news-list--link');" +
                           "                        var news = document.getElementsByClassName('card-title news-list--title ng-binding');" +
                           "                        for(var i = 0; i < x.length; i++) {" +
                           "                        if(x[i].textContent.indexOf(\"شارك\") !== -1){" +
                           "                        var OneElement = x[i];" +
                           "                        OneElement.id='i'+i;" +
                           "                        }}" +

                           "                        })();";

                   mWebview.loadUrl(Java);


                   String Java1 = "javascript:(function() {" +
                           "function createCallback( id ){\n" +

                           "  return function(){\n" +
                           "    ok.share(id,id);\n" +
                           "  }\n" +
                           "}" +
                           "function setclick(id,text){$(id).click(createCallback(text));}"+
                           "                        var x = document.getElementsByClassName('btn round news-list--link');" +
                           "                        var news = document.getElementsByClassName('card-title news-list--title ng-binding');" +
                           "                        for(var y = 0; y < x.length; y++) {" +
                           "                        if(x[y].textContent.indexOf(\"شارك\") !== -1){" +
                           "var id='#i'+y;" +
                           "var index=Math.floor(y / 2);" +
                           "setclick(id,news[index].textContent);" +
                           "                        }}" +

                           "                        })();";

                   mWebview.loadUrl(Java1);
               }


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
        if(global.CheckInternet(ShowLinkActivity.this)) {
        }else {
            mWebview.loadUrl(url);
        }


    }
}