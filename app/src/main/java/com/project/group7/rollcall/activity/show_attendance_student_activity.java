package com.project.group7.rollcall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.adapter.check_attendance_adapter;
import com.project.group7.rollcall.adapter.rollcall_date_adapter;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.fragment.show_date_fragment;
import com.project.group7.rollcall.model.Attendance;
import com.project.group7.rollcall.model.Daily;
import com.project.group7.rollcall.model.Date;
import com.project.group7.rollcall.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class show_attendance_student_activity extends AppCompatActivity {
    TextView inputdate,inputtime,inputSubject;

    Button submit,update,delete;
    ListView listView;
    dbHelp dbHelper;
    ArrayList<Student> studentsList;
    ArrayList<Attendance> attendancesList;
    ArrayList<String> absentStudents;
    Attendance attendance;
    check_attendance_adapter cta;
    ArrayList<String> input;
    public static ArrayList<Attendance> attList;
    public static boolean refresh=false;
    Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_attendance_students_layout);

        mToolBar = (Toolbar) findViewById(R.id.show_attendentce_students_layout);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Attendence details");


        dbHelper=new dbHelp(getApplicationContext(),null,null,1);
        listView=(ListView)findViewById(R.id.checkAttendanceList);
        inputdate=(TextView)findViewById(R.id.attendanceDate);
        inputtime=(TextView) findViewById(R.id.attendanceTime);
        inputSubject=(TextView) findViewById(R.id.attendanceSubject);
        submit=(Button)findViewById(R.id.checkAttendanceBtn);
        update=(Button)findViewById(R.id.updateBtn);
        delete=(Button)findViewById(R.id.deleteBtn);
        studentsList=new ArrayList<Student>();
        attendancesList=new ArrayList<Attendance>();
        absentStudents=new ArrayList<String>();
        input=new ArrayList<String>();
        attList=new ArrayList<Attendance>();


        final Intent intent=getIntent();
        String string=intent.getStringExtra("POSITION");

        StringTokenizer st1 = new StringTokenizer(string, ",");
        while (st1.hasMoreTokens()) {
            input.add(st1.nextToken());
        }

        Daily daily=new Daily();

        studentsList.clear();
        attendancesList.clear();
        daily=dbHelper.getDailyData(input);
        studentsList=dbHelper.getStudentData();

        if (daily.getStudents().equals("Absent")){
            for (int a=0;a<studentsList.size();a++){
                attendance=new Attendance(studentsList.get(a).getId(),studentsList.get(a).getRoll().toString(),
                        studentsList.get(a).getName().toString(),
                        "false");
                attendancesList.add(attendance);
            }
        }
        else if (daily.getStudents().equals("Present")){
            absentStudents.clear();
            StringTokenizer st = new StringTokenizer(daily.getPresents(), ",");
            while (st.hasMoreTokens()) {
                absentStudents.add(st.nextToken());
            }
            for (int k = 0; k < studentsList.size(); k++) {
                if (absentStudents.contains(studentsList.get(k).getId())) {
                    attendance=new Attendance(studentsList.get(k).getId(),studentsList.get(k).getRoll().toString(),
                            studentsList.get(k).getName().toString(),
                            "true");
                }
                else {
                    attendance=new Attendance(studentsList.get(k).getId(),studentsList.get(k).getRoll().toString(),
                            studentsList.get(k).getName().toString(),
                            "false");
                }
                attendancesList.add(attendance);
            }
        }
        else {
            absentStudents.clear();
            StringTokenizer st = new StringTokenizer(daily.getPresents(), ",");
            while (st.hasMoreTokens()) {
                absentStudents.add(st.nextToken());
            }


            for (int k = 0; k < studentsList.size(); k++) {
                if (absentStudents.contains(studentsList.get(k).getId())) {
                    attendance=new Attendance(studentsList.get(k).getId(),studentsList.get(k).getRoll().toString(),
                            studentsList.get(k).getName().toString(),
                            "true");
                }
                else {
                    attendance=new Attendance(studentsList.get(k).getId(),studentsList.get(k).getRoll().toString(),
                            studentsList.get(k).getName().toString(),
                            "false");
                }
                attendancesList.add(attendance);
            }
        }

        Date iDate=new Date();
        iDate=dbHelper.getOneDateData(daily.getDate());
        inputdate.setText(iDate.getDate().toString());
        inputtime.setText(daily.getCount().toString());
        inputSubject.setText(daily.getSubject().toString());
        cta=new check_attendance_adapter(getApplicationContext(),attendancesList);
        listView.setAdapter(cta);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Daily finalDaily = daily;
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String present=new String();
                String absent=new String();
                for (int s = 0; s < attList.size(); s++) {
                    if (attList.get(s).getAttendance().equals("true")) {
                        present=present.concat(attList.get(s).getId()).concat(",");
                    }
                    else {
                        absent=absent.concat(attList.get(s).getId()).concat(",");
                    }
                }
                Daily inputDaily=new Daily();
                inputDaily.setId(finalDaily.getId());
                inputDaily.setDate(finalDaily.getDate());
                inputDaily.setPresents(present);
                inputDaily.setStudents(absent);
                inputDaily.setSubject(finalDaily.getSubject());
                inputDaily.setCount(finalDaily.getCount());
                int r=dbHelper.updateDailyData(inputDaily);
                if (r==1){
                    finish();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteDaily(finalDaily.getId());
                dbHelper.deleteDate(finalDaily.getId());
                refresh=true;
                //Intent i = new Intent(show_attendance_student_activity.this, show_date_fragment.class);
               // setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}
