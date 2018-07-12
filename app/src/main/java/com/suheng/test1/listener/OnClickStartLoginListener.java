package com.suheng.test1.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.suheng.test1.activity.LoginActivity;


public class OnClickStartLoginListener implements View.OnClickListener {
    private Activity activity;

    public OnClickStartLoginListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.startActivityForResult(new Intent(activity, LoginActivity.class), 0);
    }
}
