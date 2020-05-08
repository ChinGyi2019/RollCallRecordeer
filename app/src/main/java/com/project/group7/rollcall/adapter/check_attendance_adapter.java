package com.project.group7.rollcall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.activity.add_rollcall_date_activity;
import com.project.group7.rollcall.activity.show_attendance_student_activity;
import com.project.group7.rollcall.model.Attendance;
import com.project.group7.rollcall.model.Daily;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class check_attendance_adapter extends ArrayAdapter<Attendance> {

    Context context;
    ArrayList<Attendance> attendancesList=new ArrayList<Attendance>();
    public check_attendance_adapter(@NonNull Context context, ArrayList<Attendance> attendancesList) {
        super(context, R.layout.attendace_student_detail_layout,attendancesList);
        this.context=context;
        this.attendancesList=attendancesList;
        show_attendance_student_activity.attList=attendancesList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        v=layoutInflater.inflate(R.layout.attendace_student_detail_layout,parent,false);

        TextView roll=(TextView)v.findViewById(R.id.attendanceRoll);
        TextView name=(TextView)v.findViewById(R.id.attendanceName);
        CheckBox check=(CheckBox)v.findViewById(R.id.attendanceCheck);

        roll.setText(attendancesList.get(position).getRoll());
        name.setText(attendancesList.get(position).getStudent());

        if (attendancesList.get(position).getAttendance().equals("true")){
            check.setChecked(true);
        }
        else check.setChecked(false);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attendancesList.get(position).getAttendance().equals("true"))
                {
                    Attendance attendance=new Attendance();
                    attendance.setId(attendancesList.get(position).getId());
                    attendance.setRoll(attendancesList.get(position).getRoll());
                    attendance.setStudent(attendancesList.get(position).getStudent());
                    attendance.setAttendance("false");
                    show_attendance_student_activity.attList.remove(position);
                    show_attendance_student_activity.attList.add(position,attendance);
                }
                else if (attendancesList.get(position).getAttendance().equals("false")){
                    Attendance attendance=new Attendance();
                    attendance.setId(attendancesList.get(position).getId());
                    attendance.setRoll(attendancesList.get(position).getRoll());
                    attendance.setStudent(attendancesList.get(position).getStudent());
                    attendance.setAttendance("true");
                    show_attendance_student_activity.attList.remove(position);
                    show_attendance_student_activity.attList.add(position,attendance);
                }
            }
        });

        return v;
    }


    public ArrayList<Attendance> getAttendancesList(){
        return attendancesList;
    }
}
