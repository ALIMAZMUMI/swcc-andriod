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
            Type = "بدل السكن";

        } else if (T.getElementText().equals("Nadb Allowance")) {
            Type = "بدل الندب";

        } else if (T.getElementText().equals("GOSI Ann EE")) {
            Type = "التامينات الاجتماعية";

        } else if (T.getElementText().equals("GOSI UNE EE")) {
            Type = "تعطل عن العمل *ساند";

        } else if (T.getElementText().equals("Basic Salary")) {
            Type = "الراتب الاساسي";

        } else if (T.getElementText().equals("Transportation Allowance")) {
            Type = "بدل النقل";

        } else if (T.getElementText().equals("Realestate Loan")) {
            Type = "قرض عقاري";

        } else if (T.getElementText().equals("Overtime Transportation")) {
            Type = "عمل اضافي تنقل";

        } else if (T.getElementText().equals("Overtime Eid Al Adha")) {
            Type = "عمل اضافي عيد الاضحى";

        } else if (T.getElementText().equals("Overtime Normal")) {
            Type = "عمل اضافي عادي";

        } else if (T.getElementText().equals("Overtime Eid Alfutr")) {
            Type = "عمل اضافي عيد الفطر";

        } else if (T.getElementText().equals("Local Per Diem")) {
            Type = "يوم محلي";

        } else if (T.getElementText().equals("Local Transport Allowance")) {
            Type = "يوم محلي تنقل";

        } else if (T.getElementText().equals("Housing Rent Deduction")) {
            Type = "إيجار السكن";

        } else if (T.getElementText().equals("Penalty Adj")) {
            Type = "ضبط جزائي";

        } else if (T.getElementText().equals("EARLY DEPARTURE 2 60")) {
            Type = "ترحيل مبكر";

        } else if (T.getElementText().equals("Freezing Allowance")) {
            Type = "علاوة تجمد";

        } else if (T.getElementText().equals("Transfer Salary")) {
            Type = "راتب تنقل";

        } else if (T.getElementText().equals("Overtime Weekend")) {
            Type = "عمل اضافي نهاية الاسبوع";

        } else if (T.getElementText().equals("Overtime Normal Adj")) {
            Type = "عمل اضافي تعديل";

        } else if (T.getElementText().equals("OM Clothing Allowance")) {
            Type = "بدل الملابس";

        } else if (T.getElementText().equals("Clothing Allowance")) {
            Type = "بدل الملابس";

        } else if (T.getElementText().equals("Security Allowance")) {
            Type = "بدل الأمن";

        } else if (T.getElementText().equals("Pension Contr Amt EE")) {
            Type = "تامينات التقاعد";

        } else if (T.getElementText().equals("Payment")) {
            Type = "المجموع";

        } else {
            Type = T.getElementText();
        }
        Log.d("Type", T.getElementText() + "-----" + Type);
        valueTypetxt.setText(Type);

        if (T.getClassification().equals("Deduction") || T.getAmount().contains("-")) {
            header.setBackgroundResource(R.drawable.salred);
            valueTypetxt.setText("حسم " + valueTypetxt.getText());
            if (T.getAmount().contains("-")) {

                String text = "<font color=#000000>" + T.getAmount() + "</font> <font color=#CACCCE>ر.س</font>";
                valuetxt.setText(Html.fromHtml(text));
            } else {
                String text = "<font color=#000000>-" + T.getAmount() + "</font> <font color=#CACCCE>ر.س</font>";
                valuetxt.setText(Html.fromHtml(text));
            }
            //valuetxt.setBackgroundResource(R.drawable.salgrayround);

        } else if (T.getClassification().equals("Paid") && !T.getElementText().equals("Payment")) {
            header.setBackgroundResource(R.drawable.salgreen);
            String text = "<font color=#000000>" + T.getAmount() + "</font> <font color=#CACCCE>ر.س</font>";
            valuetxt.setText(Html.fromHtml(text));

        } else {


            header.setBackgroundResource(R.drawable.salblue);
            String text = "<font color=#0066CC>" + T.getAmount() + "</font> <font color=#CACCCE>ر.س</font>";
            valuetxt.setText(Html.fromHtml(text));
            valuetxt.setTextColor(Color.parseColor("#0066CC"));
            valueTypetxt.setTextColor(Color.parseColor("#0066CC"));


        }
    }
        return rowView;

    };


}