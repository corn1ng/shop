package com.netease.shop.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @RequestMapping(value = "/login")
    public String login()
    {
        return "login/login";
    }


//    @RequestMapping(value = "verfify")
//    public String verify(Model model)
//    {
//
//    }
//
}
