
package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionsApiResult {

    @SerializedName("emp_code")
    @Expose
    private String empCode;
    @SerializedName("punch_date")
    @Expose
    private String punchDate;
    @SerializedName("punch_time")
    @Expose
    private String punchTime;
    @SerializedName("punch_state")
    @Expose
    private String punchState;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(String punchDate) {
        this.punchDate = punchDate;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    public String getPunchState() {
        return punchState;
    }

    public void setPunchState(String punchState) {
        this.punchState = punchState;
    }

}
