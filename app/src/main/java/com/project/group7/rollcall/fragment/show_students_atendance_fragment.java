package com.project.group7.rollcall.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.project.group7.rollcall.R;
import com.project.group7.rollcall.adapter.show_students_attendance_adapter;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.model.Daily;
import com.project.group7.rollcall.model.ShowAttendance;
import com.project.group7.rollcall.model.Student;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class show_students_atendance_fragment extends Fragment {


    ListView listView;
    show_students_attendance_adapter ssaa;
    ArrayList<ShowAttendance> list;
    ArrayList<Student> studentsList;
    ArrayList<Daily> dailyArrayList;
    ArrayList<String> idList;
    dbHelp dbHelper;
    ShowAttendance attendance;
    Spinner spinner;
    Button button;
    int selectedMonth,mon;
    String month;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.show_students_attendance_list_layout,container,false);

        dbHelper=new dbHelp(getContext(),null,null,1);
        list=new ArrayList<ShowAttendance>();
        listView=(ListView)view.findViewById(R.id.rollCallList);
        studentsList=new ArrayList<Student>();
        dailyArrayList=new ArrayList<Daily>();
        idList=new ArrayList<String>();
        spinner=(Spinner)view.findViewById(R.id.showRcSpin);
        button=(Button)view.findViewById(R.id.showRcBtn);

        ArrayList<String> spinnerList=new ArrayList<String>();
        spinnerList.add("January");
        spinnerList.add("February");
        spinnerList.add("March");
        spinnerList.add("April");
        spinnerList.add("May");
        spinnerList.add("June");
        spinnerList.add("July");
        spinnerList.add("August");
        spinnerList.add("September");
        spinnerList.add("October");
        spinnerList.add("November");
        spinnerList.add("December");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, spinnerList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month=new String();
                month=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (month){
                    case "January":
                        mon=1;
                        break;
                    case "February":
                        mon=2;
                        break;
                    case "March":
                        mon=3;
                        break;
                    case "April":
                        mon=4;
                        break;
                    case "May":
                        mon=5;
                        break;
                    case "June":
                        mon=6;
                        break;
                    case "July":
                        mon=7;
                        break;
                    case "August":
                        mon=8;
                        break;
                    case "September":
                        mon=9;
                        break;
                    case "October":
                        mon=10;
                        break;
                    case "November":
                        mon=11;
                        break;
                    case "December":
                        mon=12;
                        break;

                }
                list.clear();
                studentsList.clear();
                studentsList=dbHelper.getStudentData();
                for (int i=0;i<studentsList.size();i++){
                    attendance=new ShowAttendance();
                    attendance.setId(studentsList.get(i).getId());
                    attendance.setRoll(studentsList.get(i).getRoll());
                    attendance.setName(studentsList.get(i).getName());
                    attendance.setTotal(0);
                    attendance.setPercent("%");
                    list.add(attendance);
                }
                dailyArrayList.clear();
                dailyArrayList=dbHelper.getAllDailyData(mon);
                int count=0;
                for (int j=0;j<dailyArrayList.size();j++){

                    idList.clear();
                    StringTokenizer st = new StringTokenizer(dailyArrayList.get(j).getPresents(), ",");
                    while (st.hasMoreTokens()) {
                        idList.add(st.nextToken());
                    }
                    for (int a=0;a<list.size();a++){
                        if (idList.contains(list.get(a).getId())){
                            attendance=new ShowAttendance();
                            attendance.setId(list.get(a).getId());
                            attendance.setRoll(list.get(a).getRoll());
                            attendance.setName(list.get(a).getName());
                            attendance.setTotal(list.get(a).getTotal()+Integer.parseInt(dailyArrayList.get(j).getCount()));
                            attendance.setPercent("%");
                            list.remove(a);
                            list.add(a,attendance);
                        }
                    }
                    count+=Integer.parseInt(dailyArrayList.get(j).getCount());
                }
                for (int z=0;z<list.size();z++){

                    Double d=new Double(list.get(z).getTotal());
                    Double dou=(d/count)*100;

                    attendance=new ShowAttendance();
                    attendance.setId(list.get(z).getId());
                    attendance.setRoll(list.get(z).getRoll());
                    attendance.setName(list.get(z).getName());
                    attendance.setTotal(list.get(z).getTotal());
                    if (String.valueOf(dou).substring(0,2).equals(null)){
                        attendance.setPercent("0");
                    }
                    else {
                        attendance.setPercent(String.valueOf(dou).substring(0,3));
                    }
                    list.remove(z);
                    list.add(z,attendance);
                }
                ssaa=new show_students_attendance_adapter(getContext(),list);
                listView.setAdapter(ssaa);

            }
        });

        return view;
    }
}
