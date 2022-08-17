package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.Global;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.Sharekproject;
import com.gov.sa.swcc.model.Signproject;

import java.util.List;

public class ProjectSignAdapter extends ArrayAdapter<Signproject> {

    private final Activity context;
    List<Signproject> Titem;


    public ProjectSignAdapter(Activity context, List<Signproject> Titem) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.projectsignitem, null,true);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView rowtext=(TextView) rowView.findViewById(R.id.rowtext);
        TextView rowempid=(TextView) rowView.findViewById(R.id.rowempid);
        TextView date=(TextView) rowView.findViewById(R.id.date);
        TextView rowtext1=(TextView) rowView.findViewById(R.id.rowtext1);

        Global global=new Global(context);
        if (global.GetValue("Lan").equals("en")) {

            name.setText(Html.fromHtml("<font color='#004C86'>employee name: </font>" + "<font color='#0066CC'>" + Titem.get(position).getEmployeeName() + "</font>"));
            rowtext.setText(Html.fromHtml("<font color='#EA5D11'>       exit:</font>" + "<font color='#0066CC'>" + Titem.get(position).getLastOut() + "</font>"));
            rowtext1.setText(Html.fromHtml("<font color='#2BBC00'>entry:</font>" + "<font color='#0066CC'>" + Titem.get(position).getFirstIn() + "</font>"));

            rowempid.setText(Titem.get(position).getEmployeeID());
            name.setGravity(Gravity.LEFT|Gravity.CENTER);
            date.setText(Titem.get(position).getDate().substring(0, 10));
            ((LinearLayout)rowView.findViewById(R.id.l1)).setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            ((LinearLayout)rowView.findViewById(R.id.l2)).setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

//            rowtext1.setGravity(Gravity.LEFT);

        }else {
            name.setText(Html.fromHtml("<font color='#004C86'>اسم العامل: </font>" + "<font color='#0066CC'>" + Titem.get(position).getEmployeeName() + "</font>"));
            rowtext.setText(Html.fromHtml("<font color='#EA5D11'>       الخروج:</font>" + "<font color='#0066CC'>" + Titem.get(position).getLastOut() + "</font>"));
            rowtext1.setText(Html.fromHtml("<font color='#2BBC00'>الدخول:</font>" + "<font color='#0066CC'>" + Titem.get(position).getFirstIn() + "</font>"));

            rowempid.setText(Titem.get(position).getEmployeeID());

            date.setText(Titem.get(position).getDate().substring(0, 10));
        }
//        rowtext.setText(Html.fromHtml(Titem.get(position).getProjectName()+
//                " مدينة "+"<font color='#e9893c'>("+Titem.get(position).getLocationNameLK()+")</font>"
//        +" تصنيف المشروع "+"<font color='#23415b'>("+Titem.get(position).getClassfcationLKName()+")</font>"
//                +" رقم المشروع "+"<font color='#c81336'>("+Titem.get(position).getProjectManagerId()+")</font>"));
//



        return rowView;

    }








}