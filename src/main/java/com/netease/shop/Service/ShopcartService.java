package com.netease.shop.Service;

import com.netease.shop.Entity.Shopcart;
import com.netease.shop.Entity.ShopcartKey;
import com.netease.shop.To.ShopcartTo;

import java.util.List;

public interface ShopcartService  {

    int insert(Shopcart record);

    int updateByPrimaryKey(Shopcart record);

    int selectCount(Shopcart record);

    Shopcart selectByPrimaryKey(ShopcartKey key);

    List<ShopcartTo> selectbyuser(Integer id);
}
