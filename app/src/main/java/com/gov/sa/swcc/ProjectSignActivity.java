package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.PaysilpAdapter;
import com.gov.sa.swcc.Adapter.ProjectAdapter;
import com.gov.sa.swcc.Adapter.ProjectSignAdapter;
import com.gov.sa.swcc.model.Sharekproject;
import com.gov.sa.swcc.model.Signproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSignActivity extends AppCompatActivity {
    Global global ;
    ListView projrcts;
    ImageView next,prev;
    TextView saldate;
    Calendar c,Current;
String SelDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_sign);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


        ((TextView)findViewById(R.id.headertxt)).setText(getIntent().getExtras().getString("HT",""));
        ((ImageView)findViewById(R.id.backarrow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });


        global=new Global(ProjectSignActivity.this);
        projrcts=(ListView)findViewById(R.id.projrcts);



        c = Calendar.getInstance();
        Current=Calendar.getInstance();
        c.add(Calendar.DATE,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SelDate=sdf.format(c.getTime());
        saldate=(TextView)findViewById(R.id.saldate);

        String ID=getIntent().getExtras().getString("Sid","");

        next=(ImageView) findViewById(R.id.next);
        prev=(ImageView) findViewById(R.id.prev);
        if(global.CheckInternet(ProjectSignActivity.this)) {
        }else{
            saldate.setText(SelDate);
            CallSharek(ID,SelDate);
           // header.setText(SelDate);
        }


        if (global.GetValue("Lan").equals("en")) {
            ((TextView)findViewById(R.id.textheader)).setText("employee attendance");
            ((LinearLayout)findViewById(R.id.sline)).setGravity(Gravity.LEFT);


        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.DATE,1);
                SelDate=sdf.format(c.getTime());
                if(Current.getTimeInMillis()>c.getTimeInMillis()){
                    if(global.CheckInternet(ProjectSignActivity.this)) {
                    }else{
                        saldate.setText(SelDate);
                        CallSharek(ID,SelDate);
                    }
                }else {
                    c.add(Calendar.DATE,-1);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.DATE,-1);
                SelDate=sdf.format(c.getTime());
                try {
                    if(global.CheckInternet(ProjectSignActivity.this)) {
                    }else{
                        saldate.setText(SelDate);
                        CallSharek(ID,SelDate);
                    }

                }catch (Exception e){
                    Log.d("Error --------",e.toString());
                }
            }
        });




    }



    private void CallSharek(String ID,String Date) {
        String user=global.GetValue("Username").replace("u","");



        Call<List<Signproject>> call = RetrofitClient.getInstance(Api.Global).getMyApi().GetEmployeeTransactions(ID,Date);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<Signproject>>() {
            @Override
            public void onResponse(Call<List<Signproject>> call, Response<List<Signproject>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        ProjectSignAdapter adp=new ProjectSignAdapter(ProjectSignActivity.this,response.body());
                        projrcts.setAdapter(adp);
                        getHeight(adp,projrcts);
                    }else {
                        dialog.dismiss();
                        global.ShowMessage("لا يوجد بيانات مسجلة");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Signproject>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


    public void getHeight(ProjectSignAdapter listadp, ListView listview)
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