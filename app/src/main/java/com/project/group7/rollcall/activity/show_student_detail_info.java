package com.project.group7.rollcall.activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.group7.rollcall.R;
import com.project.group7.rollcall.database.dbHelp;
import com.project.group7.rollcall.model.Student;

public class show_student_detail_info extends AppCompatActivity implements View.OnClickListener{

    EditText roll,name,year,address,ph;
    Button updateBtn,deleteBtn,okBtn;
    Intent intent;
    dbHelp dbHelper;
    Student stu=null;
    String str;
    int i;

    Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_student_info_layout);

        mToolBar = (Toolbar) findViewById(R.id.show_student_info_app_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Student detials");


        dbHelper=new dbHelp(getApplicationContext(),null,null,1);
        stu=new Student();
        roll=(EditText) findViewById(R.id.showRoll);
        name=(EditText) findViewById(R.id.showName);
        year=(EditText)findViewById(R.id.showYear);
        address=(EditText)findViewById(R.id.showAddress);
        ph=(EditText)findViewById(R.id.showPh);
        updateBtn=(Button)findViewById(R.id.showUpdate);
        deleteBtn=(Button)findViewById(R.id.showDelete);
        okBtn=(Button)findViewById(R.id.showOk);

        roll.setOnClickListener(this);
        name.setOnClickListener(this);
        year.setOnClickListener(this);
        address.setOnClickListener(this);
        ph.setOnClickListener(this);


        intent=getIntent();
        i=intent.getIntExtra("POSITION",0);


        stu=dbHelper.getStudentDetailData(Integer.toString(i));
        roll.setText(stu.getRoll().toString());
        name.setText(stu.getName().toString());
        year.setText(stu.getYear().toString());
        address.setText(stu.getAddress().toString());
        ph.setText(stu.getPh().toString());

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteStudent(stu.getId());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_str = name.getText().toString().trim();
                String roll_number = roll.getText().toString().trim();
                String phone_number = ph.getText().toString().trim();
                String address_str = address.getText().toString().trim();
                String year_str = year.getText().toString().trim();

                if (name_str.length() > 0 && roll_number.length() > 0 && year_str.length() > 0) {
                    Student stuu = new Student(roll.getText().toString(),
                            name.getText().toString(),
                            year.getText().toString(),
                            address.getText().toString(),
                            ph.getText().toString());
                    int i = dbHelper.updateStudentData(stuu, stu.getId());
                    if (i == 1) {
                        Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).toString();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(show_student_detail_info.this);
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
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showRoll:
                roll.setCursorVisible(true);
                break;

            case R.id.showName:
                name.setCursorVisible(true);
                break;

            case R.id.showYear:
                year.setCursorVisible(true);
                break;

            case R.id.showAddress:
                address.setCursorVisible(true);
                break;

            case R.id.showPh:
                ph.setCursorVisible(true);
                break;
            default:
                break;
        }
    }
}
