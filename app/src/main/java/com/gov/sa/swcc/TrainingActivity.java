package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//        TextView back=(TextView)findViewById(R.id.back);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
//            }
//        });

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });

        ImageView t0=(ImageView)findViewById(R.id.t0);
        ImageView t1=(ImageView)findViewById(R.id.t1);
        ImageView t2=(ImageView)findViewById(R.id.t2);
        ImageView t3=(ImageView)findViewById(R.id.t3);
        ImageView t4=(ImageView)findViewById(R.id.t4);

        TextView treaningweb=(TextView) findViewById(R.id.treaningweb);
        TextView cuntactus=(TextView) findViewById(R.id.cuntactus);
        TextView instable=(TextView) findViewById(R.id.table);


        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.swacademy.com/ar/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.swacademy.com/ar/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://lms.swcc.gov.sa";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://firebasestorage.googleapis.com/v0/b/selfservicesmobapp.appspot.com/o/swacademy_courses.pdf?alt=media";
                Intent Link=  new Intent(TrainingActivity.this,ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+url);
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);


                }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.swacademy.com/ar/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);            }
        });


        treaningweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://lms.swcc.gov.sa";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);            }
        });

        cuntactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"SWA-Contact@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT,"");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        instable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://firebasestorage.googleapis.com/v0/b/selfservicesmobapp.appspot.com/o/swacademy_courses.pdf?alt=media";
                Intent Link=  new Intent(TrainingActivity.this,ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+url);
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });
    }
}