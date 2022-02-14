package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingModel {

    @SerializedName("EmployeeId")
    @Expose
    private String employeeId;
    @SerializedName("LookupId")
    @Expose
    private String lookupId;
    @SerializedName("LookupName")
    @Expose
    private String lookupName;
    @SerializedName("Value")
    @Expose
    private int value;
    @SerializedName("MaxEvaluationPoint")
    @Expose
    private String maxEvaluationPoint;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy="";
    @SerializedName("ModifyBy")
    @Expose
    private String modifyBy="";

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLookupId() {
        return lookupId;
    }

    public void setLookupId(String lookupId) {
        this.lookupId = lookupId;
    }

    public String getLookupName() {
        return lookupName;
    }

    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMaxEvaluationPoint() {
        return maxEvaluationPoint;
    }

    public void setMaxEvaluationPoint(String maxEvaluationPoint) {
        this.maxEvaluationPoint = maxEvaluationPoint;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

}