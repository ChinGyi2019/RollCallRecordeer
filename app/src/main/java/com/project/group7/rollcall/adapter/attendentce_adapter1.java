package com.project.group7.rollcall.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.activity.add_rollcall_date_activity;
import com.project.group7.rollcall.model.Attendance;
import com.project.group7.rollcall.model.Student;

import java.util.ArrayList;

public class attendentce_adapter1 extends ArrayAdapter<Student> {
    Context context;
    ArrayList<Student> studentsList=new ArrayList<Student>();
    ArrayList<String> checkedStudent = new ArrayList<>();

    public attendentce_adapter1(Context context, ArrayList<Student> studentsList){
        super(context, R.layout.attendace_student_detail_layout,studentsList);

        this.context=context;
        this.studentsList=studentsList;

    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        final boolean[] checkArray = new boolean[studentsList.size()];
//
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.attendace_student_detail_layout, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.roll = (TextView) convertView.findViewById(R.id.attendanceRoll);
            viewHolder.name = (TextView) convertView.findViewById(R.id.attendanceName);
             viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.attendanceCheck);



            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (add_rollcall_date_activity.attendance.get(position).equals(false)) {
                        add_rollcall_date_activity.attendance.remove(position);
                        add_rollcall_date_activity.attendance.add(position, true);
                        // checked.add(position,true) ;

//                        checkedStudent.remove(position);
                     //   checkedStudent.add(position,"checked");



                    } else if (add_rollcall_date_activity.attendance.get(position).equals(true)) {
                        add_rollcall_date_activity.attendance.remove(position);
                        add_rollcall_date_activity.attendance.add(position, false);
                        // checked.add(position,false) ;
                       // checkedStudent.remove(position);
                        //checkedStudent.add(position,"unchecked");
                    }
                }
            });

            convertView.setTag(viewHolder);

            convertView.setTag(R.id.attendanceRoll, viewHolder.roll);
            convertView.setTag(R.id.attendanceName, viewHolder.name);
            convertView.setTag(R.id.attendanceCheck, viewHolder.checkBox);



        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.checkBox.setTag(position);

        viewHolder.roll.setText(studentsList.get(position).getRoll().toString());
        viewHolder.name.setText(studentsList.get(position).getName().toString());
      //  viewHolder.checkBox.setChecked(false);
        if (add_rollcall_date_activity.attendance.get(position).equals(true)){
            viewHolder.checkBox.setChecked(true);
        }
        else viewHolder.checkBox.setChecked(false);
       // checkedStudent.clear();
/*
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(!isChecked){
                    Toast.makeText(context,"unchecked"+position,Toast.LENGTH_SHORT).show();
                    //checkedStudent.r("unchecked");
                    //checkedStudent.remove(getPosition);
                    //checkedStudent.set(getPosition,"unchecked");
                    checkedStudent.add(position,"unchecked");

                }else if(isChecked){
                    Toast.makeText(context,"checked"+position,Toast.LENGTH_SHORT).show();
                   checkedStudent.add(position,"checked");
                    //checkedStudent.remove(getPosition);
                   // checkedStudent.set(getPosition,"checked");


                }
            }
        });


        if(viewHolder.checkBox.isChecked()){
           checkedStudent.add(,true);

        }else if(!viewHolder.checkBox.isChecked()){
         checkedStudent.add(position,false);
        }
            for(int i = 0; i<checkedStudent.size(); i++){
               if(checkedStudent.get(i).equals("checked")){
                   viewHolder.checkBox.setChecked(true);
               }
               else if(checkedStudent.get(position).equals("unchecked")){
                   viewHolder.checkBox.setChecked(false);
               }
        }*/

        return convertView;
    }
    public static class ViewHolder {
        TextView roll;
        TextView name;
        CheckBox checkBox;


    }


}
