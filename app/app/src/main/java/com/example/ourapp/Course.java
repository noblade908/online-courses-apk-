package com.example.ourapp;

import java.io.Serializable;
import java.util.ArrayList;
public class Course implements Serializable {


    private String name;
    private String Description;
    private boolean isPublic;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setmList(ArrayList<Module> mList) {
        this.mList = mList;
    }

    public void setImage(int image) {
        this.image = image;
    }

    ArrayList <Module> mList = new ArrayList<>();



    int image;

    public Course(){}

    public Course(String name,int image,String description){
    this.name = name;
    this.image=image;
    Description = description;
    }

    public int getImage() {
        return image;
    }

    public Course (String name, String Desc, boolean isPub, ArrayList<Module> newlist){
    this.name = name;
    this.Description = Desc;
    this.isPublic = isPub;
    this.mList = newlist;

    }
    public Course (String name, String Desc, boolean isPub){
        this.name = name;
        this.Description = Desc;
        this.isPublic = isPub;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public ArrayList<Module> getmList() {
        return mList;
    }

    public void addModule(Module m){
        mList.add(m);

    }

}
