package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProPlanning {

    @SerializedName("ResultCode")
    @Expose
    private Object resultCode;
    @SerializedName("ResultMessage")
    @Expose
    private String resultMessage;
    @SerializedName("MoreDetails")
    @Expose
    private String moreDetails;
    @SerializedName("ResultObject")
    @Expose
    private String resultObject;

    public Object getResultCode() {
        return resultCode;
    }

    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    public String getResultObject() {
        return resultObject;
    }

    public void setResultObject(String resultObject) {
        this.resultObject = resultObject;
    }

}