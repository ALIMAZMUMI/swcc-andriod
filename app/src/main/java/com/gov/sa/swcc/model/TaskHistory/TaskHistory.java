
package com.gov.sa.swcc.model.TaskHistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskHistory {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("model")
    @Expose
    private List<Model> model = null;
    @SerializedName("tasksCount")
    @Expose
    private Integer tasksCount;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<Model> getModel() {
        return model;
    }

    public void setModel(List<Model> model) {
        this.model = model;
    }

    public Integer getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Integer tasksCount) {
        this.tasksCount = tasksCount;
    }

}
