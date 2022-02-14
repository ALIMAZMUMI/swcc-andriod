package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.Global;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.PartnerItem;
import com.gov.sa.swcc.model.PartnerModel;
import com.gov.sa.swcc.model.Sharekproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PartnerAdapter extends ArrayAdapter<PartnerModel> {

    private final Activity context;
    List<PartnerModel> Titem;
String Classfcation_LK,ProjectMangerId;


    public PartnerAdapter(Activity context, List<PartnerModel> Titem,String Classfcation_LK,String ProjectMangerId) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.Titem=Titem;
        this.Classfcation_LK=Classfcation_LK;
this.ProjectMangerId=ProjectMangerId;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.partneritem, null,true);

        TextView addnote = (TextView) rowView.findViewById(R.id.addnote);
        LinearLayout complete = (LinearLayout) rowView.findViewById(R.id.complete);
        ImageView checked = (ImageView) rowView.findViewById(R.id.checked);
        TextView rowtext1=(TextView) rowView.findViewById(R.id.rowtext1);
        LinearLayout row = (LinearLayout) rowView.findViewById(R.id.row);
        LinearLayout cmted = (LinearLayout) rowView.findViewById(R.id.cmted);
        TextView line=(TextView) rowView.findViewById(R.id.line);
        Global global=new Global(context);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String SelDate=sdf.format(c.getTime());
        if(Titem.get(position).getRoundNumber()==2) {
        if(!global.GetValue(SelDate+"1"+Classfcation_LK+ProjectMangerId).equals("true")){

            addnote.setVisibility(View.GONE);
            checked.setVisibility(View.GONE);
            line.setVisibility(View.VISIBLE);
            complete.setVisibility(View.VISIBLE);
            row.setBackgroundResource(R.drawable.ligthblueborder);
            row.setEnabled(false);
            row.setAlpha(0.5F);

        }else{
            if(Titem.get(position).getRoundResult()!=null){

                if(Titem.get(position).getCmt()!=null){
                    if(Titem.get(position).getCmt().length()>1){
                        addnote.setVisibility(View.GONE);
                        checked.setVisibility(View.GONE);
                        line.setVisibility(View.GONE);
                        row.setBackgroundResource(R.drawable.ligthgreenfullb);
                        complete.setVisibility(View.GONE);
                        cmted.setVisibility(View.VISIBLE);
                    }
                }else if((boolean)Titem.get(position).getRoundResult()==true){
                    addnote.setVisibility(View.VISIBLE);
                    checked.setVisibility(View.VISIBLE);
                    line.setVisibility(View.GONE);

                    row.setBackgroundResource(R.drawable.ligthgreenfullb);
                    complete.setVisibility(View.GONE);
                    cmted.setVisibility(View.GONE);
                }
            }else{
                addnote.setVisibility(View.GONE);
                checked.setVisibility(View.GONE);
                line.setVisibility(View.VISIBLE);

                complete.setVisibility(View.VISIBLE);
                row.setBackgroundResource(R.drawable.ligthblueborder);
            }


            complete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Titem.get(position).setRoundResult(true);
                    addnote.setVisibility(View.VISIBLE);
                    checked.setVisibility(View.VISIBLE);
                    line.setVisibility(View.GONE);
                    row.setBackgroundResource(R.drawable.ligthgreenfullb);
                    complete.setVisibility(View.GONE);
                }
            });
        }

        }else{

        if(Titem.get(position).getRoundResult()!=null){

            if(Titem.get(position).getCmt()!=null){
                if(Titem.get(position).getCmt().length()>1){
                addnote.setVisibility(View.GONE);
                checked.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);
                row.setBackgroundResource(R.drawable.ligthgreenfullb);
                complete.setVisibility(View.GONE);
                cmted.setVisibility(View.VISIBLE);
                }
            }else if((boolean)Titem.get(position).getRoundResult()==true){
            addnote.setVisibility(View.VISIBLE);
            checked.setVisibility(View.VISIBLE);
                line.setVisibility(View.GONE);

            row.setBackgroundResource(R.drawable.ligthgreenfullb);
            complete.setVisibility(View.GONE);
            cmted.setVisibility(View.GONE);
            }
        }else{
            addnote.setVisibility(View.GONE);
            checked.setVisibility(View.GONE);
            line.setVisibility(View.VISIBLE);

            complete.setVisibility(View.VISIBLE);
            row.setBackgroundResource(R.drawable.ligthblueborder);
        }


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Titem.get(position).setRoundResult(true);
                addnote.setVisibility(View.VISIBLE);
                checked.setVisibility(View.VISIBLE);
                line.setVisibility(View.GONE);
                row.setBackgroundResource(R.drawable.ligthgreenfullb);
                complete.setVisibility(View.GONE);
            }
        });
        }






        rowtext1.setText(Titem.get(position).getContractEvaluationElementName());


        return rowView;

    }








}