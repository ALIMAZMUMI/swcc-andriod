package com.gov.sa.swcc.model.MangerCount;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("activeTasksCount")
    @Expose
    private Integer activeTasksCount;
    @SerializedName("closedTasksCount")
    @Expose
    private Integer closedTasksCount;
    @SerializedName("returnedTasksCount")
    @Expose
    private Integer returnedTasksCount;
    @SerializedName("approveRequestTaskCount")
    @Expose
    private Integer approveRequestTaskCount;
    @SerializedName("delayedTasksCount")
    @Expose
    private Integer delayedTasksCount;
    @SerializedName("increaseTaskTimeRequestsCount")
    @Expose
    private Integer increaseTaskTimeRequestsCount;
    @SerializedName("evaluationRequestsCount")
    @Expose
    private Integer evaluationRequestsCount;
    @SerializedName("evaluatedTasks")
    @Expose
    private Integer evaluatedTasks;

    public Integer getActiveTasksCount() {
        return activeTasksCount;
    }

    public void setActiveTasksCount(Integer activeTasksCount) {
        this.activeTasksCount = activeTasksCount;
    }

    public Integer getClosedTasksCount() {
        return closedTasksCount;
    }

    public void setClosedTasksCount(Integer closedTasksCount) {
        this.closedTasksCount = closedTasksCount;
    }

    public Integer getReturnedTasksCount() {
        return returnedTasksCount;
    }

    public void setReturnedTasksCount(Integer returnedTasksCount) {
        this.returnedTasksCount = returnedTasksCount;
    }

    public Integer getApproveRequestTaskCount() {
        return approveRequestTaskCount;
    }

    public void setApproveRequestTaskCount(Integer approveRequestTaskCount) {
        this.approveRequestTaskCount = approveRequestTaskCount;
    }

    public Integer getDelayedTasksCount() {
        return delayedTasksCount;
    }

    public void setDelayedTasksCount(Integer delayedTasksCount) {
        this.delayedTasksCount = delayedTasksCount;
    }

    public Integer getIncreaseTaskTimeRequestsCount() {
        return increaseTaskTimeRequestsCount;
    }

    public void setIncreaseTaskTimeRequestsCount(Integer increaseTaskTimeRequestsCount) {
        this.increaseTaskTimeRequestsCount = increaseTaskTimeRequestsCount;
    }

    public Integer getEvaluationRequestsCount() {
        return evaluationRequestsCount;
    }

    public void setEvaluationRequestsCount(Integer evaluationRequestsCount) {
        this.evaluationRequestsCount = evaluationRequestsCount;
    }

    public Integer getEvaluatedTasks() {
        return evaluatedTasks;
    }

    public void setEvaluatedTasks(Integer evaluatedTasks) {
        this.evaluatedTasks = evaluatedTasks;
    }

}
