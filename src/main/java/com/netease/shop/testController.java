package com.netease.shop;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {


    @RequestMapping(value = "/se")
    public String test()
    {
        return "seller/home";
    }
}
