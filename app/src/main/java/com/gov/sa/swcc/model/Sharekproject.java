package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sharekproject {

    @SerializedName("SupervisorId")
    @Expose
    private Integer supervisorId;
    @SerializedName("ProjectManagerId")
    @Expose
    private Integer projectManagerId;
    @SerializedName("ProjectManagerName")
    @Expose
    private String projectManagerName;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("LocationManagerUID")
    @Expose
    private String locationManagerUID;
    @SerializedName("LocationManagerName")
    @Expose
    private String locationManagerName;
    @SerializedName("SupervisorUID")
    @Expose
    private String supervisorUID;
    @SerializedName("SupervisorName")
    @Expose
    private String supervisorName;
    @SerializedName("LocationId_LK")
    @Expose
    private Integer locationIdLK;
    @SerializedName("LocationName_LK")
    @Expose
    private String locationNameLK;
    @SerializedName("Classfcation_LK")
    @Expose
    private Integer classfcationLK;
    @SerializedName("Classfcation_LK_Name")
    @Expose
    private String classfcationLKName;
    @SerializedName("CreatedBy")
    @Expose
    private Object createdBy;
    @SerializedName("ModifyBy")
    @Expose
    private Object modifyBy;
    @SerializedName("CreatedDate")
    @Expose
    private Object createdDate;
    @SerializedName("ModifyDate")
    @Expose
    private Object modifyDate;
    @SerializedName("IsDeleted")
    @Expose
    private Object isDeleted;

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Integer getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(Integer projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocationManagerUID() {
        return locationManagerUID;
    }

    public void setLocationManagerUID(String locationManagerUID) {
        this.locationManagerUID = locationManagerUID;
    }

    public String getLocationManagerName() {
        return locationManagerName;
    }

    public void setLocationManagerName(String locationManagerName) {
        this.locationManagerName = locationManagerName;
    }

    public String getSupervisorUID() {
        return supervisorUID;
    }

    public void setSupervisorUID(String supervisorUID) {
        this.supervisorUID = supervisorUID;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Integer getLocationIdLK() {
        return locationIdLK;
    }

    public void setLocationIdLK(Integer locationIdLK) {
        this.locationIdLK = locationIdLK;
    }

    public String getLocationNameLK() {
        return locationNameLK;
    }

    public void setLocationNameLK(String locationNameLK) {
        this.locationNameLK = locationNameLK;
    }

    public Integer getClassfcationLK() {
        return classfcationLK;
    }

    public void setClassfcationLK(Integer classfcationLK) {
        this.classfcationLK = classfcationLK;
    }

    public String getClassfcationLKName() {
        return classfcationLKName;
    }

    public void setClassfcationLKName(String classfcationLKName) {
        this.classfcationLKName = classfcationLKName;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Object modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Object getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

}