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

import java.util.ArrayList;

public class dialog_adapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> list=new ArrayList<String>();
    public dialog_adapter(@NonNull Context context, ArrayList<String> list) {
        super(context, R.layout.daily_subject_row_dialog_layout,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        v=layoutInflater.inflate(R.layout.daily_subject_row_dialog_layout,parent,false);

        TextView textView=(TextView)v.findViewById(R.id.dialogRow);
        textView.setText(list.get(position));

        return v;
    }
}
