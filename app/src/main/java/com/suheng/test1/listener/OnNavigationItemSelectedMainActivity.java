package com.suheng.test1.listener;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.suheng.test1.R;

public class OnNavigationItemSelectedMainActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;

    public OnNavigationItemSelectedMainActivity(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //根据navagatin.xml中item的id进行case
            case R.id.navigation_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.navigation_dashboard:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.navigation_notifications:
                mViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
        return true;
    }
}
