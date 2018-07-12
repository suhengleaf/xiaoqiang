package com.suheng.test1.entity;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    public Task(JSONObject jsonObject) {
        this.taskID = jsonObject.getIntValue("TaskID");
        this.order = jsonObject.getString("Order");
        this.expressID = jsonObject.getIntValue("ExpressID");
        this.uid = jsonObject.getIntValue("UserID");
        this.carID = jsonObject.getIntValue("CarID");
        this.status = jsonObject.getString("Status");
        this.desID = jsonObject.getString("DesID");
        starttime = Calendar.getInstance();
        deliverytime = Calendar.getInstance();
        finishtime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM--dd HH:mm:ss", Locale.CHINA);
        try {
            starttime.setTime(sdf.parse(jsonObject.getString("StartTime")));
            deliverytime.setTime(sdf.parse(jsonObject.getString("DeliveryTime")));
            finishtime.setTime(sdf.parse(jsonObject.getString("FinishTime")));
        } catch (ParseException e) {
            starttime.set(1,1,1);
            deliverytime.set(1,1,1);
            finishtime.set(1,1,1);
        }
    }
}
