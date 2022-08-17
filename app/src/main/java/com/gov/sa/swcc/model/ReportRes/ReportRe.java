package com.gov.sa.swcc.model.ReportRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportRe {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ContractsId")
    @Expose
    private String contractsId;
    @SerializedName("RoleType")
    @Expose
    private String roleType;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractsId() {
        return contractsId;
    }

    public void setContractsId(String contractsId) {
        this.contractsId = contractsId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getId() {
        if(id.length()==0)
            return "0";
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}