package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.borjabravo.simpleratingbar.OnRatingChangedListener;
import com.borjabravo.simpleratingbar.SimpleRatingBar;
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
            }
        });





        return rowView;

    }








}