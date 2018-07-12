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

import com.suheng.test1.R;

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
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login_back:
                    finish();
                    break;
                case R.id.login_register:
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    finish();
                    break;
                case R.id.login_btn:
                    if (!TextUtils.isEmpty(loginUname.getText().toString()) && !TextUtils.isEmpty(loginPass.getText().toString())) {

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    break;
            }
        }
    }
}
