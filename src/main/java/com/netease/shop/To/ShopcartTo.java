package com.netease.shop.To;

public class ShopcartTo {

    private Integer id;

    private String goodname;

    private Integer goodcount;

    private Integer priceper;

    public ShopcartTo() {
    }

    public ShopcartTo(String goodname, Integer goodcount, Integer priceper) {
        this.goodname = goodname;
        this.goodcount = goodcount;
        this.priceper = priceper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Integer getGoodcount() {
        return goodcount;
    }

    public void setGoodcount(Integer goodcount) {
        this.goodcount = goodcount;
    }

    public Integer getPriceper() {
        return priceper;
    }

    public void setPriceper(Integer priceper) {
        this.priceper = priceper;
    }
}
