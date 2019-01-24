package com.netease.shop.Service.Impl;

import com.netease.shop.Entity.Goods;
import com.netease.shop.Entity.Shopcart;
import com.netease.shop.Entity.ShopcartKey;
import com.netease.shop.Mapper.GoodsMapper;
import com.netease.shop.Mapper.ShopcartMapper;
import com.netease.shop.Service.ShopcartService;
import com.netease.shop.To.ShopcartTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShopcartServiceImpl implements ShopcartService {

    @Resource
    private  ShopcartMapper shopcartMapper;

    @Resource
    private GoodsMapper goodsMapper;


    @Override
    public int insert(Shopcart record) {
        return shopcartMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(Shopcart record) {
        return shopcartMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectCount(Shopcart record) {
        return shopcartMapper.selectCount(record);
    }

    @Override
    public Shopcart selectByPrimaryKey(ShopcartKey key) {
        return shopcartMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<ShopcartTo> selectbyuser(Integer id) {
         List<Shopcart> list = shopcartMapper.selectbyuser(id);

         List<ShopcartTo> shopcartTos =new ArrayList<>();
         for(Shopcart s:list)
         {
             ShopcartTo to =new ShopcartTo();
             Goods good  =goodsMapper.selectByPrimaryKey(s.getGoodsId());
             Integer price =  good.getPrice();
             String goodname =good.getTitle();
             to.setId(s.getGoodsId());
             to.setGoodname(goodname);
             to.setGoodcount(s.getPurchasedamount());
             to.setPriceper(price);
             shopcartTos.add(to);
         }
         return shopcartTos;
    }

    @Override
    public int deleteByPrimaryKey(ShopcartKey key) {
        return shopcartMapper.deleteByPrimaryKey(key);
    }
}
