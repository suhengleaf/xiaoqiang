package com.suheng.test1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suheng.test1.R;
import com.suheng.test1.entity.MailEntity;
import com.suheng.test1.utils.Convert;

import java.util.Locale;
import java.util.Vector;

public class MailHomeAdapter extends RecyclerView.Adapter<MailHomeAdapter.MailHomeItemViewHolder> {

    private Vector<MailEntity> mList;

    public MailHomeAdapter(Vector<MailEntity> mList) {
        this.mList = mList;
    }

    @Override
    public MailHomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mail_view, parent, false);
        return new MailHomeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MailHomeItemViewHolder holder, int position) {
        holder.setEntity(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MailHomeItemViewHolder extends RecyclerView.ViewHolder {
        TextView expressNameView;
        TextView orderIDView;
        TextView deliveryTimeView;
        TextView carIDView;
        TextView finishTimeView;
        TextView addressView;

        MailHomeItemViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            this.expressNameView = (TextView) itemView.findViewById(R.id.fragment_home_express_name);
            this.orderIDView = (TextView) itemView.findViewById(R.id.fragment_home_order_id);
            this.deliveryTimeView = (TextView) itemView.findViewById(R.id.fragment_home_delivery_time);
            this.carIDView = (TextView) itemView.findViewById(R.id.fragment_home_car_id);
            this.finishTimeView = (TextView) itemView.findViewById(R.id.fragment_home_finish_time);
            this.addressView = (TextView) itemView.findViewById(R.id.fragment_home_address);
        }

        // 修改数据信息
        private void setEntity(MailEntity entity) {
            expressNameView.setText(entity.expressName);
            orderIDView.setText(String.format(Locale.CHINA, "订单编号：%d", entity.taskID));
            deliveryTimeView.setText(String.format(Locale.CHINA, "派送时间：%s", Convert.Calendar2String(entity.deliveryTime, "---")));
            carIDView.setText(String.format(Locale.CHINA, "派送车辆：%d", entity.carID));
            finishTimeView.setText(String.format(Locale.CHINA, "送达时间：%s", Convert.Calendar2String(entity.finishTime, "---")));
            addressView.setText(String.format(Locale.CHINA, "派送地址：%s%s", entity.area, entity.description));
        }



    }

    public Vector<MailEntity> getmList() {
        return mList;
    }

    public void setmList(Vector<MailEntity> mList) {
        this.mList = mList;
    }
}
