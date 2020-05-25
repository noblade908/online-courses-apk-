package com.example.ourapp;

public class Project {

    private String project_description;
    private int exppts_added;

    public Project(){
        project_description = "";
        exppts_added = 0;
    }

    public Project(String description, int exppts){
        project_description = description;
        exppts_added = exppts;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public int getExppts_added() {
        return exppts_added;
    }

    public void setExppts_added(int exppts_added) {
        this.exppts_added = exppts_added;
    }
}
