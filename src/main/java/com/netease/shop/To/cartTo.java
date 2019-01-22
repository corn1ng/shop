package com.netease.shop.To;

public class cartTo {


    private  Integer goodid;

    private Integer number;

    public cartTo() {
    }

    public cartTo(Integer goodid, Integer number) {
        this.goodid = goodid;
        this.number = number;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


}
