package com.netease.shop.Controller;

import com.netease.shop.Entity.Goods;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.To.goodTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class mainController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/")
    public String home(Model model)
    {
        List<Goods> goods =goodsService.selectAllGoods();
        model.addAttribute("goods",goods);
        return "notlogin/home";
    }

    @RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id")Integer id,Model model)
    {
        Goods g = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good",g);
        return "notlogin/xiangqing";
    }



}
