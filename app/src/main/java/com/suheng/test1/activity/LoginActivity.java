package com.suheng.test1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suheng.test1.R;
import com.suheng.test1.entity.User;
import com.suheng.test1.net.ServerAPI;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageView loginBack;
    private TextView loginRegister;
    private Button loginBtn;
    private EditText loginUname;
    private  EditText loginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setResult(RESULT_CANCELED);
        initViews();

    }
    private void initViews() {
        loginBack=(ImageView) findViewById(R.id.login_back);
        loginRegister=(TextView)findViewById(R.id.login_register);
        loginBtn=(Button)findViewById(R.id.login_btn);
        loginUname=(EditText)findViewById(R.id.login_uname);
        loginPass=(EditText)findViewById(R.id.login_pass);
        loginBack.setOnClickListener(new ButtonListener());
        loginRegister.setOnClickListener(new ButtonListener());
        loginBtn.setOnClickListener(new ButtonListener());
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login_back:
                    finish();
                    break;
                case R.id.login_register:
                    startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 0);
                    break;
                case R.id.login_btn:
                    if (!TextUtils.isEmpty(loginUname.getText().toString()) && !TextUtils.isEmpty(loginPass.getText().toString())) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(String.format(Locale.CHINA, "http://%s/user", ServerAPI.SERVER_IP))
                                .post(new FormBody.Builder()
                                        .add("account", loginUname.getText().toString())
                                        .add("password", loginPass.getText().toString())
                                        .build())
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                // 连接失败
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String userString = response.body().string();
                                if (userString.length() > 0) {
                                    JSONObject userJSON = JSON.parseObject(userString);
                                    User user = new User(userJSON);
                                    Intent intent = new Intent();
                                    intent.putExtra("userJSON", JSON.toJSONString(user));
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            }
                        });
                    }
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED)
            return;
        switch (requestCode) {
            case 0:
                String account = data.getStringExtra("account");
                String password = data.getStringExtra("password");
                loginUname.setText(account);
                loginPass.setText(password);
            default:
        }
    }
}
