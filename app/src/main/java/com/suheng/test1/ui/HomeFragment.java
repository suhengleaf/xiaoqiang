package com.suheng.test1.ui;

import android.content.Intent;
import android.os.Bundle;
//import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.suheng.test1.R;
import com.suheng.test1.activity.LoginActivity;
import com.suheng.test1.activity.MainActivity;
import com.suheng.test1.adapter.MailAdapter;
import com.suheng.test1.adapter.MailHomeAdapter;
import com.suheng.test1.entity.Address;
import com.suheng.test1.entity.Express;
import com.suheng.test1.entity.MailEntity;
import com.suheng.test1.entity.Task;
import com.suheng.test1.net.ServerAPI;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment {
    // Views
    private View view;
    private LinearLayout linearLayout;
    private MailHomeAdapter mailAdapter;
    // Variables
    private Vector<MailEntity> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //R.layout.fragment_my为该fragment的布局
        view=inflater.inflate(R.layout.fragment_home,container,false);
        initVariables();
        loadData();
        initViews();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initVariables() {
        mList = new Vector<MailEntity>();
    }

    private void loadData() {
        downloadData();
    }

    private void initViews() {
        linearLayout=(LinearLayout) view.findViewById(R.id.to_login);
        Button login_bt=(Button) view.findViewById(R.id.login) ;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_home_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mailAdapter = new MailHomeAdapter(mList);
        recyclerView.setAdapter(new MailHomeAdapter(mList));
        if (MainActivity.user!=null)
        {
            linearLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
        }
        login_bt.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login:
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
            }
        }
    }
    private void downloadData() {
        mList.clear();
        if (MainActivity.user == null)
            return;
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(Locale.CHINA, "http://%s/task?userid=%d", ServerAPI.SERVER_IP, MainActivity.user.id))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 连接失败
                Log.e("net", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject object = JSON.parseObject(response.body().string());
                    JSONArray array = object.getJSONArray("TaskList");
                    for (int i=0;i<array.size();i++) {
                        JSONObject taskJSON = array.getJSONObject(i);
                        Request expressRequest = new Request.Builder()
                                .url(String.format(Locale.CHINA, "http://%s/getExpressByID?orderid=%d", ServerAPI.SERVER_IP,taskJSON.getIntValue("expressID") ))
                                .build(); // 快递公司查询接口 TODO
                        Response expressResponse = client.newCall(expressRequest).execute();
                        String expressName = expressResponse.body().string();
                        Request addressRequest = new Request.Builder()
                                .url(String.format(Locale.CHINA, "http://%s/address?userID=%d", ServerAPI.SERVER_IP, 1)) // 查询地址接口 TODO
                                .build();
                        Response addressResponse = client.newCall(addressRequest).execute();
                        JSONObject addressListObjectJSON = JSON.parseObject(addressResponse.body().string());
                        JSONArray addressListJSON = addressListObjectJSON.getJSONArray("AddressList");
                        JSONObject addressJSON = addressListJSON.getJSONObject(0);
                        mList.add(new MailEntity(new Task(taskJSON), new Express(0, expressName, ""), new Address(addressJSON)));
                    }
                }
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mailAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
