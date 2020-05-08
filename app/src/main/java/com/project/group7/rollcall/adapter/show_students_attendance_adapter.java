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
import com.project.group7.rollcall.model.ShowAttendance;

import java.util.ArrayList;

public class show_students_attendance_adapter extends ArrayAdapter<ShowAttendance> {

    Context context;
    ArrayList<ShowAttendance> list;
    public show_students_attendance_adapter(@NonNull Context context, ArrayList<ShowAttendance> list) {
        super(context, R.layout.show_students_attendance_row_layout,list);
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        v=layoutInflater.inflate(R.layout.show_students_attendance_row_layout,parent,false);

        TextView roll=(TextView)v.findViewById(R.id.rcRoll);
        TextView name=(TextView)v.findViewById(R.id.rcName);
        TextView total=(TextView)v.findViewById(R.id.rcCount);
        TextView percent=(TextView)v.findViewById(R.id.rcPercent);

        roll.setText(list.get(position).getRoll());
        name.setText(list.get(position).getName());
        total.setText(Integer.toString(list.get(position).getTotal()));
        percent.setText(list.get(position).getPercent());

        return v;
    }
}
