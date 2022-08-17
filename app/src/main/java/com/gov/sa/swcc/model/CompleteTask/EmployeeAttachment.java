package com.gov.sa.swcc.model.CompleteTask;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeAttachment {

    @SerializedName("imageBase")
    @Expose
    private String imageBase;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fileExtension")
    @Expose
    private String fileExtension;

    public String getImageBase() {
        return imageBase;
    }

    public void setImageBase(String imageBase) {
        this.imageBase = imageBase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

}