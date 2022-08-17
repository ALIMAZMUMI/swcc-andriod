
package com.gov.sa.swcc.model.Rating;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("taskId")
    @Expose
    private Integer taskId;
    @SerializedName("employeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("taskName")
    @Expose
    private String taskName;
    @SerializedName("taskDecription")
    @Expose
    private String taskDecription;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("taskCompletedOn")
    @Expose
    private String taskCompletedOn;
    @SerializedName("taskStatusId")
    @Expose
    private Integer taskStatusId;
    @SerializedName("taskStatusName")
    @Expose
    private String taskStatusName;
    @SerializedName("employeeUid")
    @Expose
    private String employeeUid;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("taskActionId")
    @Expose
    private String taskActionId;
    @SerializedName("isTaskFinishedInTime")
    @Expose
    private Boolean isTaskFinishedInTime;
    @SerializedName("taskHours")
    @Expose
    private Integer taskHours;
    @SerializedName("isTaskExtendedBefore")
    @Expose
    private Boolean isTaskExtendedBefore;
    @SerializedName("taskReturnedCount")
    @Expose
    private Integer taskReturnedCount;
    @SerializedName("isNearToDelayedSent")
    @Expose
    private Boolean isNearToDelayedSent;
    @SerializedName("taskComment")
    @Expose
    private String taskComment;

    public List<Object> getManagerAttachmentsDTO() {
        return managerAttachmentsDTO;
    }

    public void setManagerAttachmentsDTO(List<Object> managerAttachmentsDTO) {
        this.managerAttachmentsDTO = managerAttachmentsDTO;
    }

    public List<Object> getEmployeeAttachmentsDTO() {
        return employeeAttachmentsDTO;
    }

    public void setEmployeeAttachmentsDTO(List<Object> employeeAttachmentsDTO) {
        this.employeeAttachmentsDTO = employeeAttachmentsDTO;
    }

    @SerializedName("managerAttachmentsDTO")
    @Expose
    private List<Object>  managerAttachmentsDTO;
    @SerializedName("employeeAttachmentsDTO")
    @Expose
    private List<Object> employeeAttachmentsDTO;
    @SerializedName("oneDayTask")
    @Expose
    private Boolean oneDayTask;
    @SerializedName("remaining")
    @Expose
    private String remaining;
    @SerializedName("getRemainingTime")
    @Expose
    private String getRemainingTime;
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
    private String createdBy;
    @SerializedName("errorMessages")
    @Expose
    private List<String> errorMessages = null;

    public String getExtendTimeRequestNewDate() {
        return extendTimeRequestNewDate;
    }

    public void setExtendTimeRequestNewDate(String extendTimeRequestNewDate) {
        this.extendTimeRequestNewDate = extendTimeRequestNewDate;
    }

    @SerializedName("extendTimeRequestNewDate")
    @Expose
    private String extendTimeRequestNewDate;


    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public String getTaskCompletedOn() {
        return taskCompletedOn;
    }

    public void setTaskCompletedOn(String taskCompletedOn) {
        this.taskCompletedOn = taskCompletedOn;
    }

    public Integer getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Integer taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

    public String getEmployeeUid() {
        return employeeUid;
    }

    public void setEmployeeUid(String employeeUid) {
        this.employeeUid = employeeUid;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTaskActionId() {
        return taskActionId;
    }

    public void setTaskActionId(String taskActionId) {
        this.taskActionId = taskActionId;
    }

    public Boolean getIsTaskFinishedInTime() {
        return isTaskFinishedInTime;
    }

    public void setIsTaskFinishedInTime(Boolean isTaskFinishedInTime) {
        this.isTaskFinishedInTime = isTaskFinishedInTime;
    }

    public Integer getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(Integer taskHours) {
        this.taskHours = taskHours;
    }

    public Boolean getIsTaskExtendedBefore() {
        return isTaskExtendedBefore;
    }

    public void setIsTaskExtendedBefore(Boolean isTaskExtendedBefore) {
        this.isTaskExtendedBefore = isTaskExtendedBefore;
    }

    public Integer getTaskReturnedCount() {
        return taskReturnedCount;
    }

    public void setTaskReturnedCount(Integer taskReturnedCount) {
        this.taskReturnedCount = taskReturnedCount;
    }

    public Boolean getIsNearToDelayedSent() {
        return isNearToDelayedSent;
    }

    public void setIsNearToDelayedSent(Boolean isNearToDelayedSent) {
        this.isNearToDelayedSent = isNearToDelayedSent;
    }

    public String getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(String taskComment) {
        this.taskComment = taskComment;
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

    public String getGetRemainingTime() {
        return getRemainingTime;
    }

    public void setGetRemainingTime(String getRemainingTime) {
        this.getRemainingTime = getRemainingTime;
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
