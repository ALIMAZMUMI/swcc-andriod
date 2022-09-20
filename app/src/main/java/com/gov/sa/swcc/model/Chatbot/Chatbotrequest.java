package com.gov.sa.swcc.model.Chatbot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Root;
import retrofit2.http.Body;

@Root
public class Chatbotrequest {
        @SerializedName("EId")
        @Expose
        private String EId;
        @SerializedName("Token")
        @Expose
        private String Token;

    public String getEId() {
        return EId;
    }

    public void setEId(String EId) {
        this.EId = EId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
