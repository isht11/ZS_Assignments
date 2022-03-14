package com.zs.assignment13.entity;

public class User {
    private int id;
    private String userName;
    private String mobileNumber;
    public User() {
    }

    public User(int id, String userName, String mobileNumber){
        this.id = id;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
