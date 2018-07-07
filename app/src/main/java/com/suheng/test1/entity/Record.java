package com.suheng.test1.entity;

import java.sql.Date;

public class Record {
    public int Record;                  // 记录ID
    public String taskID;               // 任务ID
    public Date time;                   // 时间
    public String detail;               // 详细信息
    public int UID;                     // 用户ID

    public Record(int record, String taskID, Date time, String detail, int UID) {
        Record = record;
        this.taskID = taskID;
        this.time = time;
        this.detail = detail;
        this.UID = UID;
    }
}
