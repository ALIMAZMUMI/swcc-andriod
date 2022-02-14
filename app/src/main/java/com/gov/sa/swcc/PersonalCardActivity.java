package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalCardActivity extends AppCompatActivity {
TextView Name,EnName,Badge,phone,city,email,emailbtn,cisco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_card);

        Name=(TextView) findViewById(R.id.Name);
        EnName=(TextView) findViewById(R.id.EnName);
        Badge=(TextView) findViewById(R.id.badge);
        //phone=(TextView) findViewById(R.id.phone);
        city=(TextView) findViewById(R.id.City);
        //email=(TextView) findViewById(R.id.email);
        emailbtn=(TextView) findViewById(R.id.emailbtn);
        cisco=(TextView) findViewById(R.id.ciscobtn);
        String Emp=getIntent().getExtras().getString("Emp","");
        String [] empdata=Emp.split("#\\$#");


        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{empdata[10]});
//                intent.putExtra(Intent.EXTRA_SUBJECT, "");
//                intent.putExtra(Intent.EXTRA_TEXT,"");
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//            }
//        });

        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{empdata[10]});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT,"");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        cisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("ciscotel:"+empdata[7]);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.cisco.im");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {

                }
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("ciscotel:"+empdata[7]));
//                startActivity(callIntent);
            }
        });

        Log.d("EmpName",Emp);
        if(empdata.length>1){
            Name.setText(empdata[0]);
            EnName.setText(empdata[2]+" " +empdata[3]);
            Badge.setText(empdata[1]);
            //phone.setText("التحويلة : "+empdata[7]);
            city.setText(empdata[6]);
            emailbtn.setText(empdata[10]);
            cisco.setText(empdata[7]);
//            String first = "البريد : ";
//            String next = "<font color='#0971ce'>"+empdata[10]+"</font>";
//            email.setText(Html.fromHtml(first + next));
            //email.setText("البريد : "+empdata[10]);
        }



    }
}