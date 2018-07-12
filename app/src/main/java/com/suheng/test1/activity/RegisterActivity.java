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
import com.suheng.test1.ui.HomeFragment;

public class RegisterActivity extends AppCompatActivity {
    private ImageView registerBack;
    private EditText registername;
    private EditText registerpassword;
    private Button registerbtn;;
    private EditText registerPhone;
    public static final String TAG = "RegisterActivity";
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }
    private void initViews() {
        registerBack=(ImageView)findViewById(R.id.register_back);
        registername=(EditText)findViewById(R.id.register_name);
        registerpassword=(EditText)findViewById(R.id.register_password);
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

                    Intent intent = new Intent(RegisterActivity.this, HomeFragment.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}
