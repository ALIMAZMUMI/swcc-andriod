
package com.gov.sa.swcc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultObject {

    @SerializedName("FirstNameAr")
    @Expose
    private String firstNameAr;
    @SerializedName("MiddleNameAr")
    @Expose
    private String middleNameAr;
    @SerializedName("LastNameAr")
    @Expose
    private String lastNameAr;



    @SerializedName("JwtToken")
    @Expose
    private String JwtToken;
    public String getJwtToken() {
        if(JwtToken==null)
            return "";
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }
    @SerializedName("FirstNameEn")
    @Expose
    private String firstNameEn;
    @SerializedName("MiddleNameEn")
    @Expose
    private String middleNameEn;
    @SerializedName("LastNameEn")
    @Expose
    private String lastNameEn;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("NationalId")
    @Expose
    private String nationalId;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("DepartmentCode")
    @Expose
    private String departmentCode;
    @SerializedName("LocationAr")
    @Expose
    private String locationAr;
    @SerializedName("LocationEn")
    @Expose
    private String locationEn;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("OTP")
    @Expose
    private Object otp;

    public String getFirstNameAr() {
        return firstNameAr;
    }

    public void setFirstNameAr(String firstNameAr) {
        this.firstNameAr = firstNameAr;
    }

    public String getMiddleNameAr() {
        return middleNameAr;
    }

    public void setMiddleNameAr(String middleNameAr) {
        this.middleNameAr = middleNameAr;
    }

    public String getLastNameAr() {
        return lastNameAr;
    }

    public void setLastNameAr(String lastNameAr) {
        this.lastNameAr = lastNameAr;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getMiddleNameEn() {
        return middleNameEn;
    }

    public void setMiddleNameEn(String middleNameEn) {
        this.middleNameEn = middleNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getLocationAr() {
        return locationAr;
    }

    public void setLocationAr(String locationAr) {
        this.locationAr = locationAr;
    }

    public String getLocationEn() {
        return locationEn;
    }

    public void setLocationEn(String locationEn) {
        this.locationEn = locationEn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Object getOtp() {
        return otp;
    }

    public void setOtp(Object otp) {
        this.otp = otp;
    }

}
