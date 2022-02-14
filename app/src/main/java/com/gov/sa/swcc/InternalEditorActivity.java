package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gov.sa.swcc.model.PersonalResult;

public class InternalEditorActivity extends AppCompatActivity {

Global global;
String Perdata="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_editor);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);



        // TextView back=(TextView)findViewById(R.id.back);
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        global=new Global(InternalEditorActivity.this);
try {
    PersonalResult per = global.GetPData("PersonalResult");
    Perdata = "الرقم الوظيفي : " + global.GetValue("Username").replaceAll("u", "") +"\n"+
            "الجنسية : " + per.getResultObject().getNationality()+"\n"
            + "رقم الهوية : " + per.getResultObject().getNationalId()+"\n"
            + "الإدارة : " + per.getResultObject().getDepartment()+"\n"
            + "رقم الجوال : " + per.getResultObject().getMobile()+"\n"
            + "الإسم : " + per.getResultObject().getFirstNameEn() + " " + per.getResultObject().getMiddleNameEn() + " " + per.getResultObject().getLastNameEn()+"\n"
            + "المسمى الوظيفي : " + per.getResultObject().getTitle();
}catch (Exception ex){

}
        LinearLayout L1=(LinearLayout) findViewById(R.id.l1);
        LinearLayout L2=(LinearLayout) findViewById(R.id.l2);
        LinearLayout L3=(LinearLayout) findViewById(R.id.l3);
        LinearLayout L4=(LinearLayout) findViewById(R.id.l4);
        LinearLayout L5=(LinearLayout) findViewById(R.id.l5);
        LinearLayout L6=(LinearLayout) findViewById(R.id.l6);

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"WB@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "مخالفة الأنظمة واللوائح والتعليمات والسياسات المعمول بها في المؤسسة والخاضعة لها");
                intent.putExtra(Intent.EXTRA_TEXT,"\n\n\n\n\n\n\n"+Perdata);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"WB@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "استغلال غير مشروع للسلطة - أياً كانت طبيعته - أو للموارد والأصول المملوكة للمؤسسة أو المخصصة لها");
                intent.putExtra(Intent.EXTRA_TEXT,"\n\n\n\n\n\n\n"+Perdata);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"WB@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "الاخلال بالالتزامات والواجبات الوظيفية");
                intent.putExtra(Intent.EXTRA_TEXT,"\n\n\n\n\n\n\n"+Perdata);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"WB@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "الإيذاء وسوء المعاملة");
                intent.putExtra(Intent.EXTRA_TEXT,"\n\n\n\n\n\n\n"+Perdata);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        L5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"WB@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "التصرفات غير الأخلاقية المخالفة للأنظمة أو الأداب العامة");
                intent.putExtra(Intent.EXTRA_TEXT,"\n\n\n\n\n\n\n"+Perdata);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        L6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"WB@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "الأضرار التي تلحق بالبيئة والصحة والسلامة");
                intent.putExtra(Intent.EXTRA_TEXT,"\n\n\n\n\n\n\n"+Perdata);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


    }
}