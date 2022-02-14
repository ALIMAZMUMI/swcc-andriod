package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportModel {

    @SerializedName("RoleType")
    @Expose
    private String roleType;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("SupervisorClassfication")
    @Expose
    private String supervisorClassfication;
    @SerializedName("ProjectsManagerId")
    @Expose
    private Integer projectsManagerId;
    @SerializedName("SupervisorsId")
    @Expose
    private Integer supervisorsId;
    @SerializedName("LocationsManagerId")
    @Expose
    private Integer locationsManagerId;
    @SerializedName("SupervisorsClassfcation_LK")
    @Expose
    private Object supervisorsClassfcationLK;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSupervisorClassfication() {
        return supervisorClassfication;
    }

    public void setSupervisorClassfication(String supervisorClassfication) {
        this.supervisorClassfication = supervisorClassfication;
    }

    public Integer getProjectsManagerId() {
        return projectsManagerId;
    }

    public void setProjectsManagerId(Integer projectsManagerId) {
        this.projectsManagerId = projectsManagerId;
    }

    public Integer getSupervisorsId() {
        return supervisorsId;
    }

    public void setSupervisorsId(Integer supervisorsId) {
        this.supervisorsId = supervisorsId;
    }

    public Integer getLocationsManagerId() {
        return locationsManagerId;
    }

    public void setLocationsManagerId(Integer locationsManagerId) {
        this.locationsManagerId = locationsManagerId;
    }

    public Object getSupervisorsClassfcationLK() {
        return supervisorsClassfcationLK;
    }

    public void setSupervisorsClassfcationLK(Object supervisorsClassfcationLK) {
        this.supervisorsClassfcationLK = supervisorsClassfcationLK;
    }

}