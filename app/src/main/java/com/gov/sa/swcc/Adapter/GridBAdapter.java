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

public class GridBAdapter extends ArrayAdapter<GridItem> {

    ArrayList<GridItem> birdList = new ArrayList<GridItem>();
int Wh,Hi;
    public GridBAdapter(Context context, int textViewResourceId, ArrayList<GridItem> objects,int W,int H) {
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
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.gridbitem, null);
        TextView textView = (TextView) v.findViewById(R.id.text);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        textView.setText(birdList.get(position).getServiceName());
        imageView.setImageResource(birdList.get(position).getImage());
        if(Wh==540){

                textView.setTextSize(9);

            textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
            imageView.getLayoutParams().height = 45;
            imageView.getLayoutParams().height = 50;
            textView.getLayoutParams().height = 110;
        }else if(Wh>540){
            float per= (float) (45.0/540.0);

            textView.setTextSize(12);
            textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
            imageView.getLayoutParams().height =(int) (Wh*per);
            textView.getLayoutParams().height = (int) (Wh*(110/540.0));
        }
        if(birdList.get(position).getServiceName().contains("a")){
            textView.setTextSize(9);
        }

        return v;
    }

}