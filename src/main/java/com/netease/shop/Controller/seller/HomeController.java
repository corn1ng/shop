package com.netease.shop.Controller.seller;


import com.netease.shop.Entity.Goods;
import com.netease.shop.Service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HomeController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/sellerHome")
    public String test(Model model)
    {

        List<Goods> goods =goodsService.selectAllGoods();
        model.addAttribute("goods",goods);
        return "seller/home";
    }

    @RequestMapping(value = "/show/{id}")
    public String xiangqing(@PathVariable("id")Integer id, Model model)
    {

        Goods g = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good",g);
        return "seller/xiangqing";
    }

    @RequestMapping(value = "/edit/{id}" ,method = RequestMethod.GET)
    public String edit(@PathVariable("id")Integer id, Model model)
    {

        Goods g = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good",g);
        return "seller/editgood";
    }
}
