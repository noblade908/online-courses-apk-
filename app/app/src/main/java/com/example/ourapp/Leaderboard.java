package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Leaderboard extends AppCompatActivity {

    RecyclerView recyclerView;

    String usernames[];
    String exppoints[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView  = findViewById(R.id.recyclerView);

//        usernames = getResources().getStringArray(R.array.Username);
//        exppoints = getResources().getStringArray(R.array.Experience_Points);

        usernames = new String[1];
        exppoints = new String[1];
        usernames[0] = "maha";
        exppoints[0] = "2000";

        LeaderboardAdaptor myadaptor = new LeaderboardAdaptor(this, usernames, exppoints);
        recyclerView.setAdapter(myadaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
