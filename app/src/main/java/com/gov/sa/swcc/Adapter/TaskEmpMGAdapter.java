package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.borjabravo.simpleratingbar.SimpleRatingBar;
import com.gov.sa.swcc.Global;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.TaskEmpManager.GetAllTask;
import com.gov.sa.swcc.model.emptask.GetAllMyTask;
import com.gov.sa.swcc.model.emptask.GetMyActiveTasks;
import com.gov.sa.swcc.model.emptask.GetMyCompletedTask;
import com.gov.sa.swcc.model.emptask.GetMyDelayedTasks;

import java.util.List;

public class TaskEmpMGAdapter extends ArrayAdapter<GetAllTask> {

    private final Activity context;
    List<GetAllTask> Titem;
    int index=0;


    public TaskEmpMGAdapter(Activity context, List<GetAllTask> Titem, int index) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.index=index;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=null;

Global global=new Global(context);
            GetAllTask getAllMyTask;
            getAllMyTask= (GetAllTask) Titem.get(position);
        if (getAllMyTask.getTaskStatusId() != 7&&getAllMyTask.getTaskStatusId() != 8&&getAllMyTask.getTaskStatusId() != 10&&getAllMyTask.getTaskStatusId() != 11) {
                rowView = inflater.inflate(R.layout.task_cell, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.datetext);
                TextView taskattc = (TextView) rowView.findViewById(R.id.attatext);
                TextView remintime=(TextView)rowView.findViewById(R.id.remintime);
                LinearLayout remincard=(LinearLayout)rowView.findViewById(R.id.remincard);
                TextView personname=(TextView)rowView.findViewById(R.id.personname);


                personname.setText(getAllMyTask.getEmployeeName());
                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText(global.GetDateFormat(getAllMyTask.getTo()));
                String Days = "";
                if (getAllMyTask.getGetRemainingTime().getDays() >= 0 && getAllMyTask.getGetRemainingTime().getHours()>=0&&!getAllMyTask.getIsDelayedTask()) {

                    Days = " متبقي " + global.DTxt(getAllMyTask.getGetRemainingTime().getDays());
                    if(getAllMyTask.getGetRemainingTime().getDays()<=4)
                    {
                        remintime.setTextColor(Color.parseColor("#EAB011"));
                        remincard.setBackgroundColor(Color.parseColor("#26EAB011"));
                        //col.setBackgroundColor(Color.parseColor("#EAB011"));
                    }
                    else {
                        remintime.setTextColor(Color.parseColor("#0066CC"));
                        remincard.setBackgroundColor(Color.parseColor("#260066CC"));
                       // col.setBackgroundColor(Color.parseColor("#0066CC"));

                    }

                }
                else {
                    Days = " متأخرة " + global.DTxt(getAllMyTask.getGetRemainingTime().getDays() );
                    remintime.setTextColor(Color.parseColor("#CC0000"));
                    remincard.setBackgroundColor(Color.parseColor("#26EA5D11"));

                    //col.setBackgroundColor(Color.parseColor("#CC0000"));

                }
            int count=0;
            if(getAllMyTask.getManagerAttachmentsDTO()!=null&&getAllMyTask.getManagerAttachmentsDTO().size()>0){
                count=1;
            }
            if(getAllMyTask.getEmployeeAttachmentsDTO()!=null&&getAllMyTask.getEmployeeAttachmentsDTO().size()>0){
                count++;
            }


            if(count==0){
                taskattc.setText("لا توجد مرفقات");

            }else {
                taskattc.setText("مع مرفقات");
            }
                remintime.setText(Days);
            }
            else {
                rowView = inflater.inflate(R.layout.task_cell_done, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView taskdate = (TextView) rowView.findViewById(R.id.datetext);
                TextView taskattc = (TextView) rowView.findViewById(R.id.attatext);
                TextView txtdone = (TextView) rowView.findViewById(R.id.txtdone);
                ImageView imgdone = (ImageView) rowView.findViewById(R.id.imgdone);
            SimpleRatingBar simple_rating_bar = (SimpleRatingBar) rowView.findViewById(R.id.simple_rating_bar);


                taskname.setText(getAllMyTask.getTaskName() + "");
                taskdate.setText( global.GetDateFormat(getAllMyTask.getTo()));

                if (getAllMyTask.getIsDelayedTask()) {
                    txtdone.setText("تمت بتأخير");
                    imgdone.setBackgroundResource(R.drawable.donetasklite);
                } else {
                    txtdone.setText("تمت بنجاح");
                    imgdone.setBackgroundResource(R.drawable.donetaskgreen);
                }
                simple_rating_bar.setEnabled(false);
if(getAllMyTask.getEvaluationId()==null){
    simple_rating_bar.setVisibility(View.GONE);

}
               else if(getAllMyTask.getEvaluationId().equals("15"))
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
            if(getAllMyTask.getManagerAttachmentsDTO()!=null&&getAllMyTask.getManagerAttachmentsDTO().size()>0){
                count=1;
            }
            if(getAllMyTask.getEmployeeAttachmentsDTO()!=null&&getAllMyTask.getEmployeeAttachmentsDTO().size()>0){
                count++;
            }


            if(count==0){
                taskattc.setText("لا توجد مرفقات");

            }else {
                taskattc.setText("مع مرفقات");
            }

            }





        return rowView;

    }








}