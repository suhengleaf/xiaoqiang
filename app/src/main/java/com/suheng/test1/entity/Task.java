package com.suheng.test1.entity;

import java.util.Calendar;

public class Task {
    public int taskID;                  // 任务ID
    public String order;                // 订单号
    public int expressID;               // 快递公司ID
    public int uid;                     // 用户ID
    public Calendar starttime;          // 任务创建时间
    public Calendar deliverytime;       // 配送开始时间
    public Calendar finishtime;         // 配送完成时间
    public int carID;                   // 智能车ID
    public String status;               // 配送状态
    public String desID;                // 目的地址ID

    public Task(int taskID, String order, int expressID, int uid, Calendar starttime, Calendar deliverytime, Calendar finishtime, int carID, String status, String desID) {
        this.taskID = taskID;
        this.order = order;
        this.expressID = expressID;
        this.uid = uid;
        this.starttime = starttime;
        this.deliverytime = deliverytime;
        this.finishtime = finishtime;
        this.carID = carID;
        this.status = status;
        this.desID = desID;
    }
}
