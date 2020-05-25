package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class addMCQuestion extends AppCompatActivity {

    String statement, firstchoice, secondchoice, thirdchoice, fourthchoice;
    int answernumber;
    double gradeweight;
    EditText Statement, Firstchoice, Secondchoice, Thirdchoice, Fourthchoice, Answernumber, Gradeweight;
    Button add;

    DecimalFormat df = new DecimalFormat("0.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_m_c_question);

        Statement = (EditText) findViewById(R.id.statementEditText);
        Firstchoice = (EditText) findViewById(R.id.firstchoiceEditText);
        Secondchoice = (EditText) findViewById(R.id.secondchoiceEditText);
        Thirdchoice = (EditText) findViewById(R.id.thirdchoiceEditText);
        Fourthchoice = (EditText) findViewById(R.id.fourthchoiceEditText);
        Answernumber = (EditText) findViewById(R.id.correctanswerEditText);
        Gradeweight  = (EditText) findViewById(R.id.gradeptsEditText);

        add = findViewById(R.id.addButton);

        statement = Statement.getText().toString();
        firstchoice = Firstchoice.getText().toString();
        secondchoice = Secondchoice.getText().toString();
        thirdchoice = Thirdchoice.getText().toString();
        fourthchoice = Fourthchoice.getText().toString();
        answernumber = Integer.parseInt(Answernumber.getText().toString());
        gradeweight = Double.parseDouble(Gradeweight.getText().toString());

        add.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                validateQuestion(Statement, Firstchoice, Secondchoice, Thirdchoice, Fourthchoice, Answernumber, Gradeweight);
            }
        });

    }

    public void validateQuestion(EditText st, EditText fic, EditText sc, EditText tc, EditText fc, EditText ansnum, EditText gw){

        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        boolean flag5 = true;
        boolean flag6 = true;
        boolean flag7 = true;

        if(st.equals("")){
            flag1 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid statement", Toast.LENGTH_LONG).show();
        }

        if(fic.equals("")){
            flag2 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid first choice", Toast.LENGTH_LONG).show();
        }

        if(sc.equals("")){
            flag3 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid second choice", Toast.LENGTH_LONG).show();
        }

        if(tc.equals("")){
            flag4 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid third choice", Toast.LENGTH_LONG).show();
        }

        if(fc.equals("")){
            flag5 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid fourth choice", Toast.LENGTH_LONG).show();
        }

        int ans_num = Integer.parseInt(ansnum.getText().toString());
        if(ans_num==1 || ans_num==2 || ans_num==3 || ans_num==4){
            flag6 = false;
            Toast.makeText(getApplicationContext(), "Enter a number between 1 and 4 in the answer number", Toast.LENGTH_LONG).show();
        }

        double grade_weight = Double.parseDouble(gw.getText().toString());
        if(grade_weight < 0.0){
            flag7 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid grade weight that is between 0 and 100", Toast.LENGTH_LONG).show();
        }

        if(grade_weight > 100.0){
            flag7 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid grade weight that is between 0 and 100", Toast.LENGTH_LONG).show();
        }

        if(flag1 == true || flag2 == true || flag3 == true || flag4 == true || flag5 == true || flag6 == true || flag7 == true){

            Toast.makeText(getApplicationContext(), "The question has been added", Toast.LENGTH_LONG).show();

            Question q = new MCQuestion(statement, answernumber, firstchoice, secondchoice, thirdchoice, fourthchoice,  gradeweight);

            Intent intent = new Intent(this, createQuiz.class);
            intent.putExtra("mcQuestion", (Parcelable) q);
            startActivity(intent);
        }

    }

}
