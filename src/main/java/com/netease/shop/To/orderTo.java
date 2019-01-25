package com.netease.shop.To;

import java.util.Date;

public class orderTo {

    private Integer goodid;

    private String picurl;

    private Date time;

    private String title;

    private Integer count;

    // 单价
    private Integer price;

    private Integer userid;

    private Integer zongjia;

    public orderTo() {
    }


    public Integer getZongjia() {
        return zongjia;
    }

    public void setZongjia(Integer zongjia) {
        this.zongjia = zongjia;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
