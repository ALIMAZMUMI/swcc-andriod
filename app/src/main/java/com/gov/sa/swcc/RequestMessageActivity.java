package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RequestMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_message);

        Global global=new Global(RequestMessageActivity.this);
        Button ok =(Button) findViewById(R.id.ok);
        TextView message=(TextView) findViewById(R.id.message);


        if(getIntent().getExtras().getString("Lan","").equals("en")){

            ( (TextView) findViewById(R.id.hedar)).setText("report has been sent successfully");
            ok.setText("close");

        }

        message.setText(getIntent().getExtras().getString("Message",""));

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestMessageActivity.this.finish();
                Global.activity.finish();
            }
        });

//        LinearLayout back =(LinearLayout) findViewById(R.id.backgroundlin);
//
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RequestMessageActivity.this.finish();
//                Global.activity.finish();
//            }
//        });









    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RequestMessageActivity.this.finish();
        Global.activity.finish();
    }
}