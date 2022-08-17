package com.gov.sa.swcc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchEmpItem {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private List<SearchResult> result = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SearchResult> getResult() {
        return result;
    }

    public void setResult(List<SearchResult> result) {
        this.result = result;
    }

}