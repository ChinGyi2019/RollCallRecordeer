package com.project.group7.rollcall.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.model.Student;

public class add_student_activity extends AppCompatActivity {

    dbHelp dbHelper;
    TextInputLayout roll,name,year,address,phone;
    Button button;
    Student stu;
    Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_layout1);

        mToolBar = (Toolbar) findViewById(R.id.adding_student_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Adding Students");


        dbHelper=new dbHelp(getApplicationContext(),null,null,1);
        roll=(TextInputLayout) findViewById(R.id.addRoll);
        name=(TextInputLayout)findViewById(R.id.addName);
        year=(TextInputLayout)findViewById(R.id.addYear);
        address=(TextInputLayout)findViewById(R.id.addAddress);
        phone=(TextInputLayout)findViewById(R.id.addPh);
        button=(Button)findViewById(R.id.addStuBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name_str = name.getEditText().getText().toString().trim();
                String roll_number = roll.getEditText().getText().toString().trim();
                String phone_number = phone.getEditText().getText().toString().trim();
                String address_str = address.getEditText().getText().toString().trim();
                String year_str = year.getEditText().getText().toString().trim();
                if (name_str.length() > 0 && roll_number.length() > 0 && year_str.length() > 0) {
                    stu=new Student(roll.getEditText().getText().toString(),name.getEditText().getText().toString(),year.getEditText().getText().toString(),
                            address.getEditText().getText().toString(),phone.getEditText().getText().toString());
                    dbHelper.addStudentData(stu);
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                    finish();

                }
                else {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(add_student_activity.this);
                    //AlertDialog.Builder alertBuilder = new AlertDialog.Builder(show_student_detail_info.this);
                    alertBuilder.setTitle("Please ! Check !");
                    alertBuilder.setMessage("Some Fields are Missing");
                    alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    alertBuilder.create().show();
                }

            }/*
                stu=new Student(roll.getEditText().getText().toString(),name.getEditText().getText().toString(),year.getEditText().getText().toString(),
                        address.getEditText().getText().toString(),phone.getEditText().getText().toString());
                dbHelper.addStudentData(stu);
                Intent intent=getIntent();
                setResult(RESULT_OK,intent);
                finish();
                */
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
