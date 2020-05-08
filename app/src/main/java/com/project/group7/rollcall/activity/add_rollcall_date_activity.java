package com.project.group7.rollcall.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.adapter.attendance_adapter;
import com.project.group7.rollcall.adapter.attendentce_adapter1;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.model.Attendance;
import com.project.group7.rollcall.model.Daily;
import com.project.group7.rollcall.model.Date;
import com.project.group7.rollcall.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class add_rollcall_date_activity  extends AppCompatActivity {
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText inputdate;
    Spinner inputtime,inputSubject;
    dbHelp dbHelpHelper;
    ListView listView;
    attendance_adapter aa;
    attendentce_adapter1 aa1;
    ArrayList<Student> studentsList;
    String selectedTime,selectedSubject;
    ArrayList<String> spinnerList;
    ArrayList<Attendance> attendenceList;
    public static ArrayList<Boolean> attendance;
    Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rollcall_date_layout);


        mToolBar = (Toolbar) findViewById(R.id.add_rollcall_app_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Record Attendence");


        attendance=new ArrayList<Boolean>();
        studentsList=new ArrayList<Student>();
        listView=(ListView)findViewById(R.id.attendanceList);
        inputdate=(EditText)findViewById(R.id.inputDate);
        inputtime=(Spinner)findViewById(R.id.inputTime);
        inputSubject=(Spinner)findViewById(R.id.inputSubject);
        Button submit=(Button)findViewById(R.id.dateSubmitBtn);

        myCalendar = Calendar.getInstance();
        dbHelpHelper =new dbHelp(add_rollcall_date_activity.this,null,null,1);
        spinnerList=new ArrayList<String >();
        spinnerList.add("Subject");

        date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            }

        };

        inputdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_rollcall_date_activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        inputtime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTime=new String();
                selectedTime = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        inputSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSubject=new String();
                selectedSubject=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        studentsList=new ArrayList<Student>();
        studentsList.clear();
        attendance.clear();
        studentsList=dbHelpHelper.getStudentData();
        int i=0;
        attendenceList = new ArrayList<Attendance>(studentsList.size());
        for (i=0;i<studentsList.size();i++){
            attendance.add(i,false);

        }
        aa=new attendance_adapter(getApplicationContext(),studentsList);
        listView.setAdapter(aa);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputdate.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Pls Fill Date",Toast.LENGTH_SHORT).show();
                }
                else if(selectedTime.equals("Period")){
                    Toast.makeText(getApplicationContext(),"Pls Select Period",Toast.LENGTH_SHORT).show();
                }
                else if(selectedSubject.equals("Subject")){
                    Toast.makeText(getApplicationContext(),"Pls Select Subject",Toast.LENGTH_SHORT).show();
                }
                else {
                    String studentId=new String();
                    String presentID=new String();
                    int count=0;
                    studentsList.clear();
                    studentsList=dbHelpHelper.getStudentData();
                    for (int j=0;j<attendance.size();j++){
                        if (attendance.get(j).equals(false)){
                            studentId=studentId.concat(studentsList.get(j).getId().toString()).concat(",");
                            count++;
                        }else{
                            presentID=presentID.concat(studentsList.get(j).getId().toString()).concat(",");
                        }
                    }
                    if (studentId.length()>0){
                        studentId = studentId.substring(0, studentId.length() - 1);
                    }else if (presentID.length()>0){
                        presentID=presentID.substring(0,presentID.length() - 1);
                    }

                    if (count==0){
                        studentId="Present";
                    }else if (count==studentsList.size()){
                        studentId="Absent";
                    }
                    Date updateDate=new Date();
                    updateDate=dbHelpHelper.checkDate(inputdate.getText().toString());

                    if (updateDate.getFound().equals("true")){
                        String concatString=updateDate.getSubjects().concat(",").concat(selectedSubject);
                        updateDate.setSubjects(concatString);
                        int success=dbHelpHelper.updateDateData(updateDate);
                    }
                    else {
                        Date addDate=new Date();
                        addDate.setDate(inputdate.getText().toString());
                        addDate.setSubjects(selectedSubject);
                        addDate.setFound("true");
                        dbHelpHelper.addDateData(addDate);
                    }

                    Date iDate=new Date();
                    iDate=dbHelpHelper.checkDate(inputdate.getText().toString());

                    Daily daily=new Daily(iDate.getId(), studentId,presentID, selectedSubject, selectedTime);
                    dbHelpHelper.addDailyData(daily);

                    Intent intent=getIntent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        aa=new attendance_adapter(getApplicationContext(),studentsList);
        listView.setAdapter(aa);

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        inputdate.setText(sdf.format(myCalendar.getTime()));

        Date iDate=new Date();
        ArrayList<String> checkSubject=new ArrayList<>();
        boolean found=dbHelpHelper.checkDateByDate(inputdate.getText().toString());

        spinnerList.add("HSS");
        spinnerList.add("Wireless");
        spinnerList.add("Networking");
        spinnerList.add("Project Manage");
       // spinnerList.add("Data Com");
        //spinnerList.add("C++");
        //spinnerList.add("Web");

        if (found)
        {
            iDate=dbHelpHelper.checkDate(inputdate.getText().toString());
            StringTokenizer st = new StringTokenizer(iDate.getSubjects(), ",");
            while (st.hasMoreTokens()) {
                checkSubject.add(st.nextToken());
            }
            for (int i=0;i<spinnerList.size();i++){
                if (checkSubject.contains(spinnerList.get(i))){
                    spinnerList.remove(i);
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, spinnerList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        inputSubject.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=getIntent();
                setResult(RESULT_OK,intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
