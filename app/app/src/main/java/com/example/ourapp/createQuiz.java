package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class createQuiz extends AppCompatActivity {

    ArrayList<Question> quiz_questions = new ArrayList<Question>();
    EditText Overall_grade;
    Button mcq, t_f, view_qs, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        mcq = findViewById(R.id.mcqButton);
        t_f = findViewById(R.id.t_fButton);
        view_qs = findViewById(R.id.viewButton);
        submit = findViewById(R.id.submitButton);

        mcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMCQ();
                Intent i = getIntent();
                quiz_questions.add((Question) i.getSerializableExtra("mcQuestion"));
            }
        });

        t_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotot_f();
                Intent i = getIntent();
                quiz_questions.add((Question)i.getSerializableExtra("t/fQuestion"));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateQuiz(quiz_questions);
            }
        });

        view_qs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuiz();
            }
        });

    }

    public void gotoMCQ(){
        startActivity(new Intent(this, addMCQuestion.class));
    }

    public void gotot_f(){
        startActivity(new Intent(this, addTFQuestion.class));
    }

    public void validateQuiz(ArrayList<Question> q_list){
        boolean flag = true;

        if(q_list.size()<1){
            flag = false;
            Toast.makeText(getApplicationContext(), "You have to add at least one question to your quiz", Toast.LENGTH_LONG).show();
        }

        if(flag == true) {
            Quiz quiz = new Quiz(q_list);
        }
        Intent intent = new Intent(this, QuestionList.class);
        intent.putExtra("quiz_questions", quiz_questions);
        startActivity(intent);
    }

    private void displayQuiz() {
        startActivity(new Intent(this, QuestionListAdapter.class));
    }

}
