package com.example.mediscreenmobileapplication;

public class User {

    private String email;
    private String password;
    private String fName;
    private String lName;
    private String memberNumber;
    private String policyNumber;

    public User(String email, String password, String fName, String lName, String memberNumber, String policyNumber) {

        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.memberNumber = memberNumber;
        this.policyNumber = policyNumber;
    }

    public User(String email, String password) {

        this.email = email;
        this.password = password;

        this.fName = null;
        this.lName = null;
        this.memberNumber = null;
        this.policyNumber = null;
    }

    public User() {
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String toString(){

        String user = (
                "Email: " + email +
                "/nPassword: " + password +
                "/nFirst Name: " + fName +
                "/nLast Name: " + lName +
                "/nMember Number: " + memberNumber +
                "/nPolicy Number: " + policyNumber);

        return user;
    }
}