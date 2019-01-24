package com.netease.shop.Controller.buyer;



import com.alibaba.fastjson.JSON;
import com.netease.shop.Entity.Goods;
import com.netease.shop.Entity.Orderinfo;
import com.netease.shop.Entity.Shopcart;
import com.netease.shop.Entity.ShopcartKey;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.Service.OrderinfoService;
import com.netease.shop.Service.ShopcartService;
import com.netease.shop.To.ShopcartTo;
import com.netease.shop.To.cartTo;

import com.netease.shop.To.orderTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class BuyerController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private ShopcartService shopcartService;

    @Resource
    private OrderinfoService orderinfoService;


    @RequestMapping(value = "/buyerHome")
    public String test(Model model)
    {

        List<Goods> goods =goodsService.selectAllGoods();
        model.addAttribute("goods",goods);
        return "buyer/home";
    }

    @RequestMapping(value = "/buyershow/{id}")
    public String xiangqing(@PathVariable("id")Integer id, Model model)
    {

        Goods g = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good",g);
        return "buyer/xiangqing";
    }

    @RequestMapping(value = "/addcart")
    @ResponseBody
    public String addcart(cartTo cart)
    {
        //TODO
        Integer userid = 1;

        Shopcart shopcart =new Shopcart();
        shopcart.setUserId(userid);
        shopcart.setGoodsId(cart.getGoodid());


        Integer c = shopcartService.selectCount(shopcart);
        if(c==0)
        {
            shopcart.setPurchasedamount(cart.getNumber());
            shopcartService.insert(shopcart);
        }
        else
        {

            Integer yuan  = shopcartService.selectByPrimaryKey(shopcart).getPurchasedamount();
            Integer newcount =yuan + cart.getNumber();
            shopcart.setPurchasedamount(newcount);
            shopcartService.updateByPrimaryKey(shopcart);
        }


        return "add success";
    }


    @RequestMapping(value = "/carthome")
    public String carthome(Model model)
    {
        //TODO
        Integer userid = 1;
        List<ShopcartTo> shopcarts =shopcartService.selectbyuser(userid);
        model.addAttribute("cart",shopcarts);
        return "buyer/carthome";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deletecartgood(@PathVariable("id")Integer id)
    {
        // TODO
        Integer userid =1 ;
        ShopcartKey key = new ShopcartKey();
        key.setGoodsId(id);
        key.setUserId(userid);
        shopcartService.deleteByPrimaryKey(key);
        return "redirect:/carthome";

    }

    @RequestMapping(value = "/submitorder",method = RequestMethod.POST)
    @ResponseBody
    public String submitorder(@RequestBody String data)
    {

        //TODO
        Integer userid =1;
        List<cartTo> carts = JSON.parseArray(data, cartTo.class);
        for(int i=0;i<carts.size();i++)
        {
            Orderinfo orderinfo =new Orderinfo();
            orderinfo.setUserid(userid);
            orderinfo.setGoodsid(carts.get(i).getGoodid());
            orderinfo.setUserid(1);
            long time =System.currentTimeMillis();
            orderinfo.setOrdertime(new Date(time));
            orderinfo.setPurchasedamount(carts.get(i).getNumber());
            Goods g = goodsService.selectByPrimaryKey(carts.get(i).getGoodid());
            Integer price =g.getPrice()*carts.get(i).getNumber();
            orderinfo.setPricesum(price);
            orderinfo.setPurchasedunitprice(g.getPrice());
            orderinfoService.insert(orderinfo);
            ShopcartKey key =new ShopcartKey(userid,carts.get(i).getGoodid());
            shopcartService.deleteByPrimaryKey(key);
        }
        return "success";
    }

    @RequestMapping(value = "/account",method = RequestMethod.GET)
    public String account(Model model)
    {
        // TODO
        Integer userid =1;
        List<orderTo> orders =  orderinfoService.selectbyuser(userid);
        Integer zong=0;
        for(int i=0;i<orders.size();i++)
        {
            zong =zong+orders.get(i).getPrice();
        }
        model.addAttribute("zong",zong);
        model.addAttribute("orders",orders);
        return "buyer/orderinfo";
    }
}