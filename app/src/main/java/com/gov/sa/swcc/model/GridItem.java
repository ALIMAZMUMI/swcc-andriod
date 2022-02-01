package com.gov.sa.swcc.model;

public class GridItem {


    String ServiceName;
    int Image;
    String Type;
boolean checked;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public GridItem(String serviceName, int image) {
        ServiceName = serviceName;
        Image = image;
    }


    public GridItem(String serviceName, int image,String Type,boolean checked) {
        ServiceName = serviceName;
        Image = image;
        this.Type=Type;
        this.checked=checked;
    }


    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
