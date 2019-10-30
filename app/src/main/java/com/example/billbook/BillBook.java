package com.example.billbook;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class BillBook extends LitePalSupport implements Serializable {
    private double money;
    private String type;
    private String describe;
    private String date;
    public BillBook(){}

    public BillBook(double money,String type,String describe,String date)
    {
        this.date=date;
        this.type=type;
        this.describe=describe;
        this.money=money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }
}
