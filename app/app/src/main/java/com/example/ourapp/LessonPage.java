package com.example.ourapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.regex.Pattern;

public class LessonPage extends AppCompatActivity implements Serializable {

    TextView description, filetext, Title;
    EditText descriptiontext, titletext;
    ScrollView sv;
    Button upload, done;


    int lesson_nmb;
    Module mod;
    Lesson lesson;

    Lesson [] a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_page);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }


        description = (TextView)findViewById(R.id.DtescriptionTV);
        descriptiontext = (EditText)findViewById(R.id.descriptionET);
        sv = (ScrollView)findViewById(R.id.descriptionSV);
        filetext = (TextView)findViewById(R.id.fileTV);
        upload = (Button)findViewById(R.id.uploadBT);
        Title = (TextView)findViewById(R.id.titleTV);
        titletext = (EditText)findViewById(R.id.titleET);
        done = (Button)findViewById(R.id.DoneBT);

        Intent p = getIntent();
        Bundle b = p.getExtras();
        if (b!=null){
            lesson_nmb = p.getIntExtra("lessonNmb",0);
            mod = (Module) p.getSerializableExtra("module");

        }

        ///////////

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(LessonPage.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // creating a Lesson object to save to the database
                lesson = new Lesson(description.getText().toString(), titletext.getText().toString());
                Toast.makeText(getApplicationContext(),"we have it",Toast.LENGTH_SHORT).show();

                a = mod.getWeek_lessons();
                a[lesson_nmb] = lesson;
                mod.setWeek_lessons(a);

                toModule();
            }
        });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String file = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            filetext.setText(file);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }
    }


    public void toModule (){

        Intent intent = new Intent();
        intent.putExtra("module", (Serializable) mod);
        setResult(RESULT_OK,intent);
        finish();
    }

}
