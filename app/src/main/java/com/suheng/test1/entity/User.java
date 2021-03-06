package com.suheng.test1.entity;

import com.alibaba.fastjson.JSONObject;

public class User {
    public int id;                          // 用户ID
    public String nickname;                 // 昵称
    public String account;                  // 用户名
    public String password;                 // 密码
    public String HashPass;                 // 加密密码
    public String email;                    // 邮箱

    public User(int id, String nickname, String account, String password, String hashPass, String email) {
        this.id = id;
        this.nickname = nickname;
        this.account = account;
        this.password = password;
        this.HashPass = hashPass;
        this.email = email;
    }

    public User() {
    }

    public User(JSONObject jsonObject) {
        this.id = jsonObject.getIntValue("ID");
        this.nickname = jsonObject.getString("NickName");
        this.account = jsonObject.getString("Account");
        this.email = jsonObject.getString("Email");
    }
}
