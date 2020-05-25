package com.example.ourapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private static final String TAG = "QuestionListAdapter";
    private Context mContext;
    private int mResource;

    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String statement = getItem(position).getStatement();
        String type;
        if(getItem(position).getClass().equals("TFQuestion"))
            type = "T/F";
        else
            type = "MCQ";

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tv_statement = (TextView) convertView.findViewById(R.id.q_stTextView);
        TextView tv_type = (TextView) convertView.findViewById(R.id.typeTextView);

        tv_statement.setText(statement);
        tv_type.setText(type);

        return convertView;
    }
}
