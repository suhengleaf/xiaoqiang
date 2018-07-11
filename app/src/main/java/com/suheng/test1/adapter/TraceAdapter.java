package com.suheng.test1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suheng.test1.R;
import com.suheng.test1.entity.TraceEntity;
import com.suheng.test1.utils.Convert;

import java.util.Collections;
import java.util.Vector;

public class TraceAdapter extends RecyclerView.Adapter<TraceAdapter.TraceViewHolder> {

    private Vector<TraceEntity> traceVector;
    private Context context;

    public TraceAdapter(Vector<TraceEntity> traceVector, Context context) {
        this.context = context;
        this.traceVector = traceVector;
        // sort by calendar
        Collections.sort(traceVector, new TraceEntity.TraceEntityCompare());
    }

    @Override
    public TraceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trace, parent, false);
        return new TraceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TraceViewHolder holder, int position) {
        holder.setEntity(traceVector.get(position));
        if (position == 0) {
            holder.acceptStationTv.setTextColor(context.getColor(R.color.color_c03));
            holder.dotIv.setImageResource(R.mipmap.dot_red);
        } else {
            holder.acceptStationTv.setTextColor(context.getResources().getColor(R.color.color_6));
            holder.dotIv.setImageResource(R.mipmap.dot_black);
        }
        if (position == traceVector.size() - 1) {
            //最后一条数据，隐藏时间轴的竖线和水平的分割线
            holder.timeLineView.setVisibility(View.INVISIBLE);
            holder.dividerLineView.setVisibility(View.INVISIBLE);
        } else {
            holder.timeLineView.setVisibility(View.VISIBLE);
            holder.dividerLineView.setVisibility(View.VISIBLE);
        }
    }

    public void mNotify() {
        Collections.sort(traceVector, new TraceEntity.TraceEntityCompare());
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return traceVector.size();
    }

    static class TraceViewHolder extends RecyclerView.ViewHolder {

        private TextView acceptTimeTv;      //接收时间
        private TextView acceptStationTv;   //接收地点
        private ImageView dotIv;            //当前位置
        private View dividerLineView;       //时间轴的竖线
        private View timeLineView;          //水平的分割线

        TraceViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            acceptTimeTv = (TextView) itemView.findViewById(R.id.accept_time_tv);
            acceptStationTv = (TextView) itemView.findViewById(R.id.accept_station_tv);
            dotIv = (ImageView) itemView.findViewById(R.id.dot_iv);
            dividerLineView = itemView.findViewById(R.id.divider_line_view);
            timeLineView = itemView.findViewById(R.id.time_line_view);
        }

        private void setEntity(TraceEntity entity) {
            acceptTimeTv.setText(Convert.Calendar2String(entity.acceptTime, "---"));
            acceptStationTv.setText(entity.acceptStation);
        }
    }
}
