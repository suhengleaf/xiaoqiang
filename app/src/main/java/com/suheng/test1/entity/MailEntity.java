package com.suheng.test1.entity;

import java.util.Calendar;

public class MailEntity {
    public String expressName;
    public String expressIcon;
    public Calendar startTime;
    public Calendar deliveryTime;
    public Calendar finishTime;
    public int taskID;
    public int carID;
    public String status;
    public String area;
    public String description;

    public MailEntity(Task task, Express express, Address address) {
        this.expressName    =   express.expressName;
        this.expressIcon    =   express.expressicon;
        this.startTime      =   task.starttime;
        this.deliveryTime   =   task.deliverytime;
        this.finishTime     =   task.finishtime;
        this.taskID         =   task.taskID;
        this.carID          =   task.carID;
        this.status         =   task.status;
        this.area           =   address.area;
        this.description    =   address.description;
    }

    public MailEntity(String expressName, String expressIcon, Calendar startTime, Calendar deliveryTime, Calendar finishTime, int taskID, int carID, String status, String area, String description) {
        this.expressName = expressName;
        this.expressIcon = expressIcon;
        this.startTime = startTime;
        this.deliveryTime = deliveryTime;
        this.finishTime = finishTime;
        this.taskID = taskID;
        this.carID = carID;
        this.status = status;
        this.area = area;
        this.description = description;
    }
}
