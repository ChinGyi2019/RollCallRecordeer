package com.project.group7.rollcall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.model.Date;

import java.util.ArrayList;


public class rollcall_date_adapter extends ArrayAdapter<Date> {

    Context context;
    ArrayList<Date> date;
    public rollcall_date_adapter(@NonNull Context context,@NonNull ArrayList<Date> date) {
        super(context, R.layout.date_deatil_layout,date);
        this.context=context;
        this.date=date;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        v=layoutInflater.inflate(R.layout.date_deatil_layout,parent,false);

        TextView number=(TextView) v.findViewById(R.id.dateNum);
        TextView textView=(TextView)v.findViewById(R.id.dateTxt);
        number.setText(Integer.toString(position+1));
        textView.setText(date.get(position).getDate().toString());

        return v;
    }
}
