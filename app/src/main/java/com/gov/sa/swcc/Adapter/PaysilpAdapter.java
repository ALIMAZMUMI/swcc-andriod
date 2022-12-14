package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.PayslipItem;

import java.util.List;

public class PaysilpAdapter extends ArrayAdapter<PayslipItem> {

    private final Activity context;
    List<PayslipItem> Titem;


    public PaysilpAdapter(Activity context, List<PayslipItem> Titem) {
        // TODO Auto-generated constructor stub
      super(context,R.layout.activity_leave,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if(Titem.get(position).getElementCode().equals("Header")){
            rowView= inflater.inflate(R.layout.leaveheaderitem, null, true);
            TextView textheader = (TextView) rowView.findViewById(R.id.textheader);
            textheader.setText(Titem.get(position).getElementText());
        }else if(Titem.get(position).getElementCode().equals("Header1")) {
            rowView= inflater.inflate(R.layout.headeritem, null, true);
            TextView textheader = (TextView) rowView.findViewById(R.id.textheader);
            textheader.setText(Titem.get(position).getElementText());
        }
       else {
             rowView = inflater.inflate(R.layout.payitem, null, true);

        TextView valuetxt = (TextView) rowView.findViewById(R.id.valuetxt);
        TextView valueTypetxt = (TextView) rowView.findViewById(R.id.valueTypetxt);
        LinearLayout header = (LinearLayout) rowView.findViewById(R.id.mainbg);

        PayslipItem T = Titem.get(position);


        //b3e931
        //cbccce
        //
        String Type = "";

        Log.d("ElementTextAdapter", T.getElementText());
        if (T.getElementText().equals("Housing Allowance")) {
            Type = "?????? ??????????";

        } else if (T.getElementText().equals("Nadb Allowance")) {
            Type = "?????? ??????????";

        } else if (T.getElementText().equals("GOSI Ann EE")) {
            Type = "?????????????????? ????????????????????";

        } else if (T.getElementText().equals("GOSI UNE EE")) {
            Type = "???????? ???? ?????????? *????????";

        } else if (T.getElementText().equals("Basic Salary")) {
            Type = "???????????? ??????????????";

        } else if (T.getElementText().equals("Transportation Allowance")) {
            Type = "?????? ??????????";

        } else if (T.getElementText().equals("Realestate Loan")) {
            Type = "?????? ??????????";

        } else if (T.getElementText().equals("Overtime Transportation")) {
            Type = "?????? ?????????? ????????";

        } else if (T.getElementText().equals("Overtime Eid Al Adha")) {
            Type = "?????? ?????????? ?????? ????????????";

        } else if (T.getElementText().equals("Overtime Normal")) {
            Type = "?????? ?????????? ????????";

        } else if (T.getElementText().equals("Overtime Eid Alfutr")) {
            Type = "?????? ?????????? ?????? ??????????";

        } else if (T.getElementText().equals("Local Per Diem")) {
            Type = "?????? ????????";

        } else if (T.getElementText().equals("Local Transport Allowance")) {
            Type = "?????? ???????? ????????";

        } else if (T.getElementText().equals("Housing Rent Deduction")) {
            Type = "?????????? ??????????";

        } else if (T.getElementText().equals("Penalty Adj")) {
            Type = "?????? ??????????";

        } else if (T.getElementText().equals("EARLY DEPARTURE 2 60")) {
            Type = "?????????? ????????";

        } else if (T.getElementText().equals("Freezing Allowance")) {
            Type = "?????????? ????????";

        } else if (T.getElementText().equals("Transfer Salary")) {
            Type = "???????? ????????";

        } else if (T.getElementText().equals("Overtime Weekend")) {
            Type = "?????? ?????????? ?????????? ??????????????";

        } else if (T.getElementText().equals("Overtime Normal Adj")) {
            Type = "?????? ?????????? ??????????";

        } else if (T.getElementText().equals("OM Clothing Allowance")) {
            Type = "?????? ??????????????";

        } else if (T.getElementText().equals("Clothing Allowance")) {
            Type = "?????? ??????????????";

        } else if (T.getElementText().equals("Security Allowance")) {
            Type = "?????? ??????????";

        } else if (T.getElementText().equals("Pension Contr Amt EE")) {
            Type = "?????????????? ??????????????";

        } else if (T.getElementText().equals("Payment")) {
            Type = "??????????????";

        } else {
            Type = T.getElementText();
        }
        Log.d("Type", T.getElementText() + "-----" + Type);
        valueTypetxt.setText(Type);

        if (T.getClassification().equals("Deduction") || T.getAmount().contains("-")) {
            header.setBackgroundResource(R.drawable.salred);
            valueTypetxt.setText("?????? " + valueTypetxt.getText());
            if (T.getAmount().contains("-")) {

                String text = "<font color=#000000>" + T.getAmount() + "</font> <font color=#CACCCE>??.??</font>";
                valuetxt.setText(Html.fromHtml(text));
            } else {
                String text = "<font color=#000000>-" + T.getAmount() + "</font> <font color=#CACCCE>??.??</font>";
                valuetxt.setText(Html.fromHtml(text));
            }
            //valuetxt.setBackgroundResource(R.drawable.salgrayround);

        } else if (T.getClassification().equals("Paid") && !T.getElementText().equals("Payment")) {
            header.setBackgroundResource(R.drawable.salgreen);
            String text = "<font color=#000000>" + T.getAmount() + "</font> <font color=#CACCCE>??.??</font>";
            valuetxt.setText(Html.fromHtml(text));

        } else {


            header.setBackgroundResource(R.drawable.salblue);
            String text = "<font color=#0066CC>" + T.getAmount() + "</font> <font color=#CACCCE>??.??</font>";
            valuetxt.setText(Html.fromHtml(text));
            valuetxt.setTextColor(Color.parseColor("#0066CC"));
            valueTypetxt.setTextColor(Color.parseColor("#0066CC"));


        }
    }
        return rowView;

    };


}