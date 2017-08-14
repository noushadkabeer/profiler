package com.lemon.idportal.beans;



public class User {
    
    private String userId;
    private String name;
    private String dob;
    private String email;
    private String mobile;
    private String lphone;
    //Photo?
    private String avatar;
    
    //current Address
    private String cAFlatNo;
    private String cAStreet;
    private String cAArea;
    private String cAResidentialComplex;
    private String cALandMark;
    private String cACity;
    private String cAPin;
    private String cACountry;
    
    //proposed Address details
    private String pAFlatNo;
    private String pAStreet;
    private String pAArea;
    private String pAResidentialComplex;
    private String pALandMark;
    private String pACity;
    private String pAPin;
    private String pACountry;
    
    public User(){}
    
    public User(String userId, String name, String dob, String email, String mobile, String lphone,
        String avatar, String cAFlatNo, String cAStreet, String cAArea, String cAResidentialComplex,
        String cALandMark, String cACity, String cAPin, String cACountry, String pAFlatNo, String pAStreet,
        String pAArea, String pAResidentialComplex, String pALandMark, String pACity, String pAPin,
        String pACountry) {
        super();
        this.userId = userId;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.lphone = lphone;
        this.avatar = avatar;
        this.cAFlatNo = cAFlatNo;
        this.cAStreet = cAStreet;
        this.cAArea = cAArea;
        this.cAResidentialComplex = cAResidentialComplex;
        this.cALandMark = cALandMark;
        this.cACity = cACity;
        this.cAPin = cAPin;
        this.cACountry = cACountry;
        this.pAFlatNo = pAFlatNo;
        this.pAStreet = pAStreet;
        this.pAArea = pAArea;
        this.pAResidentialComplex = pAResidentialComplex;
        this.pALandMark = pALandMark;
        this.pACity = pACity;
        this.pAPin = pAPin;
        this.pACountry = pACountry;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDob() {
        return dob;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getLphone() {
        return lphone;
    }
    
    public void setLphone(String lphone) {
        this.lphone = lphone;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getcAFlatNo() {
        return cAFlatNo;
    }
    
    public void setcAFlatNo(String cAFlatNo) {
        this.cAFlatNo = cAFlatNo;
    }
    
    public String getcAStreet() {
        return cAStreet;
    }
    
    public void setcAStreet(String cAStreet) {
        this.cAStreet = cAStreet;
    }
    
    public String getcAArea() {
        return cAArea;
    }
    
    public void setcAArea(String cAArea) {
        this.cAArea = cAArea;
    }
    
    public String getcAResidentialComplex() {
        return cAResidentialComplex;
    }
    
    public void setcAResidentialComplex(String cAResidentialComplex) {
        this.cAResidentialComplex = cAResidentialComplex;
    }
    
    public String getcALandMark() {
        return cALandMark;
    }
    
    public void setcALandMark(String cALandMark) {
        this.cALandMark = cALandMark;
    }
    
    public String getcACity() {
        return cACity;
    }
    
    public void setcACity(String cACity) {
        this.cACity = cACity;
    }
    
    public String getcAPin() {
        return cAPin;
    }
    
    public void setcAPin(String cAPin) {
        this.cAPin = cAPin;
    }
    
    public String getcACountry() {
        return cACountry;
    }
    
    public void setcACountry(String cACountry) {
        this.cACountry = cACountry;
    }
    
    public String getpAFlatNo() {
        return pAFlatNo;
    }
    
    public void setpAFlatNo(String pAFlatNo) {
        this.pAFlatNo = pAFlatNo;
    }
    
    public String getpAStreet() {
        return pAStreet;
    }
    
    public void setpAStreet(String pAStreet) {
        this.pAStreet = pAStreet;
    }
    
    public String getpAArea() {
        return pAArea;
    }
    
    public void setpAArea(String pAArea) {
        this.pAArea = pAArea;
    }
    
    public String getpAResidentialComplex() {
        return pAResidentialComplex;
    }
    
    public void setpAResidentialComplex(String pAResidentialComplex) {
        this.pAResidentialComplex = pAResidentialComplex;
    }
    
    public String getpALandMark() {
        return pALandMark;
    }
    
    public void setpALandMark(String pALandMark) {
        this.pALandMark = pALandMark;
    }
    
    public String getpACity() {
        return pACity;
    }
    
    public void setpACity(String pACity) {
        this.pACity = pACity;
    }
    
    public String getpAPin() {
        return pAPin;
    }
    
    public void setpAPin(String pAPin) {
        this.pAPin = pAPin;
    }
    
    public String getpACountry() {
        return pACountry;
    }
    
    public void setpACountry(String pACountry) {
        this.pACountry = pACountry;
    }
    
    
}
