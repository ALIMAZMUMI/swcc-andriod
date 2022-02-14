package com.gov.sa.swcc.model;

public class PartnerItem {


    String text;
    boolean click;

    public PartnerItem(String text, boolean click) {
        this.text = text;
        this.click = click;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}
