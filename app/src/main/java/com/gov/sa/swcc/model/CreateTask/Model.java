
package com.gov.sa.swcc.model.CreateTask;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("employees")
    @Expose
    private List<Object> employees = null;
    @SerializedName("attachmentsDTO")
    @Expose
    private List<Object> attachmentsDTO = null;
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
    private Object actionId;
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
    private Object taskReviewerId;
    @SerializedName("taskAttachmentGUID")
    @Expose
    private String taskAttachmentGUID;
    @SerializedName("uploadAttachments")
    @Expose
    private Object uploadAttachments;
    @SerializedName("uids")
    @Expose
    private Object uids;
    @SerializedName("employeesDTO")
    @Expose
    private Object employeesDTO;
    @SerializedName("oneDayTask")
    @Expose
    private Boolean oneDayTask;
    @SerializedName("remaining")
    @Expose
    private String remaining;
    @SerializedName("isDelayedTask")
    @Expose
    private Boolean isDelayedTask;
    @SerializedName("totalDelayingString")
    @Expose
    private String totalDelayingString;
    @SerializedName("totalDays")
    @Expose
    private Double totalDays;
    @SerializedName("totalDaysRemaing")
    @Expose
    private Double totalDaysRemaing;
    @SerializedName("totalHours")
    @Expose
    private Double totalHours;
    @SerializedName("totalHoursRemaing")
    @Expose
    private Double totalHoursRemaing;
    @SerializedName("totalMinutes")
    @Expose
    private Double totalMinutes;
    @SerializedName("totalMinutesRemaing")
    @Expose
    private Double totalMinutesRemaing;
    @SerializedName("totalSeconds")
    @Expose
    private Double totalSeconds;
    @SerializedName("totalSecondsRemaing")
    @Expose
    private Double totalSecondsRemaing;
    @SerializedName("percentage")
    @Expose
    private Double percentage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("errorMessages")
    @Expose
    private List<Object> errorMessages = null;

    public List<Object> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Object> employees) {
        this.employees = employees;
    }

    public List<Object> getAttachmentsDTO() {
        return attachmentsDTO;
    }

    public void setAttachmentsDTO(List<Object> attachmentsDTO) {
        this.attachmentsDTO = attachmentsDTO;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Object getActionId() {
        return actionId;
    }

    public void setActionId(Object actionId) {
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

    public Object getTaskReviewerId() {
        return taskReviewerId;
    }

    public void setTaskReviewerId(Object taskReviewerId) {
        this.taskReviewerId = taskReviewerId;
    }

    public String getTaskAttachmentGUID() {
        return taskAttachmentGUID;
    }

    public void setTaskAttachmentGUID(String taskAttachmentGUID) {
        this.taskAttachmentGUID = taskAttachmentGUID;
    }

    public Object getUploadAttachments() {
        return uploadAttachments;
    }

    public void setUploadAttachments(Object uploadAttachments) {
        this.uploadAttachments = uploadAttachments;
    }

    public Object getUids() {
        return uids;
    }

    public void setUids(Object uids) {
        this.uids = uids;
    }

    public Object getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEmployeesDTO(Object employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public Boolean getOneDayTask() {
        return oneDayTask;
    }

    public void setOneDayTask(Boolean oneDayTask) {
        this.oneDayTask = oneDayTask;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public Boolean getIsDelayedTask() {
        return isDelayedTask;
    }

    public void setIsDelayedTask(Boolean isDelayedTask) {
        this.isDelayedTask = isDelayedTask;
    }

    public String getTotalDelayingString() {
        return totalDelayingString;
    }

    public void setTotalDelayingString(String totalDelayingString) {
        this.totalDelayingString = totalDelayingString;
    }

    public Double getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Double totalDays) {
        this.totalDays = totalDays;
    }

    public Double getTotalDaysRemaing() {
        return totalDaysRemaing;
    }

    public void setTotalDaysRemaing(Double totalDaysRemaing) {
        this.totalDaysRemaing = totalDaysRemaing;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public Double getTotalHoursRemaing() {
        return totalHoursRemaing;
    }

    public void setTotalHoursRemaing(Double totalHoursRemaing) {
        this.totalHoursRemaing = totalHoursRemaing;
    }

    public Double getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(Double totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public Double getTotalMinutesRemaing() {
        return totalMinutesRemaing;
    }

    public void setTotalMinutesRemaing(Double totalMinutesRemaing) {
        this.totalMinutesRemaing = totalMinutesRemaing;
    }

    public Double getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(Double totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public Double getTotalSecondsRemaing() {
        return totalSecondsRemaing;
    }

    public void setTotalSecondsRemaing(Double totalSecondsRemaing) {
        this.totalSecondsRemaing = totalSecondsRemaing;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public List<Object> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<Object> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
