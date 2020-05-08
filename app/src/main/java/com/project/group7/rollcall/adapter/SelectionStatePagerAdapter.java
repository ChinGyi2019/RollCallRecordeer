package com.project.group7.rollcall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SelectionStatePagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    ArrayList<String> mFragmentTitileList = new ArrayList<String>();

    public SelectionStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return   mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public  void addFragment(Fragment fragment){
        mFragmentList.add(fragment);



    }
}