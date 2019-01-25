package com.netease.shop.Service.Impl;

import com.netease.shop.Entity.Goods;
import com.netease.shop.Entity.Orderinfo;
import com.netease.shop.Mapper.GoodsMapper;
import com.netease.shop.Mapper.OrderinfoMapper;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.To.goodTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private OrderinfoMapper orderinfoMapper;


    @Override
    public List<Goods> selectAllGoods() {
        return goodsMapper.selectAllGoods();
    }


    @Override
    public List<goodTo> sellerSelectAllGoodsBuy() {
        List<Goods> list =goodsMapper.selectAllGoods();
        List<Integer> goodlist = orderinfoMapper.sellerSelectgoodlist();
        List<goodTo> resultlist =new ArrayList<>();
        for(int  i=0;i<list.size();i++)
        {
            goodTo to =new goodTo();
            BeanUtils.copyProperties(list.get(i),to);
            if(goodlist.contains(list.get(i).getId()))
            {
                to.setIsbuy(1);
            }
            else
            {
                to.setIsbuy(0);
            }
            resultlist.add(to);
        }
        return resultlist;
    }




    @Override
    public List<goodTo> selectAllGoodsBuy(Integer userid)
    {
        List<Goods> list =goodsMapper.selectAllGoods();
        List<Integer> goodlist = orderinfoMapper.selectgoodlistbyuser(userid);
        List<goodTo> resultlist =new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            goodTo to =new goodTo();
            BeanUtils.copyProperties(list.get(i),to);
            if(goodlist.contains(list.get(i).getId()))
            {
                to.setIsbuy(1);
            }
            else
            {
                to.setIsbuy(0);
            }
            resultlist.add(to);

        }
        return resultlist;
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
