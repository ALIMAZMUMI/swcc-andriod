
package com.gov.sa.swcc.model.emptask;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("getAllMyTasks")
    @Expose
    private List<GetAllMyTask> getAllMyTasks = null;
    @SerializedName("getMyClosedTasks")
    @Expose
    private List<GetMyClosedTask> getMyClosedTasks = null;
    @SerializedName("getMyCompletedTasks")
    @Expose
    private List<GetMyCompletedTask> getMyCompletedTasks = null;
    @SerializedName("getMyActiveTasks")
    @Expose
    private List<GetMyActiveTasks> getMyActiveTasks = null;


    @SerializedName("getMyDelayedTasks")
    @Expose
    private List<GetMyDelayedTasks> getMyDelayedTasks = null;
    @SerializedName("getMyPendingExtendRequests")
    @Expose
    private List<Object> getMyPendingExtendRequests = null;

    public List<GetAllMyTask> getGetAllMyTasks() {
        return getAllMyTasks;
    }

    public void setGetAllMyTasks(List<GetAllMyTask> getAllMyTasks) {
        this.getAllMyTasks = getAllMyTasks;
    }

    public List<GetMyClosedTask> getGetMyClosedTasks() {
        return getMyClosedTasks;
    }

    public void setGetMyClosedTasks(List<GetMyClosedTask> getMyClosedTasks) {
        this.getMyClosedTasks = getMyClosedTasks;
    }

    public List<GetMyCompletedTask> getGetMyCompletedTasks() {
        return getMyCompletedTasks;
    }

    public void setGetMyCompletedTasks(List<GetMyCompletedTask> getMyCompletedTasks) {
        this.getMyCompletedTasks = getMyCompletedTasks;
    }



    public List<Object> getGetMyPendingExtendRequests() {
        return getMyPendingExtendRequests;
    }

    public void setGetMyPendingExtendRequests(List<Object> getMyPendingExtendRequests) {
        this.getMyPendingExtendRequests = getMyPendingExtendRequests;
    }

    public List<GetMyActiveTasks> getGetMyActiveTasks() {
        return getMyActiveTasks;
    }

    public void setGetMyActiveTasks(List<GetMyActiveTasks> getMyActiveTasks) {
        this.getMyActiveTasks = getMyActiveTasks;
    }

    public List<GetMyDelayedTasks> getGetMyDelayedTasks() {
        return getMyDelayedTasks;
    }

    public void setGetMyDelayedTasks(List<GetMyDelayedTasks> getMyDelayedTasks) {
        this.getMyDelayedTasks = getMyDelayedTasks;
    }



}
