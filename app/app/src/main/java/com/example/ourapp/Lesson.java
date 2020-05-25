package com.example.ourapp;

import java.io.Serializable;

public class Lesson implements Serializable {
    private  String title;
    private String description;

    public Lesson(){}
    public Lesson(String t,String desc){
        this.title = t;
        this.description = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}