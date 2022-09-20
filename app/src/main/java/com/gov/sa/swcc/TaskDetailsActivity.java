package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailsActivity extends AppCompatActivity {
TextView remtxt,datetext,attatext,desctxt,attachtxt,returnbtn,taskname;
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
    String SFileName,SFileExt,SFileURL,SFileURLEmp;
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

        taskname=(TextView) findViewById(R.id.taskname);

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


        attachtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Openattach();
               // OpenFile();
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


    private void GetTaskDetails()
    {
        String Token = global.GetValue("TaskToken");

        if (Index == 0) {
            taskname.setText(getAllMyTask.getTaskName());

            if(getAllMyTask.getTaskStatusId()==3||
                    getAllMyTask.getTaskStatusId()==7||
                    getAllMyTask.getTaskStatusId()==8||
                    getAllMyTask.getTaskStatusId()==10||
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

            datetext.setText(global.GetDateFormat(getAllMyTask.getTo()));
            attatext.setText(global.GetTime(getAllMyTask.getTo()));
            desctxt.setText(getAllMyTask.getTaskDecription());
            int count=0;
            if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                count=getAllMyTask.getManagerAttachmentsDTO().size();

                SFileName= URLUtil.guessFileName(getAllMyTask.getManagerAttachmentsDTO().get(0).getAttachlocation(), null, null);
                SFileExt= SFileName.substring(SFileName.lastIndexOf('.') + 1);
                SFileURL=getAllMyTask.getManagerAttachmentsDTO().get(0).getAttachlocation();

            }

if(count==0){
    attachtxt.setText("لا توجد مرفقات");

}else {
            attachtxt.setText("تحميل "+ count +" مرفق");
}
            String Days;
            if (getAllMyTask.getGetRemainingTime().getDays() >= 0) {
                Days = "متبقي" + global.DTxt(getAllMyTask.getGetRemainingTime().getDays());

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getAllMyTask.getGetRemainingTime().getDays()<4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }
                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + global.DTxt(getAllMyTask.getGetRemainingTime().getDays() );
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);

                remtxt.setText(Days);
            }
        }
        else if (Index == 1) {
            taskname.setText(getMyActiveTasks.getTaskName());

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
            attatext.setText(global.GetTime(getMyActiveTasks.getTo()));
            desctxt.setText(getMyActiveTasks.getTaskDecription());

            int count=0;
            if(getMyActiveTasks.getManagerAttachmentsDTO()!=null&&getMyActiveTasks.getManagerAttachmentsDTO().size()>0){
                count=getMyActiveTasks.getManagerAttachmentsDTO().size();
                SFileName= URLUtil.guessFileName(getMyActiveTasks.getManagerAttachmentsDTO().get(0).getAttachlocation(), null, null);
                SFileExt= SFileName.substring(SFileName.lastIndexOf('.') + 1);
                SFileURL=getMyActiveTasks.getManagerAttachmentsDTO().get(0).getAttachlocation();

            }
            if(count==0){
                attachtxt.setText("لا توجد مرفقات");

            }else {
                attachtxt.setText("تحميل "+ count +" مرفق");
            }            String Days;
            if (getMyActiveTasks.getGetRemainingTime().getDays() >= 0 ) {
                Days = "متبقي" + global.DTxt(getMyActiveTasks.getGetRemainingTime().getDays());

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getMyActiveTasks.getGetRemainingTime().getDays()<=4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }
                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + global.DTxt(getMyActiveTasks.getGetRemainingTime().getDays() );
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);
                remtxt.setText(Days);
            }
        }
        else if (Index == 2) {
            taskname.setText(getMyDelayedTasks.getTaskName());

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
            max1.setVisibility(View.GONE);
            ((TextView)findViewById(R.id.extendtxt)).setText("لا يمكن تمديد مهمة متاخرة");

            datetext.setText(global.GetDateFormat(getMyDelayedTasks.getTo()));
            attatext.setText(global.GetTime(getMyDelayedTasks.getTo()));
            desctxt.setText(getMyDelayedTasks.getTaskDecription());

            int count=0;
            if(getMyDelayedTasks.getManagerAttachmentsDTO()!=null&&getMyDelayedTasks.getManagerAttachmentsDTO().size()>0){
                count=getMyDelayedTasks.getManagerAttachmentsDTO().size();


                SFileName= URLUtil.guessFileName(getMyDelayedTasks.getManagerAttachmentsDTO().get(0).getAttachlocation(), null, null);
                SFileExt= SFileName.substring(SFileName.lastIndexOf('.') + 1);
                SFileURL=getMyDelayedTasks.getManagerAttachmentsDTO().get(0).getAttachlocation();
            }
            if(count==0){
                attachtxt.setText("لا توجد مرفقات");

            }else {
                attachtxt.setText("تحميل "+ count +" مرفق");
            }            String Days;
            if (false&&getMyDelayedTasks.getGetRemainingTime().getDays() >= 0 && getMyDelayedTasks.getGetRemainingTime().getHours()>0 ) {
                Days = "متبقي" + global.DTxt(getMyDelayedTasks.getGetRemainingTime().getDays() );

                remtxt.setTextColor(Color.parseColor("#0066CC"));
                timeremin.setBackgroundResource(R.drawable.nortime);
                if(getMyDelayedTasks.getGetRemainingTime().getDays()<=4) {
                    remtxt.setTextColor(Color.parseColor("#EAB011"));
                    timeremin.setBackgroundResource(R.drawable.neartime);
                }                remtxt.setText(Days);
            } else {
                Days = "متأخرة" + global.DTxt(getMyDelayedTasks.getGetRemainingTime().getDays() );
                remtxt.setTextColor(Color.parseColor("#CC0000"));
                timeremin.setBackgroundResource(R.drawable.pastime);
                remtxt.setText(Days);
            }
        }
        else if (Index == 3) {
            taskname.setText(getMyCompletedTask.getTaskName());
//            if(getMyCompletedTask.getTaskStatusId()==7||
//                    getMyCompletedTask.getTaskStatusId()==11){
                //remtxt.setVisibility(View.INVISIBLE);
                completebtn.setVisibility(View.GONE);
                returnbtn.setVisibility(View.GONE);
                sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime1.setEnabled(false);
                sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
                sendtime2.setEnabled(false);
            //timeremin.setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.extendtime)).setVisibility(View.GONE);
            datetext.setText(global.GetDateFormat(getMyCompletedTask.getTo()));
            attatext.setText(global.GetTime(getMyCompletedTask.getTo()));
            desctxt.setText(getMyCompletedTask.getTaskDecription());


            int count=0;
            if(getMyCompletedTask.getManagerAttachmentsDTO()!=null&&getMyCompletedTask.getManagerAttachmentsDTO().size()>0){
                count=getMyCompletedTask.getManagerAttachmentsDTO().size();

                SFileName= URLUtil.guessFileName(getMyCompletedTask.getManagerAttachmentsDTO().get(0).getAttachlocation(), null, null);
                SFileExt= SFileName.substring(SFileName.lastIndexOf('.') + 1);
                SFileURL=getMyCompletedTask.getManagerAttachmentsDTO().get(0).getAttachlocation();
            }
            if(count==0){
                attachtxt.setText("لا توجد مرفقات");

            }else {
                attachtxt.setText("تحميل "+ count +" مرفق");
            }            String Days;

            remtxt.setTextColor(Color.parseColor("#0066CC"));
            if(getMyCompletedTask.getTaskStatusId() == 7){
                remtxt.setText("قيد الاعتماد و التقييم");
                if (getMyCompletedTask.getIsDelayedTask()) {
                    timeremin.setBackgroundResource(R.drawable.donetasklite);
                }else {
                    timeremin.setBackgroundResource(R.drawable.donetaskgreen);
                }


            }else if(getMyCompletedTask.getTaskStatusId() == 8){
                remtxt.setText("بانتظار التقييم");
                if (getMyCompletedTask.getIsDelayedTask()) {
                    timeremin.setBackgroundResource(R.drawable.donetasklite);
                }else {
                    timeremin.setBackgroundResource(R.drawable.donetaskgreen);
                }
            }
            else if (getMyCompletedTask.getIsDelayedTask()) {
                remtxt.setText("تمت بتأخير");
                if (getMyCompletedTask.getIsDelayedTask()) {
                    timeremin.setBackgroundResource(R.drawable.donetasklite);
                }else {
                    timeremin.setBackgroundResource(R.drawable.donetaskgreen);
                }
            } else {
                remtxt.setText("تمت بنجاح");
                if (getMyCompletedTask.getIsDelayedTask()) {
                    timeremin.setBackgroundResource(R.drawable.donetasklite);
                }else {
                    timeremin.setBackgroundResource(R.drawable.donetaskgreen);
                }
            }

//            if (getMyCompletedTask.getGetRemainingTime().getDays() >= 0) {
//                Days = "متبقي" + global.DTxt(getMyCompletedTask.getGetRemainingTime().getDays() );
//
//                remtxt.setTextColor(Color.parseColor("#0066CC"));
//                timeremin.setBackgroundResource(R.drawable.nortime);
//                if(getMyCompletedTask.getGetRemainingTime().getDays()<=4) {
//                    remtxt.setTextColor(Color.parseColor("#EAB011"));
//                    timeremin.setBackgroundResource(R.drawable.neartime);
//                }                remtxt.setText(Days);
//            } else {
//                Days = "متأخرة" + global.DTxt(getMyCompletedTask.getGetRemainingTime().getDays() );
//                remtxt.setTextColor(Color.parseColor("#CC0000"));
//                timeremin.setBackgroundResource(R.drawable.pastime);
//                remtxt.setText(Days);
//            }





        }

        if (Index == 0) {

            int count=0;
            if(getAllMyTask.getManagerAttachmentsDTO()!=null&&getAllMyTask.getManagerAttachmentsDTO().size()>0){
                count=1;
                SFileURL=getAllMyTask.getManagerAttachmentsDTO().get(0).getAttachlocation();
            }
            if(getAllMyTask.getEmployeeAttachmentsDTO()!=null&&getAllMyTask.getEmployeeAttachmentsDTO().size()>0){
                count++;
                SFileURLEmp=getAllMyTask.getEmployeeAttachmentsDTO().get(0).getAttachlocation();
            }

            if(count==0){
                attachtxt.setText("لا توجد مرفقات");

            }else {
                attachtxt.setText("عرض المرفقات");
            }

        }
        else if (Index == 1) {

            int count=0;
            if(getMyActiveTasks.getManagerAttachmentsDTO()!=null&&getMyActiveTasks.getManagerAttachmentsDTO().size()>0){
                count=1;
                SFileURL=getMyActiveTasks.getManagerAttachmentsDTO().get(0).getAttachlocation();
            }
            if(getMyActiveTasks.getEmployeeAttachmentsDTO()!=null&&getMyActiveTasks.getEmployeeAttachmentsDTO().size()>0){
                count++;
                SFileURLEmp=getMyActiveTasks.getEmployeeAttachmentsDTO().get(0).getAttachlocation();
            }

            if(count==0){
                attachtxt.setText("لا توجد مرفقات");

            }else {
                attachtxt.setText("عرض المرفقات");
            }
        }
        else if (Index == 2) {
            int count=0;
            if(getMyDelayedTasks.getManagerAttachmentsDTO()!=null&&getMyDelayedTasks.getManagerAttachmentsDTO().size()>0){
                count=1;
                SFileURL=getMyDelayedTasks.getManagerAttachmentsDTO().get(0).getAttachlocation();
            }
            if(getMyDelayedTasks.getEmployeeAttachmentsDTO()!=null&&getMyDelayedTasks.getEmployeeAttachmentsDTO().size()>0){
                count++;
                SFileURLEmp=getMyDelayedTasks.getEmployeeAttachmentsDTO().get(0).getAttachlocation();
            }

            if(count==0){
                attachtxt.setText("لا توجد مرفقات");

            }else {
                attachtxt.setText("عرض المرفقات");
            }
        }
        else if (Index == 3) {
            int count=0;
            if(getMyCompletedTask.getManagerAttachmentsDTO()!=null&&getMyCompletedTask.getManagerAttachmentsDTO().size()>0){
                count=1;
                SFileURL=getMyCompletedTask.getManagerAttachmentsDTO().get(0).getAttachlocation();
            }
            if(getMyCompletedTask.getEmployeeAttachmentsDTO()!=null&&getMyCompletedTask.getEmployeeAttachmentsDTO().size()>0){
                count++;
                SFileURLEmp=getMyCompletedTask.getEmployeeAttachmentsDTO().get(0).getAttachlocation();
            }

            if(count==0){
                attachtxt.setText("لا توجد مرفقات");
            }else {
                Log.d("SFileURLEmp",SFileURLEmp+"----"+SFileURL);
                attachtxt.setText("عرض المرفقات");
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
                        }
                        else{
                         if(response.body().getTasksCount()==1) {
                            sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                            sendtime1.setEnabled(false);
                            sendtime2.setBackgroundResource(R.drawable.bluesmalround);
                            sendtime2.setEnabled(true);
                        }else {
                            sendtime1.setBackgroundResource(R.drawable.grayroundbtn);
                            sendtime1.setEnabled(false);
                            sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
                            sendtime2.setEnabled(false);
                        }
                            GetTaskHistoryBtn(response.body().getTasksCount());
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

    private void GetTaskHistoryBtn(int c) {
        String Token=global.GetValue("TaskToken");

int count=c;
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

if(count==1){
    for(int i=0;i<response.body().getModel().size();i++){
        if(count==1&&response.body().getModel().get(i).getToStatus().equals(" طلب تمديد للوقت للمهمة")){
            sendtime1.setBackgroundResource(R.drawable.underprogress);
            sendtime1.setEnabled(false);
            sendtime1.setText("قيد الانتظار");
            sendtime1.setTextColor(Color.parseColor("#F96523"));
            sendtime2.setBackgroundResource(R.drawable.grayroundbtn);
            sendtime2.setEnabled(false);
        }else if(count==1&&response.body().getModel().get(i).getToStatus().equals("تم قبول طلب تمديد وقت المهمة")){
            sendtime1.setBackgroundResource(R.drawable.rejected);
            sendtime1.setEnabled(false);
            sendtime1.setText("تم القبول");
            sendtime1.setTextColor(Color.parseColor("#9FCE2E"));
            break;
        }else  if(count==1&&response.body().getModel().get(i).getToStatus().equals("تم رفض إعتماد المهمة")){
            sendtime1.setBackgroundResource(R.drawable.underprogress);
            sendtime1.setEnabled(false);
            sendtime1.setText("تم الرفض");
            sendtime1.setTextColor(Color.parseColor("#F96523"));
            break;

        }

    }
}else if(count==2) {
    int i = 0;

    for (i = 0; i < response.body().getModel().size(); i++) {
        if (count == 2 && response.body().getModel().get(i).getToStatus().equals(" طلب تمديد للوقت للمهمة")) {
            sendtime2.setBackgroundResource(R.drawable.underprogress);
            sendtime2.setEnabled(false);
            sendtime2.setText("قيد الانتظار");
            sendtime2.setTextColor(Color.parseColor("#F96523"));
break;
        } else if (count == 2 && response.body().getModel().get(i).getToStatus().equals("تم قبول طلب تمديد وقت المهمة")) {
            sendtime2.setBackgroundResource(R.drawable.rejected);
            sendtime2.setEnabled(false);
            sendtime2.setText("تم القبول");
            sendtime2.setTextColor(Color.parseColor("#9FCE2E"));
            break;
        } else if (count == 2 && response.body().getModel().get(i).getToStatus().equals("تم رفض إعتماد المهمة")) {
            sendtime2.setBackgroundResource(R.drawable.underprogress);
            sendtime2.setEnabled(false);
            sendtime2.setText("تم الرفض");
            sendtime2.setTextColor(Color.parseColor("#F96523"));
            break;
        }
    }

    for ( i=i; i < response.body().getModel().size(); i++) {
        if (response.body().getModel().get(i).getToStatus().equals("تم قبول طلب تمديد وقت المهمة")) {
            sendtime1.setBackgroundResource(R.drawable.rejected);
            sendtime1.setEnabled(false);
            sendtime1.setText("تم القبول");
            sendtime1.setTextColor(Color.parseColor("#9FCE2E"));
        } else if (response.body().getModel().get(i).getToStatus().equals("تم رفض إعتماد المهمة")) {
            sendtime1.setBackgroundResource(R.drawable.underprogress);
            sendtime1.setEnabled(false);
            sendtime1.setText("تم الرفض");
            sendtime1.setTextColor(Color.parseColor("#F96523"));
        }


    }
}





//                        TaskHistoryAdapter adp=new TaskHistoryAdapter(TaskDetailsActivity.this,response.body().getModel());
//                        tranhist.setAdapter(adp);
//                        labmax2.setVisibility(View.VISIBLE);
//                        max2.setRotation(180);
//                        getHeight(adp,tranhist);

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
getHeight(adp,tranhist);

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

    public void getHeight(TaskHistoryAdapter listadp, ListView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight;
        listview.setLayoutParams(params);
        listview.requestLayout();
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


    public  boolean isStoragePermissionGranted() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED ) {
                Log.v("Swcc","Permission is granted");
                return true;
            } else {

                Log.v("SWCC","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SWCC","Permission is granted");
            return true;
        }
    }
    public void OpenFile(String SFileURL) {
        if(SFileURL!=null){
        Log.d("SFileURL",SFileURL);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(SFileURL));
        startActivity(browserIntent);}
//        if(isStoragePermissionGranted()){
//
//            //open_file();
//
//            downloadPdfContent(SFileURL);
//
//        }


    }
    public void downloadPdfContent(String urlToDownload){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String fileName=SFileName;
                    String fileExtension=SFileExt;

//           download pdf file.
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    URL url = new URL(urlToDownload);
                    System.out.println("--pdf downloaded--ok--"+urlToDownload);
                    HttpURLConnection c = (HttpURLConnection) url.openConnection();
                    c.setRequestProperty("Accept-Charset", "UTF-8");
                    c.setRequestMethod("GET");
                    //c.setDoOutput(true);
                    c.setConnectTimeout(60000);
                    c.connect();
                    String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/swccpdf/";
                    File file = new File(PATH);
                    file.mkdirs();
                    File outputFile = new File(file, fileName);

                        outputFile.createNewFile();

                    FileOutputStream fos = new FileOutputStream(outputFile);
                    InputStream is = c.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    while ((len1 = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len1);
                    }
                    fos.close();
                    is.close();
                    System.out.println("--pdf downloaded--ok--"+urlToDownload);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            open_file();

                        //                            pdfView.fromFile(outputFile).load();
//                            showres.setVisibility(View.VISIBLE);
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //new Global(TaskDetailsActivity.this).ShowMessage("فضلا كتابة البيانات بشكل صحيح");
                        }
                    });
                }


            }});
    }
    public void open_file() {

            File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/swccpdf/" + SFileName);  // -> filename = maven.pdf
            Uri path = FileProvider.getUriForFile(TaskDetailsActivity.this,
                    "com.gov.sa.swcc.fileprovider",
                    pdfFile);
        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        String mimeType = myMime.getMimeTypeFromExtension(SFileExt);

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path,mimeType);
        Log.d("mimeType",mimeType);
        Log.d("UriType",path.getPath());


            try{
                startActivity(pdfIntent);
            }catch(ActivityNotFoundException e){
                //Toast.makeText(TaskDetailsActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }

    }

    public void Openattach(){


if(SFileURL!=null&&SFileURLEmp!=null) {

    AlertDialog.Builder builderSingle = new AlertDialog.Builder(TaskDetailsActivity.this,R.style.AlertDialogCustom);
    builderSingle.setTitle("مرفقات الطلب");
    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TaskDetailsActivity.this, android.R.layout.select_dialog_item);
    arrayAdapter.add("مرفق اساسي");
    arrayAdapter.add("مرفق اضافي");
    builderSingle.setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });

    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which==0){
                OpenFile( SFileURL);
            }else if(which==1){
                OpenFile( SFileURLEmp);
            }
        }
    });
    builderSingle.show();
}else if(SFileURL!=null){
    OpenFile( SFileURL);

}else if(SFileURLEmp!=null){
    OpenFile( SFileURLEmp);
}

    }


}