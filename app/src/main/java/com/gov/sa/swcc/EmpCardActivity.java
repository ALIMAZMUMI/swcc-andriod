package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.gov.sa.swcc.model.PersonalResult;

public class EmpCardActivity extends Activity {

    Global global;
    TextView Empdept,EmpPic,EmpName,EmpID,EmpNameEn,EmpNat,EmpNatID,EmpBlood,JobTitle,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_card);
        this.setFinishOnTouchOutside(false);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        global=new Global(EmpCardActivity.this);

        Empdept=(TextView)findViewById(R.id.Empdept);
        EmpPic=(TextView)findViewById(R.id.EmpPic);
        EmpName=(TextView)findViewById(R.id.EmpName);
        EmpID=(TextView)findViewById(R.id.EmpID);
        EmpNameEn=(TextView)findViewById(R.id.EmpNameEn);
        EmpNat=(TextView)findViewById(R.id.EmpNat);
        EmpNatID=(TextView)findViewById(R.id.EmpNatID);
        EmpBlood=(TextView)findViewById(R.id.EmpBlood);
        JobTitle=(TextView)findViewById(R.id.JobTitle);
        back=(TextView)findViewById(R.id.back);



        PersonalResult per=global.GetPData("PersonalResult");

        Empdept.setText(per.getResultObject().getDepartment());
        EmpName.setText(per.getResultObject().getFullName());
        EmpID.setText(global.GetValue("Username").replaceAll("u",""));
        EmpNameEn.setText(per.getResultObject().getFirstNameEn()+" "+per.getResultObject().getMiddleNameEn()+" "+per.getResultObject().getLastNameEn());
        EmpNat.setText(per.getResultObject().getNationality());
        EmpNatID.setText(per.getResultObject().getNationalId());
        EmpBlood.setText("غير معرف");
        JobTitle.setText(per.getResultObject().getTitle());

        byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);
        EmpPic.setBackground(d);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmpCardActivity.this.finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });

    }


}
