
package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListInsuranceDatum {

    @SerializedName("Policy_no")
    @Expose
    private String policyNo;
    @SerializedName("Official_ID")
    @Expose
    private String officialID;
    @SerializedName("Medgulf_PIN")
    @Expose
    private String medgulfPIN;
    @SerializedName("Employee_ID")
    @Expose
    private String employeeID;
    @SerializedName("Dp")
    @Expose
    private String dp;
    @SerializedName("G")
    @Expose
    private String g;
    @SerializedName("Rel")
    @Expose
    private String rel;
    @SerializedName("First_Name")
    @Expose
    private String firstName;
    @SerializedName("Father_Name")
    @Expose
    private String fatherName;
    @SerializedName("Family_Name")
    @Expose
    private String familyName;
    @SerializedName("Maiden_Name")
    @Expose
    private Object maidenName;
    @SerializedName("Birdth_Date_as_dmy")
    @Expose
    private String birdthDateAsDmy;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("F15")
    @Expose
    private Object f15;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("Sub_Group")
    @Expose
    private String subGroup;
    @SerializedName("SponsorId")
    @Expose
    private String sponsorId;
    @SerializedName("Marital_Status")
    @Expose
    private Object maritalStatus;
    @SerializedName("Iqama_Expiry_Date")
    @Expose
    private String iqamaExpiryDate;
    @SerializedName("Occupation")
    @Expose
    private Object occupation;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Notes")
    @Expose
    private Object notes;
    @SerializedName("Effective_date_as_dmy")
    @Expose
    private String effectiveDateAsDmy;
    @SerializedName("Insured_Cert")
    @Expose
    private String insuredCert;
    @SerializedName("F26")
    @Expose
    private Object f26;
    @SerializedName("F27")
    @Expose
    private Object f27;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getOfficialID() {
        return officialID;
    }

    public void setOfficialID(String officialID) {
        this.officialID = officialID;
    }

    public String getMedgulfPIN() {
        return medgulfPIN;
    }

    public void setMedgulfPIN(String medgulfPIN) {
        this.medgulfPIN = medgulfPIN;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Object getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(Object maidenName) {
        this.maidenName = maidenName;
    }

    public String getBirdthDateAsDmy() {
        return birdthDateAsDmy;
    }

    public void setBirdthDateAsDmy(String birdthDateAsDmy) {
        this.birdthDateAsDmy = birdthDateAsDmy;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Object getF15() {
        return f15;
    }

    public void setF15(Object f15) {
        this.f15 = f15;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Object getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Object maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getIqamaExpiryDate() {
        return iqamaExpiryDate;
    }

    public void setIqamaExpiryDate(String iqamaExpiryDate) {
        this.iqamaExpiryDate = iqamaExpiryDate;
    }

    public Object getOccupation() {
        return occupation;
    }

    public void setOccupation(Object occupation) {
        this.occupation = occupation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getEffectiveDateAsDmy() {
        return effectiveDateAsDmy;
    }

    public void setEffectiveDateAsDmy(String effectiveDateAsDmy) {
        this.effectiveDateAsDmy = effectiveDateAsDmy;
    }

    public String getInsuredCert() {
        return insuredCert;
    }

    public void setInsuredCert(String insuredCert) {
        this.insuredCert = insuredCert;
    }

    public Object getF26() {
        return f26;
    }

    public void setF26(Object f26) {
        this.f26 = f26;
    }

    public Object getF27() {
        return f27;
    }

    public void setF27(Object f27) {
        this.f27 = f27;
    }

}