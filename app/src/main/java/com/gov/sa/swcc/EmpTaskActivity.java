package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.EmployeesTransactionAdapter;
import com.gov.sa.swcc.Adapter.MyTasksAdapter;
import com.gov.sa.swcc.Adapter.TaskEmpMGAdapter;
import com.gov.sa.swcc.model.HirereachyManager;
import com.gov.sa.swcc.model.TaskEmpManager.GetAllTask;
import com.gov.sa.swcc.model.TaskEmpManager.Model;
import com.gov.sa.swcc.model.TaskEmpManager.TaskEmpManager;
import com.gov.sa.swcc.model.emptask.EmpTasks;
import com.gov.sa.swcc.model.emptask.GetAllMyTask;
import com.gov.sa.swcc.model.emptask.GetMyActiveTasks;
import com.gov.sa.swcc.model.emptask.GetMyCompletedTask;
import com.gov.sa.swcc.model.emptask.GetMyDelayedTasks;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpTaskActivity extends AppCompatActivity {

    Global global;
    ListView list;
    TaskEmpManager data;
    TaskEmpManager dataOrg;
    TextView nodata;

    int index=1;

    TextView donetask,litetask,curtask,alltask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_task_);

        global = new Global(EmpTaskActivity.this);
        list = (ListView) findViewById(R.id.tasklist);
        donetask = (TextView) findViewById(R.id.donetask);
        litetask = (TextView) findViewById(R.id.litetask);
        curtask = (TextView) findViewById(R.id.curtask);
        alltask = (TextView) findViewById(R.id.alltask);

        nodata = (TextView) findViewById(R.id.nodata);


        ((ImageView)findViewById(R.id.backarrow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });

        CardView fab=(CardView)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmpTaskActivity.this, Emp_Add_TaskActivity.class);
                startActivityForResult(intent,1002);
            }
        });



        EditText searchvalue=(EditText)findViewById(R.id.searchvalue);

        searchvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(searchvalue.getText().length()>0) {
                    TaskEmpManager serch=new TaskEmpManager();

                    serch.setModel(new Model());
                    serch.getModel().setGetAllTasks(new ArrayList<>());
                    serch.getModel().setGetCompletedTasks(new ArrayList<>());
                    serch.getModel().setGetDelayedTasks(new ArrayList<>());
                    serch.getModel().setGetAllActiveTasks(new ArrayList<>());

                    for (int i = 0; i < data.getModel().getGetAllTasks().size(); i++) {
                        if (data.getModel().getGetAllTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                            serch.getModel().getGetAllTasks().add(data.getModel().getGetAllTasks().get(i));
                        }
                    }
                    for (int i = 0; i < data.getModel().getGetCompletedTasks().size(); i++) {
                        if (data.getModel().getGetCompletedTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                            serch.getModel().getGetCompletedTasks().add(data.getModel().getGetCompletedTasks().get(i));
                        }
                    }

                    for (int i = 0; i < data.getModel().getGetDelayedTasks().size(); i++) {
                        if (data.getModel().getGetDelayedTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                            serch.getModel().getGetDelayedTasks().add(data.getModel().getGetDelayedTasks().get(i));
                        }
                    }

                    for (int i = 0; i < data.getModel().getGetAllActiveTasks().size(); i++) {
                        if (data.getModel().getGetAllActiveTasks().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                            serch.getModel().getGetAllActiveTasks().add(data.getModel().getGetAllActiveTasks().get(i));
                        }
                    }

                    //data=serch;

                    if(index==0){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetAllTasks()),0);
                        list.setAdapter(adp);
                    }else if(index==1){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetAllActiveTasks()),0);
                        list.setAdapter(adp);
                    }else if(index==2){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetDelayedTasks()),0);
                        list.setAdapter(adp);
                    }else if(index==3){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)serch.getModel().getGetCompletedTasks()),0);
                        list.setAdapter(adp);
                    }

                }else
                {
data=dataOrg;
                    if(index==0){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetAllTasks()),0);
                        list.setAdapter(adp);
                    }else if(index==1){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetAllActiveTasks()),0);
                        list.setAdapter(adp);
                    }else if(index==2){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetDelayedTasks()),0);
                        list.setAdapter(adp);
                    }else if(index==3){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)dataOrg.getModel().getGetCompletedTasks()),0);
                        list.setAdapter(adp);
                    }
                }


            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String ID = "";
                Object Obj = ((TaskEmpMGAdapter) list.getAdapter()).getItem(i);
                if (index == 0) {
                    ID = "" + ((GetAllTask) Obj).getId();
                    TaskMangeDetailsActivity.getAllMyTask = ((GetAllTask) Obj);
                } else if (index == 1) {
                    ID = "" + ((GetAllTask) Obj).getId();
                    TaskMangeDetailsActivity.getMyActiveTasks = ((GetAllTask) Obj);

                } else if (index == 2) {
                    ID = "" + ((GetAllTask) Obj).getId();
                    TaskMangeDetailsActivity.getMyDelayedTasks = ((GetAllTask) Obj);

                } else if (index == 3) {
                    ID = "" + ((GetAllTask) Obj).getId();
                    TaskMangeDetailsActivity.getMyCompletedTask = ((GetAllTask) Obj);
                }

                Intent intent = new Intent(EmpTaskActivity.this, TaskMangeDetailsActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("Index", index);

                startActivity(intent);

            }
        });
        alltask.setVisibility(View.GONE);
        alltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alltask.setBackgroundResource(R.drawable.bluesmalround);
                alltask.setTextColor(Color.WHITE);
                if (data != null) {

                    if(data.getModel().getGetAllTasks().size()>0){
                        Log.d("Compleat",((List)data.getModel().getGetAllTasks()).size()+"");
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)data.getModel().getGetAllTasks()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
                    else {
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }
                }
                index = 0;

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

                if (data != null) {

                    if(data.getModel().getGetCompletedTasks().size()>0){
                        Log.d("Compleat",((List)data.getModel().getGetCompletedTasks()).size()+"");
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)data.getModel().getGetCompletedTasks()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
                    else {
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }
                }
                index = 3;
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

                if (data != null) {
                    if(data.getModel().getGetDelayedTasks().size()>0){
                    TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)data.getModel().getGetDelayedTasks()),0);
                    list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
                    else {
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }
                }
                index = 2;
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


                if (data != null) {
                    if(data.getModel().getGetAllActiveTasks().size()>0){
                        TaskEmpMGAdapter adp=new TaskEmpMGAdapter(EmpTaskActivity.this,((List)data.getModel().getGetAllActiveTasks()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
                    else {
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }
                }else {
                    nodata.setVisibility(View.VISIBLE);
                    list.setVisibility(View.GONE);
                }
                index = 1;

                donetask.setBackgroundResource(R.drawable.whitesmalround);
                donetask.setTextColor(Color.parseColor("#0066CC"));
                litetask.setBackgroundResource(R.drawable.whitesmalround);
                litetask.setTextColor(Color.parseColor("#0066CC"));
                alltask.setBackgroundResource(R.drawable.whitesmalround);
                alltask.setTextColor(Color.parseColor("#0066CC"));

            }
        });


        CallGetTask();


        SwipeRefreshLayout pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CallGetTask();
                pullToRefresh.setRefreshing(false);
            }
        });


        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (list.getChildAt(0) != null) {
                    pullToRefresh.setEnabled(list.getFirstVisiblePosition() == 0 && list.getChildAt(0).getTop() == 0);
                }
            }
        });

    }


    public void Deletetask(View view){
      int pos=list.getPositionForView(view);

        Intent intent = new Intent(EmpTaskActivity.this, DeleteTaskActivity.class);
            Log.d("--ID--",((GetAllTask)list.getAdapter().getItem(pos)).getId()+"");

                intent.putExtra("ID", ((GetAllTask)list.getAdapter().getItem(pos)).getId()+"");
                //intent.putExtra("Index", index);
                startActivityForResult(intent,1002);


    }


    public void Edittask(View view){
        int pos=list.getPositionForView(view);
        Intent intent = new Intent(EmpTaskActivity.this, Emp_Edit_TaskActivity.class);
        Log.d("--ID--",((GetAllTask)list.getAdapter().getItem(pos)).getId()+"");
        intent.putExtra("ID", ((GetAllTask)list.getAdapter().getItem(pos)).getId()+"");
        intent.putExtra("EmpName", ((GetAllTask)list.getAdapter().getItem(pos)).getEmployeeName()+"");
        intent.putExtra("TaskName", ((GetAllTask)list.getAdapter().getItem(pos)).getTaskName()+"");
        intent.putExtra("TaskDetailes", ((GetAllTask)list.getAdapter().getItem(pos)).getTaskDecription()+"");
        intent.putExtra("EmpID", ((GetAllTask)list.getAdapter().getItem(pos)).getEmployeeUid()+"");
        intent.putExtra("TaskDate", ((GetAllTask)list.getAdapter().getItem(pos)).getTo()+"");

        if(((GetAllTask)list.getAdapter().getItem(pos)).getManagerAttachmentsDTO()!=null) {
            intent.putExtra("Attachedfile", ((GetAllTask) list.getAdapter().getItem(pos)).getManagerAttachmentsDTO().size() );
        }else{
            intent.putExtra("Attachedfile", -1);

        }
        //intent.putExtra("Index", index);
        startActivityForResult(intent,1002);


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


    private void CallGetTask() {
        //String Token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJjNzU5Nzg4Yy0wZWVjLTQwNDMtYmMzMy04YmZiMDNiNTFjMTkiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1MjIzNzY1LCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0.Nrv_e1XDvNcz5TV4gF9lBBaBKl9XsHKrsdBSSi_d2kg";
       // global.SaveValue("TaskToken",Token);   //
        String Token= global.GetValue("TaskToken");

        curtask.setBackgroundResource(R.drawable.bluesmalround);
        curtask.setTextColor(Color.WHITE);

        donetask.setBackgroundResource(R.drawable.whitesmalround);
        donetask.setTextColor(Color.parseColor("#0066CC"));
        litetask.setBackgroundResource(R.drawable.whitesmalround);
        litetask.setTextColor(Color.parseColor("#0066CC"));
//        curtask.setBackgroundResource(R.drawable.whitesmalround);
//        curtask.setTextColor(Color.parseColor("#0066CC"));
//

            nodata.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);


        Call<TaskEmpManager> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetAllTasksMG(Token);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<TaskEmpManager>() {
            @Override
            public void onResponse(Call<TaskEmpManager> call, Response<TaskEmpManager> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        data=response.body();
                        dataOrg=response.body();
                        if(response.body().getTasksCount()>0) {
                            if(response.body().getModel().getGetAllActiveTasks().size()>0)
                            {  TaskEmpMGAdapter adp = new TaskEmpMGAdapter(EmpTaskActivity.this, ((List) response.body().getModel().getGetAllActiveTasks()), 1);
                            list.setAdapter(adp);
                            }else
                            {
                                nodata.setVisibility(View.VISIBLE);
                                list.setVisibility(View.GONE);


                            }
                        }
                        else
                        {
                            nodata.setVisibility(View.VISIBLE);
                            list.setVisibility(View.GONE);


                        }
                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TaskEmpManager>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

}