package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtendApproveActivity extends AppCompatActivity {




    Global global;
    String ID,Name,Title,Note,Ch,NewDate,CreateDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_approve);
        global=new Global(ExtendApproveActivity.this);

        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        TextView taskname=(TextView) findViewById(R.id.taskname);
        TextView empname=(TextView) findViewById(R.id.empname);
        TextView tasknote=(TextView) findViewById(R.id.tasknote);

        TextView chance=(TextView) findViewById(R.id.chance);
        TextView chatitle=(TextView) findViewById(R.id.chatitle);
        TextView chatitle1=(TextView) findViewById(R.id.chatitle1);
        TextView EmpName=(TextView) findViewById(R.id.EmpName);




        submit.setEnabled(true);
        ID=getIntent().getExtras().getString("ID");
        Name=getIntent().getExtras().getString("Name");
        Title=getIntent().getExtras().getString("Title");
        Note=getIntent().getExtras().getString("Note");
        Ch=getIntent().getExtras().getString("extend");
        NewDate=getIntent().getExtras().getString("newDate");
        CreateDate=getIntent().getExtras().getString("Create");
if(!Ch.contains("t")){
    chance.setText("الفرصة الاولى");
}else {
    chance.setText("الفرصة الثانية");
}

chatitle.setText("التاريخ الحالي لتسليم المهمة هو:"+CreateDate);
        chatitle1.setText("التاريخ الجديد سيصبح :"+NewDate);
        EmpName.setText(Name);

        taskname.setText(Title);
        empname.setText(Name);
        tasknote.setText(Note);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTaskAddTime(ID);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reject(ID);
            }
        });


    }
    private void Reject(String ID) {
        String Token=global.GetValue("TaskToken");


        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().RejectTask(Token,ID);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<ExtendTask>() {
            @Override
            public void onResponse(Call<ExtendTask> call, Response<ExtendTask> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        Intent intent=new Intent();
                        intent.putExtra("update",true);
                        setResult(1001,intent);
                        global.ShowMessageNF(response.body().getMessage(),ExtendApproveActivity.this);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ExtendTask>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

    private void GetTaskAddTime(String ID) {
        String Token=global.GetValue("TaskToken");


        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().ApproveExtendTask(Token,ID);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<ExtendTask>() {
            @Override
            public void onResponse(Call<ExtendTask> call, Response<ExtendTask> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        Intent intent=new Intent();
                        intent.putExtra("update",true);
                        setResult(1001,intent);
                        global.ShowMessageNF(response.body().getMessage(),ExtendApproveActivity.this);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ExtendTask>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


}