package com.suheng.test1.listener;

import android.app.Activity;
import android.view.View;

import com.suheng.test1.activity.MainActivity;
import com.suheng.test1.net.ServerAPI;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OnClickMailConfirmButton implements View.OnClickListener {

    private int taskID;
    private Activity activity;

    public OnClickMailConfirmButton(int taskID, Activity activity) {
        this.taskID = taskID;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(Locale.CHINA, "http://%s/confirm", ServerAPI.SERVER_IP))
                .post(new FormBody.Builder()
                        .add("taskid", Integer.toString(taskID))
                        .build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 连接失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 成功
                if (response.body().string().equals("done")) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity)activity).notifyDataChanged();
                        }
                    });
                }
            }
        });
    }
}
