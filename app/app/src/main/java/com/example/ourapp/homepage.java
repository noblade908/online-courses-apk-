package com.example.ourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Console;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //activity objects
    Button myCourses;
    SearchView coursesSearch;

    String email;
    boolean isTeacher;


    //for sidebar
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    //side bar information
    TextView sidebarname;
    TextView sidebarExp;
    //for firebase
    FirebaseFirestore mdatabase;


   @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        Intent i = getIntent();
        Bundle extras =i.getExtras();
        if(extras!=null) {
            email = i.getStringExtra("email");
            isTeacher = i.getBooleanExtra("isTeacher", false);
        }
        //setting firebase database
        mdatabase = FirebaseFirestore.getInstance();


        //setting up navigation bar
        setupNavigationBar();




    }


    public void myCourses(View v ){

        if (isTeacher){
            Intent i= new Intent(getApplicationContext(),Teacher_myCourses.class);
            i.putExtra("email", email);
            i.putExtra("isTeacher",isTeacher);
            startActivity(i);
        }else {
            Intent i= new Intent(getApplicationContext(),student_myCourses.class);
            i.putExtra("email", email);
            i.putExtra("isTeacher",isTeacher);
            startActivity(i);

        }

    }

    public void SetSideBarInfo(){


        sidebarname = navigationView.getHeaderView(0).findViewById(R.id.NameOfUser);
        sidebarExp = navigationView.getHeaderView(0).findViewById(R.id.sideExperiencepoints);

        DocumentReference docu = mdatabase.collection("Student_users").document(email);

        if (isTeacher){
            DocumentReference Tdocu = mdatabase.collection("Teacher_users").document(email);

            Tdocu.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists() && documentSnapshot!=null){
                    Teacher tch = documentSnapshot.toObject(Teacher.class);
                    sidebarname.setText(tch.getUsername());
                        sidebarExp.setText("Coach");
                    }
                }
            });
        }else {
            docu.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists() && documentSnapshot != null) {
                        Student stu = documentSnapshot.toObject(Student.class);
                        sidebarname.setText(stu.getUsername());
                        sidebarExp.setText(stu.getExp()+"");

                    }

                }
            });

        }


    }


    @SuppressLint("RestrictedApi")
    void setupNavigationBar(){
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false );
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        SetSideBarInfo();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Intent i =new Intent(getApplicationContext(),profileview.class);
                i.putExtra("email", email);
                i.putExtra("isTeacher",isTeacher);
                startActivity(i);
                break;
            case R.id.leaderboard:
                Toast.makeText(getApplicationContext(),"LEADERBOARD SELECTED",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(),"LOGOUT SELECTED",Toast.LENGTH_SHORT).show();
                break;
            case R.id.homep:

                Toast.makeText(getApplicationContext(),"HOME SELECTED",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
