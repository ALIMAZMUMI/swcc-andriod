package com.gov.sa.swcc.model.CompleteTask;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompleteTask {

    @SerializedName("empTaskId")
    @Expose
    private Integer empTaskId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("employeeAttachments")
    @Expose
    private List<EmployeeAttachment> employeeAttachments = null;

    public Integer getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Integer empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<EmployeeAttachment> getEmployeeAttachments() {
        return employeeAttachments;
    }

    public void setEmployeeAttachments(List<EmployeeAttachment> employeeAttachments) {
        this.employeeAttachments = employeeAttachments;
    }

}