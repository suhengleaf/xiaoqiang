package com.suheng.test1.entity;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Record {
    public int Record;                  // 记录ID
    public int taskID;                  // 任务ID
    public Calendar time;                   // 时间
    public String detail;               // 详细信息
    public int UID;                     // 用户ID

    public Record(int record, int taskID, Calendar time, String detail, int UID) {
        Record = record;
        this.taskID = taskID;
        this.time = time;
        this.detail = detail;
        this.UID = UID;
    }

    public Record(JSONObject jsonObject) {
        this.Record = jsonObject.getIntValue("RecordID");
        this.taskID = jsonObject.getIntValue("TaskID");
        this.detail = jsonObject.getString("Detail");
        this.time = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM--dd HH:mm:ss", Locale.CHINA);
        try {
            time.setTime(sdf.parse(jsonObject.getString("Time")));
        } catch (ParseException e) {
            time.set(1, 1, 1);
        }
    }
}
