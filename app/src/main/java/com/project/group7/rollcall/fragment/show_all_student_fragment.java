package com.project.group7.rollcall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.activity.add_student_activity;
import com.project.group7.rollcall.activity.show_student_detail_info;
import com.project.group7.rollcall.adapter.show_all_student_adapter;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.model.Student;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class show_all_student_fragment extends Fragment {

    public static final int REQUEST_CODE = 1;
    show_all_student_adapter sasa;
    ArrayList<Student> studentList=new ArrayList<Student>();
    dbHelp dbHelper;
    FloatingActionButton fab;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.show_all_student,container,false);

        fab=(FloatingActionButton)view.findViewById(R.id.fabStudent);

        dbHelper=new dbHelp(getContext(),null,null,1);
        listView=(ListView)view.findViewById(R.id.showAllStudentList);
        studentList.clear();
        studentList=dbHelper.getStudentData();
        sasa=new show_all_student_adapter(view.getContext(),studentList);
        listView.setAdapter(sasa);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),add_student_activity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),show_student_detail_info.class);
                intent.putExtra("POSITION",Integer.parseInt(studentList.get(i).getId()));
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {

                studentList.clear();
                studentList=dbHelper.getStudentData();
                sasa=new show_all_student_adapter(getContext(),studentList);
                listView.setAdapter(sasa);

            }
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
