package com.netease.shop.Service;

import com.netease.shop.Entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> selectAllGoods();

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Goods goods);

    int insert(Goods record);

    Integer selectidBytitle(String url);

}
