
package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalResult {

    @SerializedName("ResultCode")
    @Expose
    private Object resultCode;
    @SerializedName("ResultMessage")
    @Expose
    private String resultMessage;
    @SerializedName("MoreDetails")
    @Expose
    private Object moreDetails;
    @SerializedName("ResultObject")
    @Expose
    private ResultObject resultObject;

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

    public Object getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(Object moreDetails) {
        this.moreDetails = moreDetails;
    }

    public ResultObject getResultObject() {

        return resultObject;
    }

    public void setResultObject(ResultObject resultObject) {
        this.resultObject = resultObject;
    }

}
