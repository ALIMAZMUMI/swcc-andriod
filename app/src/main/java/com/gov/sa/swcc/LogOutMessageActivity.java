package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogOutMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out_message);

        Global global=new Global(LogOutMessageActivity.this);
        Button ok =(Button) findViewById(R.id.ok);
        Button cancel =(Button) findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOutMessageActivity.this.finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global.SaveValue("Home","N");
                global.SaveValue("Authentication","YY");
                global.SaveValue("Username","");
                global.SaveValue("Password","");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                LogOutMessageActivity.this.finish();
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
        LogOutMessageActivity.this.finish();
    }
}