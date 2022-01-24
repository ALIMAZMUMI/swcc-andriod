package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signproject {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("IsCompanySupervisor")
    @Expose
    private Boolean isCompanySupervisor;
    @SerializedName("FIRST_IN")
    @Expose
    private String firstIn;
    @SerializedName("LAST_OUT")
    @Expose
    private String lastOut;
    @SerializedName("DATE")
    @Expose
    private String date;
    @SerializedName("Classfcation")
    @Expose
    private String classfcation;
    @SerializedName("SupervisorName")
    @Expose
    private String supervisorName;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("LocationName")
    @Expose
    private String locationName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Boolean getIsCompanySupervisor() {
        return isCompanySupervisor;
    }

    public void setIsCompanySupervisor(Boolean isCompanySupervisor) {
        this.isCompanySupervisor = isCompanySupervisor;
    }

    public String getFirstIn() {
        return firstIn;
    }

    public void setFirstIn(String firstIn) {
        this.firstIn = firstIn;
    }

    public String getLastOut() {
        return lastOut;
    }

    public void setLastOut(String lastOut) {
        this.lastOut = lastOut;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassfcation() {
        return classfcation;
    }

    public void setClassfcation(String classfcation) {
        this.classfcation = classfcation;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}