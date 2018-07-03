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

import com.suheng.test1.R;
import com.suheng.test1.adapter.MailAdapter;
import com.suheng.test1.entity.Mail;

import java.util.Vector;

public class MailFragment extends Fragment {
    // Views
    RecyclerView recyclerView;
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
        recyclerView = (RecyclerView) view.findViewById(R.id.mail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MailAdapter(mList));
    }

}
