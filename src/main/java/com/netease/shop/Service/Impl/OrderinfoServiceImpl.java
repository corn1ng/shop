package com.netease.shop.Service.Impl;

import com.netease.shop.Entity.Goods;
import com.netease.shop.Entity.Orderinfo;
import com.netease.shop.Mapper.GoodsMapper;
import com.netease.shop.Mapper.OrderinfoMapper;
import com.netease.shop.Service.OrderinfoService;
import com.netease.shop.To.orderTo;
import com.netease.shop.To.showTo;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class OrderinfoServiceImpl implements OrderinfoService {

    @Resource
    private OrderinfoMapper orderinfoMapper;

    @Resource
    private GoodsMapper goodsMapper;


    @Override
    public int insert(Orderinfo record) {
        return orderinfoMapper.insert(record);
    }

    @Override
    public List<orderTo> selectbyuser(Integer userid) {
        List<Orderinfo> list = orderinfoMapper.selectbyuser(userid);
        List<orderTo> re =new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            orderTo to =new orderTo();
            to.setUserid(list.get(i).getUserid());
            to.setCount(list.get(i).getPurchasedamount());
            to.setGoodid(list.get(i).getGoodsid());
            Goods goods = goodsMapper.selectByPrimaryKey(list.get(i).getGoodsid());
            to.setPicurl(goods.getPicurl());

            to.setPrice(goods.getPrice());
            to.setZongjia(goods.getPrice()*list.get(i).getPurchasedamount());
            to.setTime(list.get(i).getNowtime());
            to.setTitle(goods.getTitle());
            re.add(to);
        }
        return re;
    }

    @Override
    public Orderinfo selectByGoodAndUser(showTo to) {
        return orderinfoMapper.selectByGoodAndUser(to);
    }

    @Override
    public Integer selectSellAmount(Integer goodsid) {
        return orderinfoMapper.selectSellAmount(goodsid);
    }
}
