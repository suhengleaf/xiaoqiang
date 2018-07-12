package com.suheng.test1.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.suheng.test1.R;
import com.suheng.test1.net.ServerAPI;
import com.suheng.test1.ui.HomeFragment;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private ImageView registerBack;
    private EditText registername;
    private EditText registerpassword;
    private EditText registernickname;
    private EditText registeremail;
    private Button registerbtn;;
    private EditText registerPhone;
    public static final String TAG = "RegisterActivity";
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setResult(RESULT_CANCELED);
        initViews();
    }
    private void initViews() {
        registerBack=(ImageView)findViewById(R.id.register_back);
        registername=(EditText)findViewById(R.id.register_name);
        registerpassword=(EditText)findViewById(R.id.register_password);
        registernickname = (EditText) findViewById(R.id.register_nickname);
        registeremail = (EditText) findViewById(R.id.register_email);
        registerbtn=(Button)findViewById(R.id.register_btn);

        registerBack.setOnClickListener(new ButtonListener());
        registerbtn.setOnClickListener(new ButtonListener());

    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_back:

                    finish();
                    break;

                case R.id.register_btn:
                    final String account = registername.getText().toString();
                    final String password = registerpassword.getText().toString();
                    final String nickname = registernickname.getText().toString();
                    final String email = registeremail.getText().toString();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(String.format(Locale.CHINA, "http://%s/useradd", ServerAPI.SERVER_IP))
                            .post(new FormBody.Builder()
                                    .add("account", account)
                                    .add("password", password)
                                    .add("nickname", nickname)
                                    .add("email", email)
                                    .build())
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            // 连接失败
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseString = response.body().string();
                            if (responseString.equals(String.format(Locale.CHINA, "done add $s", account))) {
                                Intent intent = new Intent();
                                intent.putExtra("account", account);
                                intent.putExtra("password", password);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        }
                    });
                    break;
            }
        }
    }
}
