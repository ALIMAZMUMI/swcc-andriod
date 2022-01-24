package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

public class SharekMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharek_menu);

        ArrayList<GridItem> birdList = new ArrayList<GridItem>();
        birdList.add(new GridItem("حضور العمال",R.drawable.singin));
        birdList.add(new GridItem("زيارات يومية",R.drawable.checklistdaily));
        birdList.add(new GridItem("تقييم العمال",R.drawable.rating));
        birdList.add(new GridItem("إصدار التقارير",R.drawable.checkin));


        DisplayMetrics displayMetrics = new DisplayMetrics();
        SharekMenuActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        GridAdapter adapter=new GridAdapter(SharekMenuActivity.this,R.layout.griditem,birdList,width,height,0);
        GridView gridView=(GridView)findViewById(R.id.servicegrid);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Animation animZoomIn = AnimationUtils.loadAnimation(SharekMenuActivity.this,R.anim.zoom_in);
                Animation animZoomOut = AnimationUtils.loadAnimation(SharekMenuActivity.this,R.anim.zoom_out);

                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);

                        if(i==0){
                            Intent ie =new Intent(SharekMenuActivity.this,SharekProjectsActivity.class);
                            ie.putExtra("SSID","1");
                            startActivity(ie);
                        }
                        else if(i==1){
                            startActivity(new Intent(SharekMenuActivity.this,SharekProjectsActivity.class));
                        }
                        else if(i==2){
                        }
                        else if(i==3){
                        }



                    }
                }, 150);


            }
        });



        getHeight(adapter,gridView);

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