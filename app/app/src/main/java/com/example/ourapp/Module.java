package com.example.ourapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Module implements Serializable {
    String name;

    public String getName() {
        return name;
    }

    public Lesson[] getWeek_lessons() {
        return week_lessons;
    }

    public Quiz getQ() {
        return q;
    }

    private Lesson [] week_lessons = new Lesson[5];
    private Quiz q;

    public void setName(String name) {
        this.name = name;
    }

    public void setWeek_lessons(Lesson[] week_lessons) {
        this.week_lessons = week_lessons;
    }

    public void setQ(Quiz q) {
        this.q = q;
    }

    public Module (){
        this.name = null;
        week_lessons[0] = null;
        week_lessons[1] = null;
        week_lessons[2] = null;
        week_lessons[3] = null;
        week_lessons[4] = null;
        q = null;
    }
    public Module(String n,Lesson L1,Lesson L2,Lesson L3,Lesson L4,Lesson L5,Quiz qu){
        this.name = n;
        week_lessons[0] = L1;
        week_lessons[1] = L2;
        week_lessons[2] = L3;
        week_lessons[3] = L4;
        week_lessons[4] = L5;
        q = qu;
    }
    public Module(String n)
    {
        this.name = n;
    }

}