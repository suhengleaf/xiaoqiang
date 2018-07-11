package com.suheng.test1.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.suheng.test1.activity.DeliveryInformationActivity;

public class OnClickMailDetailButton implements View.OnClickListener {

    private Context context;
    private int taskID;

    public OnClickMailDetailButton(Context context, int taskID) {
        this.context = context;
        this.taskID = taskID;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DeliveryInformationActivity.class);
        intent.putExtra("taskID", taskID);
        context.startActivity(intent);
    }
}
