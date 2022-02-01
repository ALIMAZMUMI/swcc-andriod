package com.gov.sa.swcc.model;

import android.graphics.drawable.BitmapDrawable;

public class InsuranceItem {
    String Text;
    String ID="";
    BitmapDrawable image;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public BitmapDrawable getImage() {
        return image;
    }

    public void setImage(BitmapDrawable image) {
        this.image = image;
    }
}
