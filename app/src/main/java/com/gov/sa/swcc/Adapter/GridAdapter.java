package com.gov.sa.swcc.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<GridItem> {

    ArrayList<GridItem> birdList = new ArrayList<GridItem>();

    int Wh,Hi,x;
    public GridAdapter(Context context, int textViewResourceId, ArrayList<GridItem> objects,int W,int H,int x) {
        super(context, textViewResourceId, objects);
        birdList = objects;
        Wh=W;
        Hi=H;
        this.x=x;
    }
    public GridAdapter(Context context, int textViewResourceId, ArrayList<GridItem> objects,int W,int H) {
        super(context, textViewResourceId, objects);
        birdList = objects;
        Wh=W;
        Hi=H;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(birdList.get(position).getServiceName().equals("AddItem")){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.gridadd, null);
            TextView textView = (TextView) v.findViewById(R.id.text);
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);


            if (Wh == 540) {
                imageView.getLayoutParams().height = 108;
                textView.setVisibility(View.GONE);
            } else if (Wh > 540) {
                float per = (float) (108.0 / 540.0);

                imageView.getLayoutParams().height = (int) (Wh * per);

                //imageView.getLayoutParams().height = 110;
                textView.setVisibility(View.GONE);
            }
        }
        else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.griditem, null);
            TextView textView = (TextView) v.findViewById(R.id.text);
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

            textView.setText(birdList.get(position).getServiceName());
            imageView.setImageResource(birdList.get(position).getImage());
            if (Wh == 540) {
                if (x == 1) {
                    textView.setTextSize(8);
                } else {
                    textView.setTextSize(9);
                }
                textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                imageView.getLayoutParams().height = 45;
                imageView.getLayoutParams().height = 50;
                textView.getLayoutParams().height = 60;
            } else if (Wh > 540) {
                float per = (float) (45.0 / 540.0);

                textView.setTextSize(10);
                textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                imageView.getLayoutParams().height = (int) (Wh * per);
                textView.getLayoutParams().height = (int) (Wh * (65.0 / 540.0));
            }
            if (birdList.get(position).getServiceName().contains("a")) {
                textView.setTextSize(9);
            }
        }
        return v;

    }



}