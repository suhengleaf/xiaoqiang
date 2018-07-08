package com.suheng.test1.entity;

import java.util.Calendar;
import java.util.Comparator;

public class TraceEntity {
    public Calendar acceptTime; //接收时间
    public String acceptStation; //接收站点和描述

    public TraceEntity(Record record) {
        this.acceptTime = record.time;
        this.acceptStation = record.detail;
    }

    public TraceEntity(Calendar acceptTime, String acceptStation) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
    }

    // 比较器
    static public class TraceEntityCompare implements Comparator<TraceEntity> {
        @Override
        public int compare(TraceEntity o1, TraceEntity o2) {
            return -o1.acceptTime.compareTo(o2.acceptTime);
        }
    }
}
