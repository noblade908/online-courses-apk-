package com.example.ourapp;

// This class is for the multiple-choice questions it has four attributes:
//the first is the statement of the question
//the second is the number of the correct answer of the question
//the third to sixth are the four choices that the user choose the answer from
// the seventh is the the worth points of the questions
//the eighth is the number of the user choice which is compared to the correct answer number
//to determine if the user got the correct answer

import java.io.Serializable;

public class MCQuestion implements Question, Serializable {

    private String statement;
    private int answer_number;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private double gradeweight;
    private int user_choice;

    public MCQuestion(String st, int ans_num, String ch1, String ch2, String ch3, String ch4, double grw){
        statement = st;
        answer_number = ans_num;
        choice1 = ch1;
        choice2 = ch2;
        choice3 = ch3;
        choice4 = ch4;
        gradeweight = grw;
    }

    public String getStatement(){
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getChoice1(){
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public double getGradeweight() {
        return gradeweight;
    }

    public void setGradeweight(double gradeweight) {
        this.gradeweight = gradeweight;
    }

    public int getUser_choice() {
        return user_choice;
    }

    public void setUser_choice(int user_choice) {
        this.user_choice = user_choice;
    }

    public int getAnswer_number() {
        return answer_number;
    }

    public void setAnswer_number(int answer_number) {
        this.answer_number = answer_number;
    }

    // a method that returns the full mark of the question of the student gets the correct answer
    @Override
    public double grade() {
        if(user_choice != answer_number)
            return 0;
        return gradeweight;
    }
}
