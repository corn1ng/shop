package com.netease.shop.Controller.buyer;


import com.netease.shop.Entity.Goods;
import com.netease.shop.Entity.Shopcart;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.Service.ShopcartService;
import com.netease.shop.To.ShopcartTo;
import com.netease.shop.To.cartTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BuyerController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private ShopcartService shopcartService;


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
        System.out.println(cart.getGoodid()+"   "+cart.getNumber());

        return "add success";
    }


    @RequestMapping(value = "carthome")
    public String carthome(Model model)
    {
        //TODO
        Integer userid = 1;
        List<ShopcartTo> shopcarts =shopcartService.selectbyuser(userid);
        model.addAttribute("cart",shopcarts);
        return "buyer/carthome";
    }
}
