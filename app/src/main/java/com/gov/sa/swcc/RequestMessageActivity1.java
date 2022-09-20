package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RequestMessageActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_message1);

        Global global=new Global(RequestMessageActivity1.this);
        Button close =(Button) findViewById(R.id.close);
        TextView message=(TextView) findViewById(R.id.message);

        if(getIntent().getExtras().getString("Lan","").equals("en")){

            ( (TextView) findViewById(R.id.hedar)).setText("report has been sent successfully");
            close.setText("close");

        }

        message.setText(getIntent().getExtras().getString("Message",""));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestMessageActivity1.this.finish();
               // Global.activity.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RequestMessageActivity1.this.finish();
        Global.activity.finish();
    }

}