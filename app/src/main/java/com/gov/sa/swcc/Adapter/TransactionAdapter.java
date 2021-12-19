package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.R;
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
        View rowView=inflater.inflate(R.layout.transactionitem, null,true);

        TextView Timetxt = (TextView) rowView.findViewById(R.id.Timetxt);
        TextView Datetxt = (TextView) rowView.findViewById(R.id.Datetxt);
        LinearLayout status=(LinearLayout)rowView.findViewById(R.id.status);
        TransactionsApiResult T=Titem.get(position);
        if(T.getPunchState().equals("I")) {
            Timetxt.setText("وقت الدخول : "+T.getPunchTime());
            Datetxt.setText("تاريخ العملية : "+T.getPunchDate());
status.setBackgroundResource(R.drawable.in);

        }else {
            Timetxt.setText("وقت الخروج : "+T.getPunchTime());
            Datetxt.setText("تاريخ العملية : "+T.getPunchDate());
            status.setBackgroundResource(R.drawable.out);
        }
        return rowView;

    };


}