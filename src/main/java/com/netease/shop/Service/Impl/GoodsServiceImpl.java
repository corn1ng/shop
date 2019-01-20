package com.netease.shop.Service.Impl;

import com.netease.shop.Entity.Goods;
import com.netease.shop.Mapper.GoodsMapper;
import com.netease.shop.Service.GoodsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;


    @Override
    public List<Goods> selectAllGoods() {
        return goodsMapper.selectAllGoods();
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {

        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Goods goods) {
        return goodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public Integer selectidBytitle(String url) {
        return goodsMapper.selectidBytitle(url);
    }
}
