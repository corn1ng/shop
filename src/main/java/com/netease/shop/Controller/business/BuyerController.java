package com.netease.shop.Controller.business;

import com.alibaba.fastjson.JSON;
import com.netease.shop.CommonMethod;
import com.netease.shop.Entity.*;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.Service.OrderinfoService;
import com.netease.shop.Service.ShopcartService;
import com.netease.shop.To.*;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("b")
public class BuyerController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private ShopcartService shopcartService;

    @Resource
    private OrderinfoService orderinfoService;


    @RequestMapping(value = "/buyerHome")
    public String buyerhome(Model model,HttpServletRequest request)
    {

        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Integer userid =user.getId();
        List<goodTo> goods =goodsService.selectAllGoodsBuy(userid);
        model.addAttribute("goods",goods);
        return "buyer/home";
    }

    @RequestMapping(value = "/notbuy")
    public String notbuy(Model model, HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Integer userid =user.getId();

        List<goodTo> goods =goodsService.selectAllGoodsBuy(userid);
        List<Goods> result =new ArrayList<>();
        for(goodTo to:goods)
        {
            if(to.getIsbuy()==0)
            {
                Goods g =new Goods();
                BeanUtils.copyProperties(to,g);
                result.add(g);
            }
        }

        model.addAttribute("goods",result);
        return "buyer/notbuy";
    }

    @RequestMapping(value = "/buyershow/{id}")
    public String xiangqing(@PathVariable("id")Integer id, Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Integer userid =user.getId();
        Goods g = goodsService.selectByPrimaryKey(id);
        showTo to =new showTo();
        to.setGoodid(id);
        to.setUserid(userid);
        Orderinfo orderinfo = orderinfoService.selectByGoodAndUser(to);
        if(orderinfo==null)
        {
            model.addAttribute("good",g);
            return "buyer/notbuyxiangqing";
        }
        else
        {
            orderTo o  =new orderTo();
            o.setTitle(g.getTitle());
            o.setPrice(orderinfo.getPurchasedunitprice());
            o.setPicurl(g.getPicurl());
            o.setAbstra(g.getAbstra());
            o.setContent(g.getContent());
            o.setCount(orderinfo.getPurchasedamount());
            model.addAttribute("good",o);
            return "buyer/xiangqing";
        }

    }

    @RequestMapping(value = "/addcart")
    @ResponseBody
    public String addcart(cartTo cart,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        Integer userid =user.getId();
        System.out.println("======"+ userid);
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
    public String carthome(Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Integer userid =user.getId();


        List<ShopcartTo> shopcarts =shopcartService.selectbyuser(userid);
        model.addAttribute("cart",shopcarts);
        return "buyer/carthome";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deletecartgood(@PathVariable("id")Integer id,Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Integer userid =user.getId();
        ShopcartKey key = new ShopcartKey();
        key.setGoodsId(id);
        key.setUserId(userid);
        shopcartService.deleteByPrimaryKey(key);
        return "redirect:/b/carthome";

    }

    @RequestMapping(value = "/submitorder",method = RequestMethod.POST)
    @ResponseBody
    public String submitorder(@RequestBody String data,HttpServletRequest request)
    {

        User user = CommonMethod.getssion(request);
        Integer userid =user.getId();
        System.out.println("userid===="+userid);
        List<cartTo> carts = JSON.parseArray(data, cartTo.class);
        for(int i=0;i<carts.size();i++)
        {
            Orderinfo orderinfo =new Orderinfo();

            orderinfo.setGoodsid(carts.get(i).getGoodid());
            orderinfo.setUserid(userid);
            //long time =System.currentTimeMillis();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println();// new Date()为获取当前系统时间
            orderinfo.setNowtime(df.format(new Date()));
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
    public String account(Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Integer userid =user.getId();
        List<orderTo> orders =  orderinfoService.selectbyuser(userid);
        Integer zong=0;
        for(int i=0;i<orders.size();i++)
        {
            zong =zong+orders.get(i).getZongjia();
        }
        model.addAttribute("zong",zong);
        model.addAttribute("orders",orders);
        return "buyer/orderinfo";
    }
}
