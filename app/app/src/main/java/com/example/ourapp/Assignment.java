package com.example.ourapp;

public class Assignment {

    private Question question;
    private double grade;

    public Assignment(Question q, double gr){
        question = q;
        grade = gr;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double evaluate(){
        grade = question.grade();
        return grade;
    }
}
