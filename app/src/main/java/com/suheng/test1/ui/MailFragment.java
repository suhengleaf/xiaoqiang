package com.suheng.test1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.suheng.test1.entity.Address;
import com.suheng.test1.entity.Express;
import com.suheng.test1.entity.MailEntity;
import com.suheng.test1.entity.Task;
import com.suheng.test1.listener.OnClickAllOrderMailFragment;
import com.suheng.test1.listener.OnClickNoOrderMailFragment;
import com.suheng.test1.listener.OnClickOrderMailFragment;
import com.suheng.test1.net.ServerAPI;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MailFragment extends Fragment {
    // Views
    View view;
    // Variables
    Vector<MailEntity> mList;
    private LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mail,container,false);
        initVariables();
        initViews();
        loadData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadData() {
        //downloadData();
    }

    private void initVariables() {
        mList = new Vector<MailEntity>();
    }

    private void initViews() {
        linearLayout=(LinearLayout) view.findViewById(R.id.to_login);
        Button maillogin =(Button) view.findViewById(R.id.mail_login);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MailAdapter(mList));
        Button allOrderButton = (Button) view.findViewById(R.id.fragment_mail_all_order);
        Button noOrderButton = (Button) view.findViewById(R.id.fragment_mail_no_order);
        Button orderButton = (Button) view.findViewById(R.id.fragment_mail_order);
        allOrderButton.setOnClickListener(new OnClickAllOrderMailFragment(this));
        noOrderButton.setOnClickListener(new OnClickNoOrderMailFragment(this));
        orderButton.setOnClickListener(new OnClickOrderMailFragment(this));
        if (MainActivity.user!=null)
        {
            // TODO linearLayout.setVisibility(View.GONE);


        }
        else
        {
           // recyclerView.setVisibility(View.GONE);
        }
        maillogin.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.mail_login:
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
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONArray array = JSON.parseObject(response.body().string()).getJSONArray("TaskList");
                    for (int i=0;i<array.size();i++) {
                        Request expressRequest = new Request.Builder().build(); // 快递公司查询接口 TODO
                        Response expressResponse = client.newCall(expressRequest).execute();
                        Request addressRequest = new Request.Builder()
                                .url(String.format(Locale.CHINA, "http://%s/address", ServerAPI.SERVER_IP, MainActivity.user.id)) // 查询地址接口 TODO
                                .build();
                        Response addressResponse = client.newCall(addressRequest).execute();
                        JSONObject taskJSON = array.getJSONObject(i);
                        JSONObject expressJSON = JSON.parseObject(expressResponse.body().string());
                        JSONObject addressJSON = JSON.parseObject(addressResponse.body().string());
                        mList.add(new MailEntity(new Task(taskJSON), new Express(expressJSON), new Address(addressJSON)));
                    }
                }
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((RecyclerView)view.findViewById(R.id.mail_list)).getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
