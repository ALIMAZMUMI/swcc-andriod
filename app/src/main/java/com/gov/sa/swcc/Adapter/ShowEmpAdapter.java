package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.HirereachyManager;

import java.util.List;

public class ShowEmpAdapter extends ArrayAdapter<HirereachyManager> {

    private final Activity context;
    List<HirereachyManager> Titem;


    public ShowEmpAdapter(Activity context, List<HirereachyManager> Titem) {
        // TODO Auto-generated constructor stub
      super(context,R.layout.activity_transactions,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();



        HirereachyManager T=Titem.get(position);
        View rowView;


         rowView=inflater.inflate(R.layout.emp_add_item, null,true);




        TextView empname = (TextView) rowView.findViewById(R.id.empname);
        CheckBox cheked = (CheckBox) rowView.findViewById(R.id.radioButton);




        empname.setText(T.getName());
        cheked.setChecked(T.isSelected());


        return rowView;

    };


}