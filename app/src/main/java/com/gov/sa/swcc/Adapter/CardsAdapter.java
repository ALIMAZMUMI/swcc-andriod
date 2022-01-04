package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.gov.sa.swcc.EmployeeIdentificationActivity;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.InsuranceInfo;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.ListInsuranceDatum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CardsAdapter extends ArrayAdapter<BitmapDrawable> {

    private final Activity context;
    List<BitmapDrawable> Titem;


    public CardsAdapter(Activity context, List<BitmapDrawable> Titem) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.activity_leave,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.insuranceitem, null,true);

        ImageView inscard = (ImageView) rowView.findViewById(R.id.inscard);
        TextView cardpadding=(TextView) rowView.findViewById(R.id.cardpadding);
        if(position!=(Titem.size()-1)){
            cardpadding.setVisibility(View.GONE);
        }
        inscard.setImageDrawable(Titem.get(position));

        return rowView;

    }








}