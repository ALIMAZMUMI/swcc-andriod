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

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.Rating.Model;
import com.gov.sa.swcc.model.Rating.PendingRatingRequests;
import com.gov.sa.swcc.model.TaskEmpManager.GetAllTask;

import java.util.List;

public class PendingRatingAdapter extends ArrayAdapter<Model> {

    private final Activity context;
    List<Model> Titem;
    int index=0;


    public PendingRatingAdapter(Activity context, List<Model> Titem, int index) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.index=index;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=null;


        Model getAllMyTask;
            getAllMyTask= (Model) Titem.get(position);
                rowView = inflater.inflate(R.layout.approvaltask_cell, null, true);

                TextView taskname = (TextView) rowView.findViewById(R.id.taskname);
                TextView tasktitle = (TextView) rowView.findViewById(R.id.tasktitle);
                TextView personname = (TextView) rowView.findViewById(R.id.personname);
                ImageView noteicon=(ImageView)rowView.findViewById(R.id.noteicon);
                TextView notetxt = (TextView) rowView.findViewById(R.id.notetxt);
        CardView taskstatusc =(CardView)rowView.findViewById(R.id.taskstatusc);

                personname.setText(getAllMyTask.getEmployeeName());
                //taskname.setText(getAllMyTask.getTaskName());
                tasktitle.setText(getAllMyTask.getTaskName());
if(getAllMyTask.getTaskStatusId()!=8){
    taskname.setText("طلب تمديد مهمة");
    notetxt.setText("ملاحظة");
    taskstatusc.setCardBackgroundColor(Color.parseColor("#590066CC"));
}else{
    taskname.setText("طلب تقييم المهمة");
    notetxt.setVisibility(View.GONE);
    noteicon.setVisibility(View.GONE);
    taskstatusc.setCardBackgroundColor(Color.parseColor("#B3E931"));


}



        return rowView;

    }








}