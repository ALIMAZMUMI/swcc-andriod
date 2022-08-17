package com.gov.sa.swcc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternalType {

    @SerializedName("Msg")
    @Expose
    private String msg;
    @SerializedName("ListCat")
    @Expose
    private List<ListCat> listCat = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListCat> getListCat() {
        return listCat;
    }

    public void setListCat(List<ListCat> listCat) {
        this.listCat = listCat;
    }

}
