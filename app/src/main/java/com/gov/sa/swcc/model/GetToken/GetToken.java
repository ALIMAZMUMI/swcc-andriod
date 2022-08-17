package com.gov.sa.swcc.model.GetToken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetToken {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("isSupervisorUser")
    @Expose
    private String isSupervisorUser;

    public String getIsSupervisorUser() {
        return isSupervisorUser;
    }

    public void setIsSupervisorUser(String isSupervisorUser) {
        this.isSupervisorUser = isSupervisorUser;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}