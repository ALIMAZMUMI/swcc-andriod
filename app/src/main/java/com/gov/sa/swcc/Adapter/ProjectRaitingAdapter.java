package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.Signproject;

import java.util.List;

public class ProjectRaitingAdapter extends ArrayAdapter<Signproject> {

    private final Activity context;
    List<Signproject> Titem;


    public ProjectRaitingAdapter(Activity context, List<Signproject> Titem) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.shareakitem, null,true);

        TextView name = (TextView) rowView.findViewById(R.id.EmpName);

        name.setText(Html.fromHtml("<font color='#004C86'>اسم العامل: </font>"+"<font color='#0066CC'>"+Titem.get(position).getEmployeeName()+"</font>"));






        return rowView;

    }








}