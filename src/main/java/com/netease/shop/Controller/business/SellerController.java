package com.netease.shop.Controller.business;


import com.netease.shop.CommonMethod;
import com.netease.shop.Entity.Goods;
import com.netease.shop.Entity.User;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.To.goodTo;
import com.netease.shop.Util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/s")
public class SellerController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/sellerHome")
    public String test(Model model,HttpServletRequest request)
    {
        User user =CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        List<goodTo> goods =goodsService.sellerSelectAllGoodsBuy();

        model.addAttribute("goods",goods);
        return "seller/home";
    }

    @RequestMapping(value = "/show/{id}")
    public String xiangqing(@PathVariable("id")Integer id, Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        Goods g = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good",g);
        return "seller/xiangqing";
    }

    @RequestMapping(value = "/edit/{id}" ,method = RequestMethod.GET)
    public String edit(@PathVariable("id")Integer id, Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());

        Goods g = goodsService.selectByPrimaryKey(id);
        model.addAttribute("good",g);
        return "seller/editgood";
    }

    @RequestMapping(value = "/deletegoods",method = RequestMethod.POST)
    @ResponseBody
    public String deletegoods(@RequestBody Integer id)
    {
        goodsService.deleteByPrimaryKey(id);
        return "success";
    }

    @RequestMapping(value = "/editSubmit" ,method = RequestMethod.POST)
    public String edit(@RequestParam("webpicurl")String url,Goods goods)
    {
        if(!"".equals(url))
        {
            try {
                String name =FileUtil.downloadpic(url);
                goods.setPicurl(name);

            }catch (Exception ignored)
            {
            }
        }
        goodsService.updateByPrimaryKey(goods);
        return "redirect:sellerHome";

    }

    @RequestMapping(value = "/newgood",method = RequestMethod.GET)
    public String newgood(Model model,HttpServletRequest request)
    {
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        return "seller/newgood";
    }


    @RequestMapping(value = "/newgood",method = RequestMethod.POST)
    public String newgoodpost(@RequestParam("webpicurl")String url, Goods goods,Model model,HttpServletRequest request)
    {
        if(!"".equals(url))
        {

            try {
                String name =FileUtil.downloadpic(url);
                goods.setPicurl(name);

            }catch (Exception ignored)
            {
            }
        }
        String title =goods.getTitle();
        goodsService.insert(goods);
        Integer id = goodsService.selectidBytitle(title);
        model.addAttribute("id",id);
        User user = CommonMethod.getssion(request);
        model.addAttribute("uname",user.getUsername());
        return "seller/publishsuccess";
    }


    // 上传照片
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request)
    {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String nowtime = String.valueOf(System.currentTimeMillis());
        fileName =nowtime+fileName;
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = "/Users/corn1ng/git/shop/src/main/resources/static/image/";
        System.out.println("filePath -->" + filePath );
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("success");
        return fileName;
    }
}
