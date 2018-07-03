package com.suheng.test1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class MPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fgList;

    public MPagerAdapter(FragmentManager fm, ArrayList<Fragment> fgList) {
        super(fm);
        this.fgList = fgList;
    }

    @Override
    public Fragment getItem(int position) {
        return fgList.get(position);
    }

    @Override
    public int getCount() {
        return fgList.size();
    }
}

