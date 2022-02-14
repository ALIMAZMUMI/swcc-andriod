package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.Sharekproject;

import java.util.List;

public class ProjectAdapter extends ArrayAdapter<Sharekproject> {

    private final Activity context;
    List<Sharekproject> Titem;


    public ProjectAdapter(Activity context, List<Sharekproject> Titem) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.projectitem, null,true);

        TextView rownum = (TextView) rowView.findViewById(R.id.rownum);
        TextView rowtext=(TextView) rowView.findViewById(R.id.rowtext);
        TextView rowtext1=(TextView) rowView.findViewById(R.id.rowtext1);

        rownum.setText((position+1)+"");



        rowtext.setText(Html.fromHtml("<font color='#004C86'>اسم المشروع:</font>"+"<font color='#0066CC'>  "+Titem.get(position).getProjectName()+"</font>"));

        rowtext1.setText(Html.fromHtml("<font color='#004C86'>موقع المشروع:</font>"+"<font color='#000000'>  "+Titem.get(position).getLocationNameLK()+"<br/></font>"+
                "<font color='#004C86'>تصنيف المشروع:</font>"+"<font color='#000000'>  "+Titem.get(position).getClassfcationLKName()+"<br/></font>"));

        return rowView;

    }








}