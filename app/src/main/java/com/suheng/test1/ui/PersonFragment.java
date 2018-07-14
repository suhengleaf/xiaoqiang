package com.suheng.test1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.suheng.test1.activity.DeliveryInformationActivity;
import com.suheng.test1.R;
import com.suheng.test1.activity.LoginActivity;
import com.suheng.test1.activity.MainActivity;
import com.suheng.test1.activity.TestActivity;

public class PersonFragment extends Fragment {
    TextView loginText;
    TextView userName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //R.layout.fragment_my为该fragment的布局
        View view=inflater.inflate(R.layout.fragment_person,container,false);
        loginText = (TextView) view.findViewById(R.id.loginText);
        userName = (TextView) view.findViewById(R.id.User_name);
        TextView update = (TextView) view.findViewById(R.id.update);
        TextView textView2 = (TextView)view.findViewById(R.id.See_delivery);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DeliveryInformationActivity.class);
                startActivity(intent);
            }
        });
        loginText.setOnClickListener(new ButtonListener());
        update.setOnClickListener(new ButtonListener());
        initViews();
        return view;

    }
    public void initViews() {

        if (MainActivity.user!=null) {
            loginText.setVisibility(View.GONE);
            userName.setVisibility(View.VISIBLE);
        } else {
            loginText.setVisibility(View.VISIBLE);
            userName.setVisibility(View.GONE);

        }
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginText:
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
                case R.id.update:
                    startActivity(new Intent(getActivity(), TestActivity.class));
                    break;
            }
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
