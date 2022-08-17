package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.borjabravo.simpleratingbar.SimpleRatingBar;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.Sharekproject;
import com.gov.sa.swcc.model.emptask.GetAllMyTask;
import com.gov.sa.swcc.model.emptask.GetMyActiveTasks;
import com.gov.sa.swcc.model.emptask.GetMyCompletedTask;
import com.gov.sa.swcc.model.emptask.GetMyDelayedTasks;

import java.util.List;

public class MyTasksAdapter extends ArrayAdapter<Object> {

    private final Activity context;
    List<Object> Titem;
    int index=0;


    public MyTasksAdapter(Activity context, List<Object> Titem,int index) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.index=index;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=null;

        if(index==0) {
            GetAllMyTask getAllMyTask;
            getAllMyTask= (GetAllMyTask) Titem.get(position);
            if (getAllMyTask.getTaskStatusId() != 7&&getAllMyTask.getTaskStatusId() != 8&&getAllMyTask.getTaskStatusId() != 10&&getAllMyTask.getTaskStatusId() != 11) {
                rowView = inflater.inflate(R.layout.mytask_cell, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.taskdate);
                TextView tasktime = (TextView) rowView.findViewById(R.id.tasktime);
                TextView taskattc = (TextView) rowView.findViewById(R.id.taskattc);
                ImageView taskstatus=(ImageView)rowView.findViewById(R.id.taskstatus);
                TextView col= (TextView) rowView.findViewById(R.id.col);

                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());
                String Days = "";
                if (getAllMyTask.getGetRemainingTime().getDays() > 0) {

                    Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
                    if(getAllMyTask.getGetRemainingTime().getDays()<=4)
                    { tasktime.setTextColor(Color.parseColor("#EAB011"));
                    taskstatus.setBackgroundResource(R.drawable.tasklite);
                        col.setBackgroundColor(Color.parseColor("#EAB011"));
                    }
                    else {
                        tasktime.setTextColor(Color.parseColor("#0066CC"));
                        taskstatus.setBackgroundResource(R.drawable.taskactivy);
                        col.setBackgroundColor(Color.parseColor("#0066CC"));

                    }

                }
                else {
                    Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                    tasktime.setTextColor(Color.parseColor("#CC0000"));
                    taskstatus.setBackgroundResource(R.drawable.taskdelay);
                    //col.setBackgroundColor(Color.parseColor("#CC0000"));

                }

                tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");
            }
            else {
                rowView = inflater.inflate(R.layout.task_cell_done, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.datetext);
                TextView taskattc = (TextView) rowView.findViewById(R.id.attatext);
                TextView txtdone = (TextView) rowView.findViewById(R.id.txtdone);
                ImageView imgdone = (ImageView) rowView.findViewById(R.id.imgdone);

                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());

                if (getAllMyTask.getIsDelayedTask()) {
                    txtdone.setText("تمت بتأخير");
                    imgdone.setBackgroundResource(R.drawable.donetasklite);
                } else {
                    txtdone.setText("تمت بنجاح");
                    imgdone.setBackgroundResource(R.drawable.donetaskgreen);
                }



                SimpleRatingBar simple_rating_bar = (SimpleRatingBar) rowView.findViewById(R.id.simple_rating_bar);

                simple_rating_bar.setEnabled(false);
                if(getAllMyTask.getEvaluationId()==null){
                    simple_rating_bar.setVisibility(View.GONE);

                }else if(getAllMyTask.getEvaluationId().equals("15"))
                {
                    simple_rating_bar.setRating(1);
                }else if(getAllMyTask.getEvaluationId().equals("16"))
                {
                    simple_rating_bar.setRating(2);
                }else if(getAllMyTask.getEvaluationId().equals("17"))
                {
                    simple_rating_bar.setRating(3);
                }else if(getAllMyTask.getEvaluationId().equals("18"))
                {
                    simple_rating_bar.setRating(4);
                }else if(getAllMyTask.getEvaluationId().equals("19"))
                {
                    simple_rating_bar.setRating(5);
                }else
                {
                    simple_rating_bar.setVisibility(View.GONE);
                }
                String Days = "";
//            if (getAllMyTask.getGetRemainingTime().getDays() > 0) {
//                Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#EAB011"));
//            } else {
//                Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#CC0000"));
//            }

                // tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");

            }
        }
        else if(index==1) {
            GetMyActiveTasks getAllMyTask;
            getAllMyTask= (GetMyActiveTasks) Titem.get(position);
            if (getAllMyTask.getTaskStatusId() != 7&&getAllMyTask.getTaskStatusId() != 8&&getAllMyTask.getTaskStatusId() != 10&&getAllMyTask.getTaskStatusId() != 11) {
                rowView = inflater.inflate(R.layout.mytask_cell, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.taskdate);
                TextView tasktime = (TextView) rowView.findViewById(R.id.tasktime);
                TextView taskattc = (TextView) rowView.findViewById(R.id.taskattc);
                TextView col= (TextView) rowView.findViewById(R.id.col);

                ImageView taskstatus=(ImageView)rowView.findViewById(R.id.taskstatus);

                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());
                String Days = "";
                if (getAllMyTask.getGetRemainingTime().getDays() > 0) {

                    Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
                    if(getAllMyTask.getGetRemainingTime().getDays()<=4)
                    { tasktime.setTextColor(Color.parseColor("#EAB011"));
                        taskstatus.setBackgroundResource(R.drawable.tasklite);
                        col.setBackgroundColor(Color.parseColor("#EAB011"));
                    }
                    else {
                        tasktime.setTextColor(Color.parseColor("#0066CC"));
                        taskstatus.setBackgroundResource(R.drawable.taskactivy);
                        col.setBackgroundColor(Color.parseColor("#0066CC"));

                    }

                }
                else {
                    Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                    tasktime.setTextColor(Color.parseColor("#CC0000"));
                    taskstatus.setBackgroundResource(R.drawable.taskdelay);

                   // col.setBackgroundColor(Color.parseColor("#CC0000"));

                }

                tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");
            }
            else {
                rowView = inflater.inflate(R.layout.task_cell_done, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.datetext);
                TextView taskattc = (TextView) rowView.findViewById(R.id.attatext);
                TextView txtdone = (TextView) rowView.findViewById(R.id.txtdone);
                ImageView imgdone = (ImageView) rowView.findViewById(R.id.imgdone);



                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());

                if (getAllMyTask.getIsDelayedTask()) {
                    txtdone.setText("تمت بتأخير");
                    imgdone.setBackgroundResource(R.drawable.donetasklite);
                } else {
                    txtdone.setText("تمت بنجاح");
                    imgdone.setBackgroundResource(R.drawable.donetaskgreen);
                }
                String Days = "";
//            if (getAllMyTask.getGetRemainingTime().getDays() > 0) {
//                Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#EAB011"));
//            } else {
//                Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#CC0000"));
//            }

                SimpleRatingBar simple_rating_bar = (SimpleRatingBar) rowView.findViewById(R.id.simple_rating_bar);

                simple_rating_bar.setEnabled(false);

                if(getAllMyTask.getEvaluationId().equals("15"))
                {
                    simple_rating_bar.setRating(1);
                }else if(getAllMyTask.getEvaluationId().equals("16"))
                {
                    simple_rating_bar.setRating(2);
                }else if(getAllMyTask.getEvaluationId().equals("17"))
                {
                    simple_rating_bar.setRating(3);
                }else if(getAllMyTask.getEvaluationId().equals("18"))
                {
                    simple_rating_bar.setRating(4);
                }else if(getAllMyTask.getEvaluationId().equals("19"))
                {
                    simple_rating_bar.setRating(5);
                }else
                {
                    simple_rating_bar.setVisibility(View.GONE);
                }

                // tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");
            }
        }
        else if(index==2) {
            GetMyDelayedTasks getAllMyTask;
            getAllMyTask= (GetMyDelayedTasks) Titem.get(position);
            if (getAllMyTask.getTaskStatusId() != 7&&getAllMyTask.getTaskStatusId() != 8&&getAllMyTask.getTaskStatusId() != 10&&getAllMyTask.getTaskStatusId() != 11) {
                rowView = inflater.inflate(R.layout.mytask_cell, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.taskdate);
                TextView tasktime = (TextView) rowView.findViewById(R.id.tasktime);
                TextView taskattc = (TextView) rowView.findViewById(R.id.taskattc);
                ImageView taskstatus=(ImageView)rowView.findViewById(R.id.taskstatus);
                TextView col= (TextView) rowView.findViewById(R.id.col);

                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());
                String Days = "";
                if (getAllMyTask.getGetRemainingTime().getDays() > 0) {

                    Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
                    if(getAllMyTask.getGetRemainingTime().getDays()<=4)
                    { tasktime.setTextColor(Color.parseColor("#EAB011"));
                        taskstatus.setBackgroundResource(R.drawable.tasklite);
                        col.setBackgroundColor(Color.parseColor("#EAB011"));
                    }
                    else {
                        tasktime.setTextColor(Color.parseColor("#0066CC"));
                        taskstatus.setBackgroundResource(R.drawable.taskactivy);
                        col.setBackgroundColor(Color.parseColor("#0066CC"));

                    }

                }
                else {
                    Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                    tasktime.setTextColor(Color.parseColor("#CC0000"));
                    taskstatus.setBackgroundResource(R.drawable.taskdelay);
                  //  col.setBackgroundColor(Color.parseColor("#CC0000"));

                }

                tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");

            }
            else {
                rowView = inflater.inflate(R.layout.task_cell_done, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.datetext);
                TextView taskattc = (TextView) rowView.findViewById(R.id.attatext);
                TextView txtdone = (TextView) rowView.findViewById(R.id.txtdone);
                ImageView imgdone = (ImageView) rowView.findViewById(R.id.imgdone);

                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());

                if (getAllMyTask.getIsDelayedTask()) {
                    txtdone.setText("تمت بتأخير");
                    imgdone.setBackgroundResource(R.drawable.donetasklite);
                } else {
                    txtdone.setText("تمت بنجاح");
                    imgdone.setBackgroundResource(R.drawable.donetaskgreen);
                }


                SimpleRatingBar simple_rating_bar = (SimpleRatingBar) rowView.findViewById(R.id.simple_rating_bar);

                simple_rating_bar.setEnabled(false);

                if(getAllMyTask.getEvaluationId().equals("15"))
                {
                    simple_rating_bar.setRating(1);
                }else if(getAllMyTask.getEvaluationId().equals("16"))
                {
                    simple_rating_bar.setRating(2);
                }else if(getAllMyTask.getEvaluationId().equals("17"))
                {
                    simple_rating_bar.setRating(3);
                }else if(getAllMyTask.getEvaluationId().equals("18"))
                {
                    simple_rating_bar.setRating(4);
                }else if(getAllMyTask.getEvaluationId().equals("19"))
                {
                    simple_rating_bar.setRating(5);
                }else
                {
                    simple_rating_bar.setVisibility(View.GONE);
                }
                String Days = "";
//            if (getAllMyTask.getGetRemainingTime().getDays() > 0) {
//                Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#EAB011"));
//            } else {
//                Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#CC0000"));
//            }

                // tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");

            }
        }
        else if(index==3) {
            GetMyCompletedTask getAllMyTask;
            getAllMyTask= (GetMyCompletedTask) Titem.get(position);
            if (getAllMyTask.getTaskStatusId() != 7&&getAllMyTask.getTaskStatusId() != 8&&getAllMyTask.getTaskStatusId() != 10&&getAllMyTask.getTaskStatusId() != 11) {
                rowView = inflater.inflate(R.layout.mytask_cell, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.taskdate);
                TextView tasktime = (TextView) rowView.findViewById(R.id.tasktime);
                TextView taskattc = (TextView) rowView.findViewById(R.id.taskattc);
                ImageView taskstatus=(ImageView)rowView.findViewById(R.id.taskstatus);
                TextView col= (TextView) rowView.findViewById(R.id.col);


                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());
                String Days = "";
                if (getAllMyTask.getGetRemainingTime().getDays() > 0) {

                    Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
                    if(getAllMyTask.getGetRemainingTime().getDays()<=4)
                    { tasktime.setTextColor(Color.parseColor("#EAB011"));
                        taskstatus.setBackgroundResource(R.drawable.tasklite);
                        col.setBackgroundColor(Color.parseColor("#EAB011"));
                    }
                    else {
                        tasktime.setTextColor(Color.parseColor("#0066CC"));
                        taskstatus.setBackgroundResource(R.drawable.taskactivy);
                        col.setBackgroundColor(Color.parseColor("#0066CC"));

                    }

                }
                else {
                    Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام".replaceAll("-","");
                    tasktime.setTextColor(Color.parseColor("#CC0000"));
                    taskstatus.setBackgroundResource(R.drawable.taskdelay);
                    //col.setBackgroundColor(Color.parseColor("#CC0000"));

                }

                tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");

            }
            else {
                rowView = inflater.inflate(R.layout.task_cell_done, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.datetext);
                TextView taskattc = (TextView) rowView.findViewById(R.id.attatext);
                TextView txtdone = (TextView) rowView.findViewById(R.id.txtdone);
                ImageView imgdone = (ImageView) rowView.findViewById(R.id.imgdone);

                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(getAllMyTask.getFrom());

                if (getAllMyTask.getIsDelayedTask()) {
                    txtdone.setText("تمت بتأخير");
                    imgdone.setBackgroundResource(R.drawable.donetasklite);
                } else {
                    txtdone.setText("تمت بنجاح");
                    imgdone.setBackgroundResource(R.drawable.donetaskgreen);
                }


                SimpleRatingBar simple_rating_bar = (SimpleRatingBar) rowView.findViewById(R.id.simple_rating_bar);

                simple_rating_bar.setEnabled(false);
                if(getAllMyTask.getEvaluationId()==null){
                    simple_rating_bar.setVisibility(View.GONE);

                }else if(getAllMyTask.getEvaluationId().equals("15"))
                {
                    simple_rating_bar.setRating(1);
                }else if(getAllMyTask.getEvaluationId().equals("16"))
                {
                    simple_rating_bar.setRating(2);
                }else if(getAllMyTask.getEvaluationId().equals("17"))
                {
                    simple_rating_bar.setRating(3);
                }else if(getAllMyTask.getEvaluationId().equals("18"))
                {
                    simple_rating_bar.setRating(4);
                }else if(getAllMyTask.getEvaluationId().equals("19"))
                {
                    simple_rating_bar.setRating(5);
                }else
                {
                    simple_rating_bar.setVisibility(View.GONE);
                }
                String Days = "";
//            if (getAllMyTask.getGetRemainingTime().getDays() > 0) {
//                Days = "متبقي" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#EAB011"));
//            } else {
//                Days = "متأخرة" + getAllMyTask.getGetRemainingTime().getDays() + "أيام";
//                tasktime.setTextColor(Color.parseColor("#CC0000"));
//            }

                // tasktime.setText(Days);
                int count=0;
                if(getAllMyTask.getManagerAttachmentsDTO()!=null){
                    count=getAllMyTask.getManagerAttachmentsDTO().size();
                }


                taskattc.setText(count +" مرفق");

            }
        }


        return rowView;

    }








}