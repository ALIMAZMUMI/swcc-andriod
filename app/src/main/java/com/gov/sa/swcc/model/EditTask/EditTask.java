package com.gov.sa.swcc.model.EditTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditTask {

    @SerializedName("taskId")
    @Expose
    private String taskId;
    @SerializedName("taskName")
    @Expose
    private String taskName;
    @SerializedName("taskDecription")
    @Expose
    private String taskDecription;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("taskHours")
    @Expose
    private Integer taskHours;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDecription() {
        return taskDecription;
    }

    public void setTaskDecription(String taskDecription) {
        this.taskDecription = taskDecription;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(Integer taskHours) {
        this.taskHours = taskHours;
    }

}