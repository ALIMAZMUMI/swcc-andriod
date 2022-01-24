package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        TextView rownum = (TextView) rowView.findViewById(R.id.rownum);
        TextView rowtext=(TextView) rowView.findViewById(R.id.rowtext);
        TextView rowempid=(TextView) rowView.findViewById(R.id.rowempid);

        rownum.setText((position+1)+"");
rowtext.setSingleLine(false);
rowtext.setText("العامل : "+Titem.get(position).getEmployeeName()+"\n"+
        "وقت الدخول : "+Titem.get(position).getFirstIn()+"\n"+
        "وقت الخروج : "+Titem.get(position).getLastOut()+"");
            rowempid.setText(Titem.get(position).getEmployeeID());
//        rowtext.setText(Html.fromHtml(Titem.get(position).getProjectName()+
//                " مدينة "+"<font color='#e9893c'>("+Titem.get(position).getLocationNameLK()+")</font>"
//        +" تصنيف المشروع "+"<font color='#23415b'>("+Titem.get(position).getClassfcationLKName()+")</font>"
//                +" رقم المشروع "+"<font color='#c81336'>("+Titem.get(position).getProjectManagerId()+")</font>"));
//



        return rowView;

    }








}