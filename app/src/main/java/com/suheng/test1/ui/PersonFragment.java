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

public class PersonFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //R.layout.fragment_my为该fragment的布局
        View view=inflater.inflate(R.layout.fragment_person,container,false);
        TextView loginText = (TextView) view.findViewById(R.id.loginText);
        TextView textView2 = (TextView)view.findViewById(R.id.See_delivery);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DeliveryInformationActivity.class);
                startActivity(intent);
            }
        });
        loginText.setOnClickListener(new ButtonListener());
        return view;
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginText:
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    break;
            }
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
