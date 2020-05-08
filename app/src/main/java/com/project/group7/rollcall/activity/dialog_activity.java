package com.project.group7.rollcall.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.adapter.dialog_adapter;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.model.Date;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class dialog_activity extends Dialog {

    Context context;
    ListView listView;
    Date date;
    dialog_adapter da;
    ArrayList<String> subjectList;
    Activity activity;
    public dialog_activity(@NonNull Activity activity,Context context, @Nullable Date date) {
        super(context);
        this.context=context;
        this.date=date;
        this.activity=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_subject_list_dialog_layout);

        subjectList=new ArrayList<String>();
        listView=(ListView)findViewById(R.id.subjectsList);
      @NonNull
        String date_ofSubject = null;
               date_ofSubject = date.getSubjects().toString();

          StringTokenizer st = new StringTokenizer(date_ofSubject, ",");
          while (st.hasMoreTokens()) {
              subjectList.add(st.nextToken());

          }

        da=new dialog_adapter(context,subjectList);
        listView.setAdapter(da);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dismiss();
                String concatString=date.getId().toString().concat(",").concat(subjectList.get(i));
                Intent intent=new Intent(context,show_attendance_student_activity.class);
                intent.putExtra("POSITION",concatString);
                activity.startActivity(intent);
            }
        });
    }

}
