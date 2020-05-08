package com.project.group7.rollcall.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.activity.add_rollcall_date_activity;
import com.project.group7.rollcall.model.Student;

import java.util.ArrayList;

public class attendance_adapter extends ArrayAdapter<Student> {


    Context context;
    ArrayList<Student> studentsList=new ArrayList<Student>();
 boolean checkBoxValue ;


    public attendance_adapter(@NonNull Context context, ArrayList<Student> studentsList) {
        super(context, R.layout.attendace_student_detail_layout,studentsList);
        this.context=context;
        this.studentsList=studentsList;

    }




    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        v = layoutInflater.inflate(R.layout.attendace_student_detail_layout, parent, false);

        TextView roll = v.findViewById(R.id.attendanceRoll);
        TextView name = v.findViewById(R.id.attendanceName);
        final CheckBox checkBox = v.findViewById(R.id.attendanceCheck);

        roll.setText(studentsList.get(position).getRoll().toString());
        name.setText(studentsList.get(position).getName().toString());

        //final SharedPreferences settings = context.getSharedPreferences("mysettings", 0);
       // final SharedPreferences.Editor editor = settings.edit();

       /*
        boolean checkBoxValue = checkBox.isChecked();
        editor.putBoolean("v42", checkBoxValue);
        editor.commit();
        */

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (add_rollcall_date_activity.attendance.get(position).equals(false)) {
                    add_rollcall_date_activity.attendance.remove(position);
                    add_rollcall_date_activity.attendance.add(position, true);



                } else if (add_rollcall_date_activity.attendance.get(position).equals(true)) {
                    add_rollcall_date_activity.attendance.remove(position);
                    add_rollcall_date_activity.attendance.add(position, false);

                }
                /*
                Parcelable state = checkBox.onSaveInstanceState();
                checkBox.onRestoreInstanceState(state);*/
            }

        });


        if (add_rollcall_date_activity.attendance.get(position).equals(true)){
            checkBox.setChecked(true);
        }
        else checkBox.setChecked(false);

      //  checkBox.setChecked(settings.getBoolean("v42", checkBoxValue));



        return v;
    }
}
