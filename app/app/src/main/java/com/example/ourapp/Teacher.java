package com.example.ourapp;


import java.util.HashMap;
import java.util.Map;

public class Teacher {
    private String username;
    private String firstname;
    private String lastname;
    private boolean isTeacher;
    private String email;
    private String password;
    private String bio;


    public Teacher(){}

    public Teacher(String username,String fname,String lname,String mail,boolean isteacher,  String password)
    {
        this.firstname = fname;
        this.lastname = lname;
        this.isTeacher = isteacher;
        this.email = mail;
        this.bio = "";
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean isTeacher() {
        return isTeacher;
    }


}
