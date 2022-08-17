package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gov.sa.swcc.model.CompleteTask.CompleteTask;
import com.gov.sa.swcc.model.CompleteTask.EmployeeAttachment;
import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteTaskActivity extends AppCompatActivity {

    Global global;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_task);
    global=new Global(DeleteTaskActivity.this);


        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        ID=getIntent().getExtras().getString("ID");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeleteTask(ID);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteTaskActivity.this.finish();
            }
        });



    }



    private void DeleteTask(String ID) {
        String Token=global.GetValue("TaskToken");

        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().DeleteEmployeeTask(Token,ID);
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
                        setResult(1002,intent);
                        global.ShowMessageNF(response.body().getMessage(),DeleteTaskActivity.this);


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