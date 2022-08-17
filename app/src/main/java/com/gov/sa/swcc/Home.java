package com.gov.sa.swcc;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

String URLLink="";

    public Home() {
        // Required empty public constructor
    }
    int runtime=0;
    static WebView mWebview;
    static PorgressDilog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home, container, false);




         mWebview  = (WebView) view.findViewById(R.id.web);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript


        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                 dialog =  new PorgressDilog(getActivity());
                dialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(dialog!=null) {
                    dialog.dismiss();
                }
            }


            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

                String Java="javascript:(function() {document.getElementsByClassName('header_absolute')[0].style.display = 'none';document.getElementsByTagName('section')[0].style.height = '0px';document.getElementById('bottomMenu').style.display = 'none';document.getElementsByClassName('container')[1].style.display = 'none';document.getElementsByClassName('blue-main-hover')[4].style.display = 'none';document.getElementsByClassName('entry-footer')[0].style.display = 'none';})()";
                mWebview.loadUrl(Java);


            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            URLLink = bundle.getString("URLLink","https://swcc.gov.sa/ar");
        }else
        {
            URLLink="https://swcc.gov.sa/ar";
        }

//TODO
        mWebview.loadUrl(URLLink);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

if(mWebview!=null) {
    if(!mWebview.getUrl().equals(URLLink)){
    Bundle bundle = this.getArguments();
    if (bundle != null) {
        URLLink = bundle.getString("URLLink", "https://swcc.gov.sa/ar");
    } else {
        URLLink = "https://swcc.gov.sa/ar";
    }

    mWebview.loadUrl(URLLink);
    }
}

    }



    public void setURLLink(String URLLink){
        try {


            if (!mWebview.getUrl().equals(URLLink)) {
                mWebview.loadUrl(URLLink);
            }
        }catch (Exception ex){

        }
    }

}
