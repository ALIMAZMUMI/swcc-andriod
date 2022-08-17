package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.Adapter.GridFavAdapter;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

public class FaveroitActivity extends AppCompatActivity {
Global global;
int height,width;
    GridFavAdapter adapter,adapter1;
    ArrayList<GridItem> birdList,birdList1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faveroit);

        Button submit=(Button)findViewById(R.id.submit);
        Button unselect=(Button)findViewById(R.id.unselect);

        global=new Global(FaveroitActivity.this);

//        global.GetValue("HRFav");
//        global.GetValue("TicFav");

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
//

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        birdList = new ArrayList<GridItem>();
        birdList.add(new GridItem("الحضور و الإنصراف",R.drawable.checkin,"HR3",global.GetValue("HRFav").contains("HR3")));

        birdList.add(new GridItem("مسيّر الراتب",R.drawable.profile,"HR2",global.GetValue("HRFav").contains("HR2")));
        birdList.add(new GridItem("الاجازات",R.drawable.leave,"HR1",global.GetValue("HRFav").contains("HR1")));


        birdList.add(new GridItem("التعريف بالراتب",R.drawable.salary,"HR6",global.GetValue("HRFav").contains("HR6")));
        birdList.add(new GridItem("دليل العاملين",R.drawable.searchicon,"HR5",global.GetValue("HRFav").contains("HR5")));
        birdList.add(new GridItem("التأمين الصحي",R.drawable.insur,"HR4",global.GetValue("HRFav").contains("HR4")));


        birdList.add(new GridItem("المرؤوسين",R.drawable.empstrans,"HR7"));

        adapter=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList,width,height,0);
        GridView gridView=(GridView)findViewById(R.id.servicegrid1);
        gridView.setAdapter(adapter);
        getHeight(adapter,gridView);

        Log.d("fav----",global.GetValue("HRFav"));
        Log.d("fav----","ALI TEST");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("fav----",i+"");
                birdList.get(i).setChecked(!birdList.get(i).isChecked());
                adapter=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList,width,height,0);
                gridView.setAdapter(adapter);
                getHeight(adapter,gridView);
                submit.setBackgroundResource(R.drawable.blueroundfull);
                submit.setEnabled(true);
            }
        });



        unselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global.SaveValue("HRFav","");
                global.SaveValue("TEFav","");
                submit.setBackgroundResource(R.drawable.grayroundbtn);
                submit.setEnabled(false);
                unselect.setVisibility(View.GONE);


                birdList = new ArrayList<GridItem>();
                birdList.add(new GridItem("الحضور و الإنصراف",R.drawable.checkin,"HR3",global.GetValue("HRFav").contains("HR3")));

                birdList.add(new GridItem("مسيّر الراتب",R.drawable.profile,"HR2",global.GetValue("HRFav").contains("HR2")));
                birdList.add(new GridItem("الاجازات",R.drawable.leave,"HR1",global.GetValue("HRFav").contains("HR1")));


                birdList.add(new GridItem("التعريف بالراتب",R.drawable.salary,"HR6",global.GetValue("HRFav").contains("HR6")));
                birdList.add(new GridItem("دليل العاملين",R.drawable.searchicon,"HR5",global.GetValue("HRFav").contains("HR5")));
                birdList.add(new GridItem("التأمين الصحي",R.drawable.insur,"HR4",global.GetValue("HRFav").contains("HR4")));
                birdList.add(new GridItem("المرؤوسين",R.drawable.empstrans,"HR7",global.GetValue("HRFav").contains("HR7")));

                adapter=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList,width,height,0);
                GridView gridView=(GridView)findViewById(R.id.servicegrid1);
                gridView.setAdapter(adapter);
                getHeight(adapter,gridView);

                birdList1 = new ArrayList<GridItem>();
                //birdList1.add(new GridItem("تقنية المعلومات",R.drawable.iticon,"TE1",global.GetValue("TEFav").contains("TE1")));
                birdList1.add(new GridItem("العناية بالعاملين",R.drawable.hricon,"TE2",global.GetValue("TEFav").contains("TE2")));
                birdList1.add(new GridItem("الملاحظات والبلاغات",R.drawable.complintnote,"TE3",global.GetValue("TEFav").contains("TE3")));

                adapter1=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList1,width,height,0);
                GridView gridView1=(GridView)findViewById(R.id.servicegrid2);
                gridView1.setAdapter(adapter1);
                getHeight(adapter1,gridView1);

            }
        });

for(int i=0;i<birdList.size();i++){
if(birdList.get(i).isChecked()){
    submit.setBackgroundResource(R.drawable.blueroundfull);
    submit.setEnabled(true);
    unselect.setVisibility(View.VISIBLE);
    break;
}
}





        birdList1 = new ArrayList<GridItem>();
        //birdList1.add(new GridItem("تقنية المعلومات",R.drawable.iticon,"TE1",global.GetValue("TEFav").contains("TE1")));
        birdList1.add(new GridItem("العناية بالعاملين",R.drawable.hricon,"TE2",global.GetValue("TEFav").contains("TE2")));
        birdList1.add(new GridItem("الملاحظات والبلاغات",R.drawable.complintnote,"TE3",global.GetValue("TEFav").contains("TE3")));

        adapter1=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList1,width,height,0);
        GridView gridView1=(GridView)findViewById(R.id.servicegrid2);
        gridView1.setAdapter(adapter1);
        getHeight(adapter1,gridView1);

        Log.d("fav----",global.GetValue("HRFav"));
        Log.d("fav----","ALI TEST");
        Log.d("fav----",global.GetValue("TEFav"));


        for(int i=0;i<birdList1.size();i++){
            if(birdList1.get(i).isChecked()){
                submit.setBackgroundResource(R.drawable.blueroundfull);
                submit.setEnabled(true);
                unselect.setVisibility(View.VISIBLE);
                break;
            }
        }

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("fav----",i+"");
                birdList1.get(i).setChecked(!birdList1.get(i).isChecked());
                adapter1=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList1,width,height,0);
                gridView1.setAdapter(adapter1);
                getHeight(adapter1,gridView1);
                for(int x=0;x<birdList1.size();x++){
                    if(birdList1.get(x).isChecked()){
                        submit.setBackgroundResource(R.drawable.blueroundfull);
                        submit.setEnabled(true);
                        unselect.setVisibility(View.VISIBLE);
                        break;
                    }
                    else {
                        submit.setBackgroundResource(R.drawable.grayroundbtn);
                        submit.setEnabled(false);
                        unselect.setVisibility(View.GONE);
                    }
                }
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("fav----",i+"");
                birdList.get(i).setChecked(!birdList.get(i).isChecked());
                adapter=new GridFavAdapter(FaveroitActivity.this,R.layout.griditem,birdList,width,height,0);
                gridView.setAdapter(adapter);
                getHeight(adapter,gridView);

                for(int x=0;x<birdList.size();x++){
                    if(birdList.get(x).isChecked()){
                        submit.setBackgroundResource(R.drawable.blueroundfull);
                        submit.setEnabled(true);
                        unselect.setVisibility(View.VISIBLE);
                        break;
                    }
                    else {
                        submit.setBackgroundResource(R.drawable.grayroundbtn);
                        submit.setEnabled(false);
                        unselect.setVisibility(View.GONE);
                    }
                }


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String HRFav="";
                for(int i=0;i<birdList.size();i++){

                    if(birdList.get(i).isChecked()){
                        HRFav+=birdList.get(i).getType();
                    }

                }

                global.SaveValue("HRFav",HRFav);


                String TEFav="";
                for(int i=0;i<birdList1.size();i++){

                    if(birdList1.get(i).isChecked()){
                        TEFav+=birdList1.get(i).getType();
                    }

                }

                global.SaveValue("TEFav",TEFav);

FaveroitActivity.this.finish();


            }
        });




    }


    public void getHeight(GridFavAdapter listadp, GridView listview)
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