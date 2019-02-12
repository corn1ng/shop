package com.netease.shop.Controller;


import com.netease.shop.Entity.User;
import com.netease.shop.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Resource
    private UserService userService;


    @RequestMapping(value = "/login")
    public String login()
    {
        return "login/login";
    }


    @RequestMapping(value = "verfify",method = RequestMethod.POST)
    public String verify(Model model, User user, HttpServletRequest request)
    {

        User user1 = userService.selectByNameAndPassword(user);
        System.out.println("======="+user1.getId());
        request.getSession().setAttribute("user",user1);
        if(user1.getType()==1)
        {
            return "redirect:/b/buyerHome";
        }
        else
        {
            return "redirect:/s/sellerHome";
        }
    }

    @RequestMapping(value = "/logout")
    public String loginout()
    {

        return "redirect:/login";
    }

}
