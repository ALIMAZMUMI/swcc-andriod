package com.gov.sa.swcc;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
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


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home, container, false);




        WebView mWebview  = (WebView) view.findViewById(R.id.web);

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
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "يرجى الإنتظار", true);

        mWebview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();

            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                String Java="javascript:(function() {document.getElementsByClassName('header_absolute')[0].style.display = 'none';document.getElementsByTagName('section')[0].style.height = '0px';document.getElementById('bottomMenu').style.display = 'none';document.getElementsByClassName('container')[1].style.display = 'none';document.getElementsByClassName('blue-main-hover')[4].style.display = 'none';document.getElementsByClassName('entry-footer')[0].style.display = 'none';})()";
                mWebview.loadUrl(Java);

            }
        });


        mWebview.loadUrl("https://www.swcc.gov.sa/ar");



        return view;
    }



}
