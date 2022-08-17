package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

public class EmpTransactionActivity extends AppCompatActivity {
    Global global;
    int height,width;
    GridAdapter adapter,adapter1;
    ArrayList<GridItem> birdList,birdList1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_transaction);


        global=new Global(EmpTransactionActivity.this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        EmpTransactionActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        birdList = new ArrayList<GridItem>();
        birdList.add(new GridItem("حضور المرؤوسين",R.drawable.emptrans,"EH1"));

        adapter=new GridAdapter(EmpTransactionActivity.this,R.layout.griditem,birdList,width,height,0);
        GridView gridView=(GridView)findViewById(R.id.servicegrid1);
        gridView.setAdapter(adapter);
        getHeight(adapter,gridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("fav----","index------"+birdList.get(i).getType());


                if(birdList.get(i).getType().contains("EH1")) {
                    startActivity(new Intent(EmpTransactionActivity.this,EmployeesTransActivity.class));
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