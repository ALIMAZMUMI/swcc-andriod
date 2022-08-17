package com.gov.sa.swcc.model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("FirstNameAr")
    @Expose
    private String firstNameAr;
    @SerializedName("MiddleNameAr")
    @Expose
    private String middleNameAr;
    @SerializedName("LastNameAr")
    @Expose
    private String lastNameAr;
    @SerializedName("FirstNameEn")
    @Expose
    private String firstNameEn;
    @SerializedName("MiddleNameEn")
    @Expose
    private String middleNameEn;
    @SerializedName("LastNameEn")
    @Expose
    private String lastNameEn;
    @SerializedName("JobCode")
    @Expose
    private String jobCode;
    @SerializedName("ArabicJobTitle")
    @Expose
    private String arabicJobTitle;
    @SerializedName("EnglishJobTitle")
    @Expose
    private String englishJobTitle;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Ext")
    @Expose
    private String ext;
    @SerializedName("NameAr")
    @Expose
    private String nameAr;
    @SerializedName("NameEn")
    @Expose
    private String nameEn;

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

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getArabicJobTitle() {
        return arabicJobTitle;
    }

    public void setArabicJobTitle(String arabicJobTitle) {
        this.arabicJobTitle = arabicJobTitle;
    }

    public String getEnglishJobTitle() {
        return englishJobTitle;
    }

    public void setEnglishJobTitle(String englishJobTitle) {
        this.englishJobTitle = englishJobTitle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

}