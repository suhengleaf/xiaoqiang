package com.suheng.test1.entity;

public class Address {
    public int addressID;                   // 地址ID
    public String area;                     // 区域
    public String description;              // 详细地址
    public int UID;                         // 用户ID

    public Address(int addressID, String area, String description, int UID) {
        this.addressID = addressID;
        this.area = area;
        this.description = description;
        this.UID = UID;
    }
}
