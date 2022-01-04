package com.gov.sa.swcc.model;

import org.simpleframework.xml.Root;

@Root
public class ITRoot {

    private ITRequest itRequest;

    public ITRequest getItRequest() {
        return itRequest;
    }

    public void setItRequest(ITRequest itRequest) {
        this.itRequest = itRequest;
    }
}
