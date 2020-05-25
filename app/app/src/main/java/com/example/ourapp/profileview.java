package com.example.ourapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileview extends AppCompatActivity {

    TextView username, firstname,lastname,experiencepoints,bio ;
    private CircleImageView profile_pic;

    String user,first,last,exp,bi;

    String email;
    boolean isTeacher;
    FirebaseFirestore mdatabase;

    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileview);

        edit = (Button) findViewById(R.id.EditButton);

        username = findViewById(R.id.UsernameView);
        firstname = findViewById(R.id.FirstnameView);
        lastname = findViewById(R.id.LastNameView);
        experiencepoints = findViewById(R.id.ExpPtsView);
        bio = findViewById(R.id.BioView);
        profile_pic = (CircleImageView)findViewById(R.id.pic);


        Intent i = getIntent();
        Bundle extras =i.getExtras();
        if(extras!=null) {
            email = i.getStringExtra("email");
            isTeacher = i.getBooleanExtra("isTeacher", false);
        }
        mdatabase = FirebaseFirestore.getInstance();
        setupinfo();


    }

    public void setupinfo(){

        if (isTeacher){
            DocumentReference docu = mdatabase.collection("Teacher_users").document(email);
            docu.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()&&documentSnapshot!=null){
                        Teacher tch = documentSnapshot.toObject(Teacher.class);
                        username.setText(tch.getUsername());
                        firstname.setText(tch.getFirstname());
                        lastname.setText(tch.getLastname());
                        experiencepoints.setText("Coach");
                        bio.setText(tch.getBio());
                    }
                }
            });
        }else{
            DocumentReference docu = mdatabase.collection("Student_users").document(email);
            docu.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()&& documentSnapshot!=null){
                        Student stu = documentSnapshot.toObject(Student.class);
                        username.setText(stu.getUsername());
                        firstname.setText(stu.getFirstname());
                        lastname.setText(stu.getLastname());
                        experiencepoints.setText(stu.getExp()+" ");
                        bio.setText(stu.getBio());

                    }
                }
            });


        }

    }




    public void editprofile(View v){
        Intent i = new Intent(getApplicationContext(),profileedit.class);
        i.putExtra("email",email);
        i.putExtra("isTeacher",isTeacher);
        i.putExtra("username",username.getText().toString());
        i.putExtra("firstname",firstname.getText().toString());
        i.putExtra("lastname",lastname.getText().toString());
        i.putExtra("exppoints",experiencepoints.getText().toString());
        i.putExtra("bio",bio.getText().toString());
        startActivityForResult(i,1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                username = findViewById(R.id.UsernameView);
                firstname = findViewById(R.id.FirstnameView);
                lastname = findViewById(R.id.LastNameView);
                experiencepoints = findViewById(R.id.ExpPtsView);
                bio = findViewById(R.id.BioView);


                user = data.getStringExtra("user");
                first = data.getStringExtra("first");
                last = data.getStringExtra("last");
                exp = data.getStringExtra("exp");
                bi = data.getStringExtra("bi");

                username.setText(user);
                firstname.setText(first);
                lastname.setText(last);
                experiencepoints.setText(exp);
                bio.setText(bi);

            }
            if (resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"there was an error",Toast.LENGTH_LONG).show();
            }
        }


    }
}
