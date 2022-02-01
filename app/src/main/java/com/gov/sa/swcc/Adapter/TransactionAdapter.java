package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<TransactionsApiResult> {

    private final Activity context;
    List<TransactionsApiResult> Titem;


    public TransactionAdapter(Activity context, List<TransactionsApiResult> Titem) {
        // TODO Auto-generated constructor stub
      super(context,R.layout.activity_transactions,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();



        TransactionsApiResult T=Titem.get(position);
        View rowView;
        if(T.getEmpCode().equals("Header")){
            rowView= inflater.inflate(R.layout.leaveheaderitem, null, true);
            TextView textheader = (TextView) rowView.findViewById(R.id.textheader);
            textheader.setText(T.getPunchState());

        }else {


         rowView=inflater.inflate(R.layout.transactionitem, null,true);

        TextView Timetxt = (TextView) rowView.findViewById(R.id.timetxt);
        TextView Datetxt = (TextView) rowView.findViewById(R.id.Datetxt);
        TextView status=(TextView)rowView.findViewById(R.id.statustxt);
         T=Titem.get(position);
        if(T.getPunchState().equals("I")) {
            Timetxt.setText(T.getPunchTime());
            Datetxt.setText(T.getPunchDate());
            status.setTextColor(Color.parseColor("#2BBC00"));
            status.setText("الدخول:");
        }else {
            Timetxt.setText(T.getPunchTime());
            Datetxt.setText(T.getPunchDate());
            status.setTextColor(Color.parseColor("#EA5D11"));
            status.setText("الخروج:");
        }
        }
        return rowView;

    };


}