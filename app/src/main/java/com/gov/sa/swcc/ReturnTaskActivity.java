package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnTaskActivity extends AppCompatActivity {

    Global global;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_task);
        global=new Global(ReturnTaskActivity.this);

        EditText detials=(EditText)findViewById(R.id.detials);
        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        submit.setEnabled(false);
        ID=getIntent().getExtras().getString("ID");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTaskAddTime(ID,detials.getText().toString());
            }
        });
        detials.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(detials.getText().length()>2){
                    submit.setEnabled(true);
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                }else {
                    submit.setEnabled(false);
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnTaskActivity.this.finish();
            }
        });


    }

    private void GetTaskAddTime(String ID,String Comment) {
        String Token=global.GetValue("TaskToken");


        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().ReturnTask(Token,ID,Comment);
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
                        global.ShowMessageNF(response.body().getMessage(),ReturnTaskActivity.this);


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