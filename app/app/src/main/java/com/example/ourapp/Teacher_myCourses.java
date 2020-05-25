package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Teacher_myCourses extends AppCompatActivity {


    List <Course> courselist;
    ListView lsv;
    String email;
    boolean isTeacher = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_my_courses);
        Intent p =getIntent();
        email = p.getStringExtra("email");
        courselist  = new ArrayList<>();
        courselist.add(new Course("java programming",R.drawable.profile_pic,"YOU WILL DO THUD DJSBH ASJFBA SJBF"));
        courselist.add(new Course("dog programming",R.drawable.profile_pic,"MAYBE YOU WILL HAVE SOME FUN"));
        courselist.add(new Course("HTML and CSS",R.drawable.profile_pic,"IT IS NOT SURE THAT YOU WILL HAVE FUN IN THIS COURSE CUZ OF A BD PREVIOUS EXPERIENCE"));

        lsv  = (ListView) findViewById(R.id.listviewCourses);
        CoursesCustomListAdapter adapter = new CoursesCustomListAdapter(this,R.layout.courses_list_item,courselist);

        lsv.setAdapter(adapter);

    }

    public void createcourse(View view) {

        Intent i= new Intent(getApplicationContext(),createACourse.class);
        i.putExtra("email", email);
        i.putExtra("isTeacher",true);
        startActivity(i);

    }
}
