package com.example.ourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;

public class createAccount extends AppCompatActivity {

    public static final String TAG = "CREATE-ACCOUNT";



    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText UserName;
    private EditText Password;
    private EditText ConfirmPassword;
    private RadioGroup rg;

    private Button CreateAnAccount;



    // first is authentication
    //firebase
    FirebaseAuth mFirebaseAuth;
    // firebase database
    FirebaseFirestore mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FirstName = (EditText) findViewById(R.id.FirstNameET);
        LastName = (EditText) findViewById(R.id.lastNameET);
        Email = (EditText) findViewById(R.id.EmailET);
        UserName = (EditText) findViewById(R.id.UserNameET);
        Password = (EditText) findViewById(R.id.PasswordET);
        ConfirmPassword = (EditText) findViewById(R.id.confirmPasswordET);
        CreateAnAccount = (Button) findViewById(R.id.createAnAccountBN);


        rg = (RadioGroup)findViewById(R.id.TrueFalseRB);


        //firebase
        mFirebaseAuth =  FirebaseAuth.getInstance();
        mdatabase = FirebaseFirestore.getInstance();


        if (mFirebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),homepage.class));
            finish();
        }

        CreateAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validation(UserName, Password, ConfirmPassword, FirstName, LastName, Email, rg);
            }
        });
    }
    private boolean validateUserName(String username) {
        if ( username.equals(""))
            return false;
        // check for valid username later
        return true;
    }

    // confirm password
    private boolean validatePassword(String password) {
        if (password.length() < 8 )
            return false;
        else
            return true;
    }

    // confirm that password is equal to confirm password is valid
    private boolean validateConfirmPassword(String confirmPassword, String password) {
        if (confirmPassword.equals(password))
            return true;
        else
            return false;
    }

    // a method that validates all scenarios mentioned previously
    public void validation(EditText usernameET, EditText passwordET, EditText confirmpasswordET, EditText firstName, EditText lastName, final EditText email, RadioGroup rg)
    {
        final String firstname = firstName.getText().toString();
        final String lastname = lastName.getText().toString();
        final String Email = email.getText().toString();
        final String username = usernameET.getText().toString();
        final String password = passwordET.getText().toString();
        String confirmpassword = confirmpasswordET.getText().toString();
        final int rbID = rg.getCheckedRadioButtonId();

        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        boolean flag5 = true;
        boolean flag6 = true;

        // validating the radio button (student/instructor) username, password and confirm passwords, first name, last name, email

        if (rbID == -1) // if user didn't choose between student and instructor throw a message for them
        {
            Toast.makeText(getApplicationContext(), "you have to click on either student box or the instructor box", Toast.LENGTH_LONG).show();
            flag1 = false;
        }
        if (!validateUserName(username))
        {
            Toast.makeText(getApplicationContext(), "you have to input a valid username", Toast.LENGTH_LONG).show();
            flag2 = false;
        }
        if (!validatePassword(password))
        {
            Toast.makeText(getApplicationContext(), "Invalid password!!!", Toast.LENGTH_LONG).show();
            flag3 = false;
        }
        if (!validateConfirmPassword(confirmpassword, password))
        {
            Toast.makeText(getApplicationContext(), "the confirm password and password are different", Toast.LENGTH_SHORT).show();
            flag4 = false;
        }
        if (firstname.equals(""))
        {
            Toast.makeText(getApplicationContext(), "invalid first name", Toast.LENGTH_SHORT).show();
            flag5 = false;
        }
        if (lastname.equals(""))
        {
            Toast.makeText(getApplicationContext(), "invalid last name", Toast.LENGTH_SHORT).show();
            flag6 = false;
        }
        // if all conditions have been meet send the user congrats and move on to the next activity
        if (flag1 == true && flag2 == true && flag3 == true && flag4 == true && flag5 == true && flag6 == true) {
            Toast.makeText(getApplicationContext(), "Congratulations, you have successfully created an account", Toast.LENGTH_LONG).show();

            //firebase
            mFirebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(createAccount.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                    if (task.isSuccessful()){
                        writenewUser(firstname,lastname,Email,username,password,rbID);
                        Intent intent = new Intent(createAccount.this, homepage.class);
                        boolean isTeacher= (rbID==R.id.TrueRB);
                        intent.putExtra("email",Email);
                        intent.putExtra("isTeacher",isTeacher);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Signup went wrong somewhere, please try again", Toast.LENGTH_LONG).show();
                    }
            }
        });


        } else {
            Toast.makeText(getApplicationContext(), "error occured!", Toast.LENGTH_LONG).show();

        }
    }
    public void writenewUser (String fname,String lname,String email,String username,String pass,int rbID) {


        Toast.makeText(getApplicationContext(), "WASSABI", Toast.LENGTH_LONG).show();

        if (rbID == R.id.FalseRB) {

            Student st = new Student(username, fname, lname, email, false, pass);
           // Map<String, Object> studentvalues = st.toMap();

            mdatabase.collection("Student_users").document(email).set(st).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"done writing user to database",Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"error writing user to database",Toast.LENGTH_SHORT).show();

                }
            });


        } else {
            Teacher tch = new Teacher(username, fname, lname, email, true, pass);
            //Map<String, Object> teachervalues = tch.toMap();

            mdatabase.collection("Teacher_users").document(email).set(tch).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"done writing user to database",Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"error writing user to database",Toast.LENGTH_SHORT).show();

                }
            });



        }
    }

    }
