package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.HirereachyManager;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.util.List;

public class EmployeesTransactionAdapter extends ArrayAdapter<HirereachyManager> {

    private final Activity context;
    List<HirereachyManager> Titem;


    public EmployeesTransactionAdapter(Activity context, List<HirereachyManager> Titem) {
        // TODO Auto-generated constructor stub
      super(context,R.layout.activity_transactions,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();



        HirereachyManager T=Titem.get(position);
        View rowView;


         rowView=inflater.inflate(R.layout.employeestranitem, null,true);




        TextView empname = (TextView) rowView.findViewById(R.id.empname);
        TextView uid = (TextView) rowView.findViewById(R.id.uid);
        TextView in = (TextView) rowView.findViewById(R.id.intime);
        TextView out = (TextView) rowView.findViewById(R.id.outtime);
        TextView date=(TextView)rowView.findViewById(R.id.date);
        TextView intxt=(TextView)rowView.findViewById(R.id.intxt);
        TextView outtxt=(TextView)rowView.findViewById(R.id.outtxt);


        if(T.getStatus().equals("1")){
            intxt.setText("     غير مرتبط بالنظام");
            intxt.setTextColor(Color.parseColor("#EA5D11"));
intxt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
intxt.setGravity(Gravity.CENTER | Gravity.RIGHT);
            out.setVisibility(View.GONE);
            in.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
            outtxt.setVisibility(View.GONE);
        }
        if(T.getStatus().equals("2")){
            intxt.setText("      لم يتم تسجيل حضور");
            intxt.setTextColor(Color.parseColor("#CC0000"));
            intxt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            intxt.setGravity(Gravity.CENTER | Gravity.RIGHT);
            out.setVisibility(View.GONE);
            in.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
            outtxt.setVisibility(View.GONE);

        }


        empname.setText(T.getName());
        uid.setText(T.getUid());
        in.setText(T.getFrom());
        out.setText(T.getTo());
        date.setText(T.getDate());


        return rowView;

    };


}