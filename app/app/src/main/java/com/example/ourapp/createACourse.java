package com.example.ourapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class createACourse extends AppCompatActivity implements Serializable {

    EditText name, description;
    RadioGroup rg;
    Button add, delete, project, done;
    String email;

    Course course;

    //firebase
    FirebaseFirestore mdatabase = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_a_course);

        name = (EditText)findViewById(R.id.namePT);
        description = (EditText)findViewById(R.id.DescriptionPT2);
        rg = (RadioGroup)findViewById(R.id.PPRG);
        add = (Button)findViewById(R.id.addBT);
        delete = (Button)findViewById(R.id.deleteBT);
        project = (Button)findViewById(R.id.projectBT);
        done = (Button)findViewById(R.id.doneBT);


        mdatabase = FirebaseFirestore.getInstance();
        // saving to the database






        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b!=null){
            email= i.getStringExtra("email");
        }

        // moving to next activity
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int rbID = rg.getCheckedRadioButtonId();
                course = new Course(name.getText().toString(), description.getText().toString(), true);
                toModule();
            }
        });


        // moving to previous activity
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTeacher_myCourses();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                course = (Course) data.getSerializableExtra("course");

            }else if (requestCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"there was an error",Toast.LENGTH_LONG).show();

            }
        }

    }
    public void toModule ()
    {
        Intent intent = new Intent(this, ModulePage.class);
        intent.putExtra("course", (Serializable) course);
        startActivityForResult(intent,1);
    }



    public void toTeacher_myCourses(){
        saveToFireBase(course);
        Toast.makeText(getApplicationContext(),email+" hai",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, Teacher_myCourses.class);
//        startActivity(intent);
    }




    public void saveToFireBase (Course course){

        mdatabase = FirebaseFirestore.getInstance();
        Course c = new Course(course.getName(),course.getDescription(),course.isPublic(),course.getmList());

    mdatabase.collection("Courses").document(c.getName()).set(c).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Toast.makeText(getApplicationContext(),"saved course",Toast.LENGTH_SHORT).show();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(getApplicationContext(),"error in saving course",Toast.LENGTH_SHORT).show();

        }
    });
    }


}
