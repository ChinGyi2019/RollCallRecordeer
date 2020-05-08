package com.project.group7.rollcall.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.activity.add_rollcall_date_activity;
import com.project.group7.rollcall.activity.dialog_activity;
import com.project.group7.rollcall.activity.show_attendance_student_activity;
import com.project.group7.rollcall.adapter.show_all_student_adapter;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.adapter.rollcall_date_adapter;
import com.project.group7.rollcall.model.Daily;
import com.project.group7.rollcall.model.Date;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class show_date_fragment extends Fragment {

    public static final int REQUEST_CODE = 1;
    ArrayList<Date> arrayList;
    dbHelp dbHelpHelper;
    ListView listView;
    rollcall_date_adapter rda;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.show_date_layout, container, false);

        arrayList=new ArrayList<Date>();
        dbHelpHelper =new dbHelp(getContext(),null,null,1);
        arrayList= dbHelpHelper.getDateData();
        listView=(ListView)view.findViewById(R.id.dateList);
        rda=new rollcall_date_adapter(view.getContext(),arrayList);
        listView.setAdapter(rda);


        FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getView().getContext(),add_rollcall_date_activity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (show_attendance_student_activity.refresh){
                    arrayList.clear();
                    arrayList= dbHelpHelper.getDateData();
                    rda=new rollcall_date_adapter(view.getContext(),arrayList);
                    listView.setAdapter(rda);
                    show_attendance_student_activity.refresh=false;
                }
            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                @Nullable
                Date date= new Date();
                date=dbHelpHelper.getOneDateData(arrayList.get(i).getId());
                dialog_activity da=new dialog_activity(getActivity(),getContext(),date);
               da.show();

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {

                arrayList.clear();
                arrayList=dbHelpHelper.getDateData();
                rda=new rollcall_date_adapter(getContext(),arrayList);
                rda.notifyDataSetChanged();
                listView.setAdapter(rda);

            }
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        dbHelpHelper =new dbHelp(getContext(),null,null,1);
        arrayList= dbHelpHelper.getDateData();
        rda=new rollcall_date_adapter(getContext(),arrayList);
        listView.setAdapter(rda);


    }
}
