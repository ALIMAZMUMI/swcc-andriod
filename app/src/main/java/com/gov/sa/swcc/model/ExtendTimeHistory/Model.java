
package com.gov.sa.swcc.model.ExtendTimeHistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("taskId")
    @Expose
    private Integer taskId;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("fromStatus")
    @Expose
    private String fromStatus;
    @SerializedName("toStatus")
    @Expose
    private String toStatus;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("errorMessages")
    @Expose
    private List<Object> errorMessages = null;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
