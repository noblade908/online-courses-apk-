package com.example.ourapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.opencensus.resource.Resource;

public class CoursesCustomListAdapter extends ArrayAdapter <Course> {

    Context mCtx;
    int resource;
    List<Course> courselist;

    public CoursesCustomListAdapter(Context mCtx, int resource, List<Course> courselist){
        super(mCtx,resource,courselist);
        this.mCtx = mCtx;
        this.resource = resource;
        this.courselist = courselist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(resource,null);
        TextView desc = (TextView) v.findViewById(R.id.CourseDesc_text);
        TextView description = (TextView) v.findViewById(R.id.CourseDescription);
        ImageView imag = (ImageView) v.findViewById(R.id.Course_pic);

        Course c = courselist.get(position);
        desc.setText(c.getName());
        description.setText(c.getDescription());
        imag.setImageDrawable(mCtx.getResources().getDrawable(c.getImage()));

        v.findViewById(R.id.ViewCoursebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


return v;


    }
}
