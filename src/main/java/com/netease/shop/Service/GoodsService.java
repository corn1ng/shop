package com.netease.shop.Service;

import com.netease.shop.Entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> selectAllGoods();

    Goods selectByPrimaryKey(Integer id);

}
