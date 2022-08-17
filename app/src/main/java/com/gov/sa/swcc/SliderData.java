package com.gov.sa.swcc;

public class SliderData {

    // image url is used to
    // store the url of image
    private int imgUrl;
    private String Link;

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getImgU() {
        return imgU;
    }

    public void setImgU(String imgU) {
        this.imgU = imgU;
    }

    private String imgU;

    // Constructor method.
    public SliderData(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public SliderData(String imgU,String Link) {
        this.imgU = imgU;
        this.Link=Link;
    }

    // Getter method
    public int getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
