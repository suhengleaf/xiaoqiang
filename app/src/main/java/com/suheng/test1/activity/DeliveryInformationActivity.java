package com.suheng.test1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Vector;

import com.suheng.test1.R;
import com.suheng.test1.adapter.TraceAdapter;
import com.suheng.test1.entity.TraceEntity;
import com.suheng.test1.utils.CalendarBuilder;


public class DeliveryInformationActivity extends AppCompatActivity {
    // Variables
    private Vector<TraceEntity> mTraceList; //物流追踪列表的数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_information);
        initVariables();
        loadData();
        initViews();
    }

    private void initVariables() {
        mTraceList = new Vector<TraceEntity>();
    }

    //加载物流信息的数据，这里是模拟一些假数据
    private void loadData() {
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 18, 11, 1, 1), "在湖北武汉洪山区光谷公司长江社区便民服务站进行签收扫描，快件已被 已签收 签收"));
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 18, 11, 57, 25), "在湖北武汉洪山区光谷公司长江社区便民服务站进行派件扫描；派送业务员：老王；联系电话：17786550311"));
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 17, 4, 43, 29), "在湖北武汉洪山区光谷公司进行快件扫描，将发往：湖北武汉洪山区光谷公司长江社区便民服务站"));
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 17, 8, 11, 21), "从湖北武汉分拨中心发出，本次转运目的地：湖北武汉洪山区光谷公司"));
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 17, 1, 53, 14), "在湖南长沙分拨中心进行装车扫描，即将发往：湖北武汉分拨中心"));
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 17, 1, 50, 18), "在分拨中心湖南长沙分拨中心进行称重扫描"));
        mTraceList.add(new TraceEntity(CalendarBuilder.get(2017, 6, 16, 11, 27, 58), "在湖南隆回县公司进行到件扫描"));
    }

    //初始化显示物流追踪的RecyclerView
    private void initViews() {
        RecyclerView traceRv = (RecyclerView) findViewById(R.id.traceRv);
        traceRv.setLayoutManager(new LinearLayoutManager(this));
        traceRv.setAdapter(new TraceAdapter(mTraceList, this));
    }
}
