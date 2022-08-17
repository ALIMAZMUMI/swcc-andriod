package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.borjabravo.simpleratingbar.OnRatingChangedListener;
import com.borjabravo.simpleratingbar.SimpleRatingBar;
import com.gov.sa.swcc.EmpRatingActivity;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.RatingModel;
import com.gov.sa.swcc.model.Signproject;

import java.util.List;

public class EmpRaitingAdapter extends ArrayAdapter<RatingModel> {

    private final Activity context;
    List<RatingModel> Titem;

int w;
    public EmpRaitingAdapter(Activity context, List<RatingModel> Titem,int w) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_sharek_projects,Titem);
        this.context=context;
        this.Titem=Titem;
this.w=w;

    }

    public List<RatingModel> getTitem() {
        return Titem;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.empratingitem, null,true);

        TextView name = (TextView) rowView.findViewById(R.id.ratingcat);
        SimpleRatingBar ratingBa=(SimpleRatingBar) rowView.findViewById(R.id.simple_rating_bar);
        TextView lb6= (TextView) rowView.findViewById(R.id.lb6);
        TextView lb5= (TextView) rowView.findViewById(R.id.lb5);
        TextView lb4= (TextView) rowView.findViewById(R.id.lb4);
        TextView lb3= (TextView) rowView.findViewById(R.id.lb3);
        TextView lb2= (TextView) rowView.findViewById(R.id.lb2);
        TextView lb1= (TextView) rowView.findViewById(R.id.lb1);


        if(Titem.get(position).getMaxEvaluationPoint().equals("6")){

        } else if(Titem.get(position).getMaxEvaluationPoint().equals("5")){
            lb1.setVisibility(View.GONE);
        } else if(Titem.get(position).getMaxEvaluationPoint().equals("4")){
            lb1.setVisibility(View.GONE);
            lb4.setVisibility(View.GONE);

        } else if(Titem.get(position).getMaxEvaluationPoint().equals("3")){
            lb1.setVisibility(View.GONE);
            lb3.setVisibility(View.GONE);
            lb4.setVisibility(View.GONE);

        } else if(Titem.get(position).getMaxEvaluationPoint().equals("2")){
            lb1.setVisibility(View.GONE);
            lb3.setVisibility(View.GONE);
            lb4.setVisibility(View.GONE);
            lb5.setVisibility(View.GONE);
        }

        name.setText(Titem.get(position).getLookupName());
        if(w<=540){
            ratingBa.setRatingSize(20);
        }else{
            ratingBa.setRatingSize(70);
        }
        ratingBa.setRatingCount(Integer.parseInt(Titem.get(position).getMaxEvaluationPoint()));
        ratingBa.setOnRatingChangedListener(new OnRatingChangedListener() {
            @Override
            public void onRatingChange(float v, float v1) {
                Titem.get(position).setValue((int)ratingBa.getRating());
                int selected=(int)ratingBa.getRating();
                if(Titem.get(position).getMaxEvaluationPoint().equals("6")){



                    if(selected==1){
                        lb1.setTextColor(Color.parseColor("#0066CC"));

                        lb2.setTextColor(Color.parseColor("#000000"));
                        lb3.setTextColor(Color.parseColor("#000000"));
                        lb4.setTextColor(Color.parseColor("#000000"));
                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==2){
                        lb1.setTextColor(Color.parseColor("#0066CC"));
                        lb2.setTextColor(Color.parseColor("#0066CC"));

                        lb3.setTextColor(Color.parseColor("#000000"));
                        lb4.setTextColor(Color.parseColor("#000000"));
                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==3){
                        lb1.setTextColor(Color.parseColor("#0066CC"));
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));

                        lb4.setTextColor(Color.parseColor("#000000"));
                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==4){
                        lb1.setTextColor(Color.parseColor("#0066CC"));
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb4.setTextColor(Color.parseColor("#0066CC"));

                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));


                    }else if(selected==5){
                        lb1.setTextColor(Color.parseColor("#0066CC"));
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb4.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));

                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==6){
                        lb1.setTextColor(Color.parseColor("#0066CC"));
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb4.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#0066CC"));
                    }


                } else if(Titem.get(position).getMaxEvaluationPoint().equals("5")){
                    lb1.setVisibility(View.GONE);
                    if(selected==1){
                        lb2.setTextColor(Color.parseColor("#0066CC"));

                        lb3.setTextColor(Color.parseColor("#000000"));
                        lb4.setTextColor(Color.parseColor("#000000"));
                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==2){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));

                        lb4.setTextColor(Color.parseColor("#000000"));
                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==3){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb4.setTextColor(Color.parseColor("#0066CC"));

                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==4){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb4.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==5){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb4.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#0066CC"));
                    }
                } else if(Titem.get(position).getMaxEvaluationPoint().equals("4")){
                    lb1.setVisibility(View.GONE);
                    lb4.setVisibility(View.GONE);

                    if(selected==1){
                        lb2.setTextColor(Color.parseColor("#0066CC"));

                        lb3.setTextColor(Color.parseColor("#000000"));
                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));


                    }else if(selected==2){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));

                        lb5.setTextColor(Color.parseColor("#000000"));

                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==3){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==4){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb3.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#0066CC"));
                    }

                } else if(Titem.get(position).getMaxEvaluationPoint().equals("3")){
                    lb1.setVisibility(View.GONE);
                    lb3.setVisibility(View.GONE);
                    lb4.setVisibility(View.GONE);

                    if(selected==1){
                        lb2.setTextColor(Color.parseColor("#0066CC"));

                        lb5.setTextColor(Color.parseColor("#000000"));
                        lb6.setTextColor(Color.parseColor("#000000"));
                    }else if(selected==2){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        //
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==3){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb5.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#0066CC"));
                    }

                } else if(Titem.get(position).getMaxEvaluationPoint().equals("2")){
                    lb1.setVisibility(View.GONE);
                    lb3.setVisibility(View.GONE);
                    lb4.setVisibility(View.GONE);
                    lb5.setVisibility(View.GONE);

                    if(selected==1){
                        lb2.setTextColor(Color.parseColor("#0066CC"));

                        //----
                        lb6.setTextColor(Color.parseColor("#000000"));

                    }else if(selected==2){
                        lb2.setTextColor(Color.parseColor("#0066CC"));
                        lb6.setTextColor(Color.parseColor("#0066CC"));
                    }
                }

                ((EmpRatingActivity)context).change();

            }
        });





        return rowView;

    }








}