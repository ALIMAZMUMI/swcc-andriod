package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gov.sa.swcc.Adapter.CardsAdapter;
import com.gov.sa.swcc.Adapter.ProjectAdapter;
import com.gov.sa.swcc.model.InsuranceInfo;
import com.gov.sa.swcc.model.Sharekproject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharekProjectsActivity extends AppCompatActivity {
Global global ;
ListView projrcts;
String SSID;
    List<Sharekproject> sharekproject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharek_projects);
        global=new Global(SharekProjectsActivity.this);
        projrcts=(ListView)findViewById(R.id.projrcts);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


SSID=getIntent().getExtras().getString("SSID","");
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        projrcts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(SSID.equals("1")){
                    Intent intent=new Intent(SharekProjectsActivity.this,ProjectSignActivity.class);
                    intent.putExtra("Sid",sharekproject.get(i).getSupervisorId().toString());
                    startActivity(intent);
                }

                if(SSID.equals("2")){
                    Intent intent=new Intent(SharekProjectsActivity.this,ProjectRatingEmpActivity.class);
                    intent.putExtra("Sid",sharekproject.get(i).getSupervisorId().toString());
                    startActivity(intent);
                }
                if(SSID.equals("3")){
                    Intent intent=new Intent(SharekProjectsActivity.this,ShareakPartnerActivity.class);
                    intent.putExtra("Sid",sharekproject.get(i).getSupervisorId().toString());
                    intent.putExtra("ProjectMangerId",sharekproject.get(i).getProjectManagerId().toString());
                    intent.putExtra("Classfcation_LK",sharekproject.get(i).getClassfcationLK().toString());
                    startActivity(intent);
                }

            }
        });

        CallSharek();


    }


    private void CallSharek() {
        String user=global.GetValue("Username").replace("u","");



        Call<List<Sharekproject>> call = RetrofitClient.getInstance(Api.Sharek).getMyApi().GetAllSupervisorProjecct(user);
        ProgressDialog dialog = ProgressDialog.show(SharekProjectsActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<List<Sharekproject>>() {
            @Override
            public void onResponse(Call<List<Sharekproject>> call, Response<List<Sharekproject>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        sharekproject=response.body();
                        ProjectAdapter adp=new ProjectAdapter(SharekProjectsActivity.this,response.body());
                        projrcts.setAdapter(adp);
                    }else {
                        dialog.dismiss();
                        global.ShowMessage("لا توجد مشاريع مسجلة");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Sharekproject>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


}