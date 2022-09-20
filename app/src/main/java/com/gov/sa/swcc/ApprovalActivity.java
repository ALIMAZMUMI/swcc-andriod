package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.gov.sa.swcc.Adapter.MyTasksAdapter;
import com.gov.sa.swcc.Adapter.PendingRatingAdapter;
import com.gov.sa.swcc.Adapter.TaskEmpMGAdapter;
import com.gov.sa.swcc.model.Rating.Model;
import com.gov.sa.swcc.model.Rating.PendingRatingRequests;
import com.gov.sa.swcc.model.TaskEmpManager.GetAllTask;
import com.gov.sa.swcc.model.TaskEmpManager.TaskEmpManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovalActivity extends AppCompatActivity {
    Global global;
    ListView list;
    PendingRatingRequests data,data1,all;
    int index=0;
    PorgressDilog dialog;
TextView nodata;
    TextView ratingtask,extendtask,alltask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        global = new Global(ApprovalActivity.this);
        list = (ListView) findViewById(R.id.tasklist);
        ratingtask = (TextView) findViewById(R.id.ratingtask);
        extendtask = (TextView) findViewById(R.id.extendtask);
        alltask = (TextView) findViewById(R.id.alltask);
        nodata = (TextView) findViewById(R.id.nodata);

//

        ((ImageView)findViewById(R.id.backarrow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
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
                    PendingRatingRequests serch=new PendingRatingRequests();

                    serch.setModel(new ArrayList<Model>());


                    if(index==0){
                        for (int i = 0; i < data.getModel().size(); i++) {
                            if (data.getModel().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                                serch.getModel().add(data.getModel().get(i));
                            }
                        }
                    }else if(index==2){
                        for (int i = 0; i < data1.getModel().size(); i++) {
                            if (data1.getModel().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                                serch.getModel().add(data1.getModel().get(i));
                            }
                        }
                    }else if(index==3) {
                                for (int i = 0; i < all.getModel().size(); i++) {
                                    if (all.getModel().get(i).getEmployeeName().toLowerCase().contains(searchvalue.getText().toString().toLowerCase())) {
                                        serch.getModel().add(all.getModel().get(i));
                                    }
                                }
                    }






                    if(index==0){
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)serch.getModel()),0);
                        list.setAdapter(adp);
                    }else if(index==2){
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)serch.getModel()),0);
                        list.setAdapter(adp);
                    }else if(index==3) {
                        PendingRatingAdapter adp = new PendingRatingAdapter(ApprovalActivity.this, ((List) serch.getModel()), 0);
                        list.setAdapter(adp);
                    }

                }else
                {
                    if(index==0){
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)data.getModel()),0);
                        list.setAdapter(adp);
                    }else if(index==2){
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)data1.getModel()),0);
                        list.setAdapter(adp);
                    }else if(index==3) {
                        PendingRatingAdapter adp = new PendingRatingAdapter(ApprovalActivity.this, ((List) all.getModel()), 0);
                        list.setAdapter(adp);
                    }
                }


            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String ID = "";
                Model Obj = ((PendingRatingAdapter) list.getAdapter()).getItem(i);
                if(Obj.getTaskStatusId()==8&& false) {
                    Intent intent = new Intent(ApprovalActivity.this, RatingTaskActivity.class);
                    intent.putExtra("ID", Obj.getId() + "");
                    //intent.putExtra("Index", index);

                    startActivityForResult(intent,1002);
                }else if(Obj.getTaskStatusId()==7) {
                    Intent intent = new Intent(ApprovalActivity.this, ApproveCompleteActivity.class);
                    intent.putExtra("ID", Obj.getId() + "");
                    intent.putExtra("TaskName", Obj.getTaskName() + "");
                    intent.putExtra("EmployeeName", Obj.getEmployeeName() + "");
                    intent.putExtra("Date", global.GetDateFormat(Obj.getTo()) + "");
                    intent.putExtra("Decription", Obj.getTaskDecription() + "");
                    intent.putExtra("Comment", Obj.getTaskComment() + "");
                    intent.putExtra("attach",  "0");

                    if(Obj.getEmployeeAttachmentsDTO()!=null) {
                        intent.putExtra("attach", Obj.getEmployeeAttachmentsDTO().size() + "");
                        intent.putExtra("attachURL", Obj.getEmployeeAttachmentsDTO().get(0).getAttachlocation() + "");
                    }
                    //intent.putExtra("Index", index);

                    startActivityForResult(intent,1001);
                }else{
                    Intent intent = new Intent(ApprovalActivity.this, ExtendApproveActivity.class);
                    intent.putExtra("ID", Obj.getId() + "");
                    intent.putExtra("Name", Obj.getEmployeeName() + "");
                    intent.putExtra("Title", Obj.getTaskName() + "");
                    intent.putExtra("Note",  Obj.getTaskComment());
                    intent.putExtra("extend",  Obj.getIsTaskExtendedBefore()+"");
                    intent.putExtra("newDate",global.GetDateFormat(Obj.getExtendTimeRequestNewDate()));
                    intent.putExtra("Create",global.GetDateFormat(Obj.getTo()));

                    //intent.putExtra("Index", index);

                    startActivityForResult(intent,1003);
                }

            }
        });

        alltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alltask.setBackgroundResource(R.drawable.bluesmalround);
                alltask.setTextColor(Color.WHITE);
                if (data != null) {

                    if(data.getModel().size()>0) {
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)data.getModel()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }else {
                        list.setAdapter(null);
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }

                }else {
                    CallGetTask();
                }
                index = 0;

                ratingtask.setBackgroundResource(R.drawable.whitesmalround);
                ratingtask.setTextColor(Color.parseColor("#0066CC"));
                extendtask.setBackgroundResource(R.drawable.whitesmalround);
                extendtask.setTextColor(Color.parseColor("#0066CC"));

            }
        });


        ratingtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingtask.setBackgroundResource(R.drawable.bluesmalround);
                ratingtask.setTextColor(Color.WHITE);

                if (all != null) {
                    Log.d("Compleat",((List)all.getModel()).size()+"");
                    if(all.getModel().size()>0) {
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)all.getModel()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }else {
                        list.setAdapter(null);
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }

                }else
                {
                    GetCompletedTasks();
                }
                index = 3;

                alltask.setBackgroundResource(R.drawable.whitesmalround);
                alltask.setTextColor(Color.parseColor("#0066CC"));
                extendtask.setBackgroundResource(R.drawable.whitesmalround);
                extendtask.setTextColor(Color.parseColor("#0066CC"));


            }
        });


        extendtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extendtask.setBackgroundResource(R.drawable.bluesmalround);
                extendtask.setTextColor(Color.WHITE);

                if (data1 != null) {
                    if(data1.getModel().size()>0) {
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)data1.getModel()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }else {
                        list.setAdapter(null);
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }

                }else {

                    GetPendingExtendRequests();
                }
                index = 2;
                ratingtask.setBackgroundResource(R.drawable.whitesmalround);
                ratingtask.setTextColor(Color.parseColor("#0066CC"));
                alltask.setBackgroundResource(R.drawable.whitesmalround);
                alltask.setTextColor(Color.parseColor("#0066CC"));


            }
        });




        CallGetTask();

        SwipeRefreshLayout pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (index==0) {
                    CallGetTask();
                }
                else if (index==3) {
                    GetCompletedTasks();
                }
                else if (index==2) {
                    GetPendingExtendRequests();
                }
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


//    public void Deletetask(View view){
//        int pos=list.getPositionForView(view);
//
//        Intent intent = new Intent(ApprovalActivity.this, DeleteTaskActivity.class);
//        Log.d("--ID--",((GetAllTask)list.getAdapter().getItem(pos)).getId()+"");
//
//        intent.putExtra("ID", ((GetAllTask)list.getAdapter().getItem(pos)).getId()+"");
//        //intent.putExtra("Index", index);
//        startActivity(intent);
//
//
//    }
private void GetCompletedTasks() {
    //String Token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJjNzU5Nzg4Yy0wZWVjLTQwNDMtYmMzMy04YmZiMDNiNTFjMTkiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1MjIzNzY1LCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0.Nrv_e1XDvNcz5TV4gF9lBBaBKl9XsHKrsdBSSi_d2kg";
    //global.SaveValue("TaskToken","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiMzNmNjNkYy0yZmUyLTQ3YjMtOTRlMS0zMGY4YjRlMjM4ODYiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1NDA0NjkxLCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0._mSzWwIMLCsLQc8-dulQH5fzWuIy7TLsaR7R7WvLaOg");   //global.GetValue("TaskToken");
    String Token=global.GetValue("TaskToken");
    ratingtask.setBackgroundResource(R.drawable.bluesmalround);
    ratingtask.setTextColor(Color.WHITE);

    alltask.setBackgroundResource(R.drawable.whitesmalround);
    alltask.setTextColor(Color.parseColor("#0066CC"));
    extendtask.setBackgroundResource(R.drawable.whitesmalround);
    extendtask.setTextColor(Color.parseColor("#0066CC"));

    nodata.setVisibility(View.GONE);
    list.setVisibility(View.VISIBLE);

    Call<PendingRatingRequests> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetCompletedTasks(Token);
     dialog =  new PorgressDilog(this);
    dialog.show();
    call.enqueue(new Callback<PendingRatingRequests>() {
        @Override
        public void onResponse(Call<PendingRatingRequests> call, Response<PendingRatingRequests> response) {
            Log.d("Resp",response.message()+"");
            if(response.isSuccessful())
            {

                if(response.body().getStatusCode()==1){
                    dialog.dismiss();
                    all=response.body();
//                        all=new PendingRatingRequests();
//                        List<Model> r=new ArrayList<>();
//                        all.setModel(r);
//                        all.getModel().addAll(data.getModel());
//                        all.getModel().addAll(data1.getModel());
//                        for(int i=0;i<data1.getModel().size();i++){
//                        all.getModel().add(data1.getModel().get(i));
//                        }
                    Log.d("Reeeeeeeedddddddd","PendingRatingAdapter");


                    if(response.body().getTasksCount()>0){
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)response.body().getModel()),0);
                        list.setAdapter(adp);
                        nodata.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                    }
                    else
                    {                            list.setAdapter(null);
                        nodata.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }
                    Log.d("Reeeeeeeedddddddd","After PendingRatingAdapter");

                }else {
                    dialog.dismiss();
                    global.ShowMessage("");
                }

            }else {
                dialog.dismiss();
            }
        }

        @Override
        public void onFailure(Call<PendingRatingRequests>call, Throwable t) {
            dialog.dismiss();
            Log.d("Reeeeeeeeeee",t.getMessage()+"");

        }


    });
}

    private void GetPendingExtendRequests() {
        //String Token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJjNzU5Nzg4Yy0wZWVjLTQwNDMtYmMzMy04YmZiMDNiNTFjMTkiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1MjIzNzY1LCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0.Nrv_e1XDvNcz5TV4gF9lBBaBKl9XsHKrsdBSSi_d2kg";
        //global.SaveValue("TaskToken","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiMzNmNjNkYy0yZmUyLTQ3YjMtOTRlMS0zMGY4YjRlMjM4ODYiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1NDA0NjkxLCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0._mSzWwIMLCsLQc8-dulQH5fzWuIy7TLsaR7R7WvLaOg");   //global.GetValue("TaskToken");
        String Token=global.GetValue("TaskToken");
        extendtask.setBackgroundResource(R.drawable.bluesmalround);
        extendtask.setTextColor(Color.WHITE);


        ratingtask.setBackgroundResource(R.drawable.whitesmalround);
        ratingtask.setTextColor(Color.parseColor("#0066CC"));
        alltask.setBackgroundResource(R.drawable.whitesmalround);
        alltask.setTextColor(Color.parseColor("#0066CC"));

        nodata.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        Call<PendingRatingRequests> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetPendingExtendRequests(Token);
         dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<PendingRatingRequests>() {
            @Override
            public void onResponse(Call<PendingRatingRequests> call, Response<PendingRatingRequests> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        data1=response.body();
//                        all=new PendingRatingRequests();
//                        List<Model> r=new ArrayList<>();
//                        all.setModel(r);
//                        all.getModel().addAll(data.getModel());
//                        all.getModel().addAll(data1.getModel());
//                        for(int i=0;i<data1.getModel().size();i++){
//                        all.getModel().add(data1.getModel().get(i));
//                        }



                        if(response.body().getTasksCount()>0){
                            PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)data1.getModel()),0);
                            list.setAdapter(adp);
                            nodata.setVisibility(View.GONE);
                            list.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            list.setAdapter(null);
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
            public void onFailure(Call<PendingRatingRequests>call, Throwable t) {
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
                case 1001:


                    if(data.getExtras().getBoolean("update")&&data.getExtras().getBoolean("rate"))
                    {
                        Intent intent = new Intent(ApprovalActivity.this, RatingTaskActivity.class);
                        intent.putExtra("ID",  data.getExtras().getString("ID")+ "");
                        //intent.putExtra("Index", index);

                        startActivityForResult(intent,1002);

                    } else if(data.getExtras().getBoolean("update")){
                        all=null;
                        data1=null;
                        this.data=null;
                        if (index==0) {
                            CallGetTask();
                        }
                        else if (index==3) {
                            GetCompletedTasks();
                        }
                        else if (index==2) {
                            GetPendingExtendRequests();
                        }
                    }

                    break;
                case 1002:

                    if(data.getExtras().getBoolean("update")){
                        all=null;
                        data1=null;
                        this.data=null;
                        if (index==0) {
                            CallGetTask();
                        }
                        else if (index==3) {
                            GetCompletedTasks();
                        }
                        else if (index==2) {
                            GetPendingExtendRequests();
                        }
                    }

                    break;

                case 1003:

                    if(data.getExtras().getBoolean("update")){
                        all=null;
                        data1=null;
                        this.data=null;
                        if (index==0) {
                            CallGetTask();
                        }
                        else if (index==3) {
                            GetCompletedTasks();
                        }
                        else if (index==2) {
                            GetPendingExtendRequests();
                        }
                    }

                    break;





            }
        }
    }

    private void CallGetTask() {
        //String Token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJjNzU5Nzg4Yy0wZWVjLTQwNDMtYmMzMy04YmZiMDNiNTFjMTkiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1MjIzNzY1LCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0.Nrv_e1XDvNcz5TV4gF9lBBaBKl9XsHKrsdBSSi_d2kg";
        //global.SaveValue("TaskToken","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiMzNmNjNkYy0yZmUyLTQ3YjMtOTRlMS0zMGY4YjRlMjM4ODYiLCJ2YWxpZCI6IjEiLCJ1aWQiOiIyOTA1NTAiLCJoaXJlcmFjaHkiOiJ7XCJOYW1lXCI6XCJNYWplZFwiLFwiVWlkXCI6XCIyOTA1NTBcIixcIkVtcGxveWVlc1wiOlt7XCJOYW1lXCI6XCJBaG1lZFwiLFwiVWlkXCI6XCIxOTAyMzZcIixcIkVtcGxveWVlc1wiOm51bGx9LHtcIk5hbWVcIjpcIkhhbnlcIixcIlVpZFwiOlwiOTgwNjUxXCIsXCJFbXBsb3llZXNcIjpudWxsfV19IiwiZXhwIjoxNjU1NDA0NjkxLCJpc3MiOiJodHRwczovL2FwaS5zd2NjLmdvdi5zYSIsImF1ZCI6Imh0dHBzOi8vYXBpLnN3Y2MuZ292LnNhIn0._mSzWwIMLCsLQc8-dulQH5fzWuIy7TLsaR7R7WvLaOg");   //global.GetValue("TaskToken");
        String Token=global.GetValue("TaskToken");

        alltask.setBackgroundResource(R.drawable.bluesmalround);
        alltask.setTextColor(Color.WHITE);
        ratingtask.setBackgroundResource(R.drawable.whitesmalround);
        ratingtask.setTextColor(Color.parseColor("#0066CC"));
        extendtask.setBackgroundResource(R.drawable.whitesmalround);
        extendtask.setTextColor(Color.parseColor("#0066CC"));
        nodata.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        Call<PendingRatingRequests> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetPendingRequests(Token);
         dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<PendingRatingRequests>() {
            @Override
            public void onResponse(Call<PendingRatingRequests> call, Response<PendingRatingRequests> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        data=response.body();
                        //GetPendingExtendRequests();
                        if(response.body().getTasksCount()>0){
                        PendingRatingAdapter adp=new PendingRatingAdapter(ApprovalActivity.this,((List)response.body().getModel()),0);
                        list.setAdapter(adp);
                        }
                        else
                        {                            list.setAdapter(null);
                            nodata.setVisibility(View.VISIBLE);
                            list.setVisibility(View.GONE);
                           // global.ShowMessageT("لا توجد طلبات مسجلة");

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
            public void onFailure(Call<PendingRatingRequests>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
}