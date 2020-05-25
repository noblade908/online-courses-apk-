package com.example.ourapp;


import java.io.Serializable;

// This class is for the true or false questions it has four attributes:
//the first is the statement of the question
//the second is the boolean correct answer of the question
//the third is the worth points of the questions
// the fourth is the answer of the user
public class TFQuestion implements Question, Serializable {

    private String statement;
    private boolean answer;
    private double gradeweight;
    private boolean user_answer;

    public TFQuestion(String st, boolean fl, double grw){
        statement = st;
        answer = fl;
        gradeweight = grw;
    }

    public String getStatement(){
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean getAnswer(){
        return answer;
    }

    public void setAnswer(boolean a){
        answer = a;
    }

    public double getGradeweight() {
        return gradeweight;
    }

    public void setGradeweight(double gradeweight) {
        this.gradeweight = gradeweight;
    }

    public boolean getUser_answer(){
        return user_answer;
    }

    public void setUser_answer(boolean user_answer) {
        this.user_answer = user_answer;
    }

    // a method that returns the full mark of the question of the student gets the correct answer
    @Override
    public double grade() {
        if(user_answer != answer)
            return 0.0;
        return gradeweight;
    }
}
