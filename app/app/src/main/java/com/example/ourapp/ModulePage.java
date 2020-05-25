package com.example.ourapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class ModulePage extends AppCompatActivity implements  Serializable{

    TextView lesson1, lesson2, lesson3, lesson4, lesson5, lesson6;
    EditText name;
    Button edit1, edit2, edit3, edit4, edit5, edit6, create;



    Course course;
    Module module= new Module();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_page);

        lesson1 = (TextView)findViewById(R.id.lesson1TV);
        lesson2 = (TextView)findViewById(R.id.lesson2TV);
        lesson3 = (TextView)findViewById(R.id.lesson3TV);
        lesson4 = (TextView)findViewById(R.id.lesson4TV);
        lesson5 = (TextView)findViewById(R.id.lesson5TV);
        lesson6 = (TextView)findViewById(R.id.lesson6TV);

        edit1 = (Button)findViewById(R.id.edit1BT);
        edit2 = (Button)findViewById(R.id.edit2BT);
        edit3 = (Button)findViewById(R.id.edit3Bt);
        edit4 = (Button)findViewById(R.id.edit4BT);
        edit5 = (Button)findViewById(R.id.edit5BT);
        edit6 = (Button)findViewById(R.id.edit6BT);

        create = (Button)findViewById(R.id.createBT);

        name = (EditText)findViewById(R.id.nameET);

        //getting extras
        Intent p = getIntent();
        Bundle b = p.getExtras();
        if (b!=null) course = (Course) p.getSerializableExtra("course");


        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLesson(0);
            }
        });
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLesson(1);
            }
        });
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLesson(2);
            }
        });
        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLesson(3);
            }
        });
        edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLesson(4);
            }
        });
        edit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"here goes the quiz",Toast.LENGTH_SHORT).show();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // saving to the Database
                module.setName(name.getText().toString());
                toCreateACourse();
            }
        });


    }

    public void toLesson(int nmb)
    {
        Intent intent = new Intent(this,LessonPage.class);
        intent.putExtra("lessonNmb",nmb);
        intent.putExtra("module", module);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                module = (Module) data.getSerializableExtra("module");
            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"there was an error",Toast.LENGTH_LONG).show();
            }

        }

    }
    public void toCreateACourse()
    {
        Intent intent = new Intent();
        ArrayList<Module> mList = course.getmList();
        mList.add(module);
        course.setmList(mList);
        intent.putExtra("course", (Serializable) course);
        setResult(RESULT_OK,intent);
        finish();
    }

}
