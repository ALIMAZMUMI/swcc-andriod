package com.gov.sa.swcc.model;

public class PayslipItem {

    String ElementCode;
    String ElementText;
    String Amount;
    String Classification;

    public String getElementCode() {
        return ElementCode;
    }

    public void setElementCode(String elementCode) {
        ElementCode = elementCode;
    }

    public String getElementText() {
        ElementText=ElementText.replaceAll("[*]","").replaceAll("[.]","")
                .replaceAll("[-]","")
        .replaceAll("[ـ]","");
        return ElementText;
    }

    public void setElementText(String elementText) {
        ElementText = elementText;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }
}
