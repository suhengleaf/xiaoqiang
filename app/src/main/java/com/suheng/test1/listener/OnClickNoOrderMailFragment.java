package com.suheng.test1.listener;

import android.view.View;

import com.suheng.test1.R;
import com.suheng.test1.entity.MailEntity;
import com.suheng.test1.ui.MailFragment;

import java.util.Vector;

public class OnClickNoOrderMailFragment implements View.OnClickListener {

    private MailFragment fragment;

    public OnClickNoOrderMailFragment(MailFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {
        // 设置点击事件
        if (fragment.getView() == null)
            return;
        View allOrderUnderline = (View) fragment.getView().findViewById(R.id.underline_all_order);
        View noOrderUnderline = (View) fragment.getView().findViewById(R.id.underline_no_order);
        View orderUnderline = (View) fragment.getView().findViewById(R.id.underline_order);
        allOrderUnderline.setVisibility(View.INVISIBLE);
        noOrderUnderline.setVisibility(View.VISIBLE);
        orderUnderline.setVisibility(View.INVISIBLE);
        Vector<MailEntity> data = new Vector<MailEntity>();
        for (MailEntity entity : fragment.mList) {
            if (!entity.status.equals("已确认"))
                data.add(entity);
        }
        fragment.setData(data);
    }
}
