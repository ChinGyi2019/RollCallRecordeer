package com.project.group7.rollcall;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.project.group7.rollcall.adapter.SelectionStatePagerAdapter;
import com.project.group7.rollcall.fragment.show_all_student_fragment;
import com.project.group7.rollcall.fragment.show_date_fragment;
import com.project.group7.rollcall.fragment.show_students_atendance_fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SelectionStatePagerAdapter mSelectionStatePagerAdapter;
    ViewPager mViewPager;
    boolean doubleBackToExitPressedOnce = false;
    Fragment fragment=null;
    Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Roll Call Recorder");

    /*
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame,new show_date_fragment()).disallowAddToBackStack().commit();
        }*/


        mSelectionStatePagerAdapter = new SelectionStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_date:
                     setViewPager(0);
                        /*
                        fragment=new show_date_fragment();
                        FragmentTransaction fr=getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,new show_date_fragment()).disallowAddToBackStack();
                        fr.detach(fragment);
                        fr.attach(fragment);
                        fr.commit();
                        */
                        return true;

                    case R.id.action_student:
                        setViewPager(1);
                        /*
                        fragment=new show_all_student_fragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,fragment).disallowAddToBackStack().commit();
                        */
                        return true;


                    case R.id.action_calculate:
                        setViewPager(2);
                         /*
                        fragment=new show_students_atendance_fragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame,fragment).disallowAddToBackStack().commit();
                                */
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    private void setUpViewPager(ViewPager viewPager) {

        SelectionStatePagerAdapter adapter = new SelectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new show_date_fragment());
        adapter.addFragment(new show_all_student_fragment() );
        adapter.addFragment(new show_students_atendance_fragment());

        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int fragmentNumber){

        mViewPager.setCurrentItem(fragmentNumber);
    }

}
