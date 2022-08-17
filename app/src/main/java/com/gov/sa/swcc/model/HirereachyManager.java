package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HirereachyManager {

    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ManagerUID")
    @Expose
    private Object managerUID;
    @SerializedName("TeamLeadUID")
    @Expose
    private Object teamLeadUID;
    @SerializedName("From")
    @Expose
    private String from;
    @SerializedName("To")
    @Expose
    private String to;
    @SerializedName("Date")
    @Expose
    private String date;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @SerializedName("Status")
    @Expose
    private String Status="";




    @SerializedName("isSelected")
    @Expose
    private boolean isSelected=false;



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getManagerUID() {
        return managerUID;
    }

    public void setManagerUID(Object managerUID) {
        this.managerUID = managerUID;
    }

    public Object getTeamLeadUID() {
        return teamLeadUID;
    }

    public void setTeamLeadUID(Object teamLeadUID) {
        this.teamLeadUID = teamLeadUID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}