package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OpenTeqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_teq);

        TextView Header=(TextView)findViewById(R.id.header);
        String text = "<font color=#004C86>الدعم الفني /</font> <font color=#0066CC>الملاحظات والبلاغات</font>";
        Header.setText(Html.fromHtml(text));





        // TextView back=(TextView)findViewById(R.id.back);
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });




        ((Button)findViewById(R.id.securty)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(OpenTeqActivity.this,IndustrialSecurityActivity.class));
            }
        });
        ((Button)findViewById(R.id.Esckan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OpenTeqActivity.this,EskanServiceActivity.class));

            }
        });
        ((Button)findViewById(R.id.internalaudit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(OpenTeqActivity.this,InternalEditorNActivity.class));

            }
        });



    }
}