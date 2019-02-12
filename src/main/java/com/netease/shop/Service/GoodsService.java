package com.netease.shop.Service;

import com.netease.shop.Entity.Goods;
import com.netease.shop.To.goodTo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GoodsService {

    List<Goods> selectAllGoods();

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Goods goods);

    int insert(Goods record);

    Integer selectidBytitle(String url);

    List<goodTo> selectAllGoodsBuy(Integer userid);

    List<goodTo> sellerSelectAllGoodsBuy();

    int deleteByPrimaryKey(Integer id);

}
