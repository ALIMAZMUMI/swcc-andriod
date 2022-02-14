package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerModel {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("SupervisorId")
    @Expose
    private String supervisorId;
    @SerializedName("ContractEvaluationElementId")
    @Expose
    private String contractEvaluationElementId;
    @SerializedName("ContractEvaluationElementName")
    @Expose
    private String contractEvaluationElementName;
    @SerializedName("RoundNumber")
    @Expose
    private Integer roundNumber;
    @SerializedName("RoundResult")
    @Expose
    private Object roundResult;



    @SerializedName("Priority_LK")
    @Expose
    private String priorityLK="";
    @SerializedName("Cmt")
    @Expose
    private String cmt="";

    @SerializedName("Photo")
    @Expose
    private String photo="";
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy="";
    @SerializedName("ModifyBy")
    @Expose
    private String modifyBy="";
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate="";
    @SerializedName("ModifyDate")
    @Expose
    private String modifyDate="";
    @SerializedName("IsDeleted")
    @Expose
    private String isDeleted="";


    public Object getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(Object roundResult) {
        this.roundResult = roundResult;
    }



    public String getCmt() {
        return cmt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getContractEvaluationElementId() {
        return contractEvaluationElementId;
    }

    public void setContractEvaluationElementId(String contractEvaluationElementId) {
        this.contractEvaluationElementId = contractEvaluationElementId;
    }

    public String getContractEvaluationElementName() {
        return contractEvaluationElementName;
    }

    public void setContractEvaluationElementName(String contractEvaluationElementName) {
        this.contractEvaluationElementName = contractEvaluationElementName;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }



    public String getPriorityLK() {
        return priorityLK;
    }

    public void setPriorityLK(String priorityLK) {
        this.priorityLK = priorityLK;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}