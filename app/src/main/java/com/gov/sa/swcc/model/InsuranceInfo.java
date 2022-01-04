package com.gov.sa.swcc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsuranceInfo {

    @SerializedName("Mesg")
    @Expose
    private String mesg;
    @SerializedName("ListInsuranceData")
    @Expose
    private List<ListInsuranceDatum> listInsuranceData = null;

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public List<ListInsuranceDatum> getListInsuranceData() {
        return listInsuranceData;
    }

    public void setListInsuranceData(List<ListInsuranceDatum> listInsuranceData) {
        this.listInsuranceData = listInsuranceData;
    }

}