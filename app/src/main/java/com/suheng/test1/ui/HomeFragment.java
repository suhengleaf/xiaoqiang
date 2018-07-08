package com.suheng.test1.ui;

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
import com.suheng.test1.adapter.MailHomeAdapter;
import com.suheng.test1.entity.MailEntity;
import com.suheng.test1.utils.CalendarBuilder;

import java.util.Vector;


public class HomeFragment extends Fragment {
    // Views
    private View view;
    // Variables
    private Vector<MailEntity> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //R.layout.fragment_my为该fragment的布局
        view=inflater.inflate(R.layout.fragment_home,container,false);
        initVariables();
        loadData();
        initViews();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initVariables() {
        mList = new Vector<MailEntity>();
    }

    private void loadData() {
        mList.add(new MailEntity("顺丰快递", ".develop.", CalendarBuilder.get(2000, 1, 1, 1, 1, 1), CalendarBuilder.get(2000, 1,1, 1, 1, 1), CalendarBuilder.get(1, 1, 1, 1, 1, 1), 1, 1, "配送中", "1", "1"));
        mList.add(new MailEntity("顺丰快递", ".develop.", CalendarBuilder.get(2000, 1, 1, 1, 1, 1), CalendarBuilder.get(2000, 1,1, 1, 1, 1), CalendarBuilder.get(1, 1, 1, 1, 1, 1), 1, 1, "配送中", "1", "1"));
        mList.add(new MailEntity("顺丰快递", ".develop.", CalendarBuilder.get(2000, 1, 1, 1, 1, 1), CalendarBuilder.get(2000, 1,1, 1, 1, 1), CalendarBuilder.get(1, 1, 1, 1, 1, 1), 1, 1, "配送中", "1", "1"));
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_home_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MailHomeAdapter(mList));
    }
}
