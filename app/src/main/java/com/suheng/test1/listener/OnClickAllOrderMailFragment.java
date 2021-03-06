package com.suheng.test1.listener;

import android.view.View;

import com.suheng.test1.R;
import com.suheng.test1.ui.MailFragment;

public class OnClickAllOrderMailFragment implements View.OnClickListener {

    private MailFragment fragment;

    public OnClickAllOrderMailFragment(MailFragment fragment) {
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
        allOrderUnderline.setVisibility(View.VISIBLE);
        noOrderUnderline.setVisibility(View.INVISIBLE);
        orderUnderline.setVisibility(View.INVISIBLE);
        fragment.setData(fragment.mList);
    }
}
