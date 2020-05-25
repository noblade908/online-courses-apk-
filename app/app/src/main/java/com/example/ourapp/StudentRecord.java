package com.example.ourapp;

public class StudentRecord {

    private String st_username;
    private int coursenumber;
    private double grade;

    public StudentRecord(String name, int num, double gr){
        st_username = name;
        coursenumber = num;
        grade = gr;
    }

    public String getSt_username(){return st_username;}
    public void setSt_username(String stun){st_username = stun;}
    public int getCoursenumber(){return coursenumber;}
    public void setCoursenumber(int num){coursenumber = num;}
    public double getGrade(){ return grade;}
    public void setGrade(double gr){ grade = gr;}

}
