package com.gov.sa.swcc.Adapter;

import android.app.Activity;
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

public class LeaveAdapter extends ArrayAdapter<LeaveItems> {

    private final Activity context;
    List<LeaveItems> Titem;


    public LeaveAdapter(Activity context, List<LeaveItems> Titem) {
        // TODO Auto-generated constructor stub
      super(context,R.layout.activity_leave,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.leaveitem, null,true);

        TextView LeaveTypetxt = (TextView) rowView.findViewById(R.id.LeaveTypetxt);
        TextView LeaveDatetxt = (TextView) rowView.findViewById(R.id.LeaveDatetxt);
        TextView LeaveCounttxt=(TextView)rowView.findViewById(R.id.LeaveCounttxt);
        LeaveItems T=Titem.get(position);

        String Type="";
        if(T.getVacationCode().equals("ANNUAL_LEAVE"))
        {
            Type="اجازة اعتيادية";

        }else if(T.getVacationCode().equals("BUSINESS_TRIP"))
        {
            Type="مهمة عمل";
        }

String NoOFDays="";
        int NOD=Integer.parseInt(T.getNoDays());
        if(NOD==1)
        {
            NoOFDays="يوم معتمد";

        }else if(NOD==2)
        {
            NoOFDays="يومان معتمدة";
        }else if(NOD>2 && NOD<10)
        {
            NoOFDays=NOD+" أيام معتمدة";
        }else{
            NoOFDays=NOD+" يوم معتمد";


        }

        LeaveTypetxt.setText(Type);
        LeaveDatetxt.setText("من "+T.getBegda()+" إلى "+T.getEndda());
LeaveCounttxt.setText(NoOFDays);
        return rowView;

    };


}