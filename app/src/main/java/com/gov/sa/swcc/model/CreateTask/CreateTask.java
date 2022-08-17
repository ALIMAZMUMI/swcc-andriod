
package com.gov.sa.swcc.model.CreateTask;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTask {

    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("actionId")
    @Expose
    private Integer actionId;
    @SerializedName("taskName")
    @Expose
    private String taskName;
    @SerializedName("taskDecription")
    @Expose
    private String taskDecription;
    @SerializedName("taskHours")
    @Expose
    private Integer taskHours;
    @SerializedName("isTaskFinishedInTime")
    @Expose
    private Boolean isTaskFinishedInTime;
    @SerializedName("taskReviewerId")
    @Expose
    private Integer taskReviewerId;
    @SerializedName("taskAttachmentGUID")
    @Expose
    private String taskAttachmentGUID;
    @SerializedName("uploadAttachments")
    @Expose
    private List<UploadAttachment> uploadAttachments = null;
    @SerializedName("uids")
    @Expose
    private List<String> uids = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("errorMessages")
    @Expose
    private List<String> errorMessages = null;

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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
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

    public Integer getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(Integer taskHours) {
        this.taskHours = taskHours;
    }

    public Boolean getIsTaskFinishedInTime() {
        return isTaskFinishedInTime;
    }

    public void setIsTaskFinishedInTime(Boolean isTaskFinishedInTime) {
        this.isTaskFinishedInTime = isTaskFinishedInTime;
    }

    public Integer getTaskReviewerId() {
        return taskReviewerId;
    }

    public void setTaskReviewerId(Integer taskReviewerId) {
        this.taskReviewerId = taskReviewerId;
    }

    public String getTaskAttachmentGUID() {
        return taskAttachmentGUID;
    }

    public void setTaskAttachmentGUID(String taskAttachmentGUID) {
        this.taskAttachmentGUID = taskAttachmentGUID;
    }

    public List<UploadAttachment> getUploadAttachments() {
        return uploadAttachments;
    }

    public void setUploadAttachments(List<UploadAttachment> uploadAttachments) {
        this.uploadAttachments = uploadAttachments;
    }

    public List<String> getUids() {
        return uids;
    }

    public void setUids(List<String> uids) {
        this.uids = uids;
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

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
