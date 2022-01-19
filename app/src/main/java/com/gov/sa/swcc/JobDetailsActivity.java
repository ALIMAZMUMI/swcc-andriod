package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.model.PersonalResult;

public class JobDetailsActivity extends AppCompatActivity {


    Global global;
    TextView Empdept,EmpPic,EmpName,EmpID,EmpNameEn,EmpNat,EmpNatID,EmpMobile,JobTitle,back,Close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);



        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        global=new Global(JobDetailsActivity.this);

        Empdept=(TextView)findViewById(R.id.Empdept);
        EmpPic=(TextView)findViewById(R.id.EmpPic);
        EmpName=(TextView)findViewById(R.id.EmpName);
        EmpID=(TextView)findViewById(R.id.EmpID);
        EmpNameEn=(TextView)findViewById(R.id.EmpNameEn);
        EmpNat=(TextView)findViewById(R.id.EmpNat);
        EmpNatID=(TextView)findViewById(R.id.EmpNatID);
        JobTitle=(TextView)findViewById(R.id.JobTitle);
        back=(TextView)findViewById(R.id.back);
        EmpMobile=(TextView)findViewById(R.id.EmpMobile);
        //Close=(TextView)findViewById(R.id.Close);

        PersonalResult per=global.GetPData("PersonalResult");

        Empdept.setText("الإدارة : "+per.getResultObject().getDepartment());
        EmpName.setText("الإسم : "+per.getResultObject().getFullName());
        EmpID.setText("الرقم الوظيفي : "+ global.GetValue("Username").replaceAll("u",""));
        EmpNameEn.setText("الإسم : " +per.getResultObject().getFirstNameEn()+" "+per.getResultObject().getMiddleNameEn()+" "+per.getResultObject().getLastNameEn());
        EmpNat.setText("الجنسية : "+per.getResultObject().getNationality());
        EmpNatID.setText("رقم الهوية : "+per.getResultObject().getNationalId());
        JobTitle.setText("المسمى الوظيفي : "+per.getResultObject().getTitle());
        EmpMobile.setText("رقم الجوال : "+per.getResultObject().getMobile());
        byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);
        EmpPic.setBackground(d);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobDetailsActivity.this.finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });
//Close.bringToFront();
//        Close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JobDetailsActivity.this.finish();
//            }
//        });


    }
}
