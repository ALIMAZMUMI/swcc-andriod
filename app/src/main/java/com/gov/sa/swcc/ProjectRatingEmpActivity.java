package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.ProjectRaitingAdapter;
import com.gov.sa.swcc.Adapter.ProjectSignAdapter;
import com.gov.sa.swcc.model.Signproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRatingEmpActivity extends AppCompatActivity {
    Global global ;
    ListView projrcts;
    List<Signproject> employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_rating_emp);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);



        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });


        global=new Global(ProjectRatingEmpActivity.this);
        projrcts=(ListView)findViewById(R.id.projrcts);
        String ID=getIntent().getExtras().getString("Sid","");


        Calendar Current=Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String SelDate=sdf.format(Current.getTime());

        CallSharek(ID,SelDate);

projrcts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(ProjectRatingEmpActivity.this,EmpRatingActivity.class);
        intent.putExtra("EID",employee.get(i).getId());
        intent.putExtra("EName",employee.get(i).getEmployeeName());
        startActivity(intent);

    }
});




    }



    private void CallSharek(String ID,String Date) {
        String user=global.GetValue("Username").replace("u","");



        Call<List<Signproject>> call = RetrofitClient.getInstance(Api.Sharek).getMyApi().GetEmployeeTransactions(ID,Date);
        ProgressDialog dialog = ProgressDialog.show(ProjectRatingEmpActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<List<Signproject>>() {
            @Override
            public void onResponse(Call<List<Signproject>> call, Response<List<Signproject>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        employee=response.body();
                        ProjectRaitingAdapter adp=new ProjectRaitingAdapter(ProjectRatingEmpActivity.this,response.body());
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


    public void getHeight(ProjectRaitingAdapter listadp, ListView listview)
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