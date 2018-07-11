package com.suheng.test1.entity;

import com.alibaba.fastjson.JSONObject;

public class Express {
    public int expressID;               // 快递公司ID
    public String expressName;          // 快递公司名称
    public String expressicon;          // 快递公司头像

    public Express(int expressID, String expressName, String expressicon) {
        this.expressID = expressID;
        this.expressName = expressName;
        this.expressicon = expressicon;
    }

    public Express(JSONObject jsonObject) {
        this.expressID = jsonObject.getIntValue("expressID");
        this.expressName = jsonObject.getString("expressName");
        this.expressicon = jsonObject.getString("expressIcon");
    }
}
