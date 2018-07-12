package com.suheng.test1.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.suheng.test1.R;
import com.suheng.test1.adapter.TraceAdapter;
import com.suheng.test1.entity.Record;
import com.suheng.test1.entity.TraceEntity;
import com.suheng.test1.net.ServerAPI;
import com.suheng.test1.utils.CalendarBuilder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeliveryInformationActivity extends AppCompatActivity {
    // Variables
    private Vector<TraceEntity> mTraceList; //物流追踪列表的数据源
    private int taskID;
    private Handler handler;
    // Views
    RecyclerView traceRv;
    TraceAdapter traceAp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_information);
        initVariables();
        initViews();
        loadData();
    }

    private void initVariables() {
        mTraceList = new Vector<TraceEntity>();
        taskID = getIntent().getIntExtra("taskID", -1);
        handler = new Handler(Looper.getMainLooper());
    }

    //加载物流信息的数据，这里是模拟一些假数据
    private void loadData() {
        Request request = new Request.Builder()
                .url(String.format(Locale.CHINA, "http://%s/record?taskid=%d", ServerAPI.SERVER_IP, taskID))
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 连接失败
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // UI update
                        traceAp.mNotify();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    JSONObject recordListObjectJSON = JSON.parseObject(responseString);
                    JSONArray array = recordListObjectJSON.getJSONArray("RecordList");
                    for (int i=0;i<array.size();i++) {
                        mTraceList.add(new TraceEntity(new Record(array.getJSONObject(i))));
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // UI update
                        traceAp.mNotify();
                    }
                });
            }
        });
    }

    //初始化显示物流追踪的RecyclerView
    private void initViews() {
        traceAp = new TraceAdapter(mTraceList, this);
        traceRv = (RecyclerView) findViewById(R.id.traceRv);
        traceRv.setLayoutManager(new LinearLayoutManager(this));
        traceRv.setAdapter(traceAp);
    }
}
