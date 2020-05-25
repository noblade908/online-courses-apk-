package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class createProject extends AppCompatActivity {

    private String description;
    private int add_exp;
    EditText Description, Add_exp;
    Button addProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        Description = (EditText) findViewById(R.id.projectEditText);
        Add_exp = (EditText) findViewById(R.id.exp_ptsEditText);

        description = Description.getText().toString();
        add_exp = Integer.parseInt(Add_exp.getText().toString());

        addProject = findViewById(R.id.add_projectButton);

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateProject(Description, Add_exp);
            }
        });

    }

    public void validateProject(EditText des, EditText exppts){
        boolean flag1 = true;
        boolean flag2 = true;

        if(des.getText().toString().equals("")){
            flag1 = false;
            Toast.makeText(getApplicationContext(), "Enter a valid project description", Toast.LENGTH_LONG).show();
        }

        if(Integer.parseInt(exppts.getText().toString())==0){
            flag2 = false;
            Toast.makeText(getApplicationContext(), "Projects must add to experience points. Enter a number greater than zero in experience points", Toast.LENGTH_LONG).show();
        }

        if(flag1 ==true && flag2==true) {
            Project project = new Project(des.getText().toString(), Integer.parseInt(exppts.getText().toString()));
            //MOVE TO ACTIVITY
        }
    }

}
