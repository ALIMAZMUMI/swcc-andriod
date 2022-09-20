package com.gov.sa.swcc.model.emptask;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachments {

    @SerializedName("attachName")
    @Expose
    private String  attachName;
    @SerializedName("attachlocation")
    @Expose
    private String attachlocation;
    @SerializedName("taskId")
    @Expose
    private Integer taskId;
    @SerializedName("taskDTO")
    @Expose
    private Object taskDTO;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("errorMessages")
    @Expose
    private List<Object> errorMessages = null;

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachlocation() {
        return attachlocation;
    }

    public void setAttachlocation(String attachlocation) {
        this.attachlocation = attachlocation;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Object getTaskDTO() {
        return taskDTO;
    }

    public void setTaskDTO(Object taskDTO) {
        this.taskDTO = taskDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Object> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<Object> errorMessages) {
        this.errorMessages = errorMessages;
    }

}