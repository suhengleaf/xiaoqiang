package com.suheng.test1.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.suheng.test1.listener.OnPageChangeMailActivity;
import com.suheng.test1.ui.HomeFragment;
import com.suheng.test1.ui.MailFragment;
import com.suheng.test1.ui.PersonFragment;
import com.suheng.test1.R;
import com.suheng.test1.adapter.MPagerAdapter;
import com.suheng.test1.listener.OnNavigationItemSelectedMainActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // Views
    BottomNavigationView bottomNavigationView;
    ViewPager mViewPager;
    // Variables
    private ArrayList<Fragment> fgLists=new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariables();
        initViews();
    }

    private void initVariables() {
        //底部导航栏有几项就有几个Fragment
        fgLists.add(new HomeFragment());
        fgLists.add(new MailFragment());
        fgLists.add(new PersonFragment());
    }

    private void initViews() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedMainActivity(mViewPager));
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setAdapter(new MPagerAdapter(getSupportFragmentManager(), fgLists));
        mViewPager.setOffscreenPageLimit(2);    //预加载剩下两页
        mViewPager.addOnPageChangeListener(new OnPageChangeMailActivity(bottomNavigationView));
    }
}

