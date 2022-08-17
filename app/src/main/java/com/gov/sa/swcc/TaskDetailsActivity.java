package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.MyTasksAdapter;
import com.gov.sa.swcc.Adapter.TaskHistoryAdapter;
import com.gov.sa.swcc.model.EmployeeTaskDetails.EmployeeTaskDetails;
import com.gov.sa.swcc.model.ExtendTimeHistory.ExtendTimeHistory;
import com.gov.sa.swcc.model.TaskHistory.TaskHistory;
import com.gov.sa.swcc.model.emptask.EmpTasks;
import com.gov.sa.swcc.model.emptask.GetAllMyTask;
import com.gov.sa.swcc.model.emptask.GetMyActiveTasks;
import com.gov.sa.swcc.model.emptask.GetMyCompletedTask;
import com.gov.sa.swcc.model.emptask.GetMyDelayedTasks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailsActivity extends AppCompatActivity {
TextView remtxt,datetext,attatext,desctxt,attachtxt,returnbtn;
ImageView max1,max2;
LinearLayout labmax1,labmax2;
Button sendtime1,sendtime2,completebtn;
ListView tranhist;
Global global;
    int Index;
    static GetAllMyTask getAllMyTask;
    static GetMyActiveTasks getMyActiveTasks;
    static GetMyDelayedTasks getMyDelayedTasks;
    static GetMyCompletedTask getMyCompletedTask;
ImageView timeremin;

boolean update=false;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);

        global=new Global(TaskDetailsActivity.this);
        remtxt=(TextView) findViewById(R.id.remtxt);
        datetext=(TextView) findViewById(R.id.datetext);
        attatext=(TextView) findViewById(R.id.attatext);
        desctxt=(TextView) findViewById(R.id.desctxt);
        attachtxt=(TextView) findViewById(R.id.attachtxt);

         max1=(ImageView) findViewById(R.id.max1);
         max2=(ImageView) findViewById(R.id.max2);
         labmax1=(LinearLayout) findViewById(R.id.labmax1);
         labmax2=(LinearLayout) findViewById(R.id.labmax2);
         sendtime1=(Button) findViewById(R.id.sendtime1);
         sendtime2=(Button) findViewById(R.id.sendtime2);
         completebtn=(Button) findViewById(R.id.completebtn);
         returnbtn=(TextView) findViewById(R.id.returnbtn);
         tranhist=(ListView) findViewById(R.id.tranhist);
        timeremin=(ImageView)findViewById(R.id.timeremin);

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("update",update);
                setResult(1002,intent);
                finish();
            }
        });




         completebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TaskDetailsActivity.this,Task_Done_Activity.class);
                intent.putExtra("ID",ID);
                startActivityForResult(intent,1002);
            }
        });
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(TaskDetailsActivity.this,ReturnTaskActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);

            }
        });


          ID=getIntent().getExtras().getString("ID","");
         Index=getIntent().getExtras().getInt("Index",0);

        max1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(labmax1.getVisibility()== View.VISIBLE){
                    labmax1.setVisibility(View.GONE);
                    max1.setRotation(0);
                }else {
                    GetExtendTimeHistory();
                }
            }
        });

        max2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(labmax2.getVisibility()== View.VISIBLE){
                    labmax2.setVisibility(View.GONE);
                    max2.setRotation(0);
                }else {
                    GetTaskHistory() ;
                }
            }
        });


        sendtime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TaskDetailsActivity.this,TaskAddTimeActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
                labmax1.setVisibility(View.GONE);
                max1.setRotation(0);
            }
        });
        sendtime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TaskDetailsActivity.this,TaskAddTimeActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
                labmax1.setVisibility(View.GONE);
                max1.setRotation(0);
            }
        });

        GetTaskDetails();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 1002:

                    if(data.getExtras().getBoolean("update")){
                        update=data.getExtras().getBoolean("update");
                        Intent intent=new Intent();
                        intent.putExtra("update",update);
                        setResult(1002,intent);
                        finish();
                    }

                    break;

                case 1400:

                    break;




            }
        }
    }


    private void GetTaskDetails() {
        String Token = global.GetValue("TaskToken");


        if (Index == 0) {
            if(getAllMyTask.getTaskStatusId()==7||
                    getAllMyTask.getTaskStatusId()==11){
                remtxt.setVisibility(View.INVISIBLE);
                timeremin.setVisibility(View.INVISIBLE);
                completebtn.setVisibility(View.GONE);
                returnbtn.setVisibility(View.GONE);
                sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime1.setEnabled(false);
                sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime2.setEnabled(false);
                ((LinearLayout)findViewById(R.id.extendtime)).setVisibility(View.GONE);
            }

            datetext.setText(global.GetDateFormat(getAllMyTask.getFrom()));
            attatext.setText(global.GetTime(getAllMyTask.getFrom()));
            desctxt.setText(getAllMyTask.getTaskDecription());
            int count=0;
            if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                count=getAllMyTask.getManagerAttachmentsDTO().size();
            }


            attachtxt.setText("تحميل "+ count +" مرفق");
            String Days;
            if (getAllMyTask.getGetRemainingTime().getDays() > 0) {
                Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getAllMyTask.getGetRemainingTime().getDays()<4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }
                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);

                remtxt.setText(Days);
            }
        } else if (Index == 1) {

            if(getMyActiveTasks.getTaskStatusId()==7||
                    getMyActiveTasks.getTaskStatusId()==11){
                remtxt.setVisibility(View.INVISIBLE);
                completebtn.setVisibility(View.GONE);
                returnbtn.setVisibility(View.GONE);
                sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime1.setEnabled(false);
                sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime2.setEnabled(false);
            }
            datetext.setText(global.GetDateFormat(getMyActiveTasks.getTo()));
            attatext.setText(global.GetTime(getMyActiveTasks.getFrom()));
            desctxt.setText(getMyActiveTasks.getTaskDecription());

            int count=0;
            if(getMyActiveTasks.getManagerAttachmentsDTO()!=null){
                count=getMyActiveTasks.getManagerAttachmentsDTO().size();
            }
            attachtxt.setText("تحميل "+count +" مرفق");
            String Days;
            if (getMyActiveTasks.getGetRemainingTime().getDays() > 0) {
                Days = "متبقي" + getMyActiveTasks.getGetRemainingTime().getDays() + "أيام";

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getMyActiveTasks.getGetRemainingTime().getDays()<4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }
                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + getMyActiveTasks.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);
                remtxt.setText(Days);
            }
        } else if (Index == 2) {

            if(getMyDelayedTasks.getTaskStatusId()==7||
                    getMyDelayedTasks.getTaskStatusId()==11){
                remtxt.setVisibility(View.INVISIBLE);
                completebtn.setVisibility(View.GONE);
                returnbtn.setVisibility(View.GONE);
                sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime1.setEnabled(false);
                sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime2.setEnabled(false);
            }
            datetext.setText(global.GetDateFormat(getMyDelayedTasks.getTo()));
            attatext.setText(global.GetTime(getMyDelayedTasks.getFrom()));
            desctxt.setText(getMyDelayedTasks.getTaskDecription());

            int count=0;
            if(getMyDelayedTasks.getManagerAttachmentsDTO()!=null){
                count=getMyDelayedTasks.getManagerAttachmentsDTO().size();
            }
            attachtxt.setText("تحميل "+count +" مرفق");
            String Days;
            if (getMyDelayedTasks.getGetRemainingTime().getDays() > 0) {
                Days = "متبقي" + getMyDelayedTasks.getGetRemainingTime().getDays() + "أيام";

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getMyDelayedTasks.getGetRemainingTime().getDays()<4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + getMyDelayedTasks.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);
                remtxt.setText(Days);
            }
        } else if (Index == 3) {

            if(getMyCompletedTask.getTaskStatusId()==7||
                    getMyCompletedTask.getTaskStatusId()==11){
                remtxt.setVisibility(View.INVISIBLE);
                completebtn.setVisibility(View.GONE);
                returnbtn.setVisibility(View.GONE);
                sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime1.setEnabled(false);
                sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime2.setEnabled(false);
            }
            datetext.setText(global.GetDateFormat(getMyCompletedTask.getTo()));
            attatext.setText(global.GetTime(getMyCompletedTask.getFrom()));
            desctxt.setText(getMyCompletedTask.getTaskDecription());


            int count=0;
            if(getMyCompletedTask.getManagerAttachmentsDTO()!=null){
                count=getMyCompletedTask.getManagerAttachmentsDTO().size();
            }
            attachtxt.setText("تحميل "+count +" مرفق");
            String Days;
            if (getMyCompletedTask.getGetRemainingTime().getDays() > 0) {
                Days = "متبقي" + getMyCompletedTask.getGetRemainingTime().getDays() + "أيام";

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getMyCompletedTask.getGetRemainingTime().getDays()<4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + getMyCompletedTask.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);
                remtxt.setText(Days);
            }





        }


    }

    private void GetExtendTimeHistory() {
        String Token=global.GetValue("TaskToken");


        Call<ExtendTimeHistory> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetExtendTimeHistory(Token,ID);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<ExtendTimeHistory>() {
            @Override
            public void onResponse(Call<ExtendTimeHistory> call, Response<ExtendTimeHistory> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        if(response.body().getTasksCount()==0){
                            sendtime1.setBackgroundResource(R.drawable.bluesmalround);
                            sendtime1.setEnabled(true);
                        }else {
                            sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                            sendtime1.setEnabled(false);
                            sendtime2.setBackgroundResource(R.drawable.bluesmalround);
                            sendtime2.setEnabled(true);
                        }
                        labmax1.setVisibility(View.VISIBLE);
                        max1.setRotation(180);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ExtendTimeHistory>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

    private void GetTaskHistory() {
        String Token=global.GetValue("TaskToken");


        Call<TaskHistory> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetTaskHistory(Token,ID);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<TaskHistory>() {
            @Override
            public void onResponse(Call<TaskHistory> call, Response<TaskHistory> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        TaskHistoryAdapter adp=new TaskHistoryAdapter(TaskDetailsActivity.this,response.body().getModel());
                        tranhist.setAdapter(adp);
                        labmax2.setVisibility(View.VISIBLE);
                        max2.setRotation(180);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TaskHistory>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

    private void GetTaskDetailsold() {
        String Token=global.GetValue("TaskToken");


        Call<EmployeeTaskDetails> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetEmployeeTaskDetails(Token,global.GetValue("Taskid"));
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<EmployeeTaskDetails>() {
            @Override
            public void onResponse(Call<EmployeeTaskDetails> call, Response<EmployeeTaskDetails> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
//                        data=response.body();
//                        MyTasksAdapter adp=new MyTasksAdapter(MyTaskActivity.this,response.body().getModel().getGetAllMyTasks());
//                        list.setAdapter(adp);

//                        if (response.body().getModel().     getDays() > 0) {
//                            Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                            tasktime.setTextColor(Color.parseColor("#EAB011"));
//                        } else {
//                            Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                            tasktime.setTextColor(Color.parseColor("#CC0000"));
//                        }
//                        remtxt.setText(response.body().getModel().getRemaining());
                        datetext.setText(global.GetDateFormat(response.body().getModel().getTo()));
                        attatext.setText("");
                        desctxt.setText(response.body().getModel().getTaskDecription());
                        attachtxt.setText("");

                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<EmployeeTaskDetails>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


}