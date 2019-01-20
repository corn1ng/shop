package com.netease.shop.Controller.seller;


import com.netease.shop.Entity.Goods;
import com.netease.shop.Service.GoodsService;
import com.netease.shop.Util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/editSubmit" ,method = RequestMethod.POST)
    public String edit(Goods goods)
    {
        System.out.println("123");
        goodsService.updateByPrimaryKey(goods);
        return "redirect:sellerHome";

    }



    // 上传照片
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request)
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
