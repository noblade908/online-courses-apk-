package com.example.ourapp;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String username;


    private int exp;
    private String firstname;
    private String lastname;
    private int exppoints;
    private boolean isTeacher;
    private String email;
    private String password;
    private String bio;


    public Student(){}

    public Student(String username,String fname,String lname,String mail,boolean isteacher,  String password)
    {
        this.firstname = fname;
        this.lastname = lname;
        this.isTeacher = isteacher;
        this.email = mail;
        this.exp = 0;
        this.username = username;
        this.password = password;
        this.bio = "";
    }



    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }


    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }
    public int getExp() {
        return exp;

    }


    public String getLastname() {
        return lastname;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setExp(int exp) {
        this.exp = exp;
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

    public void setPassword(String password) {
        this.password = password;

    }
}
