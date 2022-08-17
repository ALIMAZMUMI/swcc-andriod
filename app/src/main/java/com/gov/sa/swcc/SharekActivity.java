package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

public class SharekActivity extends AppCompatActivity {

    static    Global global;
    int height,width;
    GridAdapter adapter,adapter1;
    ArrayList<GridItem> birdList,birdList1;

    CardView mytask;
    static CardView fab;
    static LinearLayout emptask,taskaproval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharek);

        global=new Global(SharekActivity.this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        SharekActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;


        //global.SaveValue("Lan","en");

        birdList = new ArrayList<GridItem>();
        if(global.GetValue("Lan").equals("en")) {
            birdList.add(new GridItem("employees evaluation", R.drawable.ratecon, "SH1"));
            birdList.add(new GridItem("daily visits", R.drawable.dailyvist, "SH2"));
            birdList.add(new GridItem("employees attendance", R.drawable.signoncon, "SH3"));
            birdList.add(new GridItem("export report", R.drawable.report, "SH4"));
//            ((TextView)findViewById(R.id.sharektitle)).setText("Shareek Service");
//            ((LinearLayout)view.findViewById(R.id.sline)).setGravity(Gravity.LEFT);



        }else{
            birdList.add(new GridItem("تقييم العمالة", R.drawable.ratecon, "SH1"));
            birdList.add(new GridItem("زيارات يومية", R.drawable.dailyvist, "SH2"));
            birdList.add(new GridItem("حضور العمال", R.drawable.signoncon, "SH3"));

            birdList.add(new GridItem("اصدار التقارير", R.drawable.report, "SH4"));
        }
        adapter=new GridAdapter(SharekActivity.this,R.layout.griditem,birdList,width,height,0);
        GridView gridView=(GridView)findViewById(R.id.servicegrid1);
        gridView.setAdapter(adapter);
        getHeight(adapter,gridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("fav----","index------"+birdList.get(i).getType());



//                if(i==2) {
//                    Intent ie =new Intent(SharekActivity.this,SharekProjectsActivity.class);
//                    ie.putExtra("SSID","1");
//                    startActivity(ie);                }

                if(i==1) {
                    Intent ie =new Intent(SharekActivity.this,SharekProjectsActivity.class);
                    ie.putExtra("HT",birdList.get(i).getServiceName());
                    ie.putExtra("SSID","3");
                    startActivity(ie);
                }

                if(i==2) {
                    Intent ie =new Intent(SharekActivity.this,SharekProjectsActivity.class);
                    ie.putExtra("SSID","1");
                    ie.putExtra("HT",birdList.get(i).getServiceName());

                    startActivity(ie);                }

                if(i==0) {
                    Intent ie =new Intent(SharekActivity.this,SharekProjectsActivity.class);
                    ie.putExtra("SSID","2");
                    ie.putExtra("HT",birdList.get(i).getServiceName());
                    startActivity(ie);
                }

                if(i==3) {
                    Intent ie =new Intent(SharekActivity.this,ShareakReportNActivity.class);
                    ie.putExtra("HT",birdList.get(i).getServiceName());
                    startActivity(ie);
                }
                if(birdList.get(i).getType().contains("HR2")) {
                    startActivity(new Intent(SharekActivity.this,PayslipActivity.class));
                }
                if(birdList.get(i).getType().contains("HR1")) {
                    startActivity(new Intent(SharekActivity.this,LeaveActivity.class));
                }
                if(birdList.get(i).getType().contains("HR6")) {
                    startActivity(new Intent(SharekActivity.this,EmployeeIdentificationActivity.class));
                }
                if(birdList.get(i).getType().contains("HR5")) {
                    startActivity(new Intent(SharekActivity.this,EmpSearchActivity.class));
                }
                if(birdList.get(i).getType().contains("HR4")) {
                    startActivity(new Intent(SharekActivity.this,InsuranceInfoActivity.class));
                }

            }
        });
    }

    public void getHeight(GridAdapter listadp, GridView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            i=i+2;
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount()/3)));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }
}