package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class QuestionList extends AppCompatActivity {

    private static final String TAG = "QuestionList";

    ArrayList<Question> question_list = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Intent intent = getIntent();
        ArrayList<Question> quiz_questions;
        quiz_questions = (ArrayList<Question>) intent.getSerializableExtra("quiz_questions");
        assert quiz_questions != null;
        question_list.addAll(quiz_questions);

        QuestionListAdapter adapter = new QuestionListAdapter(this, R.layout.listview_adapter, question_list);

    }
}
