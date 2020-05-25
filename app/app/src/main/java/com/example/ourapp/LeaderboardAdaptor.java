package com.example.ourapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardAdaptor extends RecyclerView.Adapter<LeaderboardAdaptor.MyViewHolder> {

    String usernames[], exppoints[];
    Context context;

    public LeaderboardAdaptor(Context ct, String un[], String exppts[]){
        context = ct;
        usernames = un;
        exppoints = exppts;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.username_tv.setText(usernames[position]);
        holder.exppts_tv.setText(exppoints[position]);
    }

    @Override
    public int getItemCount() {
        return usernames.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username_tv, exppts_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username_tv = itemView.findViewById(R.id.usernameTextView);
            exppts_tv = itemView.findViewById(R.id.expptsTextView);
        }
    }
}
