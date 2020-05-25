package com.example.ourapp;


import java.util.ArrayList;


public class Quiz {

    private ArrayList<Question> questions;
    private double grade;

    public Quiz (ArrayList<Question> qs){
        for(int i=0; i<qs.size(); i++)
            questions.add(i, qs.get(i));
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> qs) {
        for(int i=0; i<qs.size(); i++)
            questions.add(i, qs.get(i));
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double changeGrade(double gr){
        setGrade(gr);
        return grade;
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    public void removeQuestion(int x){
        questions.remove(x);
    }


    // calculating the final grade of the quiz by summing up the grades of all the questions of the quiz
    public double evaluate(){
        double score = 0;
        for(int  i=0; i<questions.size(); i++)
            score+=questions.get(i).grade();
        setGrade(score);
        return grade;
    }

}
