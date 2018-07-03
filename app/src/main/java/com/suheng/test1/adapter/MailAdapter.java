package com.suheng.test1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suheng.test1.R;
import com.suheng.test1.entity.Mail;

import java.util.Vector;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailItemViewHolder> {

    private Vector<Mail> mList;

    public MailAdapter(Vector<Mail> mList) {
        this.mList = mList;
    }

    @Override
    public MailItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_view_ui, parent, false);
        return new MailItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MailItemViewHolder holder, int position) {
        holder.setEntity(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MailItemViewHolder extends RecyclerView.ViewHolder {

        MailItemViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        // 初始化视图变量
        void initView() {

        }

        // 修改实体数据
        void setEntity(Mail mail) {

        }
    }
}
