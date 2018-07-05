package com.suheng.test1.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.suheng.test1.R;
import com.suheng.test1.adapter.MailAdapter;
import com.suheng.test1.entity.Mail;
import com.suheng.test1.listener.OnClickAllOrderMailFragment;
import com.suheng.test1.listener.OnClickNoOrderMailFragment;
import com.suheng.test1.listener.OnClickOrderMailFragment;

import java.util.Vector;

public class MailFragment extends Fragment {
    // Views
    View view;
    // Variables
    Vector<Mail> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mail,container,false);
        initVariables();
        initViews();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initVariables() {
        mList = new Vector<Mail>();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MailAdapter(mList));
        Button allOrderButton = (Button) view.findViewById(R.id.fragment_mail_all_order);
        Button noOrderButton = (Button) view.findViewById(R.id.fragment_mail_no_order);
        Button orderButton = (Button) view.findViewById(R.id.fragment_mail_order);
        allOrderButton.setOnClickListener(new OnClickAllOrderMailFragment(this));
        noOrderButton.setOnClickListener(new OnClickNoOrderMailFragment(this));
        orderButton.setOnClickListener(new OnClickOrderMailFragment(this));
    }

}
