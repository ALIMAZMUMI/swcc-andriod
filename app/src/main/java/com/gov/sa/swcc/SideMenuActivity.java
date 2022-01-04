package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gov.sa.swcc.Adapter.PaysilpAdapter;

import java.util.ArrayList;

public class SideMenuActivity extends AppCompatActivity {
LinearLayout main1,main2,main3,main4,main5,main6;

ListView sub1,sub2,sub3,sub4;
ImageView menuimg,menuimg2,menuimg3,menuimg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);


        overridePendingTransition(R.anim.slide_left,R.anim.slide_right);

        main1=(LinearLayout)findViewById(R.id.main1);
        main2=(LinearLayout)findViewById(R.id.main2);
        main3=(LinearLayout)findViewById(R.id.main3);
        main4=(LinearLayout)findViewById(R.id.main4);
        main5=(LinearLayout)findViewById(R.id.main5);
        main6=(LinearLayout)findViewById(R.id.main6);
        sub1=(ListView)findViewById(R.id.sub1);
        sub2=(ListView)findViewById(R.id.sub2);
        sub3=(ListView)findViewById(R.id.sub3);
        sub4=(ListView)findViewById(R.id.sub4);
        menuimg=(ImageView)findViewById(R.id.menuimg);
        menuimg2=(ImageView)findViewById(R.id.menuimg2);
        menuimg3=(ImageView)findViewById(R.id.menuimg3);
        menuimg4=(ImageView)findViewById(R.id.menuimg4);




        ArrayList<String> arrayStrings = new ArrayList<String>();
        arrayStrings.add("نبذة عن المؤسسة");
        arrayStrings.add("تاريخ المؤسسة");
        arrayStrings.add("شركاء النجاح");
        arrayStrings.add("دليل الهوية البصرية");
        arrayStrings.add("مجلس الإدارة");
        arrayStrings.add("الهيكل التنظيمي");
        arrayStrings.add("محافظ المؤسسة");
        arrayStrings.add("تقنيات التحلية");
        arrayStrings.add("المنظومات");
        arrayStrings.add("برنامج التخصيص");
        arrayStrings.add("الذكاء الاصطناعي");
        ArrayList<String> arrayStrings2 = new ArrayList<String>();
        arrayStrings2.add("معهد الأبحاث");
        arrayStrings2.add("المحتوي المحلي");
        arrayStrings2.add("التشغيل و الصيانة");
        arrayStrings2.add("المعامل والمختبرات");
        arrayStrings2.add("الفرص الاستثمارية");
        ArrayList<String> arrayStrings3 = new ArrayList<String>();
        arrayStrings3.add("المناقصات المطروحة");
        arrayStrings3.add("تأهيل الشركات");
        arrayStrings3.add("نظام الموردين");
        arrayStrings3.add("منصة المنقولات");

        ArrayList<String> arrayStrings4 = new ArrayList<String>();
        arrayStrings4.add("السلامة و الصحة");
        arrayStrings4.add("المسؤولية المجتمعية");
        arrayStrings4.add("الإمتثال البيئي");


        ArrayAdapter resultAdapter = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings);
        sub1.setAdapter( resultAdapter );
        getHeight(resultAdapter,sub1);
        ArrayAdapter resultAdapter2 = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings2);
        sub2.setAdapter( resultAdapter2 );
        getHeight(resultAdapter2,sub2);

        ArrayAdapter resultAdapter3 = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings3);
        sub3.setAdapter( resultAdapter3 );
        getHeight(resultAdapter3,sub3);

        ArrayAdapter resultAdapter4 = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings4);
        sub4.setAdapter( resultAdapter4 );
        getHeight(resultAdapter4,sub4);



        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sub1.getVisibility()==View.VISIBLE) {
                    sub1.setVisibility(View.GONE);
                    menuimg.setImageResource(R.drawable.downw);
                }else{

                    sub1.setVisibility(View.GONE);
                    sub2.setVisibility(View.GONE);
                    sub3.setVisibility(View.GONE);
                    sub4.setVisibility(View.GONE);
                    menuimg.setImageResource(R.drawable.downw);
                    menuimg2.setImageResource(R.drawable.downw);
                    menuimg3.setImageResource(R.drawable.downw);
                    menuimg4.setImageResource(R.drawable.downw);

                    sub1.setVisibility(View.VISIBLE);
                    menuimg.setImageResource(R.drawable.upw);
                }
            }
        });

        main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sub4.getVisibility()==View.VISIBLE) {
                    sub4.setVisibility(View.GONE);
                    menuimg4.setImageResource(R.drawable.downw);
                }else{

                    sub1.setVisibility(View.GONE);
                    sub2.setVisibility(View.GONE);
                    sub3.setVisibility(View.GONE);
                    sub4.setVisibility(View.GONE);
                    menuimg.setImageResource(R.drawable.downw);
                    menuimg2.setImageResource(R.drawable.downw);
                    menuimg3.setImageResource(R.drawable.downw);
                    menuimg4.setImageResource(R.drawable.downw);

                    sub4.setVisibility(View.VISIBLE);
                    menuimg4.setImageResource(R.drawable.upw);
                }
            }
        });

        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sub2.getVisibility()==View.VISIBLE) {
                    sub2.setVisibility(View.GONE);
                    menuimg2.setImageResource(R.drawable.downw);
                }else{

                    sub1.setVisibility(View.GONE);
                    sub2.setVisibility(View.GONE);
                    sub3.setVisibility(View.GONE);
                    sub4.setVisibility(View.GONE);
                    menuimg.setImageResource(R.drawable.downw);
                    menuimg2.setImageResource(R.drawable.downw);
                    menuimg3.setImageResource(R.drawable.downw);
                    menuimg4.setImageResource(R.drawable.downw);

                    sub2.setVisibility(View.VISIBLE);
                    menuimg2.setImageResource(R.drawable.upw);
                }
            }
        });

        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sub3.getVisibility()==View.VISIBLE) {
                    sub3.setVisibility(View.GONE);
                    menuimg3.setImageResource(R.drawable.downw);
                }else{

                    sub1.setVisibility(View.GONE);
                    sub2.setVisibility(View.GONE);
                    sub3.setVisibility(View.GONE);
                    sub4.setVisibility(View.GONE);
                    menuimg.setImageResource(R.drawable.downw);
                    menuimg2.setImageResource(R.drawable.downw);
                    menuimg3.setImageResource(R.drawable.downw);
                    menuimg4.setImageResource(R.drawable.downw);

                    sub3.setVisibility(View.VISIBLE);
                    menuimg3.setImageResource(R.drawable.upw);
                }
            }
        });



    }


    public void getHeight(ArrayAdapter listadp, ListView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (listadp.getCount() - 1));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }
}