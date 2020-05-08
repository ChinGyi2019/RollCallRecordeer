package com.project.group7.rollcall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.model.Student;

import java.util.ArrayList;

public class show_all_student_adapter extends ArrayAdapter<Student> {

    ArrayList<Student> studentsList;
    Context context;
    public show_all_student_adapter(@NonNull Context context, ArrayList<Student> studentsList) {
        super(context, R.layout.show_all_student_deatil_layout,studentsList);
        this.context=context;
        this.studentsList=studentsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        v=layoutInflater.inflate(R.layout.show_all_student_deatil_layout,parent,false);

        TextView rollno=(TextView)v.findViewById(R.id.showRoll);
        TextView name=(TextView)v.findViewById(R.id.showName);

        rollno.setText(studentsList.get(position).getRoll().toString());
        name.setText(studentsList.get(position).getName().toString());

        return v;
    }
}
