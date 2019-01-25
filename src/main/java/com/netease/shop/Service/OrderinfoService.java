package com.netease.shop.Service;

import com.netease.shop.Entity.Orderinfo;
import com.netease.shop.To.orderTo;
import com.netease.shop.To.showTo;

import java.util.List;

public interface OrderinfoService {

    int insert(Orderinfo record);


    List<orderTo> selectbyuser(Integer userid);

    Orderinfo selectByGoodAndUser(showTo to);
}
