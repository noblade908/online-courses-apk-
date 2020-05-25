package com.example.ourapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileedit extends AppCompatActivity {

    private CircleImageView profile_pic;
    private String username,firstname,lastname,exppoints,bio;
    private EditText Username, Firstname,Lastname,Bio;
    private TextView Exppoints;


    private  static final int PICK = 1;

    private Button editButton;
    private Button save;

    String email;
    boolean isTeacher;
    Uri ImageUri;

    FirebaseFirestore mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit);
        configbackbtn();
        mdatabase = FirebaseFirestore.getInstance();
    }


    private void configbackbtn(){

        save = findViewById(R.id.SaveButton);


        Intent i = getIntent();
        username = i.getStringExtra("username");
        firstname = i.getStringExtra("firstname");
        lastname = i.getStringExtra("lastname");
        exppoints = i.getStringExtra("exppoints");
        bio = i.getStringExtra("bio");
        email = i.getStringExtra("email");
        isTeacher = i.getBooleanExtra("isTeacher",false);

        Username = findViewById(R.id.editUsername);
        Firstname = findViewById(R.id.editFirstname);
        Lastname = findViewById(R.id.editLastname);
        Exppoints = findViewById(R.id.ExpPtsView);
        Bio = findViewById(R.id.editBio);

        profile_pic = (CircleImageView)findViewById(R.id.pic);
        profile_pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent gallery = new Intent();
                gallery.setType("Image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "select picture"), PICK);
            }

        });

        editButton = (Button)findViewById(R.id.editBT);

        Username.setText(username);
        Firstname.setText(firstname);
        Lastname.setText(lastname);
        Exppoints.setText(exppoints);
        Bio.setText(bio);






        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent resultant = new Intent();
                resultant.putExtra("email",email);
                resultant.putExtra("isTeacher",isTeacher);
                resultant.putExtra("user",Username.getText().toString());
                resultant.putExtra("first",Firstname.getText().toString());
                resultant.putExtra("last",Lastname.getText().toString());
                resultant.putExtra("exp",Exppoints.getText().toString());
                resultant.putExtra("bi",Bio.getText().toString());




                setResult(RESULT_OK,resultant);
                finish();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK && resultCode == RESULT_OK){
            ImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUri);
                profile_pic.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
