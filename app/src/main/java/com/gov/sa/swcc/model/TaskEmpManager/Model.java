
package com.gov.sa.swcc.model.TaskEmpManager;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("getAllTasks")
    @Expose
    private List<GetAllTask> getAllTasks = null;
    @SerializedName("getAllActiveTasks")
    @Expose
    private List<GetAllTask> getAllActiveTasks = null;
    @SerializedName("getClosedTasks")
    @Expose
    private List<GetAllTask> getClosedTasks = null;
    @SerializedName("getCompletedTasks")
    @Expose
    private List<GetAllTask> getCompletedTasks = null;
    @SerializedName("getDelayedTasks")
    @Expose
    private List<GetAllTask> getDelayedTasks = null;
    @SerializedName("getPendingExtendRequests")
    @Expose
    private List<Object> getPendingExtendRequests = null;

    public List<GetAllTask> getGetAllTasks() {
        return getAllTasks;
    }

    public void setGetAllTasks(List<GetAllTask> getAllTasks) {
        this.getAllTasks = getAllTasks;
    }

    public List<GetAllTask> getGetAllActiveTasks() {
        return getAllActiveTasks;
    }

    public void setGetAllActiveTasks(List<GetAllTask> getAllActiveTasks) {
        this.getAllActiveTasks = getAllActiveTasks;
    }

    public List<GetAllTask> getGetClosedTasks() {
        return getClosedTasks;
    }

    public void setGetClosedTasks(List<GetAllTask> getClosedTasks) {
        this.getClosedTasks = getClosedTasks;
    }

    public List<GetAllTask> getGetCompletedTasks() {
        return getCompletedTasks;
    }

    public void setGetCompletedTasks(List<GetAllTask> getCompletedTasks) {
        this.getCompletedTasks = getCompletedTasks;
    }

    public List<GetAllTask> getGetDelayedTasks() {
        return getDelayedTasks;
    }

    public void setGetDelayedTasks(List<GetAllTask> getDelayedTasks) {
        this.getDelayedTasks = getDelayedTasks;
    }

    public List<Object> getGetPendingExtendRequests() {
        return getPendingExtendRequests;
    }

    public void setGetPendingExtendRequests(List<Object> getPendingExtendRequests) {
        this.getPendingExtendRequests = getPendingExtendRequests;
    }

}
