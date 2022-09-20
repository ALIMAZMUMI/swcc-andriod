package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.PaysilpAdapter;
import com.gov.sa.swcc.model.PersonalResult;

import java.util.ArrayList;

public class SideMenuActivity extends AppCompatActivity {
LinearLayout main1,main2,main3,main4,main5,main6,main7;

ListView sub1,sub2,sub3,sub4;
ImageView menuimg,menuimg2,menuimg3,menuimg4,side;
Global global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        global=new Global(SideMenuActivity.this);





TextView Name=(TextView) findViewById(R.id.name);
ImageView pic=(ImageView) findViewById(R.id.emppic);
        try {
            PersonalResult per=global.GetPData("PersonalResult");
            Name.setText(per.getResultObject().getFullName());
            byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Drawable d = new BitmapDrawable(getResources(), decodedByte);
            pic.setBackground(d);
        }catch (Exception e){

        }

        overridePendingTransition(R.anim.slide_right,R.anim.slide_left);

        main1=(LinearLayout)findViewById(R.id.swccweb);
        main2=(LinearLayout)findViewById(R.id.empgate);
        main3=(LinearLayout)findViewById(R.id.Sharkhom);
        main4=(LinearLayout)findViewById(R.id.acadyme);
        main5=(LinearLayout)findViewById(R.id.pro_plan);
        main6=(LinearLayout)findViewById(R.id.logout);
main7=(LinearLayout)findViewById(R.id.bank);

        main7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SideMenuActivity.this,BankActivity.class));
                SideMenuActivity.this.finish();

            }
        });
        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new );
            }
        });

        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=new Intent(SideMenuActivity.this,ShowLinkActivity.class);
                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/elib");
                        Link.putExtra("Auth","T");
                        Link.putExtra("Share","0");
                        startActivity(Link);
                        SideMenuActivity.this.finish();
            }
        });

        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=new Intent(SideMenuActivity.this,ShowLinkActivity.class);
                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/news");
                        Link.putExtra("Auth","T");
                        Link.putExtra("Share","0");
                        startActivity(Link);
                SideMenuActivity.this.finish();
            }
        });

        main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SideMenuActivity.this,TrainingActivity.class));
                SideMenuActivity.this.finish();

            }
        });

        main5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SideMenuActivity.this,Pro_planningActivity.class));
                SideMenuActivity.this.finish();

            }
        });

        main6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SideMenuActivity.this,LogOutMessageActivity.class));
                //SideMenuActivity.this.finish();

            }
        });



//
//        TextView sing=(TextView)findViewById(R.id.sing);
//
//        if(global.GetValue("Home").equals("y")){
//            sing.setText("تسجيل الخروج");
//        }else {
//            sing.setText("تسجيل الدخول");
//        }
//
//        sing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                global.SaveValue("Home","N");
//                global.SaveValue("Authentication","YY");
//                global.SaveValue("Username","");
//                global.SaveValue("Password","");
//                Bundle bundle = new Bundle();
//                MainActivity.login.setArguments(bundle);
//                MainActivity.changelayout(0);
//                SideMenuActivity.this.finish();
//            }
//        });
//
//
//
//
//
//
        LinearLayout rightli=(LinearLayout)findViewById(R.id.rightli);
        rightli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left);
                SideMenuActivity.this.finish();

            }
        });
//        side.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                overridePendingTransition(R.anim.slide_right,R.anim.slide_left);
//                SideMenuActivity.this.finish();
//
//            }
//        });
//
//
//        main5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("URLLink", "https://www.swcc.gov.sa/ar/home/contactus");
//                ((Home)MainActivity.home).setURLLink("https://www.swcc.gov.sa/ar/home/contactus");
//
//                MainActivity.home.setArguments(bundle);
//                MainActivity.changelayout(3);
//                SideMenuActivity.this.finish();
//
//            }
//        });
//
//        ArrayList<String> arrayStrings = new ArrayList<String>();
//        arrayStrings.add("نبذة عن المؤسسة");
//        arrayStrings.add("تاريخ المؤسسة");
//        arrayStrings.add("شركاء النجاح");
//        //arrayStrings.add("دليل الهوية البصرية");
//        arrayStrings.add("مجلس الإدارة");
//        arrayStrings.add("الهيكل التنظيمي");
//        arrayStrings.add("محافظ المؤسسة");
//        arrayStrings.add("تقنيات التحلية");
//        arrayStrings.add("المنظومات");
//        arrayStrings.add("برنامج التخصيص");
//        arrayStrings.add("الذكاء الاصطناعي");
//        ArrayList<String> arrayStrings2 = new ArrayList<String>();
//        arrayStrings2.add("معهد الأبحاث");
//        arrayStrings2.add("المحتوي المحلي");
//        arrayStrings2.add("التشغيل و الصيانة");
//        arrayStrings2.add("المعامل والمختبرات");
//        arrayStrings2.add("الفرص الاستثمارية");
//        ArrayList<String> arrayStrings3 = new ArrayList<String>();
//        arrayStrings3.add("المناقصات المطروحة");
//        arrayStrings3.add("تأهيل الشركات");
//        arrayStrings3.add("نظام الموردين");
//        arrayStrings3.add("منصة المنقولات");
//
//        ArrayList<String> arrayStrings4 = new ArrayList<String>();
//        arrayStrings4.add("السلامة و الصحة");
//        arrayStrings4.add("المسؤولية المجتمعية");
//        arrayStrings4.add("الإمتثال البيئي");
//
//
//        ArrayAdapter resultAdapter = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings);
//        sub1.setAdapter( resultAdapter );
//        getHeight(resultAdapter,sub1);
//        ArrayAdapter resultAdapter2 = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings2);
//        sub2.setAdapter( resultAdapter2 );
//        getHeight(resultAdapter2,sub2);
//
//        ArrayAdapter resultAdapter3 = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings3);
//        sub3.setAdapter( resultAdapter3 );
//        getHeight(resultAdapter3,sub3);
//
//        ArrayAdapter resultAdapter4 = new ArrayAdapter<String>(this,R.layout.menuitem,R.id.submenu,arrayStrings4);
//        sub4.setAdapter( resultAdapter4 );
//        getHeight(resultAdapter4,sub4);
//
//sub1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        String [] Link=new String[]{
//                "WhoAreWe/SWCCSummery","WhoAreWe/SWCCChronicle","WhoAreWe/SuccessPartners",
//                "Governance/DirectorsBoard","Governance/OrganizationalStructure","Governance/SWCCGovernor",
//                "ProductionSystems/DesalinationTechnologies","ProductionSystems","PrivatizationProgram",
//                "ArtificialIntelligence"};
//
//        Bundle bundle = new Bundle();
//        bundle.putString("URLLink", "https://swcc.gov.sa/ar/" +Link[i]);
//        ((Home)MainActivity.home).setURLLink("https://swcc.gov.sa/ar/" +Link[i]);
//
//        MainActivity.home.setArguments(bundle);
//        MainActivity.changelayout(3);
//        SideMenuActivity.this.finish();
//    }
//});
//
//
//
//        sub2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String [] Link=new String[]{
//                        "ResearchInstitute","LocalContent","Services/EquipmentMaintenanceService",
//                        "Services/Laboratories","Services/MineralExtractionService"};
//
//                Bundle bundle = new Bundle();
//                bundle.putString("URLLink", "https://swcc.gov.sa/ar/" +Link[i]);
//                ((Home)MainActivity.home).setURLLink("https://swcc.gov.sa/ar/" +Link[i]);
//
//                MainActivity.home.setArguments(bundle);
//                MainActivity.changelayout(3);
//                SideMenuActivity.this.finish();
//            }
//        });
//
//        sub3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String [] Link=new String[]{
//                        "https://swcc.gov.sa/ar/Suppliers/Tenders",
//                        "https://swcc.gov.sa/ar/Suppliers/CompaniesQualification",
//                        "https://swcc.gov.sa/ar/Suppliers/SupplierSystem",
//                        "https://ext.swcc.gov.sa/TendersP/"};
//
//                Bundle bundle = new Bundle();
//                bundle.putString("URLLink", Link[i]);
//                ((Home)MainActivity.home).setURLLink(Link[i]);
//
//                MainActivity.home.setArguments(bundle);
//                MainActivity.changelayout(3);
//                SideMenuActivity.this.finish();
//            }
//        });
//
//        sub4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String [] Link=new String[]{
//                        "SafetyAndHealth",
//                        "SocialResponsibility",
//                        "EnvironmentalCompliance"};
//
//                Bundle bundle = new Bundle();
//                bundle.putString("URLLink", "https://swcc.gov.sa/ar/" +Link[i]);
//                ((Home)MainActivity.home).setURLLink("https://swcc.gov.sa/ar/" +Link[i]);
//
//                MainActivity.home.setArguments(bundle);
//                MainActivity.changelayout(3);
//                SideMenuActivity.this.finish();
//            }
//        });
//
//
//
//        main1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(sub1.getVisibility()==View.VISIBLE) {
//                    sub1.setVisibility(View.GONE);
//                    menuimg.setImageResource(R.drawable.downw);
//                }else{
//
//                    sub1.setVisibility(View.GONE);
//                    sub2.setVisibility(View.GONE);
//                    sub3.setVisibility(View.GONE);
//                    sub4.setVisibility(View.GONE);
//                    menuimg.setImageResource(R.drawable.downw);
//                    menuimg2.setImageResource(R.drawable.downw);
//                    menuimg3.setImageResource(R.drawable.downw);
//                    menuimg4.setImageResource(R.drawable.downw);
//
//                    sub1.setVisibility(View.VISIBLE);
//                    menuimg.setImageResource(R.drawable.upw);
//                }
//            }
//        });
//
//        main4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(sub4.getVisibility()==View.VISIBLE) {
//                    sub4.setVisibility(View.GONE);
//                    menuimg4.setImageResource(R.drawable.downw);
//                }else{
//
//                    sub1.setVisibility(View.GONE);
//                    sub2.setVisibility(View.GONE);
//                    sub3.setVisibility(View.GONE);
//                    sub4.setVisibility(View.GONE);
//                    menuimg.setImageResource(R.drawable.downw);
//                    menuimg2.setImageResource(R.drawable.downw);
//                    menuimg3.setImageResource(R.drawable.downw);
//                    menuimg4.setImageResource(R.drawable.downw);
//
//                    sub4.setVisibility(View.VISIBLE);
//                    menuimg4.setImageResource(R.drawable.upw);
//                }
//            }
//        });
//
//        main2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(sub2.getVisibility()==View.VISIBLE) {
//                    sub2.setVisibility(View.GONE);
//                    menuimg2.setImageResource(R.drawable.downw);
//                }else{
//
//                    sub1.setVisibility(View.GONE);
//                    sub2.setVisibility(View.GONE);
//                    sub3.setVisibility(View.GONE);
//                    sub4.setVisibility(View.GONE);
//                    menuimg.setImageResource(R.drawable.downw);
//                    menuimg2.setImageResource(R.drawable.downw);
//                    menuimg3.setImageResource(R.drawable.downw);
//                    menuimg4.setImageResource(R.drawable.downw);
//
//                    sub2.setVisibility(View.VISIBLE);
//                    menuimg2.setImageResource(R.drawable.upw);
//                }
//            }
//        });
//
//        main3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(sub3.getVisibility()==View.VISIBLE) {
//                    sub3.setVisibility(View.GONE);
//                    menuimg3.setImageResource(R.drawable.downw);
//                }else{
//
//                    sub1.setVisibility(View.GONE);
//                    sub2.setVisibility(View.GONE);
//                    sub3.setVisibility(View.GONE);
//                    sub4.setVisibility(View.GONE);
//                    menuimg.setImageResource(R.drawable.downw);
//                    menuimg2.setImageResource(R.drawable.downw);
//                    menuimg3.setImageResource(R.drawable.downw);
//                    menuimg4.setImageResource(R.drawable.downw);
//
//                    sub3.setVisibility(View.VISIBLE);
//                    menuimg3.setImageResource(R.drawable.upw);
//                }
//            }
//        });



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