package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveCompleteActivity extends AppCompatActivity {
    Global global;
    String ID,TaskName,EmployeeName,Date,Decription,Comment,attachtxt,attachURL;
    TextView tasktitle,empname,taskdate,detials,note,attach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_complete);
        global=new Global(ApproveCompleteActivity.this);

        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        submit.setEnabled(true);
        ID=getIntent().getExtras().getString("ID");
        TaskName=getIntent().getExtras().getString("TaskName");
        EmployeeName=getIntent().getExtras().getString("EmployeeName");
        Date=getIntent().getExtras().getString("Date");
        Decription=getIntent().getExtras().getString("Decription");
        Comment=getIntent().getExtras().getString("Comment");
        attachtxt=getIntent().getExtras().getString("attach");
        attachURL=getIntent().getExtras().getString("attachURL");


        tasktitle=(TextView) findViewById(R.id.tasktitle);
        empname=(TextView) findViewById(R.id.empname);
        taskdate=(TextView) findViewById(R.id.taskdate);
        detials=(TextView) findViewById(R.id.detials);
        note=(TextView) findViewById(R.id.note);
        attach=(TextView) findViewById(R.id.attach);


        attach.setText(attachtxt+" مرفق");
        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFile();
            }
        });
        tasktitle.setText(TaskName);
        empname.setText(EmployeeName);
        taskdate.setText(Date);
        note.setText(Comment);
        detials.setText(Decription);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTaskAddTime(ID);
            }
        });

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RejectTaskAddTime(ID);
            }
        });


    }

    private void GetTaskAddTime(String ID) {
        String Token=global.GetValue("TaskToken");


        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().ApproveTask(Token,ID);
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
                        intent.putExtra("rate",true);
                        intent.putExtra("ID",ID);
                        setResult(1001,intent);
                        ApproveCompleteActivity.this.finish();

//                        Intent intent = new Intent(ApproveCompleteActivity.this, RatingTaskActivity.class);
//                        intent.putExtra("ID", ID+ "");
//                        //intent.putExtra("Index", index);

                        //startActivityForResult(intent,1002);
                       // global.ShowMessageNF(response.body().getMessage(),ApproveCompleteActivity.this);


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
    private void RejectTaskAddTime(String ID) {
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
                        global.ShowMessageNFH(TaskName,ApproveCompleteActivity.this,"تم ارجاع المهمة بنجاح");
ApproveCompleteActivity.this.finish();

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


    public void OpenFile() {
        if(attachURL!=null){
        Log.d("SFileURL",attachURL);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(attachURL));
        startActivity(browserIntent);}
//        if(isStoragePermissionGranted()){
//
//            //open_file();
//
//            downloadPdfContent(SFileURL);
//
//        }


    }

}