package com.netease.shop.Controller;


import com.netease.shop.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @RequestMapping(value = "/login")
    public String login()
    {
        return "login/login";
    }


    @RequestMapping(value = "verfify",method = RequestMethod.POST)
    public String verify(Model model, User user, HttpServletRequest request)
    {

        request.getSession().setAttribute("user", "1");
        return "redirect:/s/sellerHome";
    }

}
