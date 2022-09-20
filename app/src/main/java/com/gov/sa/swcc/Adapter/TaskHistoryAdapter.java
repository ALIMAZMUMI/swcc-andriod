package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.Global;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.TaskHistory.Model;
import com.gov.sa.swcc.model.TaskHistory.TaskHistory;
import com.gov.sa.swcc.model.emptask.GetAllMyTask;
import com.gov.sa.swcc.model.emptask.GetMyActiveTasks;
import com.gov.sa.swcc.model.emptask.GetMyCompletedTask;
import com.gov.sa.swcc.model.emptask.GetMyDelayedTasks;

import java.util.List;

public class TaskHistoryAdapter extends ArrayAdapter<Model> {

    private final Activity context;
    List<Model> Titem;


    public TaskHistoryAdapter(Activity context, List<Model> Titem) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=null;
        rowView = inflater.inflate(R.layout.move_item, null, true);

        TextView titletxt=(TextView) rowView.findViewById(R.id.titletxt);
        TextView datetext=(TextView) rowView.findViewById(R.id.datetxt);
        TextView timetxt=(TextView) rowView.findViewById(R.id.timetxt);
        Global global=new Global(context);
        datetext.setText(global.GetDateFormat(Titem.get(position).getCreatedOn()));
        titletxt.setText(Titem.get(position).getToStatus());
        timetxt.setText(global.GetTime(Titem.get(position).getCreatedOn()));
        return rowView;

    }








}