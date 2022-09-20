package com.gov.sa.swcc.model.locationAttend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TransactionLocationData {
    @SerializedName("Mes")
    @Expose
    public String Mes;
    @SerializedName("Timeallowed")
    @Expose
    public String Timeallowed;
    @SerializedName("currentDatetime")
    @Expose
    public String currentDatetime;
    @SerializedName("LastTransactionDatetime")
    @Expose
    public String LastTransactionDatetime;
    @SerializedName("DifferenceMinutes")
    @Expose
    public Double DifferenceMinutes;

}
