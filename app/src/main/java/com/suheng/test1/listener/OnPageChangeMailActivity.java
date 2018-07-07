package com.suheng.test1.listener;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;

public class OnPageChangeMailActivity implements ViewPager.OnPageChangeListener {

    private BottomNavigationView navigationView;

    public OnPageChangeMailActivity(BottomNavigationView navigationView) {
        this.navigationView = navigationView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
