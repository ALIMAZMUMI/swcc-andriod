package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.gov.sa.swcc.model.PersonalResult;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Chatbot extends AppCompatActivity {
    Global global;
    String url="";
    boolean Share,Auth;
    ImageView Shear;
    WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        global=new Global(Chatbot.this);
        mWebview  = (WebView) findViewById(R.id.web);
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WebSettings ws = mWebview.getSettings() ;
        ws.setJavaScriptEnabled( true ) ;



        mWebview.loadData(readTrimRawTextFile(), "text/html; charset=utf-8", "UTF-8");

//        mWebview.addJavascriptInterface( new Object() {
//            @JavascriptInterface // For API 17+
//            public void share(String text,String image) {
//                if (click == 0) {
//
//                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//                    /*This will be the actual content you wish you share.*/
//                    String shareBody = text;
//                    /*The type of the content is text, obviously.*/
//                    intent.setType("text/plain");
//                    /*Applying information Subject and Body.*/
//                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
//                    intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                    /*Fire!*/
//                    startActivity(Intent.createChooser(intent,""));
//                    click++;
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            click=0;
//                        }
//                    }, 1000);
//
//                }
//
//            }
//        } , "ok" ) ;

    }
    private  String readTrimRawTextFile() {


        PersonalResult per=global.GetPData("PersonalResult");

        String HTML="<html>\n" +
                "    <body onLoad=\"document.getElementById('form').submit()\">\n" +
                "        <form id=\"form\" target=\"_self\" method=\"POST\" action=\"https://chatbotapi.swcc.gov.sa/api/Token/Insert\">\n" +
                "            <input type=\"hidden\" name=\"EId\" value=\""+global.GetValue("Username").replaceAll("u","")+"\" />\n" +
                "            <input type=\"hidden\" name=\"Token\" value=\""+per.getResultObject().getJwtToken()+"\" />\n" +
                "        </form>\n" +
                "    </body>\n" +
                "</html>";

        InputStream inputStream = new ByteArrayInputStream(HTML.getBytes());;

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while ((line = buffreader.readLine()) != null) {
                text.append(line.trim());
            }
        }
        catch (IOException e) {
            return null;
        }
        Log.d("lkasjf",text.toString());
        return text.toString();
    }

}