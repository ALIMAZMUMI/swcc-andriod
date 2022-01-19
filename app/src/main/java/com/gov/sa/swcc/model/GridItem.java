package com.gov.sa.swcc.model;

public class GridItem {


    String ServiceName;
    int Image;

    public GridItem(String serviceName, int image) {
        ServiceName = serviceName;
        Image = image;
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
