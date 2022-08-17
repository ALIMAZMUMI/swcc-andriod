package com.gov.sa.swcc;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.MyTasksAdapter;
import com.gov.sa.swcc.Adapter.ProjectAdapter;
import com.gov.sa.swcc.Adapter.TaskEmpMGAdapter;
import com.gov.sa.swcc.model.Sharekproject;
import com.gov.sa.swcc.model.TaskEmpManager.TaskEmpManager;
import com.gov.sa.swcc.model.emptask.EmpTasks;
import com.gov.sa.swcc.model.emptask.GetAllMyTask;
import com.gov.sa.swcc.model.emptask.GetMyActiveTasks;
import com.gov.sa.swcc.model.emptask.GetMyCompletedTask;
import com.gov.sa.swcc.model.emptask.GetMyDelayedTasks;
import com.gov.sa.swcc.model.emptask.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTaskActivity extends AppCompatActivity {
Global global;
ListView list;
    EmpTasks data;
    int index=0;
    EmpTasks dataOrg;

    TextView donetask,litetask,curtask,alltask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

        global=new Global(MyTaskActivity.this);
        list=(ListView) findViewById(R.id.tasklist);
        donetask=(TextView) findViewById(R.id.donetask);
        litetask=(TextView) findViewById(R.id.litetask);
        curtask=(TextView) findViewById(R.id.curtask);
        alltask=(TextView) findViewById(R.id.alltask);


//        EditText searchvalue=(EditText)findViewById(R.id.searchvalue);
//
//        searchvalue.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                Toast.makeText(MyTaskActivity.this,"Change",Toast.LENGTH_SHORT).show();
//                if(searchvalue.getText().length()>0) {
//                    EmpTasks serch=new EmpTasks();
//
//                    serch.setModel(new Model());
//                    serch.getModel().setGetAllMyTasks(new ArrayList<>());
//                    serch.getModel().setGetMyCompletedTasks(new ArrayList<>());
//                    serch.getModel().setGetMyDelayedTasks(new ArrayList<>());
//                    serch.getModel().setGetMyActiveTasks(new ArrayList<>());
//
//                    for (int i = 0; i < data.getModel().getGetAllMyTasks().size(); i++) {
//                        if (data.getModel().getGetAllMyTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
//                            serch.getModel().getGetAllMyTasks().add(data.getModel().getGetAllMyTasks().get(i));
//                        }
//                    }
//                    for (int i = 0; i < data.getModel().getGetCompletedTasks().size(); i++) {
//                        if (data.getModel().getGetCompletedTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
//                            serch.getModel().getGetCompletedTasks().add(data.getModel().getGetCompletedTasks().get(i));
//                        }
//                    }
//
//                    for (int i = 0; i < data.getModel().getGetDelayedTasks().size(); i++) {
//                        if (data.getModel().getGetDelayedTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
//                            serch.getModel().getGetDelayedTasks().add(data.getModel().getGetDelayedTasks().get(i));
//                        }
//                    }
//
//                    for (int i = 0; i < data.getModel().getGetAllActiveTasks().size(); i++) {
//                        if (data.getModel().getGetAllActiveTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
//                            serch.getModel().getGetAllActiveTasks().add(data.getModel().getGetAllActiveTasks().get(i));
//                        }
//                    }
//
//                    //data=serch;
//
//                    if(index==0){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetAllTasks()),0);
//                        list.setAdapter(adp);
//                    }else if(index==1){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetAllActiveTasks()),0);
//                        list.setAdapter(adp);
//                    }else if(index==2){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetDelayedTasks()),0);
//                        list.setAdapter(adp);
//                    }else if(index==3){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetCompletedTasks()),0);
//                        list.setAdapter(adp);
//                    }
//
//                }else
//                {
//                    data=dataOrg;
//                    if(index==0){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetAllTasks()),0);
//                        list.setAdapter(adp);
//                    }else if(index==1){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetAllActiveTasks()),0);
//                        list.setAdapter(adp);
//                    }else if(index==2){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetDelayedTasks()),0);
//                        list.setAdapter(adp);
//                    }else if(index==3){
//                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetCompletedTasks()),0);
//                        list.setAdapter(adp);
//                    }
//                }
//
//
//            }
//        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String ID="";
        int status=0;
        Object Obj=((MyTasksAdapter)list.getAdapter()).getItem(i);
        if(index==0) {
            ID =""+((GetAllMyTask)Obj).getId();
            TaskDetailsActivity.getAllMyTask=((GetAllMyTask)Obj);
            status=((GetAllMyTask)Obj).getTaskStatusId();

        }else if(index==1) {
            ID =""+((GetMyActiveTasks)Obj).getId();
            TaskDetailsActivity.getMyActiveTasks=((GetMyActiveTasks)Obj);
            status=((GetMyActiveTasks)Obj).getTaskStatusId();


        }else if(index==2) {
            ID =""+((GetMyDelayedTasks)Obj).getId();
            TaskDetailsActivity.getMyDelayedTasks=((GetMyDelayedTasks)Obj);
            status=((GetMyDelayedTasks)Obj).getTaskStatusId();


        }else if(index==3) {
            ID =""+((GetMyCompletedTask)Obj).getId();
            TaskDetailsActivity.getMyCompletedTask=((GetMyCompletedTask)Obj);
            status=((GetMyCompletedTask)Obj).getTaskStatusId();

        }
//if(status!=3&&status!=7&&status!=8&&status!=10&&status!=11){

    if(true){




        Intent intent=new Intent(MyTaskActivity.this,TaskDetailsActivity.class);
        intent.putExtra("ID",ID);
        intent.putExtra("Index",index);
    startActivityForResult(intent,1002);
        //startActivity(intent);
}

    }
});

alltask.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        alltask.setBackgroundResource(R.drawable.bluesmalround);
        alltask.setTextColor(Color.WHITE);
        if(data!=null){
            MyTasksAdapter adp=new MyTasksAdapter(MyTaskActivity.this,((List)data.getModel().getGetAllMyTasks()),0);
            list.setAdapter(adp);
        }
        index=0;

        donetask.setBackgroundResource(R.drawable.whitesmalround);
        donetask.setTextColor(Color.parseColor("#0066CC"));
        litetask.setBackgroundResource(R.drawable.whitesmalround);
        litetask.setTextColor(Color.parseColor("#0066CC"));
        curtask.setBackgroundResource(R.drawable.whitesmalround);
        curtask.setTextColor(Color.parseColor("#0066CC"));

    }
});



        donetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donetask.setBackgroundResource(R.drawable.bluesmalround);
                donetask.setTextColor(Color.WHITE);

                if(data!=null){
                    MyTasksAdapter adp=new MyTasksAdapter(MyTaskActivity.this,((List)data.getModel().getGetMyCompletedTasks()),3);
                    list.setAdapter(adp);
                }
                index=3;
                alltask.setBackgroundResource(R.drawable.whitesmalround);
                alltask.setTextColor(Color.parseColor("#0066CC"));
                litetask.setBackgroundResource(R.drawable.whitesmalround);
                litetask.setTextColor(Color.parseColor("#0066CC"));
                curtask.setBackgroundResource(R.drawable.whitesmalround);
                curtask.setTextColor(Color.parseColor("#0066CC"));

            }
        });


        litetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                litetask.setBackgroundResource(R.drawable.bluesmalround);
                litetask.setTextColor(Color.WHITE);

                if(data!=null){
                    MyTasksAdapter adp=new MyTasksAdapter(MyTaskActivity.this,((List)data.getModel().getGetMyDelayedTasks()),2);
                    list.setAdapter(adp);
                }
                index=2;
                donetask.setBackgroundResource(R.drawable.whitesmalround);
                donetask.setTextColor(Color.parseColor("#0066CC"));
                alltask.setBackgroundResource(R.drawable.whitesmalround);
                alltask.setTextColor(Color.parseColor("#0066CC"));
                curtask.setBackgroundResource(R.drawable.whitesmalround);
                curtask.setTextColor(Color.parseColor("#0066CC"));

            }
        });


        curtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curtask.setBackgroundResource(R.drawable.bluesmalround);
                curtask.setTextColor(Color.WHITE);

                if(data!=null){
                    MyTasksAdapter adp=new MyTasksAdapter(MyTaskActivity.this,((List)data.getModel().getGetMyActiveTasks()),1);
                    list.setAdapter(adp);
                }
                index=1;

                donetask.setBackgroundResource(R.drawable.whitesmalround);
                donetask.setTextColor(Color.parseColor("#0066CC"));
                litetask.setBackgroundResource(R.drawable.whitesmalround);
                litetask.setTextColor(Color.parseColor("#0066CC"));
                alltask.setBackgroundResource(R.drawable.whitesmalround);
                alltask.setTextColor(Color.parseColor("#0066CC"));

            }
        });



        CallGetTask();



    }



    private void CallGetTask() {
        //String Token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJhNTYzYmM1Mi1kNGU2LTRjOGEtOGEzMC1lYWRkMTFjNGJlNjYiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIxOTAyNTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1MzA2MTk0LCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0.Qoz-j5-2TvUELCxiZoImVeD7AsbYxw7NJCd8m2VMPDk";
        String Token=global.GetValue("TaskToken");

        alltask.setBackgroundResource(R.drawable.bluesmalround);
        alltask.setTextColor(Color.WHITE);
        donetask.setBackgroundResource(R.drawable.whitesmalround);
        donetask.setTextColor(Color.parseColor("#0066CC"));
        litetask.setBackgroundResource(R.drawable.whitesmalround);
        litetask.setTextColor(Color.parseColor("#0066CC"));
        curtask.setBackgroundResource(R.drawable.whitesmalround);
        curtask.setTextColor(Color.parseColor("#0066CC"));


        Call<EmpTasks> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetAllMyTasks(Token);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<EmpTasks>() {
            @Override
            public void onResponse(Call<EmpTasks> call, Response<EmpTasks> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        if(response.body().getTasksCount()>0){
                        data=response.body();
                            dataOrg=response.body();
                        MyTasksAdapter adp=new MyTasksAdapter(MyTaskActivity.this,((List)response.body().getModel().getGetAllMyTasks()),0);
                        list.setAdapter(adp);
                        }else
                        {
                            global.ShowMessageNF("لا توجد مهام مسجلة",MyTaskActivity.this);

                        }
                    }else {
                        dialog.dismiss();
                       // global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<EmpTasks>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 1002:

                        if(data.getExtras().getBoolean("update")){
                            CallGetTask();
                        }

                    break;

                case 1400:

                    break;




            }
        }
    }




}