package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Statement;
import java.text.DecimalFormat;

public class addTFQuestion extends AppCompatActivity {

    String statement;
    boolean answer;
    double gradeweight;
    EditText Statement, Gradeweight;
    int rid;
    RadioGroup rg;
    RadioButton t_value, f_value;

    Button add;

    DecimalFormat df = new DecimalFormat("0.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_t_f_question);

        add = findViewById(R.id.addButton);

        Statement = (EditText) findViewById(R.id.statementEditText);
        Gradeweight = (EditText) findViewById(R.id.gradeptsEditText);
        rg = findViewById(R.id.TrueFalseRB);
        rid = rg.getCheckedRadioButtonId();
        t_value = findViewById(R.id.TrueRB);
        f_value = findViewById(R.id.FalseRB);

        statement = Statement.getText().toString();
        gradeweight = Double.parseDouble(Gradeweight.getText().toString());

        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                validateQuestion(Statement, Gradeweight, rid);
            }
        });

    }

    public void validateQuestion(EditText st, EditText gw, int id){
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;

        if(st.equals("")){
            Toast.makeText(getApplicationContext(), "There is no statement, please type a statement", Toast.LENGTH_LONG).show();
            flag1 = false;
        }

        if(Double.parseDouble(gw.getText().toString())<0.0){
            Toast.makeText(getApplicationContext(), "You have entered a value less than 0", Toast.LENGTH_LONG).show();
            flag2=false;
        }

        if(Double.parseDouble(gw.getText().toString())>100.0){
            Toast.makeText(getApplicationContext(), "You have entered a value greater than 100", Toast.LENGTH_LONG).show();
            flag2=false;
        }

        if(id == -1){
            Toast.makeText(getApplicationContext(), "You have to choose an answer", Toast.LENGTH_LONG).show();
            flag3=false;
        }

        if(flag1==true && flag2 == true && flag3 == true){
            Toast.makeText(getApplicationContext(), "The question has been added", Toast.LENGTH_LONG).show();

            if(id == t_value.getId())
                answer = true;
            else if(id == f_value.getId())
                answer = false;

            Question q = new TFQuestion(statement, answer, gradeweight);

            Intent intent = new Intent(this, createQuiz.class);
            intent.putExtra("t/fQuestion", (Parcelable) q);
            startActivity(intent);
        }

    }

}
