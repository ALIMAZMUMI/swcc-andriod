package com.gov.sa.swcc.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gov.sa.swcc.R;
import com.gov.sa.swcc.SliderData;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    // list for storing urls of images.
    private final List<SliderData> mSliderItems;
Context context;
int w,h;
    // Constructor
    public SliderAdapter(Context context, ArrayList<SliderData> sliderDataArrayList,int w,int h) {
        this.mSliderItems = sliderDataArrayList;
        this.context=context;
        this.w=w;
        this.h=h;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final SliderData sliderItem = mSliderItems.get(position);


        Drawable dr = context.getResources().getDrawable(sliderItem.getImgUrl());
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
// Scale it to 50 x 50
int hi=(int)(w/2.7);
        Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, w, hi, true));
// Set your new, scaled drawable "d"
        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
.load(d)
            //    .load(sliderItem.getImgUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}
