package com.example.muhammadimran.parkingbookingsystem.UserPanel;

/**
 * Created by muhammad imran on 1/27/2017.
 */

public class UserModel {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String cpassword;
    private String dateofbirth;
    private String contact;

    public UserModel() {
    }

    public UserModel(String fname, String lname, String email, String password, String cpassword, String dateofbirth, String contact) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
        this.dateofbirth = dateofbirth;
        this.contact = contact;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
