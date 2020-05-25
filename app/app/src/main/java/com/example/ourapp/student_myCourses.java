package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class student_myCourses extends AppCompatActivity {

    TextView created, allcourses;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_my_courses);

        created = (TextView)findViewById(R.id.createdTV);
        allcourses = (TextView)findViewById(R.id.allCoursesTV);
        create = (Button)findViewById(R.id.createBT);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                toCreateACourse();
            }
        });

    }

    public void toCreateACourse()
    {
        Intent intent = new Intent(this,createACourse.class);
        startActivity(intent);
    }


}
