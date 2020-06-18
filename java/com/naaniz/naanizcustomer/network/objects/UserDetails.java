package com.naaniz.naanizcustomer.network.objects;

public class UserDetails {

    private String name;
    private String phoneNumber;
    private String uuid;
    private String DOB="";
    private String language = "en";
    private String state="";

    public UserDetails(String name, String phoneNumber, String DOB, String uuid, String language, String state) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.uuid = uuid;
        this.DOB = DOB;
        this.language = language;
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
