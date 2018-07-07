package com.suheng.test1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.suheng.test1.R;
import com.suheng.test1.entity.MailEntity;
import com.suheng.test1.utils.Convert;

import java.util.Locale;
import java.util.Vector;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailItemViewHolder> {

    private Vector<MailEntity> mList;

    public MailAdapter(Vector<MailEntity> mList) {
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
        private ImageView expressIconView;
        private TextView expressNameView;
        private TextView statusView;
        private TextView taskIDView;
        private TextView deliveryTimeView;
        private TextView carIDView;
        private Button mailOKButton;

        MailItemViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        // 初始化视图变量
        void initView(View view) {
            this.expressIconView = (ImageView) view.findViewById(R.id.mail_entity_express_icon);
            this.expressNameView = (TextView) view.findViewById(R.id.mail_entity_express_name);
            this.statusView = (TextView) view.findViewById(R.id.mail_entity_status);
            this.taskIDView = (TextView) view.findViewById(R.id.mail_entity_task_id);
            this.deliveryTimeView = (TextView) view.findViewById(R.id.mail_entity_delivery_time);
            this.carIDView = (TextView) view.findViewById(R.id.mail_entity_car_id);
            this.mailOKButton = (Button) view.findViewById(R.id.mail_entity_mail_ok);
        }

        // 修改实体数据
        void setEntity(MailEntity entity) {
            expressNameView.setText(entity.expressName);
            statusView.setText(entity.status);
            taskIDView.setText(String.format(Locale.CHINA, "订单编号：%d", entity.taskID));
            deliveryTimeView.setText(String.format(Locale.CHINA, "配送时间：%s", Convert.Calendar2String(entity.deliveryTime, "未定义")));
            carIDView.setText(String.format(Locale.CHINA, "派送车辆：%d", entity.carID));
        }
    }
}
